package com.gtel.hrm.dto.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
@Setter
public class RoleResponse {
    private String name;
    private String description;
    private Set<PermissionResponse> permissions;

}
