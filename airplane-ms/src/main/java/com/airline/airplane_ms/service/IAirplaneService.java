package com.airline.airplane_ms.service;

import com.airline.airplane_ms.model.dto.request.AirplaneRequest;
import com.airline.airplane_ms.model.dto.request.AirplaneUpdateRequest;
import com.airline.airplane_ms.model.dto.response.AirplaneResponse;

import java.util.List;

public interface IAirplaneService {
    List<AirplaneResponse> findAll(boolean busy);

    AirplaneResponse findById(Long id);

    String registration(AirplaneRequest airplaneRequest);

    String update(Long id,AirplaneUpdateRequest airplaneRequest);

    String delete(Long id);

    String updateIsBusy(Long id, boolean busy);

}
