package com.airline.booking_ms.model.dto.response;

import com.airline.common_notification.model.dto.response.AirlineResponse;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Builder
public class FlightResponse {
    private AirlineResponse from;

    private AirlineResponse to;

    private Long airplaneId;

    private LocalDateTime departureDate;

    private LocalDateTime arrivalDate;

    private BigDecimal price;

    private Integer availableSeats;

    private boolean isEnable;


    public FlightResponse(AirlineResponse from, AirlineResponse to, Long airplaneId, LocalDateTime departureDate, LocalDateTime arrivalDate, BigDecimal price, Integer availableSeats, boolean isEnable) {
        this.from = from;
        this.to = to;
        this.airplaneId = airplaneId;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.price = price;
        this.availableSeats = availableSeats;
        this.isEnable = isEnable;
    }

    public FlightResponse() {
    }
    public AirlineResponse getFrom() {
        return from;
    }

    public void setFrom(AirlineResponse from) {
        this.from = from;
    }

    public AirlineResponse getTo() {
        return to;
    }

    public void setTo(AirlineResponse to) {
        this.to = to;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(Integer availableSeats) {
        this.availableSeats = availableSeats;
    }

    public boolean isEnable() {
        return isEnable;
    }

    public void setEnable(boolean enable) {
        isEnable = enable;
    }
}
