package com.project.doctorya.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.doctorya.entities.DoctorEntity;

@Repository
public interface IDoctorRepository extends JpaRepository<DoctorEntity, UUID> {
    Optional<DoctorEntity> findByIdentification(String identification);
}