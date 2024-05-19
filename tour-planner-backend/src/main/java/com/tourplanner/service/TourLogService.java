package com.tourplanner.service;

import com.tourplanner.service.dtos.TourLogDto;

import java.util.List;

public interface TourLogService {

    void saveNewTourLog(TourLogDto tourLogDto);

    List<TourLogDto> getTourLogsByTourId(Long tourId);

    List<TourLogDto> getAllTourLogs();

}