package com.example.jpademo;

import com.example.jpademo.persistence.entities.TourEntity;
import com.example.jpademo.persistence.entities.TourLogEntity;
import com.example.jpademo.persistence.repositories.TourLogRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@SpringBootTest
@Transactional
public class TourLogTest {

    @Autowired
    private TourLogRepository tourLogRepository;

    @Test
    void test_TourLogEntity() {
        TourLogEntity tourLog1 = TourLogEntity.builder()
                .tour(new TourEntity()) // replace with actual TourEntity
                .dateTime(LocalDateTime.now())
                .comment("Comment 1")
                .difficulty("Easy")
                .totalDistance(100.0f)
                .totalTime("2 hours")
                .rating(4.0f)
                .build();

        tourLogRepository.save(tourLog1);

        tourLogRepository.save(TourLogEntity.builder()
                .tour(new TourEntity()) // replace with actual TourEntity
                .dateTime(LocalDateTime.now())
                .comment("Comment 2")
                .difficulty("Hard")
                .totalDistance(200.0f)
                .totalTime("3 hours")
                .rating(5.0f)
                .build());

        System.out.printf("%d rows in table tour_log\n", tourLogRepository.count());
        tourLogRepository.findAll().forEach(System.out::println);
    }

}
