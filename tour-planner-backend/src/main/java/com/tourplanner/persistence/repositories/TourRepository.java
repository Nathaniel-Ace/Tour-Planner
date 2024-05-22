package com.tourplanner.persistence.repositories;

import com.tourplanner.persistence.entities.TourEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.List;

public interface TourRepository extends JpaRepository<TourEntity, Long> {

    List<TourEntity> findByNameIgnoreCase(String name);
    void deleteById(@NonNull Long id);

}
