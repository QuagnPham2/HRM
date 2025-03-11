package com.gtel.hrm.mapper;


import com.gtel.hrm.dto.request.UserCreateRequest;
import com.gtel.hrm.dto.response.UserResponse;
import com.gtel.hrm.models.Role;
import com.gtel.hrm.models.Users;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {
    Users toUsers(UserCreateRequest request);
    UserResponse toUserResponse(Users user);

    @Mapping(target = "roles", ignore = true)
    void updateUser(@MappingTarget Users user, UserCreateRequest request);
}
