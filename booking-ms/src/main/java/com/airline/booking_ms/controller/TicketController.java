package com.airline.booking_ms.controller;

import com.airline.booking_ms.model.constants.Constants;
import com.airline.booking_ms.model.dto.request.UserRequest;
import com.airline.booking_ms.service.ITicketService;
import com.airline.common_notification.model.dto.response.TicketResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.core.HttpHeaders;
import java.util.List;

@RestController
@RequestMapping("/booking-ms/booking")
@Slf4j
@RequiredArgsConstructor
public class TicketController {

    private final ITicketService ticketService;

    @PostMapping("/ticket/{flightId}")
    public String save(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String authToken,
                       @RequestHeader(name = "user-id") Long userId,
                       @PathVariable Long flightId,
                       @RequestBody @Valid UserRequest userRequest) {
        log.info("Request auth token: {} , request user id : {} , request flight id : {}", authToken, userId, flightId);

        return ticketService.bookTicket(authToken, userId, flightId, userRequest);
    }


    @GetMapping("/tickets")
    public List<TicketResponse> findAll(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String authToken,
                                        @RequestHeader(name = "user-id") Long userId) {
        log.info("Request auth token: {} , request user id : {}", authToken, userId);

        return ticketService.findAllTicketByUserId(authToken,userId);
    }

    @GetMapping("/ticket/{ticketId}")
    public TicketResponse findById(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String authToken,
                                   @RequestHeader(name = "user-id") Long userId,
                                   @PathVariable("ticketId") Long ticketId) {
        log.info("Request auth token: {} , request user id : {} , ticket id {}", authToken, userId, ticketId);

        return ticketService.findById(authToken, userId, ticketId);
    }
}
