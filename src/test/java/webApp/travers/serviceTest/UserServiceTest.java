package webApp.travers.serviceTest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
import webApp.travers.domain.User;
import webApp.travers.repository.UserRepository;
import webApp.travers.service.UserService;
import webApp.travers.service.UserServiceImpl;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class UserServiceTest {
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private AutoCloseable closeable;

    @BeforeEach
    public void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void tearDown() throws Exception {
        closeable.close(); // 자원 해제
    }

    @Test
    public void testRegisterUser() {
        User user = User.builder()
                .username("testuser")
                .password("password")
                .build();

        when(userRepository.findByUsername("testuser")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);

        User registeredUser = userService.registerUser("testuser", "password");

        assertThat(registeredUser).isNotNull();
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testAuthenticate() {
        User user = User.builder()
                .username("testuser")
                .password("encodedPassword")
                .build();

        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("password", "encodedPassword")).thenReturn(true);

        Optional<User> authenticatedUser = userService.authenticate("testuser", "password");

        assertThat(authenticatedUser).isPresent();
        assertThat(authenticatedUser.get().getUsername()).isEqualTo("testuser");
    }
}
