package webApp.travers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webApp.travers.domain.TravelRecord;
import webApp.travers.domain.User;

import java.util.List;

public interface TravelRecordRepository extends JpaRepository<TravelRecord,Long> {
    List<TravelRecord> findByUserId(Long userId);
}
