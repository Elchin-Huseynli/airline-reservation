package com.airline.flight_ms.service.impl;

import com.airline.flight_ms.helper.FlightManagerServiceHelper;
import com.airline.flight_ms.helper.FlightServiceHelper;
import com.airline.flight_ms.model.manage.FlightManager;
import com.airline.flight_ms.model.manage.FlightManagerData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScheduleService {

    private final FlightManagerServiceHelper flightManagerServiceHelper;
    private final FlightServiceHelper flightServiceHelper;
    private final FlightManagerData flightManagerData;


    @Transactional
    @PostConstruct
    public void flightManagerAdding(){
        log.error("flightManagerAdding");
        flightManagerServiceHelper
                .flightsAddList();
    }


    @Transactional
    @Scheduled(fixedRateString = "${application.common.update-price}")
    public void updateSaleTicket() {
        log.error("saleTicket");
        flightManagerServiceHelper
                .updateSaleTicket();
    }

//    @Transactional
//    @Scheduled(fixedRateString = "${application.common.update-price}")
//    public void updatePrice() {
//        flightManagerData.flightManagers
//                .forEach(FlightManager::updateTicketPrice);
//
//    }

    @Transactional
    @Scheduled(fixedRateString = "${application.common.update-fly-detail}")
    public void updateFlyDetail(){
        log.error("updateFlyDetail");
        flightServiceHelper
                .updateFlyDetail();
    }
}
