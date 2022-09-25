package com.example.appointment.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "doctor")
@Getter
@Setter
public class Doctor implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private String password;
    private String email;
    private String mobile;
    private String gender; // MALE/FEMALE - enum to be use
    private String speciality; // Dermatologist, Urologist,Psychiatrist
    private int experience;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "role_id", referencedColumnName = "id")
//    private Roles role;

}
