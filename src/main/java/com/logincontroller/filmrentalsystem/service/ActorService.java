package com.logincontroller.filmrentalsystem.service;

import com.logincontroller.filmrentalsystem.dto.ActorDTO;
import com.logincontroller.filmrentalsystem.model.Actor;
import com.logincontroller.filmrentalsystem.repository.ActorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActorService {

    private final ActorRepository actorRepository;

    private ActorDTO toDTO(Actor actor) {
        ActorDTO dto = new ActorDTO();
        dto.setActorId(actor.getActorId());
        dto.setFirstName(actor.getFirstName());
        dto.setLastName(actor.getLastName());
        dto.setLastUpdate(actor.getLastUpdate());
        if (actor.getFilms() != null) {
            dto.setFilmTitles(
                    actor.getFilms().stream()
                            .map(film -> film.getTitle())
                            .collect(Collectors.toList())
            );
        }
        return dto;
    }

    public Actor getEntityById(Short id) {
        return actorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Actor not found with id: " + id));
    }

    public List<ActorDTO> getAllActors() {
        return actorRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // returns only full names — used for lightweight actor listing page
    public List<String> getAllActorNames() {
        return actorRepository.findAll()
                .stream()
                .map(actor -> actor.getFirstName() + " " + actor.getLastName())
                .collect(Collectors.toList());
    }

    public ActorDTO getActorById(Short id) {
        return toDTO(getEntityById(id));
    }



    public List<ActorDTO> searchByFirstName(String firstName) {
        return actorRepository.findByFirstNameContainingIgnoreCase(firstName)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<ActorDTO> searchByLastName(String lastName) {
        return actorRepository.findByLastNameContainingIgnoreCase(lastName)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public ActorDTO saveActor(Actor actor) {
        return toDTO(actorRepository.save(actor));
    }

    public void deleteActor(Short id) {
        actorRepository.deleteById(id);
    }
}