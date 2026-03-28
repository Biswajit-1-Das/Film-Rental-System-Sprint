package com.logincontroller.filmrentalsystem.repository;

import com.logincontroller.filmrentalsystem.model.FilmActor;
import com.logincontroller.filmrentalsystem.model.FilmActorId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmActorRepository extends JpaRepository<FilmActor, FilmActorId> {

    List<FilmActor> findByFilm_FilmId(Short filmId);

    List<FilmActor> findByActor_ActorId(Short actorId);

    @Query(value = "SELECT actor_id FROM film_actor GROUP BY actor_id ORDER BY COUNT(*) DESC LIMIT 10", nativeQuery = true)
    List<Short> findTopActorIdsByFilmCount();
}
