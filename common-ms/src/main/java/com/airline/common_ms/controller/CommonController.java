package com.airline.common_ms.controller;

import com.airline.common_ms.model.dao.Airline;
import com.airline.common_ms.service.IAirlineService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/common-ms")
public class CommonController {

    private final IAirlineService airlineService;

    @GetMapping("/airlines")
    public List<Airline> findAll(@RequestParam(required = false) String country,
                                 @RequestParam(required = false) String airline
    ) {
        return airlineService.findAll(country, airline);
    }

    @GetMapping("/airlines/{id}")
    public Airline findById(@PathVariable Long id) {
        return airlineService.findById(id);
    }
}
