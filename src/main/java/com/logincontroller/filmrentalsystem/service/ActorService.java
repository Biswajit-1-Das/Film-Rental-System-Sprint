package com.logincontroller.filmrentalsystem.service;

import com.logincontroller.filmrentalsystem.dto.ActorResponseDTO;
import com.logincontroller.filmrentalsystem.dto.FilmResponseDTO;
import com.logincontroller.filmrentalsystem.exception.ResourceNotFoundException;
import com.logincontroller.filmrentalsystem.model.Actor;
import com.logincontroller.filmrentalsystem.repository.ActorRepository;
import com.logincontroller.filmrentalsystem.repository.FilmActorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Array;
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
                .orElseThrow(() -> new ResourceNotFoundException("Actor not found with id: " + id));
    }

    @Transactional(readOnly = true)
    public List<FilmResponseDTO> getFilmsForActor(Short actorId) {
        return filmService.getFilmsForActor(actorId);
    }

    @Transactional(readOnly = true)
    public List<ActorResponseDTO> getAllActors() {
//        return actorRepository.findAll().stream().map(this::toResponseDTO).collect(Collectors.toList());
        List<Actor> actor = actorRepository.findAll();
        List<ActorResponseDTO> actors = new ArrayList<>();
        for(Actor atr:actor)
        {
            actors.add(toResponseDTO(atr));
        }
        return actors;
    }

    public List<String> getAllActorNames() {
        List<Actor> actor = actorRepository.findAll();
        List<String> names= new ArrayList<>();
        for(Actor ar : actor)
        {
            names.add(toResponseDTO(ar).getFirstName() + " " + toResponseDTO(ar).getLastName());
        }
        return names;
    }

    public ActorResponseDTO getActorById(Short id) {

        return toResponseDTO(getEntityById(id));
    }


    public List<ActorResponseDTO> searchByFirstName(String firstName) {
//        return actorRepository.findByFirstNameContainingIgnoreCase(firstName)
//                .stream()
//                .map(this::toResponseDTO)
//                .collect(Collectors.toList());
        List<Actor> actors = actorRepository.findByFirstNameContainingIgnoreCase(firstName);
        List<ActorResponseDTO> res = new ArrayList<>();
        for(Actor ar : actors)
        {
            if(firstName.equalsIgnoreCase(toResponseDTO(ar).getFirstName()))
            {
                res.add(toResponseDTO(ar));
            }
        }
        return res;
    }

    public List<ActorResponseDTO> searchByLastName(String lastName) {
//        return actorRepository.findByLastNameContainingIgnoreCase(lastName)
//                .stream()
//                .map(this::toResponseDTO)
//                .collect(Collectors.toList());
        List<Actor> actors = actorRepository.findByLastNameContainingIgnoreCase(lastName);
        List<ActorResponseDTO> res = new ArrayList<>();
        for(Actor ar : actors)
        {
            if(toResponseDTO(ar).getLastName().equalsIgnoreCase(lastName))
            {
                res.add(toResponseDTO(ar));
            }
        }
        return res;
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



    @Transactional(readOnly = true)
    public List<ActorResponseDTO> getTopTenActorsByFilmCount() {
        List<Short> ids = filmActorRepository.findTopActorIdsByFilmCount();
        List<ActorResponseDTO> out = new ArrayList<>();
        for (Short id : ids) {
            actorRepository.findById(id).ifPresent(a -> out.add(toResponseDTO(a)));
        }
        return out;
    }

    @Transactional(readOnly = true)
    public List<ActorResponseDTO> getActorsByFirstNamePrefix(String prefix) {
        return actorRepository.findByFirstNameStartingWithIgnoreCase(prefix)
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

}
