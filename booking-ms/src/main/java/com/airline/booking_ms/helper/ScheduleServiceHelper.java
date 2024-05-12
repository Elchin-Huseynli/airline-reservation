package com.airline.booking_ms.helper;

import com.airline.booking_ms.model.dto.request.UserRequest;
import com.airline.booking_ms.model.dto.response.FlightAllDetailResponse;
import com.airline.booking_ms.model.entity.Ticket;
import com.airline.booking_ms.model.feign.FlightFeignClient;
import com.airline.booking_ms.repository.TicketRepository;
import com.airline.common_exception.exception.ApplicationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;


@Service
@RequiredArgsConstructor
@Slf4j
public class ScheduleServiceHelper {

    private final FlightFeignClient flightFeignClient;
    private final TicketServiceHelper ticketServiceHelper;
    private final TicketRepository ticketRepository;


    @Value("${application.common.email}")
    private String email;

    @Value("${application.common.user-id}")
    private Long userId;

    public void bookTicketWithBot() {

        long randomFlightId = randomIdGenerator();
        log.error("flights {}",randomFlightId);

        Ticket ticket = ticketServiceHelper.
                createTicketBuild(randomFlightId, randomUserGenerator(), userId);
        log.error("ticket {}",ticket);
        ticketRepository.save(ticket);
    }

    private UserRequest randomUserGenerator() {
        UUID uuid = UUID.randomUUID();

        String randomFirstName = uuid.toString().substring(0, 7);
        String randomLastname = uuid.toString().substring(0, 7);
        String randomEmail = randomFirstName + "@" + email;
        String randomFin = uuid.toString().substring(0,7);

        return UserRequest.builder()
                .firstName(randomFirstName)
                .lastName(randomLastname)
                .email(randomEmail)
                .birthdate(randomAgeGenerator())
                .fin(randomFin)
                .serialNumber(randomSerialNumberGenerator())
                .build();
    }

    private LocalDate randomAgeGenerator() {
        Random random = new Random();
        int year = random.nextInt(0, 100);
        int day = random.nextInt(0, 30);
        int month = random.nextInt(0, 12);

        return LocalDate.now()
                .minusYears(year)
                .minusMonths(month)
                .minusDays(day);
    }

    private String randomSerialNumberGenerator() {
        Random random = new Random();
        List<String> serialNumberIdentification = new ArrayList<>(List.of("AA", "AZE"));
        Collections.shuffle(serialNumberIdentification);

        StringBuilder randomSerialNumber = new StringBuilder(serialNumberIdentification.get(0));

        for (int i = 0; i < 7; i++) {
            int randomNumber = random.nextInt(10);
            randomSerialNumber.append(randomNumber);
        }

        return randomSerialNumber.toString();
    }

    private long randomIdGenerator() {

        List<FlightAllDetailResponse> flightResponses = flightFeignClient.findAllDetailFlight();

        List<Long> idList = new CopyOnWriteArrayList<>();



        for (FlightAllDetailResponse flightResponse : flightResponses) {
            idList.add(flightResponse.getId());
        }
        Collections.shuffle(idList);

        return idList.parallelStream()
                .findAny()
                .orElseThrow(() -> new ApplicationException("FLIGHT_NOT_FOUND"));
    }
}
