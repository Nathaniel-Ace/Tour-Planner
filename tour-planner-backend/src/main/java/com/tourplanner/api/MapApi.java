package com.tourplanner.api;

import com.tourplanner.service.MapService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "http://localhost:3002"})
@RestController
public class MapApi {

    private final MapService mapService;

    public MapApi(MapService mapService) {
        this.mapService = mapService;
    }

    @GetMapping("/searchAddress")
    public String searchAddress(@RequestParam String text) {
        return mapService.searchAddress(text);
    }

    @GetMapping("/searchDirection")
    public String searchDirection(@RequestParam String start, @RequestParam String end, @RequestParam String profile) {
        return mapService.searchDirection(start, end, profile);
    }

    @GetMapping("/autocomplete")
    public List<String> autocompleteAddress(@RequestParam String text) {
        return mapService.autocompleteAddress(text);
    }

}
