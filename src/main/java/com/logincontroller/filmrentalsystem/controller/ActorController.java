package com.logincontroller.filmrentalsystem.controller;

import com.logincontroller.filmrentalsystem.model.Actor;
import com.logincontroller.filmrentalsystem.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/actors")
public class ActorController {

    @Autowired
    private ActorService actorService;

    @GetMapping
    public List<Actor> getAllActors() {
        return actorService.getAllActors();
    }

    @GetMapping("/{id}")
    public Actor getActorById(@PathVariable Short id) {
        return actorService.getActorById(id);
    }

    @PostMapping
    public Actor createActor(@RequestBody Actor actor) {
        return actorService.saveActor(actor);
    }

    @PutMapping("/{id}")
    public Actor updateActor(@PathVariable Short id, @RequestBody Actor actor) {
        actor.setActorId(id);
        return actorService.saveActor(actor);
    }

    @DeleteMapping("/{id}")
    public void deleteActor(@PathVariable Short id) {
        actorService.deleteActor(id);
    }
}