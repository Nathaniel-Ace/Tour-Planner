package com.tourplanner.service.impl;

import com.tourplanner.service.MapApi;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Component
public class MapApiImpl implements MapApi {

    private static final String API_KEY = "5b3ce3597851110001cf6248eeafdff05740442d8d10be93cc3afdb4";

    public String searchAddress(String text) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(
                "https://api.openrouteservice.org/geocode/search?boundary.country=AT&api_key=" + API_KEY + "&text=" + text,
                String.class);

        String coordinate = Objects.requireNonNull(response.getBody()).substring(response.getBody().indexOf("coordinates") + 14, response.getBody().indexOf("properties") - 4);
        System.out.println(response);
        return coordinate;
    }

    public List<double[]> searchDirection(String start, String end) {
        return null;
    }

    public void getMap() {
    }

}
