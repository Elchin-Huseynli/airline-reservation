package com.airline.booking_ms.service;

import com.airline.booking_ms.model.dto.request.UserRequest;
import com.airline.common_notification.model.dto.response.AirlineResponse;
import com.airline.common_notification.model.dto.response.TicketResponse;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ITicketService {
    String bookTicket(String authToken, Long adminId, Long flightId, UserRequest userRequest);

    ResponseEntity<Resource> makePdf(String authToken, Long adminId, Long ticketId);

    List<TicketResponse> findAllTicketByUserId(String authToken, Long userId);

    TicketResponse findById(String authToken, Long userId, Long ticketId);

}
