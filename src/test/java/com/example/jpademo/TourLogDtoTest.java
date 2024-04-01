package com.example.jpademo;

import com.example.jpademo.service.dtos.TourLogDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TourLogDtoTest {

    private TourLogDto tourLogDto;

    @BeforeEach
    public void init() {
        tourLogDto = new TourLogDto();
    }

    @Test
    public void testId() {
        Long id = 1L;
        tourLogDto.setId(id);
        assertEquals(id, tourLogDto.getId());
    }

    @Test
    public void testTour() {
        Long tour = 1L;
        tourLogDto.setTour(tour);
        assertEquals(tour, tourLogDto.getTour());
    }

    @Test
    public void testDateTime() {
        String dateTime = "2022-01-01T00:00:00";
        tourLogDto.setDateTime(dateTime);
        assertEquals(dateTime, tourLogDto.getDateTime());
    }

    @Test
    public void testComment() {
        String comment = "Test Comment";
        tourLogDto.setComment(comment);
        assertEquals(comment, tourLogDto.getComment());
    }

    @Test
    public void testDifficulty() {
        String difficulty = "Test Difficulty";
        tourLogDto.setDifficulty(difficulty);
        assertEquals(difficulty, tourLogDto.getDifficulty());
    }

    @Test
    public void testTotalDistance() {
        float totalDistance = 100.0f;
        tourLogDto.setTotalDistance(totalDistance);
        assertEquals(totalDistance, tourLogDto.getTotalDistance());
    }

    @Test
    public void testTotalTime() {
        String totalTime = "2 hours";
        tourLogDto.setTotalTime(totalTime);
        assertEquals(totalTime, tourLogDto.getTotalTime());
    }

    @Test
    public void testRating() {
        float rating = 4.5f;
        tourLogDto.setRating(rating);
        assertEquals(rating, tourLogDto.getRating());
    }
}