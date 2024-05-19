package com.tourplanner.service;

import com.tourplanner.service.dtos.PersonDto;

import java.util.List;

public interface PersonService {

    void saveNewPerson(PersonDto personDto);
    List<PersonDto> getAllPersons();
    List<PersonDto> getPersonByName(String name);
}
