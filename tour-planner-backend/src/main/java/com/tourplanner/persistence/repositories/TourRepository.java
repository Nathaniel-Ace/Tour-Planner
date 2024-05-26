package com.tourplanner.persistence.repositories;

import com.tourplanner.persistence.entities.TourEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

public interface TourRepository extends JpaRepository<TourEntity, Long> {

    List<TourEntity> findByNameIgnoreCase(String name);
    Optional<TourEntity> findById(@NonNull Long id);
    void deleteById(@NonNull Long id);

}
