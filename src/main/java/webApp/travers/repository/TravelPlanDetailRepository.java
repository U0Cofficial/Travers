package webApp.travers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webApp.travers.domain.TravelPlanDetail;

public interface TravelPlanDetailRepository extends JpaRepository<TravelPlanDetail, Long> {

}
