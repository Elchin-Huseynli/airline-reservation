package com.airline.flight_ms.service.impl;

import com.airline.common_exception.exception.ApplicationException;
import com.airline.common_exception.util.MessagesUtil;
import com.airline.flight_ms.model.dto.response.AirlineResponse;
import com.airline.flight_ms.model.dto.response.FlightAllDetailResponse;
import com.airline.flight_ms.model.feign.AirlineFeignClient;
import com.airline.flight_ms.helper.FlightServiceHelper;
import com.airline.flight_ms.mapper.FlightMapping;
import com.airline.flight_ms.model.dto.request.FlightRequest;
import com.airline.flight_ms.model.dto.response.AirplaneResponse;
import com.airline.flight_ms.model.dto.response.FlightResponse;
import com.airline.flight_ms.model.entity.Flight;
import com.airline.flight_ms.model.feign.AirplaneFeignClient;
import com.airline.flight_ms.model.feign.UserFeignClient;
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
    private final AirlineFeignClient airlineFeignClient;
    private final FlightMapping flightMapping;
    private final FlightRepository flightRepository;
    private final FlightServiceHelper flightServiceHelper;
    private final MessagesUtil messagesUtil;

    @Override
    public String registration(String authToken, Long adminId, FlightRequest flightRequest) {

        boolean tokenValid = userFeignClient.jwtAuthCheck(authToken, adminId);
        if (tokenValid) {
            Long airplaneId = flightRequest.getAirplaneId();

            AirplaneResponse airplane = airplaneFeignClient.findById(airplaneId);

            flightServiceHelper.checkFlight(flightRequest);  //TODO Check the matches, arrival date and departure date, from and to
            flightServiceHelper.checkAirplane(airplaneId, flightRequest); //TODO No plane of times for the same airplane

            Flight flight = flightMapping.flightRequestDtoToFlight(flightRequest);
            flight.setEnable(true);
            flight.setAvailableSeats(airplane.getCapacity());
            Flight saveFlight = flightRepository.save(flight);

            flightServiceHelper.flightManagerBuild(airplane,saveFlight);

            flight.setAvailableSeats(airplane.getCapacity());

            flightRepository.save(flight);
            return messagesUtil.getMessage("REGISTER_SUCCESSFULLY");
        }
        throw new ApplicationException("TOKEN_IS_INVALID_EXCEPTION");
    }


    @Override
    public List<FlightResponse> findAll() {

        List<Flight> flightsTop100 = flightRepository.findTop100ByIsEnableTrueOrderByIdAsc();

        log.error("show list {}", flightsTop100);

        List<Flight> flights = flightServiceHelper.suitableFlight(flightsTop100); //TODO  Check out suitable flight

        log.error("show list {}", flights);
        List<CompletableFuture<FlightResponse>> futures = flights.parallelStream()
                .map(flight -> CompletableFuture
                        .supplyAsync(() -> flightServiceHelper.createFlightResponse(flight)))
                .toList();
        log.error("show list {}", futures);


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
        LocalDateTime departureDate = flight.getDepartureDate();
        boolean beforeAcceptable = departureDate.isBefore(now());
        boolean futureAcceptable = arrivalDate.isAfter(now());

        if (beforeAcceptable || futureAcceptable) {
            flight.setEnable(false);
            flightRepository.save(flight);

            return messagesUtil.getMessage("DELETE_SUCCESSFULLY");
        }
        throw new ApplicationException("FLIGHT_DELETE_UNSUCCESSFULLY");
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

        return messagesUtil.getMessage("UPDATE_SUCCESSFULLY");
    }

    @Override
    public String updateIsFly(Long flightId, boolean isFly) {

        Flight flight = flightServiceHelper.checkFindFlight(flightId);
        flight.setFly(isFly);
        flightRepository.save(flight);

        return messagesUtil.getMessage("UPDATE_SUCCESSFULLY");
    }


    @Override
    public String decreaseUpdateAvailableSeats(Long id) {
        flightRepository.decreaseUpdateAvailableSeats(id);

        return messagesUtil.getMessage("UPDATE_SUCCESSFULLY");

    }

    @Override
    public List<AirlineResponse> findAllAirline(String country, String airline) {

        return airlineFeignClient.airlines(country, airline);
    }

    @Override
    public List<FlightAllDetailResponse> flights() {
        List<Flight> flights = flightRepository.findAll();

        List<Flight> suitableFlights = flightServiceHelper.suitableFlight(flights);

         return flightMapping.flightToFlightAllDetailResponse(suitableFlights);
    }


}
