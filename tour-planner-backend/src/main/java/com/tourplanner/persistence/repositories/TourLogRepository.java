package com.tourplanner.persistence.repositories;

import com.tourplanner.persistence.entities.TourEntity;
import com.tourplanner.persistence.entities.TourLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

public interface TourLogRepository extends JpaRepository<TourLogEntity, Long> {

    List<TourLogEntity> findByTour(TourEntity tour);
    Optional<TourLogEntity> findById(@NonNull Long id);
    void deleteById(@NonNull Long id);

}
