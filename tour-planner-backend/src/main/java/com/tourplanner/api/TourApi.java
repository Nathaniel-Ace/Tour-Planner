package com.tourplanner.api;

import com.tourplanner.service.TourLogService;
import com.tourplanner.service.TourService;
import com.tourplanner.service.dtos.TourDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "http://localhost:3002"})
@RestController
@RequestMapping(path = "tour")
public class TourApi {

    @Autowired
    private TourService tourService;

    @Autowired
    private TourLogService tourLogService;

    @GetMapping
    public List<TourDto> getAllTours() {
        return tourService.getAllTours();
    }

    @GetMapping("/name/{name}")
    public List<TourDto> getTourByName(@PathVariable String name) {
        return tourService.getTourByName(name);
    }

    @GetMapping("/{id}")
    public TourDto getTourById(@PathVariable Long id) {
        return tourService.getTourById(id);
    }

    @PostMapping
    public void insertNewTour(@RequestBody TourDto tour) {
        tourService.saveNewTour(tour);
    }

    @DeleteMapping("/{id}")
    public void deleteTour(@PathVariable Long id) {
        tourLogService.deleteByTour(id); // Delete all tour logs associated with the tour
        tourService.deleteTour(id); // Delete the tour
    }

    @PutMapping("/{id}")
    public void updateTour(@PathVariable Long id, @RequestBody TourDto tourDto) {
        tourService.updateTour(id, tourDto);
    }

}
