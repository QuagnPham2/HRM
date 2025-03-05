package com.gtel.hrm.repositories;

import com.gtel.hrm.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<Users, Long> {
    boolean existsByUsername(String username);
    Optional<Users> findByUsername(String username);
}
