package webApp.travers.repositoryTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import webApp.travers.domain.User;
import webApp.travers.repository.UserRepository;

import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository repository;

    @Test
    public void testFindByUsername() {
        User user = User.builder()
                .username("testuser")
                .password("password")
                .build();

        repository.save(user);

        Optional<User> foundUser = repository.findByUsername("testuser");
        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getUsername()).isEqualTo("testuser");
    }
}
