package com.tourplanner.persistence.repositories;

import com.tourplanner.persistence.entities.AddressEntity;
import com.tourplanner.persistence.entities.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<AddressEntity, Long> {

    List<AddressEntity> findByPerson(PersonEntity person);

}
