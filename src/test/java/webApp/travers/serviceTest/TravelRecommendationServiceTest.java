package webApp.travers.serviceTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import webApp.travers.domain.TravelRecommendation;
import webApp.travers.repository.TravelRecommendationRepository;
import webApp.travers.service.TravelRecommendationService;
import webApp.travers.service.TravelRecommendationServiceImpl;

import java.io.Closeable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class TravelRecommendationServiceTest {
    @InjectMocks
    private TravelRecommendationService service;

    @Mock
    private TravelRecommendationRepository repository;

    private AutoCloseable closeable;

    @BeforeEach
    public void setUp() {
       closeable =  MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void tearDown() throws Exception {
        closeable.close(); // 자원 해제
    }


    @Test
    public void testGetRecommendations() {
        TravelRecommendation recommendation = TravelRecommendation.builder()
                .companionType("연인")
                .moodType("활동적인")
                .seasonType("봄")
                .title("제주도 여행")
                .description("봄에 연인과 제주도를 방문해보세요.")
                .build();

        when(repository.findByCompanionTypeAndMoodTypeAndSeasonType("연인", "활동적인", "봄"))
                .thenReturn(Collections.singletonList(recommendation));

        List<TravelRecommendation> results = service.getRecommendations("연인", "활동적인", "봄");

        assertThat(results).isNotEmpty();
        assertThat(results.get(0).getTitle()).isEqualTo("제주도 여행");
        verify(repository, times(1)).findByCompanionTypeAndMoodTypeAndSeasonType("연인", "활동적인", "봄");
    }
}
