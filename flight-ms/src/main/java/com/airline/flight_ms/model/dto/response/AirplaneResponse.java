package com.airline.flight_ms.model.dto.response;

import lombok.Builder;

@Builder
public class AirplaneResponse {
    private Long id;
    private String name;
    private Float maxSpeed;
    private Integer capacity;
    private Integer availableSeats;


    public AirplaneResponse(Long id, String name, Float maxSpeed, Integer capacity, Integer availableSeats) {
        this.id = id;
        this.name = name;
        this.maxSpeed = maxSpeed;
        this.capacity = capacity;
        this.availableSeats = availableSeats;
    }

    public AirplaneResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(Float maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(Integer availableSeats) {
        this.availableSeats = availableSeats;
    }

}
