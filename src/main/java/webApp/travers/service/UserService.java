package webApp.travers.service;

import webApp.travers.domain.User;

import java.util.Optional;

public interface UserService {
    User registerUser(String username, String password);
    Optional<User> authenticate(String username, String password);
}
