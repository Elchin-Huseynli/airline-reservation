package com.airline.booking_ms.model.feign;

import com.airline.booking_ms.model.dto.response.FlightAllDetailResponse;
import com.airline.booking_ms.model.dto.response.FlightResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@FeignClient(name = "FlightFeignClient", url = "http://localhost:9091/flight-ms")
public interface FlightFeignClient {

    @GetMapping("flight/{id}")
    FlightResponse flight(@PathVariable(name = "id") Long flightId);

    @PostMapping("flight/decreasing-seats/{flight}")
    String decreaseUpdateAvailableSeats(@PathVariable(name = "flight") Long id);

    @GetMapping("/flight/find-all")
    List<FlightResponse> flights();

    @GetMapping("/flight/flights")
    List<FlightAllDetailResponse> findAllDetailFlight();
}
