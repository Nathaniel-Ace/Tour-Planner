package com.tourplanner.service.impl;

import com.tourplanner.service.PersonService;
import com.tourplanner.service.dtos.PersonDto;
import com.tourplanner.persistence.entities.PersonEntity;
import com.tourplanner.persistence.repositories.PersonRepository;
import com.tourplanner.service.mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private PersonMapper personMapper;

    @Override
    public void saveNewPerson(PersonDto personDto) {
        PersonEntity entity = PersonEntity.builder()
                .name(personDto.getName())
                .email(personDto.getEmail())
                .age(personDto.getAge())
                .build();
        personRepository.save(entity);
    }

    @Override
    public List<PersonDto> getAllPersons() {
        return personMapper.mapToDto(personRepository.findAll());
    }

    @Override
    public List<PersonDto> getPersonByName(String name) {
        return personMapper.mapToDto(personRepository.findByNameIgnoreCase(name));
    }
}
