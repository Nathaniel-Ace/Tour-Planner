package com.tourplanner.service.impl;

import com.tourplanner.persistence.entities.TourEntity;
import com.tourplanner.persistence.entities.TourLogEntity;
import com.tourplanner.persistence.repositories.TourLogRepository;
import com.tourplanner.persistence.repositories.TourRepository;
import com.tourplanner.service.TourLogService;
import com.tourplanner.service.dtos.TourLogDto;
import com.tourplanner.service.mapper.TourLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TourLogServiceImpl implements TourLogService {


    @Autowired
    private TourLogRepository tourLogRepository;

    @Autowired
    private TourLogMapper tourLogMapper;

    @Autowired
    private TourRepository tourRepository;

    @Override
    public void saveNewTourLog(TourLogDto tourLogDto) {
        TourLogEntity entity = tourLogMapper.mapToEntity(tourLogDto);
        tourLogRepository.save(entity);
    }

    @Override
    public List<TourLogDto>  getTourLogsByTourId(Long tourId) {
        TourEntity tour = tourRepository.findById(tourId).orElseThrow(()-> new RuntimeException("Tour not found"));
        return tourLogMapper.mapToDto(tourLogRepository.findByTour(tour));
    }

    public List<TourLogDto> getAllTourLogs() {
        return tourLogMapper.mapToDto(tourLogRepository.findAll());
    }

}
