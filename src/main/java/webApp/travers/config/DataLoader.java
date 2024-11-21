package webApp.travers.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import webApp.travers.domain.TravelRecommendation;
import webApp.travers.repository.TravelRecommendationRepository;

import java.util.Arrays;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {
    private final TravelRecommendationRepository travelRecommendationRepository;

    @Autowired
    public DataLoader(TravelRecommendationRepository travelRecommendationRepository) {
        this.travelRecommendationRepository = travelRecommendationRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        List<TravelRecommendation> recommendations = Arrays.asList(
                TravelRecommendation.builder()
                        .companionType("연인")
                        .moodType("활동적인")
                        .seasonType("봄")
                        .title("제주도 오름 트레킹")
                        .description("제주도의 오름을 오르며 활기찬 봄 여행을 즐기세요.")
                        .imageUrl("/images/jeju_trekking.jpg")
                        .build(),
                TravelRecommendation.builder()
                        .companionType("친구")
                        .moodType("여유로운")
                        .seasonType("여름")
                        .title("부산 해운대 해수욕장")
                        .description("여름의 더위를 식히며 친구들과 해변에서 여유로운 시간을 보내세요.")
                        .imageUrl("/images/busan_beach.jpg")
                        .build(),
                TravelRecommendation.builder()
                        .companionType("가족")
                        .moodType("특별한 경험")
                        .seasonType("겨울")
                        .title("강원도 눈꽃축제")
                        .description("겨울철 가족과 함께하는 눈꽃축제에서 특별한 추억을 만들어 보세요.")
                        .imageUrl("/images/snow_festival.jpg")
                        .build()
        );

        travelRecommendationRepository.saveAll(recommendations);
    }
}
