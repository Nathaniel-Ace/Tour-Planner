package com.tourplanner.service;

import com.tourplanner.service.dtos.TourDto;

import java.util.List;

public interface TourService {

    void saveNewTour(TourDto tourDto);
    TourDto getTourById(Long id);
    List<TourDto> getAllTours();
    List<TourDto> getTourByName(String name);
    void deleteTour(Long id);
    void updateTour(Long id, TourDto tourDto);

}
