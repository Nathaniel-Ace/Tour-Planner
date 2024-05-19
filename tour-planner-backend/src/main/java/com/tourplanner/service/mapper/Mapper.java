package com.tourplanner.service.mapper;

public interface Mapper<S, T> {

    T mapToDto(S source);

}
