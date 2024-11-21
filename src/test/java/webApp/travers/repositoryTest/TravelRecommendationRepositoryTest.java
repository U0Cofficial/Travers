package webApp.travers.repositoryTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import webApp.travers.domain.TravelRecommendation;
import webApp.travers.repository.TravelRecommendationRepository;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
@DataJpaTest
public class TravelRecommendationRepositoryTest {
    @Autowired
    private TravelRecommendationRepository repository;

    @Test
    public void testFindByCompanionTypeAndMoodTypeAndSeasonType() {
        TravelRecommendation recommendation = TravelRecommendation.builder()
                .companionType("연인")
                .moodType("활동적인")
                .seasonType("봄")
                .title("제주도 여행")
                .description("봄에 연인과 제주도를 방문해보세요.")
                .imageUrl("/images/jeju.jpg")
                .build();

        repository.save(recommendation);

        List<TravelRecommendation> results = repository.findByCompanionTypeAndMoodTypeAndSeasonType(
                "연인", "활동적인", "봄");
        assertThat(results).isNotEmpty();
        assertThat(results.get(0).getTitle()).isEqualTo("제주도 여행");
    }
}
