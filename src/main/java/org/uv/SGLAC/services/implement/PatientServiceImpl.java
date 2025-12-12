package org.uv.SGLAC.services.implement;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityNotFoundException;
import org.uv.SGLAC.entities.Patient;
import org.uv.SGLAC.entities.User;
import org.uv.SGLAC.repositories.PatientRepository;
import org.uv.SGLAC.repositories.UserRepository;
import org.uv.SGLAC.services.PatientService;

@Service
@Transactional
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final UserRepository userRepository;

    public PatientServiceImpl(PatientRepository patientRepository, UserRepository userRepository) {
        this.patientRepository = patientRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Patient create(Patient patient) {
        if (patient.getUser() == null || patient.getUser().getId() == null)
            throw new IllegalArgumentException("User requerido");

        User user = userRepository.findById(patient.getUser().getId())
                .orElseThrow(() -> new EntityNotFoundException("User no encontrado"));


        if (patientRepository.findByUserId(user.getId()).isPresent())
            throw new RuntimeException("Este usuario ya está registrado como paciente");

        if (patientRepository.findByRecordNumber(patient.getRecordNumber()).isPresent())
            throw new RuntimeException("El número de registro ya existe");

        patient.setUser(user);
        return patientRepository.save(patient);
    }

    @Override
    public Patient update(Long id, Patient patient) {
        Patient existing = patientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Paciente no encontrado"));

        if (patient.getRecordNumber() != null && !patient.getRecordNumber().isBlank())
            existing.setRecordNumber(patient.getRecordNumber());

        return patientRepository.save(existing);
    }

    @Override
    public Patient getById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Paciente no encontrado"));
    }

    @Override
    public List<Patient> getAll() {
        return patientRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        if (!patientRepository.existsById(id))
            throw new EntityNotFoundException("Paciente no encontrado");
        patientRepository.deleteById(id);
    }
}
