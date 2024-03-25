package com.example.jpademo.service.impl;

import com.example.jpademo.persistence.entities.TourEntity;
import com.example.jpademo.persistence.entities.TourLogEntity;
import com.example.jpademo.persistence.repositories.TourLogRepository;
import com.example.jpademo.persistence.repositories.TourRepository;
import com.example.jpademo.service.TourLogService;
import com.example.jpademo.service.dtos.TourLogDto;
import com.example.jpademo.service.mapper.TourLogMapper;
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
