package com.gtel.hrm.dto.response;


import lombok.*;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private String username;
    private String email;
    private Set<RoleResponse> roles;
}
