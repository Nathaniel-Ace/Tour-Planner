package com.example.jpademo.api;

import com.example.jpademo.service.TourService;
import com.example.jpademo.service.dtos.TourDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "tour")
public class TourApi {

    @Autowired
    private TourService tourService;

    @GetMapping
    public List<TourDto> getAllTours() {
        return tourService.getAllTours();
    }

    @GetMapping("/name/{name}")
    public List<TourDto> getTourByName(@PathVariable String name) {
        return tourService.getTourByName(name);
    }

    @PostMapping
    public void insertNewTour(@RequestBody TourDto tour) {
        tourService.saveNewTour(tour);
    }


}
