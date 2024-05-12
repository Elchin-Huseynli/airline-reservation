package com.airline.flight_ms.service;

import com.airline.flight_ms.model.dto.request.FlightRequest;
import com.airline.flight_ms.model.dto.response.AirlineResponse;
import com.airline.flight_ms.model.dto.response.FlightAllDetailResponse;
import com.airline.flight_ms.model.dto.response.FlightResponse;

import java.util.List;

public interface IFlightService {

    String registration(String authToken, Long adminId, FlightRequest flightService);

    List<FlightResponse> findAll();

    FlightResponse findById(Long flightId);

    String delete(Long flightId);

    String update(Long flightId, FlightRequest flightRequest);

    String updateIsFly(Long flightId, boolean isFly);

    String decreaseUpdateAvailableSeats(Long id);

    List<AirlineResponse> findAllAirline(String country, String airline);

    List<FlightAllDetailResponse> flights();
}
