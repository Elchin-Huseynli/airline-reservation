package com.airline.booking_ms.model.feign;

import com.airline.booking_ms.model.dto.response.AirplaneResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@FeignClient(name = "AirplaneFeignClient", url = "http://localhost:9095/airplane-ms")
public interface AirplaneFeignClient {

    @GetMapping("/airplanes/{id}")
    AirplaneResponse findById(@PathVariable Long id);

    @GetMapping("/airplanes/find-all")
    List<AirplaneResponse> findAllByBusyFalse(@RequestParam boolean busy);

    @PostMapping("/airplanes/{id}")
    String updateIsBusy(@PathVariable Long id, @RequestParam boolean busy);

}
