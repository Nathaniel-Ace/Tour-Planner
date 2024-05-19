package com.tourplanner.service;

import com.tourplanner.service.dtos.TourDto;

import java.util.List;

public interface TourService {

    void saveNewTour(TourDto tourDto);
    List<TourDto> getAllTours();
    List<TourDto> getTourByName(String name);

}
