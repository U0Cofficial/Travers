package webApp.travers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webApp.travers.domain.TravelRecommendation;

import java.util.List;

public interface TravelRecommendationRepository extends JpaRepository<TravelRecommendation, Long> {
    List<TravelRecommendation> findByCompanionTypeAndMoodTypeAndSeasonType(String companionType, String moodType, String seasonType);
}

