package com.airline.booking_ms.helper;

import com.airline.booking_ms.mapper.TicketMapper;
import com.airline.booking_ms.model.dto.request.UserRequest;
import com.airline.booking_ms.model.dto.response.FlightResponse;
import com.airline.booking_ms.model.entity.Ticket;
import com.airline.booking_ms.model.feign.AirlineFeignClient;
import com.airline.booking_ms.model.feign.AirplaneFeignClient;
import com.airline.booking_ms.model.feign.FlightFeignClient;
import com.airline.common_notification.model.dto.response.AirlineResponse;
import com.airline.common_notification.model.dto.response.TicketResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketServiceHelper {

    private final FlightFeignClient flightFeignClient;
    private final AirlineFeignClient airlineFeignClient;
    private final TicketMapper ticketMapper;


    public Ticket createTicketBuild(Long flightId, UserRequest userRequest, Long userId) {

        FlightResponse flight = flightFeignClient.flight(flightId);
        AirlineResponse from = flight.getFrom();
        AirlineResponse to = flight.getTo();
        flightFeignClient.decreaseUpdateAvailableSeats(flightId);

        return Ticket.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .email(userRequest.getEmail())
                .birthday(userRequest.getBirthdate())
                .fin(userRequest.getFin())
                .serialNumber(userRequest.getSerialNumber())
                .toAirlineId(to.getId())
                .fromAirlineId(from.getId())
                .arrivalDate(flight.getArrivalDate())
                .departureDate(flight.getDepartureDate())
                .price(flight.getPrice())
                .flightId(flightId)
                .userId(userId)
                .build();
    }

    public TicketResponse createTicketResponseWithAirlineInfo(Ticket ticket) {
        AirlineResponse from = airlineFeignClient.airlineById(ticket.getFromAirlineId());
        AirlineResponse to = airlineFeignClient.airlineById(ticket.getToAirlineId());

        TicketResponse ticketResponse = ticketMapper.ticketToTicketResponse(ticket);

        if (ticket.getFromAirlineId().equals(from.getId()) && ticket.getToAirlineId().equals(to.getId())) {
            ticketResponse.setFrom(from);
            ticketResponse.setTo(to);
        }

        return ticketResponse;
    }
}
