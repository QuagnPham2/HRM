package com.gtel.hrm.dto.request;

import jakarta.validation.constraints.Size;

public class UserCreateRequest {
    @Size(min = 3, message = "USERNAME_INVALID")
    private String username;

    @Size(min = 8, message = "PASSWORD_INVALID")
    private String password;
    private String role;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
