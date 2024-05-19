package com.tourplanner.service.mapper;

import com.tourplanner.service.dtos.PersonDto;
import com.tourplanner.persistence.entities.PersonEntity;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper extends AbstractMapper<PersonEntity, PersonDto> {

    @Override
    public PersonDto mapToDto(PersonEntity source) {
        return PersonDto.builder()
                .id(source.getId())
                .name(source.getName())
                .email(source.getEmail())
                .age(source.getAge())
                .build();
    }
}
