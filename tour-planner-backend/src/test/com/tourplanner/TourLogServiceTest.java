package com.tourplanner;

import com.tourplanner.service.TourLogService;
import com.tourplanner.service.dtos.TourLogDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TourLogServiceTest {

    @Mock
    private TourLogService tourLogService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllTourLogs() {
        TourLogDto log1 = new TourLogDto();
        TourLogDto log2 = new TourLogDto();
        List<TourLogDto> expectedLogs = Arrays.asList(log1, log2);

        when(tourLogService.getAllTourLogs()).thenReturn(expectedLogs);

        List<TourLogDto> actualLogs = tourLogService.getAllTourLogs();

        assertEquals(expectedLogs, actualLogs);
        verify(tourLogService, times(1)).getAllTourLogs();
    }

    @Test
    public void testGetTourLogsByTourId() {
        TourLogDto log1 = new TourLogDto();
        TourLogDto log2 = new TourLogDto();
        List<TourLogDto> expectedLogs = Arrays.asList(log1, log2);

        when(tourLogService.getTourLogsByTourId(anyLong())).thenReturn(expectedLogs);

        List<TourLogDto> actualLogs = tourLogService.getTourLogsByTourId(1L);

        assertEquals(expectedLogs, actualLogs);
        verify(tourLogService, times(1)).getTourLogsByTourId(1L);
    }

    @Test
    public void testSaveNewTourLog() {
        TourLogDto log = new TourLogDto();

        doNothing().when(tourLogService).saveNewTourLog(log);

        tourLogService.saveNewTourLog(log);

        verify(tourLogService, times(1)).saveNewTourLog(log);
    }

    @Test
    public void testDeleteTourLog() {
        doNothing().when(tourLogService).deleteTourLog(anyLong());

        tourLogService.deleteTourLog(1L);

        verify(tourLogService, times(1)).deleteTourLog(1L);
    }

    @Test
    public void testUpdateTourLog() {
        TourLogDto log = new TourLogDto();

        doNothing().when(tourLogService).updateTourLog(anyLong(), any(TourLogDto.class));

        tourLogService.updateTourLog(1L, log);

        verify(tourLogService, times(1)).updateTourLog(1L, log);
    }

    @Test
    public void testGetLogById() {
        TourLogDto expectedLog = new TourLogDto();

        when(tourLogService.getLogById(anyLong())).thenReturn(expectedLog);

        TourLogDto actualLog = tourLogService.getLogById(1L);

        assertEquals(expectedLog, actualLog);
        verify(tourLogService, times(1)).getLogById(1L);
    }

    @Test
    public void testDeleteByTour() {
        doNothing().when(tourLogService).deleteByTour(anyLong());

        tourLogService.deleteByTour(1L);

        verify(tourLogService, times(1)).deleteByTour(1L);
    }
}