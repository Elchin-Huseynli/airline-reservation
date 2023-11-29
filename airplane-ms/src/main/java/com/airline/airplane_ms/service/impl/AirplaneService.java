package com.airline.airplane_ms.service.impl;

import com.airline.airplane_ms.mapping.AirplaneMapping;
import com.airline.airplane_ms.model.constant.Constants;
import com.airline.airplane_ms.model.dto.request.AirplaneRequest;
import com.airline.airplane_ms.model.dto.request.AirplaneUpdateRequest;
import com.airline.airplane_ms.model.dto.response.AirplaneResponse;
import com.airline.airplane_ms.model.entity.Airplane;
import com.airline.airplane_ms.model.enums.Exceptions;
import com.airline.airplane_ms.repository.AirplaneRepository;
import com.airline.airplane_ms.service.IAirplaneService;
import com.airline.common_exception.exception.ApplicationException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AirplaneService implements IAirplaneService {
    private final AirplaneRepository repository;
    private final AirplaneMapping mapping;
    @Override
    public List<AirplaneResponse> findAll(boolean busy) {

        return busy ?
                mapping.airplaneListToAirplaneResponseList(repository.findAll()) :
                mapping.airplaneListToAirplaneResponseList(repository.findAllByBusyFalse());
    }

    @Override
    @SneakyThrows
    public AirplaneResponse findById(Long id) {
        Airplane airplane = repository.findById(id)
                .orElseThrow(() -> new ApplicationException(Exceptions.AIRPLANE_NOT_FOUND));

        return mapping.airplaneToAirplaneResponse(airplane);

    }

    @Override
    public String registration(AirplaneRequest airplaneRequest) {
        Airplane airplane = mapping.airplaneRequestToAirplane(airplaneRequest);
        airplane.setAvailableSeats(airplane.getCapacity());
        repository.save(airplane);

        return Constants.REGISTER_SUCCESSFULLY;
    }

    @Override
    public String update(Long id, AirplaneUpdateRequest airplaneRequest) {
        AirplaneResponse airplaneResponse = findById(id);
        Airplane airplane = mapping.airplaneResponseToAirplane(airplaneResponse);
        repository.save(airplane);

        return Constants.UPDATE_SUCCESSFULLY;
    }

    @Override
    public String delete(Long id) {
        findById(id);
        repository.updateStatusById(id);

        return Constants.DELETE_SUCCESSFULLY;
    }

    @Override
    public String updateIsBusy(Long id, boolean busy) {

        findById(id);
        repository.updateBusyById(busy,id);

        return Constants.UPDATE_IS_BUSY_SUCCESSFULLY;
    }

    @Override
    public String increaseUpdateAvailableSeats(Long id) {
        repository.increaseUpdateAvailableSeats(id);

        return Constants.UPDATE_SUCCESSFULLY;
    }

    @Override
    public String decreaseUpdateAvailableSeats(Long id) {
        repository.decreaseUpdateAvailableSeats(id);
        return Constants.UPDATE_SUCCESSFULLY;

    }


    // FIXME UPDATE AVAILABLE SEATS
}
