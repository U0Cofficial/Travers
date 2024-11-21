package webApp.travers.repositoryTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import webApp.travers.domain.TravelRecord;
import webApp.travers.domain.User;
import webApp.travers.repository.TravelRecordRepository;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
@DataJpaTest
public class TravelRecordRepositoryTest {
    @Autowired
    private TravelRecordRepository repository;

    private User user;

    @BeforeEach
    public void setUp() {
        user = User.builder()
                .username("testuser")
                .password("password")
                .build();
    }

    @Test
    public void testFindByUserId() {
        TravelRecord record = TravelRecord.builder()
                .user(user)
                .title("Test Record")
                .description("Description")
                .imageUrl("/images/test.jpg")
                .build();

        repository.save(record);

        List<TravelRecord> records = repository.findByUserId(user.getId());
        assertThat(records).isNotEmpty();
        assertThat(records.get(0).getTitle()).isEqualTo("Test Record");
    }
}
