package org.uv.SGLAC.services.implement;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.uv.SGLAC.dtos.PatientCreateDTO;
import org.uv.SGLAC.entities.Patient;
import org.uv.SGLAC.entities.Role;
import org.uv.SGLAC.entities.User;
import org.uv.SGLAC.repositories.PatientRepository;
import org.uv.SGLAC.repositories.RoleRepository;
import org.uv.SGLAC.repositories.UserRepository;
import org.uv.SGLAC.services.PatientService;

@Service
@Transactional
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public PatientServiceImpl(PatientRepository patientRepository, UserRepository userRepository, RoleRepository roleRepository) {
        this.patientRepository = patientRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public Patient create(PatientCreateDTO dto) {
        if (userRepository.findByUsername(dto.user().username()).isPresent())
            throw new RuntimeException("Username already exists");

        if (userRepository.findByEmail(dto.user().email()).isPresent())
            throw new RuntimeException("Email already exists");

        if (dto.user().phoneNumber() != null &&
            userRepository.findByPhoneNumber(dto.user().phoneNumber()).isPresent())
            throw new RuntimeException("Phone number already exists");

        if (patientRepository.findByRecordNumber(dto.recordNumber()).isPresent())
            throw new RuntimeException("Record number already exists");

        Role role = roleRepository.findById(dto.user().roleId())
                .orElseThrow(() -> new RuntimeException("Role not found"));

        User user = new User();
        user.setNames(dto.user().names());
        user.setLastname(dto.user().lastname());
        user.setUsername(dto.user().username());
        user.setEmail(dto.user().email());
        user.setPassword(passwordEncoder.encode(dto.user().password()));
        user.setPhoneNumber(dto.user().phoneNumber());
        user.setSex(dto.user().sex());
        user.setDateOfBirth(dto.user().dateOfBirth());
        user.setRole(role);
        user.setJoined(LocalDateTime.now());

        user = userRepository.save(user);

        Patient patient = new Patient();
        patient.setRecordNumber(dto.recordNumber());
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
