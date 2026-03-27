package com.logincontroller.filmrentalsystem.service;

import com.logincontroller.filmrentalsystem.model.Actor;
import com.logincontroller.filmrentalsystem.repository.ActorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ActorService {

    private final ActorRepository actorRepository;

    public List<Actor> getAllActors() {
        return actorRepository.findAll();
    }

    public Actor getActorById(Short id) {
        return actorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Actor not found with id: " + id));
    }

    public List<Actor> searchByFirstName(String firstName) {
        return actorRepository.findByFirstNameContainingIgnoreCase(firstName);
    }

    public List<Actor> searchByLastName(String lastName) {
        return actorRepository.findByLastNameContainingIgnoreCase(lastName);
    }

    public Actor updateActor(Short id, Actor updatedData) {
        Actor existing = getActorById(id);
        existing.setFirstName(updatedData.getFirstName());
        existing.setLastName(updatedData.getLastName());
        return actorRepository.save(existing);
    }

    public Actor saveActor(Actor actor) {
        return actorRepository.save(actor);
    }

    public void deleteActor(Short id) {
        actorRepository.deleteById(id);
    }
}