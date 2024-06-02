package com.tourplanner;

import com.tourplanner.service.MapService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MapServiceTest {

    @Mock
    private MapService mapService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSearchAddress() {
        String expectedAddress = "123 Main St, Anytown, USA";

        when(mapService.searchAddress(anyString())).thenReturn(expectedAddress);

        String actualAddress = mapService.searchAddress("123 Main St");

        assertEquals(expectedAddress, actualAddress);
        verify(mapService, times(1)).searchAddress("123 Main St");
    }

    @Test
    public void testSearchDirection() {
        String expectedDirection = "Start at 123 Main St, go straight for 2 miles, turn left on Elm St.";

        when(mapService.searchDirection(anyString(), anyString(), anyString())).thenReturn(expectedDirection);

        String actualDirection = mapService.searchDirection("123 Main St", "456 Elm St", "driving");

        assertEquals(expectedDirection, actualDirection);
        verify(mapService, times(1)).searchDirection("123 Main St", "456 Elm St", "driving");
    }

    @Test
    public void testAutocompleteAddress() {
        List<String> expectedAddresses = Arrays.asList("123 Main St, Anytown, USA", "456 Elm St, Othertown, USA");

        when(mapService.autocompleteAddress(anyString())).thenReturn(expectedAddresses);

        List<String> actualAddresses = mapService.autocompleteAddress("123");

        assertEquals(expectedAddresses, actualAddresses);
        verify(mapService, times(1)).autocompleteAddress("123");
    }
}