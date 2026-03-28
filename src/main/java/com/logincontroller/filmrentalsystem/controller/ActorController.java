package com.logincontroller.filmrentalsystem.controller;

import com.logincontroller.filmrentalsystem.dto.ActorDTO;
import com.logincontroller.filmrentalsystem.model.Actor;
import com.logincontroller.filmrentalsystem.service.ActorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/actors")
@RequiredArgsConstructor
public class ActorController {

    private final ActorService actorService;
    // GET /actors/names — lightweight endpoint for the actor listing page
    @GetMapping
    public ResponseEntity<List<String>> getAllActorNames() {
        return ResponseEntity.ok(actorService.getAllActorNames());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActorDTO> getActorById(@PathVariable Short id) {
        return ResponseEntity.ok(actorService.getActorById(id));
    }

    @GetMapping("/search/firstname")
    public ResponseEntity<List<ActorDTO>> searchByFirstName(@RequestParam String firstName) {
        return ResponseEntity.ok(actorService.searchByFirstName(firstName));
    }

    @GetMapping("/search/lastname")
    public ResponseEntity<List<ActorDTO>> searchByLastName(@RequestParam String lastName) {
        return ResponseEntity.ok(actorService.searchByLastName(lastName));
    }

    @PostMapping
    public ResponseEntity<ActorDTO> createActor(@RequestBody Actor actor) {
        return ResponseEntity.ok(actorService.saveActor(actor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActorDTO> updateActor(@PathVariable Short id,
                                                @RequestBody Actor actor) {
        Actor existing = actorService.getEntityById(id);
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