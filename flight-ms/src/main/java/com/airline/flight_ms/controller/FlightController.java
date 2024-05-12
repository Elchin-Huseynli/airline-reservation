package com.airline.flight_ms.controller;

import com.airline.flight_ms.model.dto.request.FlightRequest;
import com.airline.flight_ms.model.dto.response.AirlineResponse;
import com.airline.flight_ms.model.dto.response.FlightAllDetailResponse;
import com.airline.flight_ms.model.dto.response.FlightResponse;
import com.airline.flight_ms.service.IFlightService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.HttpHeaders;
import java.util.List;

@RestController
@RequestMapping("/flight-ms")
@RequiredArgsConstructor
@Slf4j
public class FlightController {

    private final IFlightService flightService;

    @PostMapping("/admin/registration") //TODO  Admin
    public String registration(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String authToken,
                               @RequestHeader(name = "admin-id") Long adminId,
                               @RequestBody FlightRequest flightRequest,
                               @RequestHeader(name = HttpHeaders.ACCEPT_LANGUAGE) String locale) {

        return flightService.registration(authToken, adminId, flightRequest);

    }


    @GetMapping("/flight/find-all") //TODO Permit all
    public List<FlightResponse> findAll(){

        return flightService.findAll();
    }

    @GetMapping("/flight/{id}") //TODO PERMIT ALL
    public FlightResponse flight(@PathVariable(name = "id") Long flightId) {
        log.info("Request flight id: {}", flightId);

        return flightService.findById(flightId);
    }

    @DeleteMapping("/admin/flight/{id}") //TODO ADMIN
    public String delete(@PathVariable(name = "id") Long flightId,
                         @RequestHeader(name = HttpHeaders.ACCEPT_LANGUAGE) String locale) {
        log.info("Request flight id: {}", flightId);

        return flightService.delete(flightId);
    }

    @PutMapping("/admin/flight/{id}") //TODO ADMIN
    public String update(@PathVariable(name = "id") Long flightId,
                         @RequestBody FlightRequest flightRequest,
                         @RequestHeader(name = HttpHeaders.ACCEPT_LANGUAGE) String locale) {
        log.info("Request flight id: {}", flightId);

        return flightService.update(flightId, flightRequest);
    }

    @PatchMapping("/admin/flight/{id}") //TODO ADMIN
    public String updateIsFly(@PathVariable(name = "id") Long flightId, @RequestParam boolean isFly,
                              @RequestHeader(name = HttpHeaders.ACCEPT_LANGUAGE) String locale) {
        log.info("Request flight id: {} and is fly {}", flightId, isFly);

        return flightService.updateIsFly(flightId, isFly);
    }


    @PostMapping("/flight/decreasing-seats/{flight}")  //FIXME PATCH
    public String decreaseUpdateAvailableSeats(@PathVariable(name = "flight") Long id){
        log.info("Request flight id : {} ", id);

        return flightService.decreaseUpdateAvailableSeats(id);
    }
    @GetMapping("/flight/airlines")
    public List<AirlineResponse> findAll(@RequestParam(required = false) String country,
                                         @RequestParam(required = false) String airline) {

        return flightService.findAllAirline(country, airline);
    }


    @GetMapping("flight/flights")
    public  List<FlightAllDetailResponse> flights() {

        return flightService.flights();
    }
}
