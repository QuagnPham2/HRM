package com.gtel.hrm.controllers;


import com.gtel.hrm.models.Employees;
import com.gtel.hrm.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/getAllEp")
    public List<Employees> getEmployee() {
        return employeeService.getAllEmployees();
    }

    @PostMapping("/getEpbyId")
    public ResponseEntity<Employees> getEmployeeById(@RequestBody Map<String, Long> request) {
        Long idEp = request.get("idEp");
        Optional<Employees> employee = employeeService.getEmployeeById(idEp);
        if(employee.isPresent()) {
            return ResponseEntity.ok(employee.get());
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/addEp")
    public Employees createUser(@RequestBody Employees employees) {
        return employeeService.createUser(employees);
    }

    @PostMapping("/deleteEp")
    public ResponseEntity<?> deleteUser(@RequestBody Map<String, Long> requestBody) {
        Long idEp = requestBody.get("idEp");

        if(idEp == null) {
            return ResponseEntity.badRequest().body("Employee ID must not be null");
        }

        boolean deleted = employeeService.deleteEmployeeById(idEp);
        if(deleted){
            return ResponseEntity.ok("Delete Successfully");
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("editEp")
    public ResponseEntity<?> editUser(@RequestBody Employees employees) {
        try{
            Employees updatedEmployee = employeeService.editEmployee(employees);
            return ResponseEntity.ok(updatedEmployee);
        }
        catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
