package com.gtel.hrm.services;


import com.gtel.hrm.models.Employees;
import com.gtel.hrm.repositories.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class EmployeeService {
    @Autowired
    EmployeeRepo employeeRepo;

    public List<Employees> getAllEmployees() {
        return employeeRepo.findAll();
    }

    public Optional<Employees> getEmployeeById(int idEp) {
        return employeeRepo.findById(idEp);
    }

    public Employees createUser(Employees employees){
        employees.setIdEp(0);
        return employeeRepo.save(employees);
    }

    public boolean deleteEmployeeById(int idEp) {
        if(employeeRepo.existsById(idEp)) {
            employeeRepo.deleteById(idEp);
            return true;
        }

        return false;
    }


    public Employees editEmployee(Employees employees) {
        Optional<Employees> existingEp = employeeRepo.findById(employees.getIdEp());
        if(existingEp.isPresent()) {
            Employees employeeUpdate = existingEp.get();
            employeeUpdate.setNameEp(employees.getNameEp());
            employeeUpdate.setAgeEp(employees.getAgeEp());
            employeeUpdate.setPhoneEp(employees.getPhoneEp());
            employeeUpdate.setEmailEp(employees.getEmailEp());

            return employeeRepo.save(employeeUpdate);
        }
        else {
            throw new RuntimeException("Employee not found" + employees.getIdEp());
        }
    }
}
