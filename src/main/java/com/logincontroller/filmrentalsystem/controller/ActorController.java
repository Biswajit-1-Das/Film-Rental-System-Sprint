package com.logincontroller.filmrentalsystem.controller;

import com.logincontroller.filmrentalsystem.model.Actor;
import com.logincontroller.filmrentalsystem.service.ActorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/actors")
@RequiredArgsConstructor
public class ActorController {

    private final ActorService actorService;

    @GetMapping
    public ResponseEntity<List<Actor>> getAllActors() {
        return ResponseEntity.ok(actorService.getAllActors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Actor> getActorById(@PathVariable Short id) {
        return ResponseEntity.ok(actorService.getActorById(id));
    }

    // search by first name — /actors/search?firstName=john
    @GetMapping("/search/firstname")
    public ResponseEntity<List<Actor>> searchByFirstName(@RequestParam String firstName) {
        return ResponseEntity.ok(actorService.searchByFirstName(firstName));
    }

    // search by last name — /actors/search?lastName=smith
    @GetMapping("/search/lastname")
    public ResponseEntity<List<Actor>> searchByLastName(@RequestParam String lastName) {
        return ResponseEntity.ok(actorService.searchByLastName(lastName));
    }

    @PostMapping
    public ResponseEntity<Actor> createActor(@RequestBody Actor actor) {
        return ResponseEntity.ok(actorService.saveActor(actor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Actor> updateActor(@PathVariable Short id,
                                             @RequestBody Actor actor) {
        Actor existing = actorService.getActorById(id);
        existing.setFirstName(actor.getFirstName());
        existing.setLastName(actor.getLastName());
        return ResponseEntity.ok(actorService.saveActor(existing));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteActor(@PathVariable Short id) {
        actorService.deleteActor(id);
        return ResponseEntity.ok("Actor deleted successfully");
    }
}