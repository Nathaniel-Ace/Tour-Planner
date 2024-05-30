package com.tourplanner.service.impl;

import com.tourplanner.service.MapService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class MapServiceImpl implements MapService {

    private static final String API_KEY = "5b3ce3597851110001cf6248eeafdff05740442d8d10be93cc3afdb4";

    @Override
    public String searchAddress(String text) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(
                "https://api.openrouteservice.org/geocode/search?boundary.country=AT&api_key=" + API_KEY + "&text=" + text,
                String.class);

        String coordinate = Objects.requireNonNull(response.getBody()).substring(response.getBody().indexOf("coordinates") + 14, response.getBody().indexOf("properties") - 4);
        System.out.println(response);
        return coordinate;
    }

    @Override
    public String searchDirection(String start, String end, String profile) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.openrouteservice.org/v2/directions/" + profile + "?api_key=" + API_KEY + "&start=" + start + "&end=" + end;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        // Return the entire response body
        return response.getBody();
    }

    @Override
    public List<String> autocompleteAddress(String text) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.openrouteservice.org/geocode/autocomplete?api_key=" + API_KEY + "&text=" + text;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        // Parse the response to extract the suggestions
        String responseBody = Objects.requireNonNull(response.getBody());
        List<String> suggestions = new ArrayList<>();
        int index = 0;

        while ((index = responseBody.indexOf("\"label\":\"", index)) != -1) {
            index += 9;
            int endIndex = responseBody.indexOf("\"", index);
            String suggestion = responseBody.substring(index, endIndex);
            suggestions.add(suggestion);
        }

        return suggestions;
    }
}
