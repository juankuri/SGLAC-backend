package org.uv.SGLAC.services;
import org.uv.SGLAC.entities.User;
import java.util.List;

public interface UserService {
    User createUser(User user); 
    User getUser(Long id);
    List<User> getAllUsers();
    User updateUser(Long id, User user);
    void deleteUser(Long id);
    void sendVerificationCode(Long userId);
    boolean verifyCode(Long userId, String code);
}
