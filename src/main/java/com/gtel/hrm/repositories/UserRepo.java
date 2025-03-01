package com.gtel.hrm.repositories;

import com.gtel.hrm.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Users, Integer> {
}
