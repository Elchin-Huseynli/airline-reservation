package com.airline.booking_ms.service.impl;

import com.airline.booking_ms.helper.TicketServiceHelper;
import com.airline.booking_ms.mapper.TicketMapper;
import com.airline.booking_ms.model.constants.Constants;
import com.airline.booking_ms.model.dto.request.UserRequest;
import com.airline.booking_ms.model.entity.Ticket;
import com.airline.booking_ms.model.enums.Exceptions;
import com.airline.booking_ms.model.feign.AirlineFeignClient;
import com.airline.booking_ms.model.feign.UserFeignClient;
import com.airline.common_notification.model.dto.response.AirlineResponse;
import com.airline.common_notification.model.dto.response.TicketResponse;
import com.airline.common_notification.model.kafka.TicketEvent;
import com.airline.booking_ms.repository.TicketRepository;
import com.airline.booking_ms.service.ITicketService;
import com.airline.common_exception.exception.ApplicationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TicketService implements ITicketService {

    private final UserFeignClient userFeignClient;
    private final TicketServiceHelper ticketServiceHelper;
    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;
    private final AirlineFeignClient airlineFeignClient;
    private final TicketProducer ticketProducer;

    @Override
    public String bookTicket(String authToken, Long userId, Long flightId, UserRequest userRequest) {
        boolean tokenValid = userFeignClient.jwtAuthCheckUser(authToken, userId);

        if (tokenValid){
            Ticket ticket = ticketServiceHelper.createTicketBuild(flightId, userRequest,userId);
            ticketRepository.save(ticket);

            TicketResponse ticketResponse = createTicketResponseWithAirlineInfo(ticket);
            TicketEvent ticketEvent = new TicketEvent();
            ticketEvent.setStatus("PENDING");
            ticketEvent.setMessage("ticket status is in pending state");
            ticketEvent.setTicket(ticketResponse);
            ticketProducer.sendMessage(ticketEvent);

            return Constants.TICKET_BOUGHT_SUCCESSFULLY;

        }

        throw new ApplicationException(Exceptions.TOKEN_IS_INVALID_EXCEPTION);
    }

    @Override
    public void makePdf() {

    }


    @Override
    public TicketResponse findById(String authToken, Long userId, Long ticketId) {

        boolean tokenValid = userFeignClient.jwtAuthCheckUser(authToken, userId);

        if (tokenValid){
            Ticket ticket = ticketRepository.findByIdAndUserIdCheck(ticketId, userId)
                    .orElseThrow(() -> new ApplicationException(Exceptions.TICKET_NOT_FOUND));

            return createTicketResponseWithAirlineInfo(ticket);

        }

        throw new ApplicationException(Exceptions.TOKEN_IS_INVALID_EXCEPTION);

    }

    public List<TicketResponse> findAllTicketByUserId(String authToken, Long userId) {
        if (!userFeignClient.jwtAuthCheckUser(authToken, userId)) {
            return Collections.emptyList();
        }

        return ticketRepository.findAllByUserIdCheck(userId).parallelStream()
                .map(this::createTicketResponseWithAirlineInfo)
                .filter(ticketResponse -> ticketResponse.getFrom() != null && ticketResponse.getTo() != null)
                .collect(Collectors.toList());
    }

    private TicketResponse createTicketResponseWithAirlineInfo(Ticket ticket) {
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
