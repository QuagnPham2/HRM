package com.gtel.hrm.services;


import com.gtel.hrm.dto.request.PermissionRequest;
import com.gtel.hrm.dto.response.PermissionResponse;
import com.gtel.hrm.mapper.PermissionMapper;
import com.gtel.hrm.models.Permission;
import com.gtel.hrm.repositories.PermissionRepo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PermissionService {
    private final PermissionRepo permissionRepo;

    private final PermissionMapper permissionMapper;

    public PermissionResponse create(PermissionRequest request){
        Permission permission = permissionMapper.toPermission(request);
        permission = permissionRepo.save(permission);
        return permissionMapper.toPermissionResponse(permission);
    }

    public List<PermissionResponse> getAll(){
        var permissions = permissionRepo.findAll();
        return permissions.stream().map(permissionMapper::toPermissionResponse).toList();

    }

    public void delete(String permission){
        permissionRepo.deleteById(permission);
    }
}
