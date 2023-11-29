package com.airline.flight_ms.helper;

import com.airline.common_exception.exception.ApplicationException;
import com.airline.flight_ms.model.manage.FlightManagerData;
import com.airline.flight_ms.model.dto.request.FlightRequest;
import com.airline.flight_ms.model.dto.response.AirlineResponse;
import com.airline.flight_ms.model.dto.response.AirplaneResponse;
import com.airline.flight_ms.model.dto.response.FlightResponse;
import com.airline.flight_ms.model.entity.Flight;
import com.airline.flight_ms.model.enums.Exceptions;
import com.airline.flight_ms.model.feign.AirlineFeignClient;
import com.airline.flight_ms.model.feign.AirplaneFeignClient;
import com.airline.flight_ms.model.manage.FlightManager;
import com.airline.flight_ms.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static java.time.LocalDateTime.now;

@Service
@RequiredArgsConstructor
@Slf4j
public class FlightServiceHelper {

    private final AirlineFeignClient airlineFeignClient;
    private final FlightRepository flightRepository;
    private final AirplaneFeignClient airplaneFeignClient;
    private final FlightManagerData flightManagerData;

    public FlightResponse createFlightResponse(Flight flight) {
        Long fromAirlineId = flight.getFromAirlineId();
        Long toAirlineId = flight.getToAirlineId();
        Long airplaneId = flight.getAirplaneId();

        AirlineResponse from = airlineFeignClient.airlineById(fromAirlineId);
        AirlineResponse to = airlineFeignClient.airlineById(toAirlineId);

        double price = 0;

        for (FlightManager flightManager:flightManagerData.flightManagers) {
            if (flightManager.getFlightId().equals(flight.getId())){
                price = flightManager.getTicketPrice();
            }
        }

        LocalDateTime arrivalDate = flight.getArrivalDate();
        LocalDateTime departureDate = flight.getDepartureDate();

        return FlightResponse.builder()
                .to(to)
                .from(from)
                .airplaneId(airplaneId)
                .price(price)
                .arrivalDate(arrivalDate)
                .departureDate(departureDate)
                .build();
    }

    public void checkAirplane(Long airplaneId, FlightRequest flightRequest) {

        //TODO          A   D <   // A 10  -> D 12  // < A   D
        List<Flight> flights = flightRepository.findAll();
        if (!flights.isEmpty()) {
            List<Flight> filteredFlights = flights.stream()
                    .filter(flight -> flight.getAirplaneId().equals(airplaneId))
                    .filter(flight -> {
                        boolean before = flightRequest.getDepartureDate().isBefore(flight.getArrivalDate());
                        boolean after = flightRequest.getArrivalDate().isAfter(flight.getDepartureDate());
                        return !(before || after);
                    })
                    .toList();

            if (!filteredFlights.isEmpty()) {
                throw new ApplicationException(Exceptions.PLANE_BUSY);
            }
        }
    }

    public void checkFlight(FlightRequest flightRequest ){

        Long fromAirlineId = flightRequest.getFromAirlineId();
        Long toAirlineId = flightRequest.getToAirlineId();

        AirlineResponse from = airlineFeignClient.airlineById(fromAirlineId);
        AirlineResponse to = airlineFeignClient.airlineById(toAirlineId);

        if (from.equals(to)) throw new ApplicationException(Exceptions.FROM_ID_AND_TO_ID_EQUALS);

        boolean isTrue = flightRequest.getArrivalDate()
                .isBefore(flightRequest.getDepartureDate());

        if (!isTrue) throw new ApplicationException(Exceptions.ILLEGAL_DATE_TIME);
    }

    public List<Flight>  suitableFlight(List<Flight> flightsTop100) {
        return flightsTop100.stream()
                .filter(Flight::getEnable)
                .filter(flight -> flight.getDepartureDate().isAfter(now()))
                .filter(flight -> {
                    AirplaneResponse airplane = airplaneFeignClient.findById(flight.getAirplaneId());
                    return airplane.getAvailableSeats() > 0;
                })
                .toList();
    }

    public Flight checkFindFlight (Long flightId) {
        return flightRepository.findById(flightId)
                .orElseThrow(() -> new ApplicationException(Exceptions.FLIGHT_NOT_FOUND));
    }

    public void flightManagerBuild(AirplaneResponse airplane, Flight flight) {
        int saleTicketCount = airplane.getCapacity() - airplane.getAvailableSeats();
        FlightManager flightManager = new FlightManager(flight.getInitialPrice(),
                airplane.getAvailableSeats(),
                saleTicketCount,airplane.getCapacity());
        flightManager.setFlightId(flight.getId());
        flightManagerData.flightManagers.add(flightManager);
    }

    public void updateFlyDetail() {
        List<Flight> flights = flightRepository.findAll();

        for (Flight flight : flights) {

            if (flight.airplaneIsTookOff() && !flight.airplaneIsLanded()){
                airplaneFeignClient.updateIsBusy(flight.getAirplaneId(), true);
                flight.setFly(true);
                flight.setEnable(false);
                flightRepository.save(flight);
            }
            else if (flight.airplaneIsLanded()){
                airplaneFeignClient.updateIsBusy(flight.getAirplaneId(), false);
                airplaneFeignClient.increaseUpdateAvailableSeats(flight.getAirplaneId()); // TODO CAPACITY = AVAILABLE_SEATS
            }
        }
    }
}
