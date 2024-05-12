package com.airline.booking_ms.service.impl;

import com.airline.booking_ms.helper.TicketServiceHelper;
import com.airline.booking_ms.model.dto.request.UserRequest;
import com.airline.booking_ms.model.entity.Ticket;
import com.airline.booking_ms.model.feign.UserFeignClient;
import com.airline.booking_ms.repository.TicketRepository;
import com.airline.booking_ms.service.ITicketService;
import com.airline.common_exception.exception.ApplicationException;
import com.airline.common_exception.util.MessagesUtil;
import com.airline.common_notification.model.dto.response.TicketResponse;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.airline.common_file_generator.service.PdfGenerator.generatePdfContent;

@Service
@RequiredArgsConstructor
public class TicketService implements ITicketService {

    private final UserFeignClient userFeignClient;
    private final TicketServiceHelper ticketServiceHelper;
    private final TicketRepository ticketRepository;
    private final TicketProducer ticketProducer;
    private final MessagesUtil messagesUtil;

    @Override
    public String bookTicket(String authToken, Long userId, Long flightId, UserRequest userRequest) {
        boolean tokenValid = userFeignClient.jwtAuthCheckUser(authToken, userId);

        if (tokenValid) {
            Ticket ticket = ticketServiceHelper.createTicketBuild(flightId, userRequest, userId);
            ticketRepository.save(ticket);

            TicketResponse ticketResponse = ticketServiceHelper.createTicketResponseWithAirlineInfo(ticket);
            ticketProducer.sendMessage(ticketResponse);

            return messagesUtil.getMessage("TICKET_BOUGHT_SUCCESSFULLY");
        }

        throw new ApplicationException("TOKEN_IS_INVALID_EXCEPTION");
    }

    @Override
    @SneakyThrows
    public ResponseEntity<Resource> makePdf(String authToken, Long userId, Long ticketId) {
        boolean tokenValid = userFeignClient.jwtAuthCheckUser(authToken, userId);

        if (tokenValid) {
            Ticket ticket = ticketRepository.findByIdAndUserIdCheck(ticketId, userId)
                    .orElseThrow(() -> new ApplicationException("TICKET_NOT_FOUND"));

            TicketResponse formattingTicket = ticketServiceHelper.createTicketResponseWithAirlineInfo(ticket);

            byte[] pdfBytes = generatePdfContent(formattingTicket);

            String fileName = "ticket_" + formattingTicket.getFirstName() + ".pdf";

            ByteArrayResource resource = new ByteArrayResource(pdfBytes);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_PDF)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
                    .body(resource);

        }
        throw new ApplicationException("TOKEN_IS_INVALID_EXCEPTION");
    }

    @Override
    public TicketResponse findById(String authToken, Long userId, Long ticketId) {

        boolean tokenValid = userFeignClient.jwtAuthCheckUser(authToken, userId);

        if (tokenValid){
            Ticket ticket = ticketRepository.findByIdAndUserIdCheck(ticketId, userId)
                    .orElseThrow(() -> new ApplicationException("TICKET_NOT_FOUND"));

            return ticketServiceHelper.createTicketResponseWithAirlineInfo(ticket);

        }

        throw new ApplicationException("TOKEN_IS_INVALID_EXCEPTION");
    }

    @Override
    public List<TicketResponse> findAllTicketByUserId(String authToken, Long userId) {
        if (!userFeignClient.jwtAuthCheckUser(authToken, userId)) {
            return Collections.emptyList();
        }

        return ticketRepository.findAllByUserIdCheck(userId).parallelStream()
                .map(ticketServiceHelper::createTicketResponseWithAirlineInfo)
                .filter(ticketResponse -> ticketResponse.getFrom() != null && ticketResponse.getTo() != null)
                .collect(Collectors.toList());
    }
}
