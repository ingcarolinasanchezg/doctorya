package com.project.doctorya.services;

import java.lang.reflect.Type;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.doctorya.dtos.DoctorDto;
import com.project.doctorya.entities.DoctorEntity;
import com.project.doctorya.exceptions.EntityNotExistsException;
import com.project.doctorya.models.Doctor;
import com.project.doctorya.repositories.IDoctorRepository;
import com.project.doctorya.shared.Constants;

@SuppressWarnings("all")
@Service
public class DoctorService {

    @Autowired
    private IDoctorRepository doctorRepository;

    @Autowired
    private ModelMapper mapper;

    public Doctor create(DoctorDto doctorDto) {
        DoctorEntity entity = mapper.map(doctorDto, DoctorEntity.class);
        DoctorEntity saved = doctorRepository.save(entity);
        return mapper.map(saved, Doctor.class);
    }

    public Doctor update(DoctorDto doctorDto, UUID id) {
        DoctorEntity doctorExist = doctorRepository.findById(id)
                .orElseThrow(() -> new EntityNotExistsException(Constants.doctorNotFound));

        mapper.map(doctorDto, doctorExist);
        DoctorEntity updated = doctorRepository.save(doctorExist);
        return mapper.map(updated, Doctor.class);
    }

    public List<Doctor> getAll() {
        List<DoctorEntity> doctors = doctorRepository.findAll();
        Type listType = new TypeToken<List<Doctor>>() {}.getType();
        return mapper.map(doctors, listType);
    }

    public Doctor getById(UUID id) {
        DoctorEntity doctorExist = doctorRepository.findById(id)
                .orElseThrow(() -> new EntityNotExistsException(Constants.doctorNotFound));

        return mapper.map(doctorExist, Doctor.class);
    }

    public Doctor getByIdentification(String identification) {
        DoctorEntity doctorExist = doctorRepository.findByIdentification(identification)
                .orElseThrow(() -> new EntityNotExistsException(Constants.doctorNotFound));

        return mapper.map(doctorExist, Doctor.class);
    }

    public void delete(UUID id) {
        DoctorEntity doctorExist = doctorRepository.findById(id)
                .orElseThrow(() -> new EntityNotExistsException(Constants.doctorNotFound));

        doctorRepository.delete(doctorExist);
    }
}