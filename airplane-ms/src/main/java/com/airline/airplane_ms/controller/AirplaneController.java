package com.airline.airplane_ms.controller;

import com.airline.airplane_ms.model.dto.request.AirplaneRequest;
import com.airline.airplane_ms.model.dto.request.AirplaneUpdateRequest;
import com.airline.airplane_ms.model.dto.response.AirplaneResponse;
import com.airline.airplane_ms.service.IAirplaneService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpHeaders;
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
    public List<AirplaneResponse> airplanes(@RequestParam(value = "busy", defaultValue = "false") boolean busy) {
        log.info("Request busy {}",busy);

        return airplaneService.findAll(busy);
    }

    @GetMapping("/airplanes/{id}")
    public AirplaneResponse findById(@PathVariable Long id) {
        log.info("Request id {}",id);

        return airplaneService.findById(id);
    }

    @PostMapping("/admin/registration")
    public String registration(@RequestBody @Valid AirplaneRequest airplaneRequest,
                               @RequestHeader(name = HttpHeaders.ACCEPT_LANGUAGE) String locale) {
        log.info("Request airplane {}",airplaneRequest);

        return airplaneService.registration(airplaneRequest);
    }

    @PutMapping("/admin/airplanes/update/{id}")
    public String update(@PathVariable Long id, @RequestBody @Valid AirplaneUpdateRequest airplaneRequest,
                         @RequestHeader(name = HttpHeaders.ACCEPT_LANGUAGE) String locale) {
        log.info("Request airplane {}",airplaneRequest);

        return airplaneService.update(id,airplaneRequest);
    }

    @DeleteMapping("/admin/airplanes/{id}")
    public String delete(@PathVariable Long id,
                         @RequestHeader(name = HttpHeaders.ACCEPT_LANGUAGE) String locale){
        log.info("Request id {}",id);

        return airplaneService.delete(id);
    }

    @PostMapping("/airplanes/{id}") //FIXME PATCH
    public String updateIsBusy(@PathVariable Long id,
                               @RequestParam(name = "busy", defaultValue = "true") boolean busy){
        log.info("Request id {} and busy {}",id,busy);

        return airplaneService.updateIsBusy(id,busy);
    }


}
