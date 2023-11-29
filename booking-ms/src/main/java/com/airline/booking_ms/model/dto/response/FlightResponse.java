package com.airline.booking_ms.model.dto.response;

import com.airline.common_notification.model.dto.response.AirlineResponse;
import lombok.Builder;
import java.time.LocalDateTime;
@Builder
public class FlightResponse {
    private AirlineResponse from;

    private AirlineResponse to;

    private Long airplaneId;

    private LocalDateTime departureDate;

    private LocalDateTime arrivalDate;

    private Double price;


    public FlightResponse(AirlineResponse from, AirlineResponse to, Long airplaneId, LocalDateTime departureDate, LocalDateTime arrivalDate, Double price) {
        this.from = from;
        this.to = to;
        this.airplaneId = airplaneId;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.price = price;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

}
