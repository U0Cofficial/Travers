package webApp.travers.serviceTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import webApp.travers.domain.TravelPlan;
import webApp.travers.domain.User;
import webApp.travers.repository.TravelPlanRepository;
import webApp.travers.service.TravelPlanService;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class TravelPlanServiceTest {
    @InjectMocks
    private TravelPlanService travelPlanService;

    @Mock
    private TravelPlanRepository travelPlanRepository;

    private User user;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        user = User.builder()
                .id(1L)
                .username("testuser")
                .password("password")
                .build();
    }

    @Test
    public void testGetTravelPlansByUser() {
        TravelPlan travelPlan = TravelPlan.builder()
                .user(user)
                .title("Test Trip")
                .description("A test trip")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(5))
                .build();

        when(travelPlanRepository.findByUserId(user.getId())).thenReturn(Collections.singletonList(travelPlan));

        List<TravelPlan> travelPlans = travelPlanService.getTravelPlansByUser(user.getId());

        assertThat(travelPlans).isNotEmpty();
        assertThat(travelPlans.get(0).getTitle()).isEqualTo("Test Trip");
        verify(travelPlanRepository, times(1)).findByUserId(user.getId());
    }

    @Test
    public void testCreateTravelPlan() {
        TravelPlan travelPlan = TravelPlan.builder()
                .user(user)
                .title("New Trip")
                .description("A new trip")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(5))
                .build();

        when(travelPlanRepository.save(travelPlan)).thenReturn(travelPlan);

        TravelPlan savedPlan = travelPlanService.createTravelPlan(travelPlan);

        assertThat(savedPlan).isNotNull();
        assertThat(savedPlan.getTitle()).isEqualTo("New Trip");
        verify(travelPlanRepository, times(1)).save(travelPlan);
    }
}
