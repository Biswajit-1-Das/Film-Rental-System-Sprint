package com.logincontroller.filmrentalsystem.service;

import com.logincontroller.filmrentalsystem.dto.ActorResponseDTO;
import com.logincontroller.filmrentalsystem.dto.FilmResponseDTO;
import com.logincontroller.filmrentalsystem.model.Actor;
import com.logincontroller.filmrentalsystem.repository.ActorRepository;
import com.logincontroller.filmrentalsystem.repository.FilmActorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActorService {

    private final ActorRepository actorRepository;
    private final FilmActorRepository filmActorRepository;
    private final FilmService filmService;

    private ActorResponseDTO toResponseDTO(Actor actor) {
        ActorResponseDTO dto = new ActorResponseDTO();
        dto.setActorId(actor.getActorId());
        dto.setFirstName(actor.getFirstName());
        dto.setLastName(actor.getLastName());
        dto.setLastUpdate(actor.getLastUpdate());
        if (actor.getFilmActors() != null) {
            dto.setFilmTitles(
                    actor.getFilmActors().stream()
                            .map(fa -> fa.getFilm().getTitle())
                            .collect(Collectors.toList())
            );
        }
        return dto;
    }

    public Actor getEntityById(Short id) {
        return actorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Actor not found with id: " + id));
    }

    @Transactional(readOnly = true)
    public List<FilmResponseDTO> getFilmsForActor(Short actorId) {
        return filmService.getFilmsForActor(actorId);
    }

    @Transactional(readOnly = true)
    public List<ActorResponseDTO> getAllActors() {
        return actorRepository.findAll().stream().map(this::toResponseDTO).collect(Collectors.toList());
    }

    public List<String> getAllActorNames() {
        return actorRepository.findAll()
                .stream()
                .map(actor -> actor.getFirstName() + " " + actor.getLastName())
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ActorResponseDTO getActorById(Short id) {
        return toResponseDTO(getEntityById(id));
    }

    @Transactional(readOnly = true)
    public List<ActorResponseDTO> searchByFirstName(String firstName) {
        return actorRepository.findByFirstNameContainingIgnoreCase(firstName)
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ActorResponseDTO> searchByLastName(String lastName) {
        return actorRepository.findByLastNameContainingIgnoreCase(lastName)
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public ActorResponseDTO saveActor(Actor actor) {
        return toResponseDTO(actorRepository.save(actor));
    }

    public void deleteActor(Short id) {
        actorRepository.deleteById(id);
    }

    @Transactional
    public ActorResponseDTO updateLastName(Short id, String lastName) {
        Actor existing = getEntityById(id);
        existing.setLastName(lastName);
        return toResponseDTO(actorRepository.save(existing));
    }

    @Transactional
    public ActorResponseDTO updateFirstName(Short id, String firstName) {
        Actor existing = getEntityById(id);
        existing.setFirstName(firstName);
        return toResponseDTO(actorRepository.save(existing));
    }

    @Transactional(readOnly = true)
    public List<ActorResponseDTO> getTopTenActorsByFilmCount() {
        List<Short> ids = filmActorRepository.findTopActorIdsByFilmCount();
        List<ActorResponseDTO> out = new ArrayList<>();
        for (Short id : ids) {
            actorRepository.findById(id).ifPresent(a -> out.add(toResponseDTO(a)));
        }
        return out;
    }
}
