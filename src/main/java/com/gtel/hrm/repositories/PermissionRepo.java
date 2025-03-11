package com.gtel.hrm.repositories;

import com.gtel.hrm.models.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PermissionRepo extends JpaRepository<Permission, String> {
}
