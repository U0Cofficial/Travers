package webApp.travers.repositoryTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import webApp.travers.domain.TravelPlan;
import webApp.travers.domain.User;
import webApp.travers.repository.TravelPlanRepository;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
public class TravelPlanRepositoryTest {
    @Autowired
    private TravelPlanRepository travelPlanRepository;

    private User user;

    @BeforeEach
    public void setUp() {
        user = User.builder()
                .username("testuser")
                .password("password")
                .build();
    }

    @Test
    public void testSaveAndFindByUserId() {
        TravelPlan travelPlan = TravelPlan.builder()
                .user(user)
                .title("Test Trip")
                .description("A test trip")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(5))
                .build();

        travelPlanRepository.save(travelPlan);

        List<TravelPlan> travelPlans = travelPlanRepository.findByUserId(user.getId());
        assertThat(travelPlans).isNotEmpty();
        assertThat(travelPlans.get(0).getTitle()).isEqualTo("Test Trip");
    }
}
