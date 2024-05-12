package com.airline.booking_ms.controller;

import com.airline.booking_ms.model.dto.request.UserRequest;
import com.airline.booking_ms.service.ITicketService;
import com.airline.common_notification.model.dto.response.TicketResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/booking-ms/booking")
@Slf4j
@RequiredArgsConstructor
public class TicketController {

    private final ITicketService ticketService;

    @PostMapping("/ticket/{flightId}")
    public String registration(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String authToken,
                               @RequestHeader(name = "user-id") Long userId,
                               @PathVariable Long flightId,
                               @RequestBody @Valid UserRequest userRequest,
                               @RequestHeader(name = HttpHeaders.ACCEPT_LANGUAGE) String locale) {
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

    @GetMapping("/tickets/{ticketId}/pdf")

    public ResponseEntity<Resource> makePdf(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String authToken,
                                             @RequestHeader(name = "user-id") Long userId,
                                             @PathVariable("ticketId") Long ticketId) {
        log.info("Request auth token: {} , request user id : {} , ticket id {}", authToken, userId, ticketId);

        return ticketService.makePdf(authToken, userId, ticketId);
    }
}
