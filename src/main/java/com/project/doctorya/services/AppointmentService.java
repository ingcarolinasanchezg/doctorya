package com.project.doctorya.services;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.doctorya.dtos.AppointmentDto;
import com.project.doctorya.entities.AppointmentEntity;
import com.project.doctorya.exceptions.EntityNotExistsException;
import com.project.doctorya.models.Appointment;
import com.project.doctorya.repositories.AppointmentRepository;
import com.project.doctorya.shared.Constants;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private ModelMapper mapper;

    public Appointment create(AppointmentDto dto) {
        AppointmentEntity entity = mapper.map(dto, AppointmentEntity.class);
        AppointmentEntity saved = appointmentRepository.save(entity);
        return mapper.map(saved, Appointment.class);
    }

    public Appointment getById(UUID id) {
        AppointmentEntity entity = appointmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotExistsException(Constants.appointmentNotFound));

        return mapper.map(entity, Appointment.class);
    }

    public Appointment update(AppointmentDto dto, UUID id) {
        AppointmentEntity existing = appointmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotExistsException(Constants.appointmentNotFound));

        mapper.map(dto, existing);
        AppointmentEntity updated = appointmentRepository.save(existing);

        return mapper.map(updated, Appointment.class);
    }

    public void delete(UUID id) {
        AppointmentEntity entity = appointmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotExistsException(Constants.appointmentNotFound));

        appointmentRepository.delete(entity);
    }
}