package webApp.travers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webApp.travers.domain.TravelRecommendation;
import webApp.travers.repository.TravelRecommendationRepository;

import java.util.List;

@Service
public class TravelRecommendationServiceImpl implements TravelRecommendationService{
    private final TravelRecommendationRepository recommendationRepository;

    @Autowired
    public TravelRecommendationServiceImpl(TravelRecommendationRepository recommendationRepository) {
        this.recommendationRepository = recommendationRepository;
    }

    @Override
    public List<TravelRecommendation> getRecommendations(String companionType, String moodType, String seasonType) {
        return recommendationRepository.findByCompanionTypeAndMoodTypeAndSeasonType(
                companionType, moodType, seasonType);
    }
}
