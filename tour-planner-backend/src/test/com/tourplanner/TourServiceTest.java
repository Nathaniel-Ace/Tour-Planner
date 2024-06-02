package com.tourplanner;

import com.tourplanner.service.TourService;
import com.tourplanner.service.dtos.TourDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TourServiceTest {

    @Mock
    private TourService tourService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllTours() {
        TourDto tour1 = new TourDto();
        TourDto tour2 = new TourDto();
        List<TourDto> expectedTours = Arrays.asList(tour1, tour2);

        when(tourService.getAllTours()).thenReturn(expectedTours);

        List<TourDto> actualTours = tourService.getAllTours();

        assertEquals(expectedTours, actualTours);
        verify(tourService, times(1)).getAllTours();
    }

    @Test
    public void testGetTourById() {
        TourDto expectedTour = new TourDto();

        when(tourService.getTourById(anyLong())).thenReturn(expectedTour);

        TourDto actualTour = tourService.getTourById(1L);

        assertEquals(expectedTour, actualTour);
        verify(tourService, times(1)).getTourById(1L);
    }

    @Test
    public void testSaveNewTour() {
        TourDto tour = new TourDto();

        doNothing().when(tourService).saveNewTour(tour);

        tourService.saveNewTour(tour);

        verify(tourService, times(1)).saveNewTour(tour);
    }

    @Test
    public void testDeleteTour() {
        doNothing().when(tourService).deleteTour(anyLong());

        tourService.deleteTour(1L);

        verify(tourService, times(1)).deleteTour(1L);
    }

    @Test
    public void testUpdateTour() {
        TourDto tour = new TourDto();

        doNothing().when(tourService).updateTour(anyLong(), any(TourDto.class));

        tourService.updateTour(1L, tour);

        verify(tourService, times(1)).updateTour(1L, tour);
    }

    @Test
    public void testGetTourByName() {
        TourDto expectedTour = new TourDto();

        when(tourService.getTourByName(anyString())).thenReturn(Arrays.asList(expectedTour));

        List<TourDto> actualTours = tourService.getTourByName("test");

        assertEquals(Arrays.asList(expectedTour), actualTours);
        verify(tourService, times(1)).getTourByName("test");
    }
}