package com.gtel.hrm.models;


import com.gtel.hrm.exception.ErrorCode;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long idUser;
    private String username;
    private String password;
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> role;

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

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

    public Set<String> getRole() {
        return role;
    }

    public void setRoles(Set<String> role) {
        this.role = role;
    }

    //    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "id_ep", unique = true)
//    private Employees employees;

//    public Users() {
//    }


//    public Employees getEmployees() {
//        return employees;
//    }
//
//    public void setEmployees(Employees employees) {
//        this.employees = employees;
//    }
}
