package com.airline.common_ms.service.impl;

import com.airline.common_exception.exception.ApplicationException;
import com.airline.common_ms.model.dao.Airline;
import com.airline.common_ms.service.IAirlineService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class AirlineService implements IAirlineService {
    @Override
    public List<Airline> findAll(String country, String airline) {

        return Airline.values.stream()
                .filter(a -> (country == null || a.getCountry().contains(country))
                        && (airline == null || a.getName().contains(airline)))
                .collect(Collectors.toList());



    }

    @Override
    public Airline findById(Long id) {
        return Airline.values.stream()
                .filter(airline -> Objects.equals(id, airline.getId()))
                .findFirst()
                .orElseThrow(() -> new ApplicationException("AIRLINE_NOT_FOUND"));

    }
}
