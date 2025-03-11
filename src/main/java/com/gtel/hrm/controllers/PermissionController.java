package com.gtel.hrm.controllers;


import com.gtel.hrm.dto.request.ApiResponse;
import com.gtel.hrm.dto.request.PermissionRequest;
import com.gtel.hrm.dto.response.PermissionResponse;
import com.gtel.hrm.services.PermissionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permissions")
@RequiredArgsConstructor
@Slf4j
public class PermissionController {
    private final PermissionService permissionService;

    @PostMapping("/createPermission")
    public ApiResponse<PermissionResponse> create(@RequestBody PermissionRequest request) {
        return ApiResponse.<PermissionResponse>builder()
                .result(permissionService.create(request))
                .code(1000)
                .build();
    }

    @GetMapping("/getAllPermission")
    public ApiResponse<List<PermissionResponse>> getAllPermission() {
        return ApiResponse.<List<PermissionResponse>>builder()
                .result(permissionService.getAll())
                .code(1000)
                .build();
    }

    @DeleteMapping("/deletePermission/{permission}")
    public ApiResponse<Void> deletePermission(@PathVariable String permission) {
        permissionService.delete(permission);
        return ApiResponse.<Void>builder()
                .code(1000)
                .message("Delete successful")
                .build();
    }
}
