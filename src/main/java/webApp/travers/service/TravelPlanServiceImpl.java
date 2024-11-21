package webApp.travers.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webApp.travers.domain.TravelPlan;
import webApp.travers.domain.User;
import webApp.travers.repository.TravelPlanRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TravelPlanServiceImpl implements TravelPlanService{
    private final TravelPlanRepository travelPlanRepository;

    @Autowired
    public TravelPlanServiceImpl(TravelPlanRepository travelPlanRepository) {
        this.travelPlanRepository = travelPlanRepository;
    }

    @Override
    public TravelPlan createTravelPlan(TravelPlan travelPlan) {
        return travelPlanRepository.save(travelPlan);
    }

    @Override
    public List<TravelPlan> getTravelPlansByUser(Long userId) {
        return travelPlanRepository.findByUserId(userId);
    }
    @Override
    public Optional<TravelPlan> getTravelPlanById(Long id) {
        return travelPlanRepository.findById(id);
    }
    @Override
    public void deleteTravelPlan(Long id) {
        travelPlanRepository.deleteById(id);
    }
}
