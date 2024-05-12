package com.airline.airplane_ms.model.dto.request;

import lombok.Builder;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;


@Builder
@Validated
public class AirplaneRequest {

    @NotBlank(message = "AIRPLANE_NAME_URGENT")
    private String name;
    @Max(value = 900, message = "MAX_SPEED_BE_GREATER")
    @Min(value = 300, message = "MIN_SPEED_CANNOT_BE_LESS")
    private Float maxSpeed;

    @NotNull(message = "CAPACITY_CANNOT_BE_NULL")
    @Positive(message = "CAPACITY_POSITIVE_VALUE")
    @Max(value = 500, message = "CAPACITY_BE_GREATER")
    @Min(value = 1, message = "CAPACITY_CANNOT_BE_LESS")
    private Integer capacity;


    public AirplaneRequest(String name, Float maxSpeed, Integer capacity) {
        this.name = name;
        this.maxSpeed = maxSpeed;
        this.capacity = capacity;
    }

    public AirplaneRequest() {
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
        return "AirplaneRequest{" +
                "name='" + name + '\'' +
                ", maxSpeed=" + maxSpeed +
                ", capacity=" + capacity +
                '}';
    }
}
