package com.gtel.hrm.repositories;

import com.gtel.hrm.models.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface EmployeeRepo extends JpaRepository<Employees, Integer> {
    Optional<Employees> deleteById(int idEp);
}
