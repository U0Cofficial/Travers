package webApp.travers.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import webApp.travers.domain.User;
import webApp.travers.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public User registerUser(String username, String password) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new IllegalStateException("Username already exists");
        }
        User user = User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .createdAt(LocalDateTime.now())
                .roles("ROLE_USER")
                .build();
        return userRepository.save(user);
    }
    @Override
    public Optional<User> authenticate(String username, String password) {
        return userRepository.findByUsername(username)
                .filter(user -> passwordEncoder.matches(password, user.getPassword()));
    }
}
