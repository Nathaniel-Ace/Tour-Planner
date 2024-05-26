package com.tourplanner.api;

import com.tourplanner.service.TourLogService;
import com.tourplanner.service.dtos.TourLogDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "http://localhost:3002"})
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

    @GetMapping("/{id}")
    public TourLogDto getLogById(@PathVariable Long id) {
        return tourLogService.getLogById(id);
    }

    @PostMapping
    public void insertNewTourLog(@RequestBody TourLogDto tourLog) {
        tourLogService.saveNewTourLog(tourLog);
    }

    @DeleteMapping("/{id}")
    public void deleteTourLog(@PathVariable Long id) {
        tourLogService.deleteTourLog(id);
    }

    @PutMapping("/{id}")
    public void updateTourLog(@PathVariable Long id, @RequestBody TourLogDto tourLogDto) {
        tourLogService.updateTourLog(id, tourLogDto);
    }

}
