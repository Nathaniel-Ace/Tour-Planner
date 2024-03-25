package com.example.jpademo.service;

import com.example.jpademo.service.dtos.TourLogDto;

import java.util.List;

public interface TourLogService {

    void saveNewTourLog(TourLogDto tourLogDto);

    List<TourLogDto> getTourLogsByTourId(Long tourId);

    List<TourLogDto> getAllTourLogs();

}
