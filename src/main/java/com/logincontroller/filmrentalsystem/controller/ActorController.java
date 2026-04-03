package com.logincontroller.filmrentalsystem.controller;

import com.logincontroller.filmrentalsystem.dto.ActorResponseDTO;
import com.logincontroller.filmrentalsystem.dto.FilmActorResponseDTO;
import com.logincontroller.filmrentalsystem.dto.FilmResponseDTO;
import com.logincontroller.filmrentalsystem.model.Actor;
import com.logincontroller.filmrentalsystem.service.ActorService;
import com.logincontroller.filmrentalsystem.service.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="http://10.191.27.14:9090")
@RequestMapping("/api/actors")
@RequiredArgsConstructor
public class ActorController {



    private final ActorService actorService;
    private final FilmService filmService;

    @PostMapping("/post")
    public ResponseEntity<ActorResponseDTO> createActor(@RequestBody Actor actor) {
        return ResponseEntity.ok(actorService.saveActor(actor));
    }

    @GetMapping("/lastname/{ln}")
    public ResponseEntity<List<ActorResponseDTO>> byLastName(@PathVariable String ln) {
        return ResponseEntity.ok(actorService.searchByLastName(ln));
    }

    @GetMapping("/firstname/{fn}")
    public ResponseEntity<List<ActorResponseDTO>> byFirstName(@PathVariable String fn) {
        return ResponseEntity.ok(actorService.searchByFirstName(fn));
    }

    @PutMapping("/update/lastname/{id}")
    public ResponseEntity<ActorResponseDTO> updateLastName(
            @PathVariable Short id,
            @RequestParam String lastName) {
        return ResponseEntity.ok(actorService.updateLastName(id, lastName));
    }



    @GetMapping("/{id}/films")
    public ResponseEntity<List<FilmResponseDTO>> filmsForActor(@PathVariable Short id) {
        return ResponseEntity.ok(actorService.getFilmsForActor(id));
    }

    @GetMapping("/{id}/film")
    public ResponseEntity<List<FilmActorResponseDTO>> actorFilmLinks(@PathVariable Short id) {
        return ResponseEntity.ok(filmService.getActorFilmLinks(id));
    }

    @GetMapping("/toptenbyfilmcount")
    public ResponseEntity<List<ActorResponseDTO>> topTenByFilmCount() {
        return ResponseEntity.ok(actorService.getTopTenActorsByFilmCount());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActorResponseDTO> getActorById(@PathVariable Short id) {
        return ResponseEntity.ok(actorService.getActorById(id));
    }
}
