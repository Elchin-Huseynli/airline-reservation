package com.airline.common_ms.service;

import com.airline.common_ms.model.dao.Airline;

import java.util.List;

public interface IAirlineService {
    List<Airline> findAll(String country, String airline);
    Airline findById(Long id);
}
