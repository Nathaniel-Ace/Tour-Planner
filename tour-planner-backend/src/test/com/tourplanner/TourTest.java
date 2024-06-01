package com.tourplanner;

import com.tourplanner.persistence.entities.TourEntity;
import com.tourplanner.persistence.repositories.TourRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TourTest {

    private TourRepository tourRepository;
    private TourEntity tour1;
    private TourEntity tour2;

    @BeforeEach
    public void setup() {
        // Mock the TourRepository
        tourRepository = Mockito.mock(TourRepository.class);

        // Create TourEntity objects
        tour1 = TourEntity.builder()
                .name("Tour 1")
                .description("Description 1")
                .from_location("Location 1")
                .to_location("Location 2")
                .transport_type("Bus")
                .build();

        tour2 = TourEntity.builder()
                .name("Tour 2")
                .description("Description 2")
                .from_location("Location 3")
                .to_location("Location 4")
                .transport_type("Train")
                .build();
    }

    @Test
    public void testSave() {
        // Call the save method on the mock
        tourRepository.save(tour1);

        // Verify that the save method was called with the correct TourEntity
        verify(tourRepository, times(1)).save(tour1);
    }

    @Test
    public void testCount() {
        // Set up the mock to return a count of 2
        when(tourRepository.count()).thenReturn(2L);

        // Call the count method and assert that it returns the expected count
        assertEquals(2L, tourRepository.count());
    }

    @Test
    public void testFindAll() {
        // Set up the mock to return a list of TourEntity objects
        when(tourRepository.findAll()).thenReturn(Arrays.asList(tour1, tour2));

        // Call the findAll method and assert that it returns the expected list
        List<TourEntity> tours = tourRepository.findAll();
        assertEquals(2, tours.size());
        assertEquals(tour1, tours.get(0));
        assertEquals(tour2, tours.get(1));
    }
}