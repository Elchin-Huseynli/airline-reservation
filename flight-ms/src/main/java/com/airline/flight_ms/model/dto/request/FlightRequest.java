package com.airline.flight_ms.model.dto.request;

import lombok.Builder;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Future;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public class FlightRequest {

    @NotNull(message = "FROM_AIRLINE_ID_URGENT")
    private Long fromAirlineId;

    @NotNull(message = "TO_AIRLINE_ID_URGENT")
    private Long toAirlineId;

    @NotNull(message = "AIRPLANE_ID_URGENT")
    private Long airplaneId;

    @NotNull(message = "ARRIVAL_DATE_URGENT")
    @Future(message = "ARRIVAL_DATE_FUTURE")
    private LocalDateTime arrivalDate;

    @NotNull(message = "DEPARTURE_DATE_URGENT")
    @Future(message = "DEPARTURE_DATE_FUTURE")
    private LocalDateTime departureDate;

    @NotNull(message = "INITIAL_PRICE_URGENT")
    @Positive(message = "INITIAL_PRICE_IS_POSITIVE")
    private BigDecimal initialPrice;

    public FlightRequest(Long fromAirlineId, Long toAirlineId, Long airplaneId, LocalDateTime departureDate, LocalDateTime arrivalDate, BigDecimal initialPrice) {
        this.fromAirlineId = fromAirlineId;
        this.toAirlineId = toAirlineId;
        this.airplaneId = airplaneId;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.initialPrice = initialPrice;
    }
    public FlightRequest() {
    }

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

    public BigDecimal getInitialPrice() {
        return initialPrice;
    }

    public void setInitialPrice(BigDecimal initialPrice) {
        this.initialPrice = initialPrice;
    }

}
