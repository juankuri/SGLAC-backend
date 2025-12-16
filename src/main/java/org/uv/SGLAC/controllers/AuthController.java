package org.uv.SGLAC.controllers;


import org.uv.SGLAC.entities.User;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.uv.SGLAC.services.OTPCodeService;
import org.uv.SGLAC.services.UserService;

// @CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final OTPCodeService otpService;

    public AuthController(UserService userService, OTPCodeService otpService) {
        this.userService = userService;
        this.otpService = otpService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {
        System.out.println("email: " + email + ", password: " + password);

        Optional<User> user = userService.findByEmail(email);
        if (!user.isPresent() || !userService.checkPassword(user.get(), password)) {
            System.out.println("Invalid credentials for email: " + email);
            return ResponseEntity.status(401).body("Credenciales inválidas");
        }

         System.out.println("User found: " + user.get().getEmail());
    
        boolean passwordMatch = userService.checkPassword(user.get(), password);
        System.out.println("Password match: " + passwordMatch);
        
        if (!passwordMatch) {
            System.out.println("Password incorrect");
            return ResponseEntity.status(401).body("Incorrect email or password");
        }

        otpService.generateOTP(user.get());

        return ResponseEntity.ok("Verifique con el código OTP enviado a su correo.");
    }

    @PostMapping("/validate")
    public ResponseEntity<?> validateOTP(@RequestParam String email, @RequestParam String code) {
        Optional<User> opt = userService.findByEmail(email);
        if (!opt.isPresent()) {
            return ResponseEntity.status(404).body("Usuario no encontrado");
        }

    User user = opt.get();

    boolean valid = otpService.validateOTP(user, code);        if (!valid) {
        return ResponseEntity
                .status(401)
                .body("Código OTP inválido o expirado");
    }

    AuthResponseDTO response = new AuthResponseDTO(
        user.getId().toString(),
        user.getUsername(),
        user.getNames(),
        user.getLastname(),
        user.getRole().getName()
    );

    return ResponseEntity.ok(response);
    }
}
