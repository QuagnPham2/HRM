package com.gtel.hrm.mapper;


import com.gtel.hrm.dto.request.PermissionRequest;
import com.gtel.hrm.dto.request.RoleRequest;
import com.gtel.hrm.dto.response.PermissionResponse;
import com.gtel.hrm.dto.response.RoleResponse;
import com.gtel.hrm.models.Permission;
import com.gtel.hrm.models.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}
