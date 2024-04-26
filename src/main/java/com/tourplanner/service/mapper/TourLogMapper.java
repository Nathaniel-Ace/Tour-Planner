package com.tourplanner.service.mapper;

import com.tourplanner.persistence.entities.TourEntity;
import com.tourplanner.persistence.entities.TourLogEntity;
import com.tourplanner.persistence.repositories.TourRepository;
import com.tourplanner.service.dtos.TourLogDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TourLogMapper extends AbstractMapper<TourLogEntity, TourLogDto>{

    public TourLogMapper() {}

    @Autowired
    private TourRepository tourRepository;

    @Override
    public TourLogDto mapToDto(TourLogEntity source) {
        return TourLogDto.builder()
                .id(source.getTour_log_id())
                .tour(source.getTour().getTour_id())
                .dateTime(source.getDateTime().toString())
                .comment(source.getComment())
                .difficulty(source.getDifficulty())
                .totalDistance(source.getTotalDistance())
                .totalTime(source.getTotalTime())
                .rating(source.getRating())
                .build();
    }


    public TourLogEntity mapToEntity(TourLogDto dto) {

        TourEntity tourEntity = tourRepository.findById(dto.getTour())
                .orElseThrow(() -> new RuntimeException("Tour not found with id " + dto.getTour()));

        return TourLogEntity.builder()
                .tour_log_id(dto.getId())
                .tour(tourEntity)
                .dateTime(LocalDateTime.parse(dto.getDateTime()))
                .comment(dto.getComment())
                .difficulty(dto.getDifficulty())
                .totalDistance(dto.getTotalDistance())
                .totalTime(dto.getTotalTime())
                .rating(dto.getRating())
                .build();
    }
}
