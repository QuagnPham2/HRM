package com.gtel.hrm.controllers;


import com.gtel.hrm.dto.request.ApiResponse;
import com.gtel.hrm.dto.request.RoleRequest;
import com.gtel.hrm.dto.response.RoleResponse;
import com.gtel.hrm.services.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@Slf4j
public class RoleController {
    private final RoleService roleService;

    @PostMapping("/createRole")
    public ApiResponse<RoleResponse> create(@RequestBody RoleRequest request) {
        return ApiResponse.<RoleResponse>builder()
                .result(roleService.createRole(request))
                .code(1000)
                .build();
    }

    @GetMapping("/getAllRole")
    public ApiResponse<List<RoleResponse>> getAllRole() {
        return ApiResponse.<List<RoleResponse>>builder()
                .result(roleService.getAllRole())
                .code(1000)
                .build();
    }

    @DeleteMapping("/deletePermission/{role}")
    public ApiResponse<Void> deleteRole(@PathVariable String role) {
        roleService.deleteRole(role);
        return ApiResponse.<Void>builder()
                .code(1000)
                .message("Delete successful")
                .build();
    }
}
