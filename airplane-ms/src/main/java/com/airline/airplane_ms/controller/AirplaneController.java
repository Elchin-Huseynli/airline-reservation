package com.airline.airplane_ms.controller;

import com.airline.airplane_ms.model.dto.request.AirplaneRequest;
import com.airline.airplane_ms.model.dto.request.AirplaneUpdateRequest;
import com.airline.airplane_ms.model.dto.response.AirplaneResponse;
import com.airline.airplane_ms.service.IAirplaneService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/airplane-ms")
@RestController
@Slf4j
@RequiredArgsConstructor
public class AirplaneController {

    private final IAirplaneService airplaneService;

    @GetMapping("/airplanes/find-all")
    public List<AirplaneResponse> airplanes(@RequestParam("busy") boolean busy) {
        log.info("Request busy {}",busy);
        return airplaneService.findAll(busy);
    }

    @GetMapping("/airplanes/{id}")
    public AirplaneResponse findById(@PathVariable Long id) {
        log.info("Request id {}",id);

        return airplaneService.findById(id);
    }

    @PostMapping("/registration")
    public String registration(@RequestBody @Valid AirplaneRequest airplaneRequest) {
        log.info("Request airplane {}",airplaneRequest);

        return airplaneService.registration(airplaneRequest);
    }

    @PutMapping("/airplanes/update/{id}")
    public String update(@PathVariable Long id, @RequestBody AirplaneUpdateRequest airplaneRequest) {
        log.info("Request airplane {}",airplaneRequest);

        return airplaneService.update(id,airplaneRequest);
    }

    @DeleteMapping("/airplanes/{id}")
    public String delete(@PathVariable Long id){
        log.info("Request id {}",id);

        return airplaneService.delete(id);
    }

    @PostMapping("/airplane/{id}")
    public String updateIsBusy(@PathVariable Long id, @RequestParam Boolean busy){
        log.info("Request id {} and busy {}",id,busy);

        return airplaneService.updateIsBusy(id,busy);
    }

    @PostMapping("airplanes/increasing-seats/{airplaneId}")
    public String increaseUpdateAvailableSeats(@PathVariable(name = "airplaneId") Long id){

        log.info("Request airplane id : {} ", id);

        return airplaneService.increaseUpdateAvailableSeats(id);
    }

    @PostMapping("airplanes/decreasing-seats/{airplaneId}")
    public String decreaseUpdateAvailableSeats(@PathVariable(name = "airplaneId") Long id){
        log.info("Request airplane id : {} ", id);

        return airplaneService.decreaseUpdateAvailableSeats(id);
    }
}
