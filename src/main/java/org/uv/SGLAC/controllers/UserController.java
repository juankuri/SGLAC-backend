package org.uv.SGLAC.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.uv.SGLAC.entities.User;
import org.uv.SGLAC.services.UserService;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public User create(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @GetMapping
    public List<User> getAll() {
        return userService.getAllUsers();
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> put(@RequestBody User user, @PathVariable Long id) {
        Optional<User> optUser = userService.findById(id);

        if (optUser.isPresent()) {
            User updUser = optUser.get();
            updUser.setNames(user.getNames());
            updUser.setLastname(user.getLastname());
            updUser.setUsername(user.getUsername());
            updUser.setEmail(user.getEmail());
            updUser.setPassword(user.getPassword());
            updUser.setPhoneNumber(user.getPhoneNumber());
            updUser.setDateOfBirth(user.getDateOfBirth());

            userService.createUser(updUser);
            return ResponseEntity.ok(updUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @PostMapping("/{id}/send-code")
    public void sendVerificationCode(@PathVariable Long id) {
        userService.sendVerificationCode(id);
    }

    @PostMapping("/{id}/verify-code")
    public boolean verifyCode(@PathVariable Long id, @RequestParam String code) {
        return userService.verifyCode(id, code);
    }
}
