package com.example.jpademo.service.mapper;

import com.example.jpademo.persistence.entities.TourEntity;
import com.example.jpademo.service.dtos.TourDto;
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
                .distance(source.getDistance())
                .time(source.getTime())
                .route_info(source.getRoute_info())
                .build();
    }

    public TourEntity mapToEntity(TourDto dto) {
        return TourEntity.builder()
                .tour_id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .from_location(dto.getFrom_location())
                .to_location(dto.getTo_location())
                .transport_type(dto.getTransport_type())
                .distance(dto.getDistance())
                .time(dto.getTime())
                .route_info(dto.getRoute_info())
                .build();
    }

}
