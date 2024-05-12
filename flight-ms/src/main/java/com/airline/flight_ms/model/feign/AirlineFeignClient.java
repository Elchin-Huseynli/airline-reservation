package com.airline.flight_ms.model.feign;

import com.airline.flight_ms.model.dto.response.AirlineResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "AirlineResponseData", url = "http://localhost:9090/common-ms/")
public interface AirlineFeignClient {

    @GetMapping("airlines/{id}")
    AirlineResponse airlineById(@PathVariable Long id);

    @GetMapping("airlines")
    List<AirlineResponse> airlines(@RequestParam(required = false) String country,
                                   @RequestParam(required = false) String airline);


}
