package com.airline.flight_ms.model.dto.request;

import com.airline.flight_ms.model.constants.Constants;
import lombok.Builder;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Future;
import java.time.LocalDateTime;

@Builder
public class FlightRequest {

    @NotNull(message = Constants.FROM_AIRLINE_ID_URGENT)
    private Long fromAirlineId;

    @NotNull(message = Constants.TO_AIRLINE_ID_URGENT)
    private Long toAirlineId;

    @NotNull(message = Constants.AIRPLANE_ID_URGENT)
    private Long airplaneId;

    @NotNull(message = Constants.ARRIVAL_DATE_URGENT)
    @Future(message = Constants.ARRIVAL_DATE_FUTURE)
    private LocalDateTime arrivalDate;

    @NotNull(message = Constants.DEPARTURE_DATE_URGENT)
    @Future(message = Constants.DEPARTURE_DATE_FUTURE)
    private LocalDateTime departureDate;

    @NotNull(message = Constants.INITIAL_PRICE_URGENT)
    @Positive(message = Constants.INITIAL_PRICE_IS_POSITIVE)
    private Double initialPrice;


    public Long getFromAirlineId() {
        return fromAirlineId;
    }

    public void setFromAirlineId(Long fromAirlineId) {
        this.fromAirlineId = fromAirlineId;
    }

    public Long getToAirlineId() {
        return toAirlineId;
    }

    public void setToAirlineId(Long toAirlineId) {
        this.toAirlineId = toAirlineId;
    }

    public Long getAirplaneId() {
        return airplaneId;
    }

    public void setAirplaneId(Long airplaneId) {
        this.airplaneId = airplaneId;
    }

    public LocalDateTime getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDateTime departureDate) {
        this.departureDate = departureDate;
    }

    public LocalDateTime getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDateTime arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Double getInitialPrice() {
        return initialPrice;
    }

    public void setInitialPrice(Double initialPrice) {
        this.initialPrice = initialPrice;
    }

    public FlightRequest() {
    }

    public FlightRequest(Long fromAirlineId, Long toAirlineId, Long airplaneId, LocalDateTime departureDate, LocalDateTime arrivalDate, Double initialPrice) {
        this.fromAirlineId = fromAirlineId;
        this.toAirlineId = toAirlineId;
        this.airplaneId = airplaneId;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.initialPrice = initialPrice;
    }

}
