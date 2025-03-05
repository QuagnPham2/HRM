package com.gtel.hrm.controllers;


import com.gtel.hrm.dto.request.ApiResponse;
import com.gtel.hrm.dto.request.UserCreateRequest;
import com.gtel.hrm.dto.request.UserUpdateRequest;
import com.gtel.hrm.models.Users;
import com.gtel.hrm.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public ApiResponse<Users> createUser(@RequestBody @Valid UserCreateRequest request){
        ApiResponse<Users> apiResponse = new ApiResponse();
//        try{
//            if (request == null) {
//                return ResponseEntity.badRequest().body("Request body is missing");
//            }
//            if (request.getEmployeeId() == null) {
//                return ResponseEntity.badRequest().body("Employee ID must not be null");
//            }
//            Users createdUser = userService.createUser(request);
        apiResponse.setResult(userService.createUser(request));
        return apiResponse;
//        }
//        catch (RuntimeException e){
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
    }

    @GetMapping("/getAllUser")
    public List<Users> getAllUser(){
        return userService.getAllUsers();
    }

    @PostMapping("/getUserByID/{userId}")
    public Users getUserByID(@PathVariable Long userId){
        return userService.getUserById(userId);
    }

    @PutMapping("/updateUser/{userId}")
    public Users updateUser(@PathVariable Long userId, @RequestBody UserUpdateRequest request){
        return userService.updateUser(userId, request);
    }

    @DeleteMapping("/deleteUser/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId){
        if (userId == null){
            return ResponseEntity.badRequest().body("userId is null");
        }
        userService.deleteUser(userId);
        return ResponseEntity.ok("User deleted successfully");
    }
}
