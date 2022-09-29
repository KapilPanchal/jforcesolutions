package com.appjforce.serverjforce.repository;

import com.appjforce.serverjforce.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {
    @Query(value = "SELECT * FROM appjforcesch.appuser WHERE username = ?1",
            nativeQuery = true)
    Optional<AppUser> getByUsername(String username);
}
