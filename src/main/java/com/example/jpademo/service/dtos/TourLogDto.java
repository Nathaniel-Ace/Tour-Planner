package com.example.jpademo.service.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TourLogDto {

    private Long id;
    private Long tour;
    private String dateTime;
    private String comment;
    private String difficulty;
    private float totalDistance;
    private String totalTime;
    private float rating;

}
