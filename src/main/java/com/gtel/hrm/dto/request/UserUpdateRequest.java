package com.gtel.hrm.dto.request;


import lombok.*;

import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserUpdateRequest {
    private String username;
    private String password;
    private String email;
    private List<String> roles;

}
