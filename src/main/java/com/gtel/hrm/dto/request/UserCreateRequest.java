package com.gtel.hrm.dto.request;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateRequest {
    @Size(min = 3, message = "USERNAME_INVALID")
    private String username;

    @Size(min = 8, message = "PASSWORD_INVALID")
    private String password;
    private Set<String> role;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "id_ep", unique = true)
//    private Employees employees;

//    public Long getEmployeeId() {
//        return idEp;
//    }
//
//    public void setEmployeeId(Long idEp) {
//        this.idEp = idEp;
//    }

//    public Long getEpId() {
//        if (employees == null || employees.getIdEp() == null) {
//            throw new IllegalArgumentException("Employee ID must not be null");
//        }
//        return employees.getIdEp();
//    }

}
