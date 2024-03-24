package com.example.jpademo.service.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TourDto {

        private Long id;
        private String name;
        private String description;
        private String from_location;
        private String to_location;
        private String transport_type;
        private float distance;
        private int time;
        private String route_info;

}
