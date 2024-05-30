package com.tourplanner.service;

import java.util.List;

public interface MapService {

    String searchAddress(String text);
    String searchDirection(String start, String end, String profile);
    List<String> autocompleteAddress(String text);

}
