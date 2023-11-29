package com.airline.flight_ms.model.feign;

import com.airline.flight_ms.model.dto.response.AirlineResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@FeignClient(name = "AirlineResponseData", url = "http://host.docker.internal:9090/common-ms/")
public interface AirlineFeignClient {

    @GetMapping("airlines/{id}")
    AirlineResponse airlineById(@PathVariable Long id);

    @GetMapping("airlines")
    List<AirlineResponse> airlines();


}
