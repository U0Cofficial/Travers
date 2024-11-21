package webApp.travers.service;

import webApp.travers.domain.TravelPlan;
import webApp.travers.domain.User;

import java.util.List;
import java.util.Optional;

public interface TravelPlanService {
    TravelPlan createTravelPlan(TravelPlan travelPlan);
    List<TravelPlan> getTravelPlansByUser(Long userId);
    Optional<TravelPlan> getTravelPlanById(Long id);
    void deleteTravelPlan(Long id);
}
