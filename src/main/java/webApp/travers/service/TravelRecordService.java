package webApp.travers.service;

import webApp.travers.domain.TravelRecord;
import webApp.travers.domain.User;

import java.util.List;
import java.util.Optional;

public interface TravelRecordService {
    TravelRecord createTravelRecord(TravelRecord travelRecord);
    List<TravelRecord> getTravelRecordsByUser(Long userId);
    Optional<TravelRecord> getTravelRecordById(Long id);
    void deleteTravelRecord(Long id);
}
