package com.logincontroller.filmrentalsystem.repository;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.logincontroller.filmrentalsystem.model.Store;

@Repository
public interface StoresRepository extends JpaRepository<Store, Integer> {
}