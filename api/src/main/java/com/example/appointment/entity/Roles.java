package com.example.appointment.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "role")
@Getter
@Setter
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String roleCode;
    private String roleType;
    private String description;
    @CreationTimestamp
    @Column(updatable = false)
    Timestamp createdOn;
    @UpdateTimestamp
    Timestamp modifiedOn;
}
