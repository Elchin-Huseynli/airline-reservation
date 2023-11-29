package com.airline.airplane_ms.model.dto.request;

import lombok.Builder;

@Builder
public class AirplaneUpdateRequest {

    private String name;
    private Float maxSpeed;
    private Integer capacity;

    public AirplaneUpdateRequest(String name, Float maxSpeed, Integer capacity) {
        this.name = name;
        this.maxSpeed = maxSpeed;
        this.capacity = capacity;
    }

    public AirplaneUpdateRequest() {
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

    @Override
    public String toString() {
        return "AirplaneUpdateRequest{" +
                "name='" + name + '\'' +
                ", maxSpeed=" + maxSpeed +
                ", capacity=" + capacity +
                '}';
    }
}
