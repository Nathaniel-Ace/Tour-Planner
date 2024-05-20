package com.tourplanner.api;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3001")
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

}
