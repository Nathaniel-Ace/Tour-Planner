package com.example.jpademo.service.impl;

import com.example.jpademo.persistence.entities.TourEntity;
import com.example.jpademo.persistence.repositories.TourRepository;
import com.example.jpademo.service.TourService;
import com.example.jpademo.service.dtos.TourDto;
import com.example.jpademo.service.mapper.TourMapper;
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
        TourEntity entity = TourEntity.builder()
                .name(tourDto.getName())
                .description(tourDto.getDescription())
                .from_location(tourDto.getFrom_location())
                .to_location(tourDto.getTo_location())
                .transport_type(tourDto.getTransport_type())
                .distance(tourDto.getDistance())
                .time(tourDto.getTime())
                .route_info(tourDto.getRoute_info())
                .build();
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

}
