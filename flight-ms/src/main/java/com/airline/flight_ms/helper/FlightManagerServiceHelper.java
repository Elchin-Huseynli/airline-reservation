package com.airline.flight_ms.helper;

import com.airline.flight_ms.model.dto.response.AirplaneResponse;
import com.airline.flight_ms.model.entity.Flight;
import com.airline.flight_ms.model.feign.AirplaneFeignClient;
import com.airline.flight_ms.model.manage.FlightManager;
import com.airline.flight_ms.model.manage.FlightManagerData;
import com.airline.flight_ms.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FlightManagerServiceHelper {

    private final FlightManagerData flightManagerData;
    private final FlightRepository flightRepository;
    private final AirplaneFeignClient airplaneFeignClient;


    public void flightsAddList() {
        List<AirplaneResponse> airplanes = airplaneFeignClient
                .findAllByBusyFalse(false);

        List<Flight> flights = flightRepository
                .findAll();

        Map<Long, AirplaneResponse> airplaneMap = airplanes.stream()
                .collect(Collectors.toMap(AirplaneResponse::getId, Function.identity()));

        flights.forEach(flight -> {
            FlightManager flightManager = new FlightManager();
            flightManager.setFlightId(flight.getId());
            flightManager.setTicketPrice(flight.getInitialPrice());

            AirplaneResponse airplaneResponse = airplaneMap.get(flight.getAirplaneId());
            if (airplaneResponse != null) {
                flightManager.setAvailableSeats(airplaneResponse.getAvailableSeats());
                flightManager.setMaxSeats(airplaneResponse.getCapacity());
                int stc = airplaneResponse.getCapacity() - airplaneResponse.getAvailableSeats();
                flightManager.setSaleTicketCount(stc);
                flightManagerData.flightManagers.add(flightManager);
            }
        });
    }
}
