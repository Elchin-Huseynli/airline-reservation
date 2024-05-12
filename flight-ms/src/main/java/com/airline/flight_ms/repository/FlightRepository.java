package com.airline.flight_ms.repository;

import com.airline.flight_ms.model.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    List<Flight> findTop100ByIsEnableTrueOrderByIdAsc();


    List<Flight> findAllByIsEnableTrueAndFlyFalse();

    Optional<Flight> findByIdAndIsEnableTrue(long id);
    @Modifying
    @Transactional
    @Query("UPDATE Flight SET availableSeats = (availableSeats-1) WHERE id = :id and availableSeats > 0")
    void decreaseUpdateAvailableSeats(@Param("id") Long id);


}