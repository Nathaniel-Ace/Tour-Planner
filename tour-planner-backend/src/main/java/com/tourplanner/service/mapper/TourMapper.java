package com.tourplanner.service.mapper;

import com.tourplanner.persistence.entities.TourEntity;
import com.tourplanner.service.dtos.TourDto;
import org.springframework.stereotype.Component;

@Component
public class TourMapper extends AbstractMapper<TourEntity, TourDto>{

    @Override
    public TourDto mapToDto(TourEntity source) {
        return TourDto.builder()
                .id(source.getTour_id())
                .name(source.getName())
                .description(source.getDescription())
                .from_location(source.getFrom_location())
                .to_location(source.getTo_location())
                .transport_type(source.getTransport_type())
                .startCoordinates(source.getStartCoordinates())
                .endCoordinates(source.getEndCoordinates())
                .build();
    }

    public TourEntity mapToEntity(TourDto dto) {
        return TourEntity.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .from_location(dto.getFrom_location())
                .to_location(dto.getTo_location())
                .transport_type(dto.getTransport_type())
                .startCoordinates(dto.getStartCoordinates())
                .endCoordinates(dto.getEndCoordinates())
                .build();
    }

}
