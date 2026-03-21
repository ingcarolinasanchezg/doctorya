package com.project.doctorya.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository; // 👈 Importante
import com.project.doctorya.entities.AppointmentEntity;

@Repository // 👈 Añade esta anotación
public interface IAppointmentRepository extends JpaRepository<AppointmentEntity, UUID> {
    // Al añadir la "I" al principio, mantienes la consistencia con Doctor y Patient
}