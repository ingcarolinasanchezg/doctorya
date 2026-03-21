package com.project.doctorya.services;

import java.util.UUID;
import org.modelmapper.ModelMapper;
import org.modelmapper.Conditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.doctorya.dtos.AppointmentDto;
import com.project.doctorya.entities.AppointmentEntity;
import com.project.doctorya.exceptions.EntityNotExistsException;
import com.project.doctorya.models.Appointment;
import com.project.doctorya.repositories.IAppointmentRepository;
import com.project.doctorya.shared.Constants;

@Service
public class AppointmentService {

    @Autowired
    private IAppointmentRepository appointmentRepository;

    @Autowired
    private ModelMapper mapper;

    @Transactional
public Appointment create(AppointmentDto dto) {
    AppointmentEntity entity = mapper.map(dto, AppointmentEntity.class);
    
    // 👈 USA ESTO: saveAndFlush
    AppointmentEntity saved = appointmentRepository.saveAndFlush(entity); 
    
    return mapper.map(saved, Appointment.class);
}

    public Appointment getById(UUID id) {
        AppointmentEntity entity = appointmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotExistsException(Constants.appointmentNotFound));

        return mapper.map(entity, Appointment.class);
    }


    @Transactional
    public Appointment update(AppointmentDto dto, UUID id) {
        AppointmentEntity existing = appointmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotExistsException(Constants.appointmentNotFound));

        // Evita que si el DTO trae campos nulos, se borren los datos que ya están en la BD
        mapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        
        mapper.map(dto, existing);
        AppointmentEntity updated = appointmentRepository.saveAndFlush(existing);

        return mapper.map(updated, Appointment.class);
    }

    @Transactional
    public void delete(UUID id) {
        AppointmentEntity entity = appointmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotExistsException(Constants.appointmentNotFound));

        appointmentRepository.delete(entity);
        // Flush después de borrar para asegurar que desaparezca de la BD en el test
        appointmentRepository.flush();
    }
}