package org.uv.SGLAC.services.implement;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.uv.SGLAC.dtos.LabTechnicianCreateDTO;
import org.uv.SGLAC.entities.LabTechnician;
import org.uv.SGLAC.entities.Role;
import org.uv.SGLAC.entities.Speciality;
import org.uv.SGLAC.entities.User;
import org.uv.SGLAC.repositories.LabTechnicianRepository;
import org.uv.SGLAC.repositories.RoleRepository;
import org.uv.SGLAC.repositories.SpecialityRepository;
import org.uv.SGLAC.repositories.UserRepository;
import org.uv.SGLAC.services.LabTechnicianService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class LabTechnicianServiceImpl implements LabTechnicianService {

    private final LabTechnicianRepository labTechnicianRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final SpecialityRepository specialityRepository;
    

    public LabTechnicianServiceImpl(
        LabTechnicianRepository labTechnicianRepository, 
        UserRepository userRepository, 
        RoleRepository roleRepository, 
        SpecialityRepository specialityRepository
    ) {
        this.labTechnicianRepository = labTechnicianRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.specialityRepository = specialityRepository;
    }

    @Override
    public LabTechnician create(LabTechnicianCreateDTO dto) {

        if (userRepository.findByUsername(dto.user().username()).isPresent())
            throw new RuntimeException("Username already exists");

        if (userRepository.findByEmail(dto.user().email()).isPresent())
            throw new RuntimeException("Email already exists");

        if (dto.user().phoneNumber() != null &&
            userRepository.findByPhoneNumber(dto.user().phoneNumber()).isPresent())
            throw new RuntimeException("Phone number already exists");

        Role role = roleRepository.findById(dto.user().roleId())
                .orElseThrow(() -> new RuntimeException("Role not found"));

        Speciality speciality = specialityRepository.findById(dto.specialityId())
                .orElseThrow(() -> new RuntimeException("Speciality not found"));

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

        if (labTechnicianRepository.findByUserId(user.getId()).isPresent())
            throw new RuntimeException("This user is already registered as a lab technician");

        LabTechnician labTech = new LabTechnician();
        labTech.setUser(user);
        labTech.setSpeciality(speciality);

        return labTechnicianRepository.save(labTech);
    }

    @Override
    @Transactional
    public LabTechnician update(Long id, LabTechnician labTechnician) {
        LabTechnician existing = labTechnicianRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Técnico de laboratorio no encontrado: " + id));        
        return labTechnicianRepository.save(existing);
    }

    @Override
    public LabTechnician getById(Long id) {
        return labTechnicianRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Técnico de laboratorio no encontrado: " + id));
    }

    @Override
    public List<LabTechnician> getAll() {
        return labTechnicianRepository.findAll();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!labTechnicianRepository.existsById(id)) {
            throw new EntityNotFoundException("No existe el técnico de laboratorio con id: " + id);
        }
        labTechnicianRepository.deleteById(id);
    }
}
