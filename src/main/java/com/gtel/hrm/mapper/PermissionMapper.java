package com.gtel.hrm.mapper;


import com.gtel.hrm.dto.request.PermissionRequest;
import com.gtel.hrm.dto.response.PermissionResponse;
import com.gtel.hrm.models.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper{
    Permission toPermission(PermissionRequest request);
    PermissionResponse toPermissionResponse(Permission permission);
}
