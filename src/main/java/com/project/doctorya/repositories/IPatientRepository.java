package com.project.doctorya.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.doctorya.entities.PatientEntity;

@Repository
public interface IPatientRepository extends JpaRepository<PatientEntity, UUID> {
    Optional<PatientEntity> findByIdentification(String identification);
}
