package com.logincontroller.filmrentalsystem.repository;


import com.logincontroller.filmrentalsystem.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Short> {



}