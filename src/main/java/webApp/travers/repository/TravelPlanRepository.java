package webApp.travers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webApp.travers.domain.TravelPlan;
import webApp.travers.domain.User;

import java.util.List;

public interface TravelPlanRepository extends JpaRepository<TravelPlan, Long> {
    List<TravelPlan> findByUserId(Long userId);

}
