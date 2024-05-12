package com.airline.flight_ms.model.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class FlightAllDetailResponse {

    private Long id;
    private Long fromAirlineId;
    private Long toAirlineId;
    private Long airplaneId;
    private LocalDateTime arrivalDate;
    private LocalDateTime departureDate;
    private BigDecimal initialPrice;
    private Integer availableSeats;

    private boolean fly ;

    private Boolean isEnable ;

    public FlightAllDetailResponse(Long id, Long fromAirlineId, Long toAirlineId, Long airplaneId, LocalDateTime arrivalDate, LocalDateTime departureDate, BigDecimal initialPrice, Integer availableSeats, boolean fly, Boolean isEnable) {
        this.id = id;
        this.fromAirlineId = fromAirlineId;
        this.toAirlineId = toAirlineId;
        this.airplaneId = airplaneId;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.initialPrice = initialPrice;
        this.availableSeats = availableSeats;
        this.fly = fly;
        this.isEnable = isEnable;
    }

    public FlightAllDetailResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDateTime getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDateTime arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public LocalDateTime getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDateTime departureDate) {
        this.departureDate = departureDate;
    }

    public BigDecimal getInitialPrice() {
        return initialPrice;
    }

    public void setInitialPrice(BigDecimal initialPrice) {
        this.initialPrice = initialPrice;
    }

    public Integer getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(Integer availableSeats) {
        this.availableSeats = availableSeats;
    }

    public boolean isFly() {
        return fly;
    }

    public void setFly(boolean fly) {
        this.fly = fly;
    }

    public Boolean getEnable() {
        return isEnable;
    }

    public void setEnable(Boolean enable) {
        isEnable = enable;
    }
}
