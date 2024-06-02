package com.tourplanner;

import com.tourplanner.persistence.entities.TourLogEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TourLogEntityTest {

    private TourLogEntity tourLogEntity;

    @BeforeEach
    public void setup() {
        tourLogEntity = new TourLogEntity();
    }

    @Test
    public void testTourLogId() {
        Long expectedId = 1L;
        tourLogEntity.setTour_log_id(expectedId);
        assertEquals(expectedId, tourLogEntity.getTour_log_id());
    }

    @Test
    public void testDateTime() {
        OffsetDateTime expectedDateTime = OffsetDateTime.now();
        tourLogEntity.setDateTime(expectedDateTime);
        assertEquals(expectedDateTime, tourLogEntity.getDateTime());
    }

    @Test
    public void testComment() {
        String expectedComment = "This is a test comment";
        tourLogEntity.setComment(expectedComment);
        assertEquals(expectedComment, tourLogEntity.getComment());
    }

    @Test
    public void testDifficulty() {
        String expectedDifficulty = "Hard";
        tourLogEntity.setDifficulty(expectedDifficulty);
        assertEquals(expectedDifficulty, tourLogEntity.getDifficulty());
    }

    @Test
    public void testTotalDistance() {
        float expectedTotalDistance = 100.0f;
        tourLogEntity.setTotalDistance(expectedTotalDistance);
        assertEquals(expectedTotalDistance, tourLogEntity.getTotalDistance());
    }

    @Test
    public void testTotalTime() {
        String expectedTotalTime = "1 hour";
        tourLogEntity.setTotalTime(expectedTotalTime);
        assertEquals(expectedTotalTime, tourLogEntity.getTotalTime());
    }

    @Test
    public void testRating() {
        float expectedRating = 4.5f;
        tourLogEntity.setRating(expectedRating);
        assertEquals(expectedRating, tourLogEntity.getRating());
    }
}