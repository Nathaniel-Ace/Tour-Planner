package com.example.jpademo;

import com.example.jpademo.persistence.entities.TourEntity;
import com.example.jpademo.persistence.entities.TourLogEntity;
import com.example.jpademo.persistence.repositories.TourLogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TourLogTest {

    private TourLogRepository tourLogRepository;
    private TourLogEntity tourLog1;
    private TourLogEntity tourLog2;

    @BeforeEach
    public void setup() {
        // Mock the TourLogRepository
        tourLogRepository = Mockito.mock(TourLogRepository.class);

        // Create TourLogEntity objects
        tourLog1 = TourLogEntity.builder()
                .tour(new TourEntity()) // replace with actual TourEntity
                .dateTime(LocalDateTime.now())
                .comment("Comment 1")
                .difficulty("Easy")
                .totalDistance(100.0f)
                .totalTime("2 hours")
                .rating(4.0f)
                .build();

        tourLog2 = TourLogEntity.builder()
                .tour(new TourEntity()) // replace with actual TourEntity
                .dateTime(LocalDateTime.now())
                .comment("Comment 2")
                .difficulty("Hard")
                .totalDistance(200.0f)
                .totalTime("3 hours")
                .rating(5.0f)
                .build();
    }

    @Test
    public void testSave() {
        // Call the save method on the mock
        tourLogRepository.save(tourLog1);

        // Verify that the save method was called with the correct TourLogEntity
        verify(tourLogRepository, times(1)).save(tourLog1);
    }

    @Test
    public void testCount() {
        // Set up the mock to return a count of 2
        when(tourLogRepository.count()).thenReturn(2L);

        // Call the count method and assert that it returns the expected count
        assertEquals(2L, tourLogRepository.count());
    }

    @Test
    public void testFindAll() {
        // Set up the mock to return a list of TourLogEntity objects
        when(tourLogRepository.findAll()).thenReturn(Arrays.asList(tourLog1, tourLog2));

        // Call the findAll method and assert that it returns the expected list
        List<TourLogEntity> tourLogs = tourLogRepository.findAll();
        assertEquals(2, tourLogs.size());
        assertEquals(tourLog1, tourLogs.get(0));
        assertEquals(tourLog2, tourLogs.get(1));
    }
}