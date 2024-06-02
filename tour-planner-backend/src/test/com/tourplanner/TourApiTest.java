package com.tourplanner;

import com.tourplanner.api.TourApi;
import com.tourplanner.service.TourLogService;
import com.tourplanner.service.TourService;
import com.tourplanner.service.dtos.TourDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

public class TourApiTest {

    @Mock
    private TourService tourService;

    @Mock
    private TourLogService tourLogService;

    @InjectMocks
    private TourApi tourApi;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(tourApi).build();
    }

    @Test
    public void testGetAllTours() throws Exception {
        TourDto tour1 = new TourDto();
        TourDto tour2 = new TourDto();
        List<TourDto> expectedTours = Arrays.asList(tour1, tour2);

        when(tourService.getAllTours()).thenReturn(expectedTours);

        mockMvc.perform(get("/tour"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetTourByName() throws Exception {
        TourDto expectedTour = new TourDto();
        String name = "test";

        when(tourService.getTourByName(name)).thenReturn(Arrays.asList(expectedTour));

        mockMvc.perform(get("/tour/name/{name}", name))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetTourById() throws Exception {
        TourDto expectedTour = new TourDto();
        Long id = 1L;

        when(tourService.getTourById(id)).thenReturn(expectedTour);

        mockMvc.perform(get("/tour/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}