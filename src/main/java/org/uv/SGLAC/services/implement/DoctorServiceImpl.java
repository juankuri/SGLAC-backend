package org.uv.SGLAC.services.implement;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;

import org.uv.SGLAC.dtos.DoctorCreateDTO;
import org.uv.SGLAC.entities.Doctor;
import org.uv.SGLAC.entities.Role;
import org.uv.SGLAC.entities.Speciality;
import org.uv.SGLAC.entities.User;

import org.uv.SGLAC.repositories.DoctorRepository;
import org.uv.SGLAC.repositories.RoleRepository;
import org.uv.SGLAC.repositories.SpecialityRepository;
import org.uv.SGLAC.repositories.UserRepository;
import org.uv.SGLAC.services.DoctorService;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final SpecialityRepository specialityRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public DoctorServiceImpl(
            DoctorRepository doctorRepository,
            UserRepository userRepository,
            RoleRepository roleRepository,
            SpecialityRepository specialityRepository
    ) {
        this.doctorRepository = doctorRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.specialityRepository = specialityRepository;
    }

    @Override
    @Transactional
    public Doctor create(DoctorCreateDTO dto) {

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

        if (doctorRepository.findByUserId(user.getId()).isPresent()) {
            throw new RuntimeException("This user is already registered as a doctor");
        }

        Doctor doctor = new Doctor();
        doctor.setUser(user);
        doctor.setSpeciality(speciality);
        doctor.setProfessionalId(dto.professionalId());

        return doctorRepository.save(doctor);
    }

  @Override
  @Transactional
  public Doctor update(Long id, Doctor doctor) {
      Doctor existing = doctorRepository.findById(id)
              .orElseThrow(() -> new EntityNotFoundException("Doctor no encontrado"));

      if (doctor.getUser() != null && doctor.getUser().getId() != null) {
          Long userId = doctor.getUser().getId();
          User user = userRepository.findById(userId)
                  .orElseThrow(() -> new EntityNotFoundException("User no encontrado"));
          existing.setUser(user);
      }

      if (doctor.getSpeciality() != null && doctor.getSpeciality().getId() != null) {
          Long specialityId = doctor.getSpeciality().getId();
          Speciality speciality = specialityRepository.findById(specialityId)
                  .orElseThrow(() -> new EntityNotFoundException("Speciality no encontrada"));
          existing.setSpeciality(speciality);
      }

      if (doctor.getProfessionalId() != null) {
          existing.setProfessionalId(doctor.getProfessionalId());
      }

      return doctorRepository.save(existing);
  }

    @Override
    public Doctor getById(Long id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found: " + id));
    }

    @Override
    public List<Doctor> getAll() {
        return doctorRepository.findAll();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!doctorRepository.existsById(id)) {
            throw new EntityNotFoundException("Doctor not found: " + id);
        }
        doctorRepository.deleteById(id);
    }
}
