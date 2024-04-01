package com.example.jpademo;

import com.example.jpademo.service.dtos.TourDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TourDtoTest {

    private TourDto tourDto;

    @BeforeEach
    public void init() {
        tourDto = new TourDto();
    }

    @Test
    public void testId() {
        Long id = 1L;
        tourDto.setId(id);
        assertEquals(id, tourDto.getId());
    }

    @Test
    public void testName() {
        String name = "Test Name";
        tourDto.setName(name);
        assertEquals(name, tourDto.getName());
    }

    @Test
    public void testDescription() {
        String description = "Test Description";
        tourDto.setDescription(description);
        assertEquals(description, tourDto.getDescription());
    }

    @Test
    public void testFromLocation() {
        String fromLocation = "Test From Location";
        tourDto.setFrom_location(fromLocation);
        assertEquals(fromLocation, tourDto.getFrom_location());
    }

    @Test
    public void testToLocation() {
        String toLocation = "Test To Location";
        tourDto.setTo_location(toLocation);
        assertEquals(toLocation, tourDto.getTo_location());
    }

    @Test
    public void testTransportType() {
        String transportType = "Test Transport Type";
        tourDto.setTransport_type(transportType);
        assertEquals(transportType, tourDto.getTransport_type());
    }

    @Test
    public void testDistance() {
        int distance = 100;
        tourDto.setDistance(distance);
        assertEquals(distance, tourDto.getDistance());
    }

    @Test
    public void testTime() {
        int time = 2;
        tourDto.setTime(time);
        assertEquals(time, tourDto.getTime());
    }

    @Test
    public void testRouteInfo() {
        String routeInfo = "Test Route Info";
        tourDto.setRoute_info(routeInfo);
        assertEquals(routeInfo, tourDto.getRoute_info());
    }
}