package com.airline.booking_ms.service.impl;

import com.airline.booking_ms.helper.ScheduleServiceHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleServiceHelper scheduleServiceHelper;



    @Transactional
    @Scheduled(fixedRateString = "${application.common.buy-ticket}")
    public void bookTicket() {
        scheduleServiceHelper
                .bookTicketWithBot();
    }
}
