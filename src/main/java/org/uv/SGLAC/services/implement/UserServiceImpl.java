package org.uv.SGLAC.services.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.uv.SGLAC.entities.*;
import org.uv.SGLAC.repositories.*;
import org.uv.SGLAC.services.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Random;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private OTPCodeRepository otpCodeRepository;
    @Autowired
    private EmailService emailService;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    private final java.util.regex.Pattern emailPattern = java.util.regex.Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    private final java.util.regex.Pattern phonePattern = java.util.regex.Pattern.compile("^\\d{10}$");
    private final java.util.regex.Pattern passwdPattern = java.util.regex.Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,15}$");

    @Override

    public User createUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email ya registrado");
        }
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Nombre de usuario ya registrado");
        }
        if (user.getPhoneNumber() != null && !phonePattern.matcher(user.getPhoneNumber().replaceAll("\\s", "")).matches()) {
            throw new RuntimeException("Teléfono inválido");
        }
        if (!emailPattern.matcher(user.getEmail()).matches()) {
            throw new RuntimeException("Email inválido");
        }
        if (!passwdPattern.matcher(user.getPassword()).matches()) {
            throw new RuntimeException("La contraseña no cumple requisitos");
        }
        if (user.getDateOfBirth() == null) {
            throw new RuntimeException("Fecha de nacimiento requerida");
        }
        int age = Period.between(user.getDateOfBirth(), LocalDate.now()).getYears();
        if (age < 18) {
            throw new RuntimeException("Usuario debe ser mayor de 18 años");
        }
        Role role;
        if (user.getRole() != null && user.getRole().getId() != null) {
            role = roleRepository.findById(user.getRole().getId())
                    .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
        } else {
            role = roleRepository.findByName("USER")
                    .orElseThrow(() -> new RuntimeException("Rol USER no existe en BD"));
        }
        user.setRole(role);
        user.setPassword(encoder.encode(user.getPassword()));
        User saved = userRepository.save(user);
        sendVerificationCode(saved.getId());
        return saved;
    }

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(Long id, User u) {
        User s = getUser(id);
        if (u.getUsername() != null && !u.getUsername().equals(s.getUsername()) && userRepository.findByUsername(u.getUsername()).isPresent()) {
            throw new RuntimeException("Nombre de usuario ya registrado");
        }
        if (u.getEmail() != null && !u.getEmail().equals(s.getEmail()) && userRepository.findByEmail(u.getEmail()).isPresent()) {
            throw new RuntimeException("Email ya registrado");
        }
        s.setNames(u.getNames() != null ? u.getNames() : s.getNames());
        s.setLastname(u.getLastname() != null ? u.getLastname() : s.getLastname());
        s.setUsername(u.getUsername() != null ? u.getUsername() : s.getUsername());
        s.setEmail(u.getEmail() != null ? u.getEmail() : s.getEmail());
        if (u.getPassword() != null) {
            if (!passwdPattern.matcher(u.getPassword()).matches()) {
                throw new RuntimeException("Contraseña no válida");
            }
            s.setPassword(encoder.encode(u.getPassword()));
        }
        s.setPhoneNumber(u.getPhoneNumber() != null ? u.getPhoneNumber() : s.getPhoneNumber());
        s.setAddress(u.getAddress() != null ? u.getAddress() : s.getAddress());
        s.setSex(u.getSex() != null ? u.getSex() : s.getSex());
        s.setDateOfBirth(u.getDateOfBirth() != null ? u.getDateOfBirth() : s.getDateOfBirth());
        if (u.getRole() != null && u.getRole().getId() != null) {
            Role role = roleRepository.findById(u.getRole().getId()).orElseThrow(() -> new RuntimeException("Rol no encontrado"));
            s.setRole(role);
        }
        return userRepository.save(s);
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado");
        }
        userRepository.deleteById(id);
    }

    @Override
    public void sendVerificationCode(Long userId) {
        User u = getUser(userId);
        // se crea o se actualiza el otp
        OTPCode otp = otpCodeRepository.findByUserId(u.getId()).orElse(new OTPCode());
        int code = new Random().nextInt(900000) + 100000;
        otp.setUser(u);
        otp.setCode(String.valueOf(code));
        otp.setCreation_date(java.time.LocalDateTime.now());
        otp.setExp_date(java.time.LocalDateTime.now().plusMinutes(15));
        otp.setUsed(false);
        otpCodeRepository.save(otp);
        emailService.sendVerificationCode(u.getEmail(), otp.getCode());
    }

    @Override
    public boolean verifyCode(Long userId, String code) {
        OTPCode otp = otpCodeRepository.findByUserId(userId).orElseThrow(() -> new RuntimeException("Código no encontrado"));
        if (otp.isUsed()) {
            throw new RuntimeException("Código ya usado");
        }
        if (otp.getExp_date().isBefore(java.time.LocalDateTime.now())) {
            otpCodeRepository.delete(otp);
            throw new RuntimeException("Código expirado");
        }
        if (!otp.getCode().equals(code)) {
            return false;
        }
        otp.setUsed(true);
        otpCodeRepository.save(otp);
        return true;
    }
}
