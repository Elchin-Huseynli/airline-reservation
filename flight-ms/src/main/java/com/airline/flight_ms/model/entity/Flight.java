package com.airline.flight_ms.model.entity;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

@Entity
@Builder
@Slf4j
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long fromAirlineId;
    private Long toAirlineId;
    private Long airplaneId;
    private LocalDateTime arrivalDate;
    private LocalDateTime departureDate;
    private Double initialPrice;

    @Builder.Default
    private boolean fly = false;

    @Builder.Default
    private Boolean isEnable = true;


    public Flight(Long id, Long fromAirlineId, Long toAirlineId, Long airplaneId, LocalDateTime departureDate, LocalDateTime arrivalDate, Double initialPrice, boolean isFly, Boolean isEnable) {
        this.id = id;
        this.fromAirlineId = fromAirlineId;
        this.toAirlineId = toAirlineId;
        this.airplaneId = airplaneId;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.initialPrice = initialPrice;
        this.fly = isFly;
        this.isEnable = isEnable;

    }

    public Flight() {
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


    public Boolean getEnable() {
        return isEnable;
    }

    public void setEnable(Boolean enable) {
        isEnable = enable;
    }

    public boolean isFly() {
        return fly;
    }

    public void setFly(boolean fly) {
        this.fly = fly;
    }

    public boolean airplaneIsTookOff() {
        return arrivalDate.isBefore(now());
    }

    public boolean airplaneIsLanded() {
        return departureDate.isBefore(now());
    }
}
