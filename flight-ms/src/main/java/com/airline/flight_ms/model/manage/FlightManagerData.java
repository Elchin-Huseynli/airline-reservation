package com.airline.flight_ms.model.manage;

import com.airline.flight_ms.model.manage.FlightManager;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class FlightManagerData {
    public List<FlightManager> flightManagers = new LinkedList<>();

}
