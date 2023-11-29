package com.airline.flight_ms.service.impl;

import com.airline.flight_ms.helper.FlightManagerServiceHelper;
import com.airline.flight_ms.helper.FlightServiceHelper;
import com.airline.flight_ms.model.manage.FlightManagerData;
import com.airline.flight_ms.model.manage.FlightManager;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final FlightManagerData flightManagerData;
    private final FlightManagerServiceHelper flightManagerServiceHelper;
    private final FlightServiceHelper flightServiceHelper;


    @Transactional
    @PostConstruct
    public void flightManagerAdding(){
        flightManagerServiceHelper
                .flightsAddList();
    }

    @Transactional
    @Scheduled(fixedRate = 10000)
    public void updatePrice() {
        flightManagerData.
                flightManagers.
                forEach(FlightManager::updateTicketPrice);
    }

    @Transactional
    @Scheduled(fixedRate = 10000)
    public void updateFlyDetail(){

        flightServiceHelper
                .updateFlyDetail();
    }
}
