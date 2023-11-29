package com.airline.flight_ms.service.impl;

import com.airline.common_exception.exception.ApplicationException;
import com.airline.flight_ms.model.manage.FlightManagerData;
import com.airline.flight_ms.helper.FlightServiceHelper;
import com.airline.flight_ms.mapper.FlightMapping;
import com.airline.flight_ms.model.constants.Constants;
import com.airline.flight_ms.model.dto.request.FlightRequest;
import com.airline.flight_ms.model.dto.response.AirplaneResponse;
import com.airline.flight_ms.model.dto.response.FlightResponse;
import com.airline.flight_ms.model.entity.Flight;
import com.airline.flight_ms.model.enums.Exceptions;
import com.airline.flight_ms.model.feign.AirplaneFeignClient;
import com.airline.flight_ms.model.feign.UserFeignClient;
import com.airline.flight_ms.model.manage.FlightManager;
import com.airline.flight_ms.repository.FlightRepository;
import com.airline.flight_ms.service.IFlightService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static java.time.LocalDateTime.now;

@Service
@RequiredArgsConstructor
@Slf4j
public class FlightService implements IFlightService {

    private final UserFeignClient userFeignClient;
    private final AirplaneFeignClient airplaneFeignClient;
    private final FlightMapping flightMapping;
    private final FlightRepository flightRepository;
    private final FlightServiceHelper flightServiceHelper;

    @Override
    public String registration(String authToken, Long adminId, FlightRequest flightRequest) {

        boolean tokenValid = userFeignClient.jwtAuthCheck(authToken, adminId);
        if (tokenValid) {
            Long airplaneId = flightRequest.getAirplaneId();

            AirplaneResponse airplane = airplaneFeignClient.findById(airplaneId);

            flightServiceHelper.checkFlight(flightRequest); //TODO Check the matches, arrival date and departure date, from and to
            flightServiceHelper.checkAirplane(airplaneId, flightRequest); //TODO No plane of times for the same airplane

            Flight flight = flightMapping.flightRequestDtoToFlight(flightRequest);
            flight.setEnable(true);
            Flight saveFlight = flightRepository.save(flight);

            flightServiceHelper.flightManagerBuild(airplane,saveFlight);

            flightRepository.save(flight);
            return Constants.REGISTER_SUCCESSFULLY;
        }
        throw new ApplicationException(Exceptions.TOKEN_IS_INVALID_EXCEPTION);
    }


    @Override
    public List<FlightResponse> findAll() {

        List<Flight> flightsTop100 = flightRepository.findTop100ByIsEnableTrueOrderByIdAsc();

        List<Flight> flights = flightServiceHelper.suitableFlight(flightsTop100); //TODO  Check out suitable flight

        List<CompletableFuture<FlightResponse>> futures = flights.parallelStream()
                .map(flight -> CompletableFuture
                        .supplyAsync(() -> flightServiceHelper.createFlightResponse(flight)))
                .toList();

        return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                .thenApply(v -> futures.stream().map(CompletableFuture::join).collect(Collectors.toList()))
                .join();
    }

    @Override
    public FlightResponse findById(Long flightId) {

        Flight flight = flightServiceHelper.checkFindFlight(flightId);

        return flightServiceHelper.createFlightResponse(flight);
    }

    @Override
    public String delete(Long flightId) {
        Flight flight = flightServiceHelper.checkFindFlight(flightId);
        LocalDateTime arrivalDate = flight.getArrivalDate();
        boolean acceptable = arrivalDate.isBefore(now());

        if (acceptable) {
            flight.setEnable(false);
            flightRepository.save(flight);

            return Constants.DELETE_SUCCESSFULLY;
        }
        throw new ApplicationException(Exceptions.FLIGHT_DELETE_UNSUCCESSFULLY);
    }

    @Override
    public String update(Long flightId, FlightRequest flightRequest) {

        Flight flight = flightServiceHelper.checkFindFlight(flightId);

        Long airplaneId = flightRequest.getAirplaneId();

        airplaneFeignClient.findById(airplaneId);

        flightServiceHelper.checkFlight(flightRequest); //TODO Check the matches, arrival date and departure date, from and to
        flightServiceHelper.checkAirplane(airplaneId, flightRequest); //TODO No plane of times for the same airplane

        Flight updateFlight = flightMapping.flightUpdateRequestToFlight(flight, flightRequest);
        flightRepository.save(updateFlight);

        return Constants.UPDATE_SUCCESSFULLY;
    }

    @Override
    public String updateIsFly(Long flightId, boolean isFly) {

        Flight flight = flightServiceHelper.checkFindFlight(flightId);
        flight.setFly(isFly);
        flightRepository.save(flight);

        return Constants.UPDATE_SUCCESSFULLY;
    }

}
