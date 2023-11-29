package com.airline.flight_ms.controller;

import com.airline.flight_ms.model.dto.request.FlightRequest;
import com.airline.flight_ms.model.dto.response.FlightResponse;
import com.airline.flight_ms.service.IFlightService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.HttpHeaders;
import java.util.List;

@RestController
@RequestMapping("flight-ms/")
@RequiredArgsConstructor
@Slf4j
public class FlightController {

    private final IFlightService flightService;

    @PostMapping("registration") //TODO  Admin
    public String registration(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String authToken,
                         @RequestHeader(name = "admin-id") Long adminId,
                         @RequestBody FlightRequest flightRequest){

        return flightService.registration(authToken, adminId, flightRequest);

    }


    @GetMapping("flights") //TODO Permit all
    public List<FlightResponse> flights(){

        return flightService.findAll();
    }

    @GetMapping("flight/{id}")
    public FlightResponse flight(@PathVariable(name = "id") Long flightId) {
        log.info("Request flight id: {}", flightId);

        return flightService.findById(flightId);
    }

    @DeleteMapping("flight/{id}")
    public String delete(@PathVariable(name = "id") Long flightId) {
        log.info("Request flight id: {}", flightId);

        return flightService.delete(flightId);
    }

    @PutMapping("flight/{id}")
    public String update(@PathVariable(name = "id") Long flightId,
                         @RequestBody FlightRequest flightRequest) {
        log.info("Request flight id: {}", flightId);

        return flightService.update(flightId, flightRequest);
    }

    @PatchMapping("flight/{id}")
    public String updateIsFly(@PathVariable(name = "id") Long flightId, @RequestParam boolean isFly ) {
        log.info("Request flight id: {} and is fly {}", flightId, isFly);

        return flightService.updateIsFly(flightId, isFly);
    }
}
