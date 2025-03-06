package com.gtel.hrm.services;


import com.gtel.hrm.dto.request.UserCreateRequest;
import com.gtel.hrm.dto.request.UserUpdateRequest;
import com.gtel.hrm.enums.Role;
import com.gtel.hrm.exception.AppException;
import com.gtel.hrm.exception.ErrorCode;
import com.gtel.hrm.models.Users;
import com.gtel.hrm.repositories.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashSet;
import java.util.List;
@Slf4j
@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
//    @Autowired
//    private EmployeeService employeeService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Users createUser(UserCreateRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request body must not be null");
        }

//        if (request.getEpId() == null) {
//            throw new IllegalArgumentException("Employee ID must not be null");
//        }
//        Employees employees = employeeService.getEmployeeById(request.getEmployeeId())
//                .orElseThrow(()->new RuntimeException("Employee not found with ID: " + request.getEmployeeId()));

        if(userRepo.existsByUsername(request.getUsername())){
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        Users user = new Users();

//        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
//        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        HashSet<String> roles = new HashSet<>();
        roles.add(Role.USER.name());

        user.setRoles(roles);
//        user.setEmployees(employees);
        return userRepo.save(user);
    }

    public List<Users> getAllUsers() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("Username {}", authentication.getName());
        authentication.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));

        return userRepo.findAll();
    }

    public Users getUserById(Long id) {
        return userRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public Users updateUser(@PathVariable Long id, UserUpdateRequest request) {
        Users user = userRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        return userRepo.save(user);
    }

    public void deleteUser(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("User ID must not be null");
        }
        userRepo.deleteById(userId);
    }
}
