package com.tourplanner;


import com.tourplanner.service.dtos.PersonDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonDtoTest {

    private PersonDto personDto;

    @BeforeEach
    public void init() {
        personDto = new PersonDto();
    }

    @Test
    public void testId() {
        Long id = 1L;
        personDto.setId(id);
        assertEquals(id, personDto.getId());
    }

    @Test
    public void testName() {
        String name = "Test Name";
        personDto.setName(name);
        assertEquals(name, personDto.getName());
    }

    @Test
    public void testEmail() {
        String email = "test@example.com";
        personDto.setEmail(email);
        assertEquals(email, personDto.getEmail());
    }

    @Test
    public void testAge() {
        int age = 30;
        personDto.setAge(age);
        assertEquals(age, personDto.getAge());
    }
}
