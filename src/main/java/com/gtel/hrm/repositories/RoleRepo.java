package com.gtel.hrm.repositories;

import com.gtel.hrm.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepo extends JpaRepository<Role, String> {

}
