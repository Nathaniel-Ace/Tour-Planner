package com.tourplanner.service.impl;

import com.tourplanner.persistence.entities.TourEntity;
import com.tourplanner.persistence.repositories.TourRepository;
import com.tourplanner.service.TourService;
import com.tourplanner.service.dtos.TourDto;
import com.tourplanner.service.mapper.TourMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TourServiceImpl implements TourService {

    @Autowired
    private TourRepository tourRepository;

    @Autowired
    private TourMapper tourMapper;

    @Override
    public void saveNewTour(TourDto tourDto) {
        TourEntity entity = tourMapper.mapToEntity(tourDto);
        tourRepository.save(entity);
    }

    @Override
    public List<TourDto> getAllTours() {
        return tourMapper.mapToDto(tourRepository.findAll());
    }

    @Override
    public List<TourDto> getTourByName(String name) {
        return tourMapper.mapToDto(tourRepository.findByNameIgnoreCase(name));
    }

    @Override
    public void deleteTour(Long id) {
        tourRepository.deleteById(id);
    }

    @Override
    public void updateTour(Long id, TourDto tourDto) {
        TourEntity entity = tourRepository.findById(id).orElseThrow(() -> new RuntimeException("Tour not found"));
        TourEntity updatedEntity = tourMapper.mapToEntity(tourDto);
        updatedEntity.setTour_id(entity.getTour_id());
        tourRepository.save(updatedEntity);
    }

}
