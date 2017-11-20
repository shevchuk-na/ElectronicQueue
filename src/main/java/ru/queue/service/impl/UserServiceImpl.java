package ru.queue.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.queue.domain.User;
import ru.queue.domain.security.PasswordResetToken;
import ru.queue.repository.UserRepository;
import ru.queue.repository.PasswordResetTokenRepository;
import ru.queue.service.UserService;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Override
    public PasswordResetToken getPasswordResetToken(final String token) {
        return passwordResetTokenRepository.findByToken(token);
    }

    @Override
    public void createPasswordResetTokenForUser(final User user, final String token) {
        final PasswordResetToken myToken = new PasswordResetToken(token, user);
        passwordResetTokenRepository.save(myToken);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User findByUsername(String email) {
        return userRepository.findByUsername(email);
    }

    @Override
    @Transactional
    public User createUser(User user) throws Exception {
    User localUser = userRepository.findByUsername(user.getUsername());
    if(localUser != null){
        LOG.info("User already exists. Nothing will be done", user.getUsername());
    } else {
        localUser = userRepository.save(user);
    }
        return localUser;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}
