package com.tourplanner.api;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "http://localhost:3002"})
@RestController
public class MapApi {

    private final com.tourplanner.service.MapApi mapApi;

    public MapApi(com.tourplanner.service.MapApi mapApi) {
        this.mapApi = mapApi;
    }

    @GetMapping("/searchAddress")
    public String searchAddress(@RequestParam String text) {
        return mapApi.searchAddress(text);
    }

    @GetMapping("/searchDirection")
    public List<double[]> searchDirection(@RequestParam String start, @RequestParam String end, @RequestParam String profile) {
        return mapApi.searchDirection(start, end, profile);
    }

    @GetMapping("/autocomplete")
    public List<String> autocompleteAddress(@RequestParam String text) {
        return mapApi.autocompleteAddress(text);
    }

}
