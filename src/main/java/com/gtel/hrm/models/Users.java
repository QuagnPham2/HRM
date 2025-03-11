package com.gtel.hrm.models;


import com.gtel.hrm.exception.ErrorCode;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.mapstruct.Mapper;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long idUser;
    private String username;
    private String password;
    private String email;


    @ManyToMany
    private Set<Role> roles;



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
