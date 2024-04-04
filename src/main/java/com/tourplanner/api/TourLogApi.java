package com.tourplanner.api;

import com.tourplanner.service.TourLogService;
import com.tourplanner.service.dtos.TourLogDto;
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
