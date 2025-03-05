package com.gtel.hrm.models;


import jakarta.persistence.*;

@Entity
@Table(name = "epl")
public class Employees {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ep")
    private Long idEp;
    private String nameEp;
    private int ageEp;
    private String phoneEp;
    private String emailEp;

    public Long getIdEp() {
        return idEp;
    }

    public void setIdEp(Long idEp) {
        this.idEp = idEp;
    }

    public String getNameEp() {
        return nameEp;
    }

    public void setNameEp(String nameEp) {
        this.nameEp = nameEp;
    }

    public int getAgeEp() {
        return ageEp;
    }

    public void setAgeEp(int ageEp) {
        this.ageEp = ageEp;
    }

    public String getPhoneEp() {
        return phoneEp;
    }

    public void setPhoneEp(String phoneEp) {
        this.phoneEp = phoneEp;
    }

    public String getEmailEp() {
        return emailEp;
    }

    public void setEmailEp(String emailEp) {
        this.emailEp = emailEp;
    }
}
