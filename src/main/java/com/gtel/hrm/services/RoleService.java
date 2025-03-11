package com.gtel.hrm.services;


import com.gtel.hrm.dto.request.RoleRequest;
import com.gtel.hrm.dto.response.RoleResponse;
import com.gtel.hrm.mapper.RoleMapper;
import com.gtel.hrm.repositories.PermissionRepo;
import com.gtel.hrm.repositories.RoleRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepo roleRepo;
    private final RoleMapper roleMapper;
    private final PermissionRepo permissionRepo;


    public RoleResponse createRole(RoleRequest request) {
        var role = roleMapper.toRole(request);

        var permissions = permissionRepo.findAllById(request.getPermissions());
        role.setPermissions(new HashSet<>(permissions));

        role = roleRepo.save(role);
        return roleMapper.toRoleResponse(role);
    }

    public List<RoleResponse> getAllRole() {
        return roleRepo.findAll()
                .stream()
                .map(roleMapper::toRoleResponse)
                .toList();
    }

    public void deleteRole(String role) {
        roleRepo.deleteById(role);
    }

}
