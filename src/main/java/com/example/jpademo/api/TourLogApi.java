package com.example.jpademo.api;

import com.example.jpademo.service.TourLogService;
import com.example.jpademo.service.dtos.TourLogDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "tourlog")
public class TourLogApi {

    @Autowired
    private TourLogService tourLogService;

    @GetMapping
    public List<TourLogDto> getAllTourLogs() {
        return tourLogService.getAllTourLogs();
    }

    @GetMapping("/tour/{tourId}")
    public List<TourLogDto> getLogsByTour(@PathVariable Long tourId) {
        return tourLogService.getTourLogsByTourId(tourId);
    }


    @PostMapping
    public void insertNewTourLog(@RequestBody TourLogDto tourLog) {
        tourLogService.saveNewTourLog(tourLog);
    }

}
