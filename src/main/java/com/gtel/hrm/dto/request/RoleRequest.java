package com.gtel.hrm.dto.request;

import com.gtel.hrm.models.Permission;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleRequest {
    private String name;
    private String description;
    private Set<String> permissions;
}
