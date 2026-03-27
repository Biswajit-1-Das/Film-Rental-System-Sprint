package com.logincontroller.filmrentalsystem.repository;


import com.logincontroller.filmrentalsystem.model.Staff;
import com.logincontroller.filmrentalsystem.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer> {

    // Used by Spring Security to load user at login
    Optional<Staff> findByUsername(String username);

    // All staff at a given store
    List<Staff> findByStore(Store store);

    // Only currently employed staff
    List<Staff> findByActiveTrue();

    // All staff at a store by store ID directly
    List<Staff> findByStoreStoreId(Integer storeId);

    // Pull data from the staff_list DB view
    @Query(value = "SELECT * FROM staff_list", nativeQuery = true)
    List<Object[]> getStaffListView();
}
