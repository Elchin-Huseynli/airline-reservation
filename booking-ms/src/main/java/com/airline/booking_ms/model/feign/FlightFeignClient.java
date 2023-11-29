package com.airline.booking_ms.model.feign;

import com.airline.booking_ms.model.dto.response.FlightResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@FeignClient(name = "FlightFeignClient", url = "http://host.docker.internal:9091/flight-ms")
public interface FlightFeignClient {

    @GetMapping("flight/{id}")
    FlightResponse flight(@PathVariable(name = "id") Long flightId);

}
