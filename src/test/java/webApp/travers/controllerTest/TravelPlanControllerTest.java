package webApp.travers.controllerTest;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import webApp.travers.controller.TravelPlanController;
import webApp.travers.domain.TravelPlan;
import webApp.travers.domain.User;
import webApp.travers.service.TravelPlanService;

//import javax.servlet.http.HttpSession;

import java.time.LocalDate;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
public class TravelPlanControllerTest {
    @InjectMocks
    private TravelPlanController travelPlanController;

    @Mock
    private TravelPlanService travelPlanService;

    @Mock
    private HttpSession session;

    @Mock
    private Model model;

    private User user;
    private AutoCloseable closeable;
    @BeforeEach
    public void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        user = User.builder()
                .id(1L)
                .username("testuser")
                .password("password")
                .build();
    }

    @AfterEach
    public void tearDown() throws Exception {
        closeable.close(); // 자원 해제
    }

    @Test
    public void testShowTravelPlans() {
        when(session.getAttribute("loggedInUser")).thenReturn(user);

        TravelPlan travelPlan = TravelPlan.builder()
                .user(user)
                .title("Sample Trip")
                .description("Sample description")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(5))
                .build();

        when(travelPlanService.getTravelPlansByUser(user.getId())).thenReturn(Collections.singletonList(travelPlan));

        String viewName = travelPlanController.showTravelPlans(session, model);

        assertThat(viewName).isEqualTo("travelPlans");
        verify(travelPlanService, times(1)).getTravelPlansByUser(user.getId());
    }

    @Test
    public void testCreateTravelPlan() {
        when(session.getAttribute("loggedInUser")).thenReturn(user);

        String viewName = travelPlanController.createTravelPlan(
                "Trip Title", "Description", "2024-01-01", "2024-01-05", session);

        assertThat(viewName).isEqualTo("redirect:/plan-travel");
        verify(travelPlanService, times(1)).createTravelPlan(any(TravelPlan.class));
    }


}
