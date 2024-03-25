package com.example.jpademo.service;

import com.example.jpademo.service.dtos.TourDto;

import java.util.List;

public interface TourService {

    void saveNewTour(TourDto tourDto);
    List<TourDto> getAllTours();
    List<TourDto> getTourByName(String name);

}
