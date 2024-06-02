package com.tourplanner;

import com.tourplanner.persistence.entities.TourEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TourEntityTest {

    private TourEntity tourEntity;

    @BeforeEach
    public void setup() {
        tourEntity = new TourEntity();
    }

    @Test
    public void testTourId() {
        Long expectedId = 1L;
        tourEntity.setTour_id(expectedId);
        assertEquals(expectedId, tourEntity.getTour_id());
    }

    @Test
    public void testName() {
        String expectedName = "Test Tour";
        tourEntity.setName(expectedName);
        assertEquals(expectedName, tourEntity.getName());
    }

    @Test
    public void testDescription() {
        String expectedDescription = "This is a test tour";
        tourEntity.setDescription(expectedDescription);
        assertEquals(expectedDescription, tourEntity.getDescription());
    }

    @Test
    public void testFromLocation() {
        String expectedFromLocation = "Start Location";
        tourEntity.setFrom_location(expectedFromLocation);
        assertEquals(expectedFromLocation, tourEntity.getFrom_location());
    }

    @Test
    public void testToLocation() {
        String expectedToLocation = "End Location";
        tourEntity.setTo_location(expectedToLocation);
        assertEquals(expectedToLocation, tourEntity.getTo_location());
    }

    @Test
    public void testTransportType() {
        String expectedTransportType = "Car";
        tourEntity.setTransport_type(expectedTransportType);
        assertEquals(expectedTransportType, tourEntity.getTransport_type());
    }

    @Test
    public void testStartCoordinates() {
        String expectedStartCoordinates = "50.8503, 4.3517";
        tourEntity.setStartCoordinates(expectedStartCoordinates);
        assertEquals(expectedStartCoordinates, tourEntity.getStartCoordinates());
    }

    @Test
    public void testEndCoordinates() {
        String expectedEndCoordinates = "51.2195, 4.4024";
        tourEntity.setEndCoordinates(expectedEndCoordinates);
        assertEquals(expectedEndCoordinates, tourEntity.getEndCoordinates());
    }
}