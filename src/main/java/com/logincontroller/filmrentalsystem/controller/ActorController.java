package com.logincontroller.filmrentalsystem.controller;


import com.logincontroller.filmrentalsystem.model.Actor;
import com.logincontroller.filmrentalsystem.service.ActorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/actors")
public class ActorController {

    private final ActorService actorService;

    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

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
    public String deleteActor(@PathVariable Short id) {
        actorService.deleteActor(id);
        return "Actor deleted successfully!";
    }
}