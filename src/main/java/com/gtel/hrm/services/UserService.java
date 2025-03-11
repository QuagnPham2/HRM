package com.gtel.hrm.services;


import com.gtel.hrm.dto.request.UserCreateRequest;
import com.gtel.hrm.dto.request.UserUpdateRequest;
import com.gtel.hrm.dto.response.UserResponse;
import com.gtel.hrm.enums.Role;
import com.gtel.hrm.exception.AppException;
import com.gtel.hrm.exception.ErrorCode;
import com.gtel.hrm.mapper.UserMapper;
import com.gtel.hrm.models.Users;
import com.gtel.hrm.repositories.RoleRepo;
import com.gtel.hrm.repositories.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private UserMapper userMapper;

    public Users createUser(UserCreateRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request body must not be null");
        }
        if(userRepo.existsByUsername(request.getUsername())){
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        Users user = new Users();


        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user.setEmail(request.getEmail());

        HashSet<String> roles = new HashSet<>();
        roles.add(Role.USER.name());

        return userRepo.save(user);

    }

//    @PreAuthorize("hasRole('ADMIN')")
    @PreAuthorize("hasAuthority('APPROVE_POST')")
    public List<Users> getAllUsers() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("Username {}", authentication.getName());
        authentication.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));

        return userRepo.findAll();
    }


    @PostAuthorize("returnObject.username == authentication.name")
    public Users getUserById(Long id) {
        return userRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public Users updateUser(@PathVariable Long id, UserUpdateRequest request) {
        Users user = userRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        var roles = roleRepo.findAllById(request.getRoles());
        user.setRoles(new HashSet<>(roles));

        return userRepo.save(user);
    }

    public void deleteUser(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("User ID must not be null");
        }
        userRepo.deleteById(userId);
    }

    public UserResponse getMyInfo(){
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();

        Users user = userRepo.findByUsername(name).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        return userMapper.toUserResponse(user);
    }
}
