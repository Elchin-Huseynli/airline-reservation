package com.airline.flight_ms.repository;

import com.airline.flight_ms.model.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    List<Flight> findTop100ByIsEnableTrueOrderByIdAsc();


}