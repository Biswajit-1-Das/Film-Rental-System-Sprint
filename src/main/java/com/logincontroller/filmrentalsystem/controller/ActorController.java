package com.logincontroller.filmrentalsystem.controller;

import com.logincontroller.filmrentalsystem.dto.ActorDTO;
import com.logincontroller.filmrentalsystem.model.Actor;
import com.logincontroller.filmrentalsystem.service.ActorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/actors")
@RequiredArgsConstructor
public class ActorController {

    private final ActorService actorService;

    @GetMapping
    public ResponseEntity<List<ActorDTO>> getAllActors() {
        List<ActorDTO> actorDTOs = actorService.getAllActors()
                .stream()
                .map(ActorDTO::new) // Converts every Actor into an ActorDTO
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(actorDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActorDTO> getActorById(@PathVariable Short id) {
        Actor actor = actorService.getActorById(id);
        return ResponseEntity.ok(new ActorDTO(actor));
    }

    @GetMapping("/search/firstname")
    public ResponseEntity<List<ActorDTO>> searchByFirstName(@RequestParam String firstName) {
        List<ActorDTO> actorDTOs = actorService.searchByFirstName(firstName)
                .stream()
                .map(ActorDTO::new)
                .collect(Collectors.toList());
                
        return ResponseEntity.ok(actorDTOs);
    }

    @GetMapping("/search/lastname")
    public ResponseEntity<List<ActorDTO>> searchByLastName(@RequestParam String lastName) {
        List<ActorDTO> actorDTOs = actorService.searchByLastName(lastName)
                .stream()
                .map(ActorDTO::new)
                .collect(Collectors.toList());
                
        return ResponseEntity.ok(actorDTOs);
    }

    // POST, PUT, and DELETE stay exactly the same for now, 
    // as you usually pass raw entities when saving/updating.
    
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