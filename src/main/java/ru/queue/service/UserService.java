package ru.queue.service;

import ru.queue.domain.User;
import ru.queue.domain.security.PasswordResetToken;

import java.util.Set;

public interface UserService {

    PasswordResetToken getPasswordResetToken(final String token);

    void createPasswordResetTokenForUser(final User user, final String token);

    User findById(Long id);

    User findByUsername(String username);

    User createUser(User user) throws Exception;

    User save(User user);

}
