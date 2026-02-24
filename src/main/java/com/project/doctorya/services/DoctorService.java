package com.project.doctorya.services;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;
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

@Service
public class DoctorService {

    @Autowired
    private IDoctorRepository doctorRepository;

    @Autowired
    private ModelMapper mapper;

    public Doctor create(DoctorDto doctorDto) {
        DoctorEntity doctorEntity = mapper.map(doctorDto, DoctorEntity.class);
        DoctorEntity response = doctorRepository.save(doctorEntity);
        return mapper.map(response, Doctor.class);
    }

    public Doctor update(DoctorDto doctorDto, UUID id) {
        Optional<DoctorEntity> entity = doctorRepository.findById(id);
        if (entity.isEmpty()) {
            throw new EntityNotExistsException(Constants.doctorNotFound);
        }
        DoctorEntity doctorExist = entity.get();
        mapper.map(doctorDto, doctorExist);
        DoctorEntity response = doctorRepository.save(doctorExist);
        return mapper.map(response, Doctor.class);
    }

    public List<Doctor> getAll() {
        List<DoctorEntity> doctors = doctorRepository.findAll();
        Type listType = new TypeToken<List<Doctor>>() {}.getType();
        return mapper.map(doctors, listType);
    }

    public Doctor getById(UUID id) {
        Optional<DoctorEntity> doctorEntity = doctorRepository.findById(id);
        if (doctorEntity.isEmpty()) {
            throw new EntityNotExistsException(Constants.doctorNotFound);
        }
        return mapper.map(doctorEntity.get(), Doctor.class);
    }

    public Doctor getByIdentification(String identification) {
        Optional<DoctorEntity> doctorEntity = doctorRepository.findByIdentification(identification);
        if (doctorEntity.isEmpty()) {
            throw new EntityNotExistsException(Constants.doctorNotFound);
        }
        return mapper.map(doctorEntity.get(), Doctor.class);
    }

    public void delete(UUID id) {
        Optional<DoctorEntity> doctor = doctorRepository.findById(id);
        if (doctor.isEmpty()) {
            throw new EntityNotExistsException(Constants.doctorNotFound);
        }
        doctorRepository.delete(doctor.get());
    }
}