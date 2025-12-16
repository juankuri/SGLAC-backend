package org.uv.SGLAC.services;
import org.uv.SGLAC.entities.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(User user); 
    User getUser(Long id);
    List<User> getAllUsers();
    User updateUser(Long id, User user);
    void deleteUser(Long id);
    void sendVerificationCode(Long userId);
    boolean verifyCode(Long userId, String code);
    Optional<User> findByEmail(String email);
    Optional<User> findById(Long id);
    boolean checkPassword(User user, String password);
}
