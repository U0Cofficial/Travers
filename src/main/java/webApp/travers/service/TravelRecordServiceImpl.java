package webApp.travers.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webApp.travers.domain.TravelRecord;
import webApp.travers.domain.User;
import webApp.travers.repository.TravelRecordRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TravelRecordServiceImpl implements TravelRecordService{
    private final TravelRecordRepository travelRecordRepository;

    @Autowired
    public TravelRecordServiceImpl(TravelRecordRepository travelRecordRepository) {
        this.travelRecordRepository = travelRecordRepository;
    }

    @Override
    public TravelRecord createTravelRecord(TravelRecord travelRecord) {
        return travelRecordRepository.save(travelRecord);
    }

    @Override
    public List<TravelRecord> getTravelRecordsByUser(Long userId) {
        return travelRecordRepository.findByUserId(userId);
    }
    @Override
    public Optional<TravelRecord> getTravelRecordById(Long id) {
        return travelRecordRepository.findById(id);
    }
    @Override
    public void deleteTravelRecord(Long id) {
        travelRecordRepository.deleteById(id);
    }
}
