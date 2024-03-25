package com.example.jpademo;

import com.example.jpademo.persistence.entities.TourEntity;
import com.example.jpademo.persistence.repositories.TourRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class TourTest {

    @Autowired
    private TourRepository tourRepository;

    @Test
    void test_TourEntity() {
        TourEntity tour1 = TourEntity.builder()
                .name("Tour 1")
                .description("Description 1")
                .from_location("Location 1")
                .to_location("Location 2")
                .transport_type("Bus")
                .distance(100)
                .time(2)
                .route_info("Route 1")
                .build();

        tourRepository.save(tour1);

        tourRepository.save(TourEntity.builder()
                .name("Tour 2")
                .description("Description 2")
                .from_location("Location 3")
                .to_location("Location 4")
                .transport_type("Train")
                .distance(200)
                .time(3)
                .route_info("Route 2")
                .build());

        System.out.printf("%d rows in table tour\n", tourRepository.count());
        tourRepository.findAll().forEach(System.out::println);
    }

}
