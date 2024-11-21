package webApp.travers.service;

import webApp.travers.domain.TravelRecommendation;

import java.util.List;

public interface TravelRecommendationService {
    List<TravelRecommendation> getRecommendations(String companionType, String moodType, String seasonType);
}
