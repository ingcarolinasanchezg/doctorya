package com.project.doctorya.models;

import java.util.UUID;
import lombok.Data;

@Data
public class Doctor {
    private UUID id;
    private String identification;
    private String name;
    private String specialty;
}