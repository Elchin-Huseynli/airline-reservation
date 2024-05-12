package com.airline.flight_ms.helper;

import com.airline.common_exception.exception.ApplicationException;
import com.airline.flight_ms.model.dto.response.AirplaneResponse;
import com.airline.flight_ms.model.entity.Flight;
import com.airline.flight_ms.model.feign.AirplaneFeignClient;
import com.airline.flight_ms.model.manage.FlightManager;
import com.airline.flight_ms.model.manage.FlightManagerData;
import com.airline.flight_ms.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class FlightManagerServiceHelper {

    private final FlightManagerData flightManagerData;
    private final FlightRepository flightRepository;
    private final AirplaneFeignClient airplaneFeignClient;


    public void flightsAddList() {
        List<AirplaneResponse> airplanes = airplaneFeignClient
                .findAllByBusyFalse(false);

        List<Flight> flights = flightRepository
                .findAllByIsEnableTrueAndFlyFalse();

        Map<Long, AirplaneResponse> airplaneMap = airplanes.stream()
                .collect(Collectors.toMap(AirplaneResponse::getId, Function.identity()));

        flights.forEach(flight -> {
            FlightManager flightManager = new FlightManager();
            flightManager.setFlightId(flight.getId());
            flightManager.setInitialPrice(flight.getInitialPrice());
            flightManager.setTicketPrice(flight.getInitialPrice());

            AirplaneResponse airplaneResponse = airplaneMap.get(flight.getAirplaneId());
            if (airplaneResponse != null) {
                flightManager.setAvailableSeats(flight.getAvailableSeats());
                flightManager.setMaxSeats(airplaneResponse.getCapacity());

                flightManager.setSaleTicketCount(0);
                flightManagerData.flightManagers.add(flightManager);
            }
        });
    }

    public void updateSaleTicket() {
        for (FlightManager flightManager : flightManagerData.flightManagers) {
            Flight flight = flightRepository.findById(flightManager.getFlightId())
                    .orElseThrow(() -> new ApplicationException("FLIGHT_NOT_FOUND"));
            int currentAvailableSeats = flight.getAvailableSeats();
            int lastAvailableSeats = flightManager.getAvailableSeats();
            int saleTicket = lastAvailableSeats - currentAvailableSeats;

            flightManager.setSaleTicketCount(saleTicket);
            flightManager.setAvailableSeats(currentAvailableSeats);

            log.error("sale {}", saleTicket);
            flightManager.updateTicketPrice();


            log.error("flight manager {}", flightManager);
        }
    }
}
