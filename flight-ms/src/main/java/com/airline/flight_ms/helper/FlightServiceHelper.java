package com.airline.flight_ms.helper;

import com.airline.common_exception.exception.ApplicationException;
import com.airline.flight_ms.model.manage.FlightManagerData;
import com.airline.flight_ms.model.dto.request.FlightRequest;
import com.airline.flight_ms.model.dto.response.AirlineResponse;
import com.airline.flight_ms.model.dto.response.AirplaneResponse;
import com.airline.flight_ms.model.dto.response.FlightResponse;
import com.airline.flight_ms.model.entity.Flight;
import com.airline.flight_ms.model.feign.AirlineFeignClient;
import com.airline.flight_ms.model.feign.AirplaneFeignClient;
import com.airline.flight_ms.model.manage.FlightManager;
import com.airline.flight_ms.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

        BigDecimal price = BigDecimal.valueOf(0);

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
                .availableSeats(flight.getAvailableSeats())
                .isEnable(flight.getEnable())
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
                throw new ApplicationException("PLANE_BUSY");
            }
        }
    }

    public void checkFlight(FlightRequest flightRequest ){

        Long fromAirlineId = flightRequest.getFromAirlineId();
        Long toAirlineId = flightRequest.getToAirlineId();

        AirlineResponse from = airlineFeignClient.airlineById(fromAirlineId);
        AirlineResponse to = airlineFeignClient.airlineById(toAirlineId);

        if (from.equals(to)) throw new ApplicationException("FROM_ID_AND_TO_ID_EQUALS");

        boolean isTrue = flightRequest.getArrivalDate()
                .isBefore(flightRequest.getDepartureDate());

        if (!isTrue) throw new ApplicationException("ILLEGAL_DATE_TIME");
    }

    public List<Flight> suitableFlight(List<Flight> flightsTop100) {
        return flightsTop100.stream()
                .filter(Flight::getEnable)
                .filter(flight -> flight.getDepartureDate().isAfter(now()))
                .filter(flight -> flight.getAvailableSeats() > 0)
                .toList();
    }

    public Flight checkFindFlight (Long flightId) {
        return flightRepository.findByIdAndIsEnableTrue(flightId)
                .orElseThrow(() -> new ApplicationException("FLIGHT_NOT_FOUND"));
    }

    public void flightManagerBuild(AirplaneResponse airplane, Flight flight) {
        int saleTicketCount = airplane.getCapacity() - flight.getAvailableSeats();

        FlightManager flightManager = FlightManager.builder()
                .initialPrice(flight.getInitialPrice())
                .availableSeats(flight.getAvailableSeats())
                .saleTicketCount(saleTicketCount)
                .maxSeats(airplane.getCapacity())
                .ticketPrice(flight.getInitialPrice())
                .build();
        flightManager.setFlightId(flight.getId());
        log.error("flight manager {}",flightManager);

        flightManagerData.flightManagers.add(flightManager);
    }

    public void updateFlyDetail() {
        List<Flight> flights = flightRepository.findAll();

        for (Flight flight : flights) {

            if (flight.airplaneIsTookOff() && !flight.airplaneIsLanded()){
                airplaneFeignClient.updateIsBusy(flight.getAirplaneId(), true);
                flight.setFly(true);
                flight.setEnable(false);
            }
            else if (flight.airplaneIsLanded()){
                airplaneFeignClient.updateIsBusy(flight.getAirplaneId(), false);
            }

            if (flight.getAvailableSeats() == 0) {
                flight.setEnable(false);
            }
            flightRepository.save(flight);
            
        }
    }
}
