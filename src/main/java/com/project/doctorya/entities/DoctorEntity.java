package com.project.doctorya.entities;

import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "doctor")
public class DoctorEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String identification;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String specialty;
}