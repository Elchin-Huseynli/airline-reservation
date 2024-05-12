package com.airline.airplane_ms.model.entity;

import lombok.Builder;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Builder
public class Airplane {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "max_speed",nullable = false)
    private Float maxSpeed;

    @Column(name = "capacity",nullable = false)
    private Integer capacity;  // 500

    @Builder.Default
    @Column(name = "busy",nullable = false)
    private boolean busy = false;

    @Column(name = "status")
    private boolean status ;




    public Airplane(Long id, String name, Float maxSpeed, Integer capacity, boolean busy, boolean status) {
        this.id = id;
        this.name = name;
        this.maxSpeed = maxSpeed;
        this.capacity = capacity;
        this.busy = busy;
        this.status = status;
    }

    public Airplane() {
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

    public boolean busy() {
        return busy;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isBusy() {
        return busy;
    }

    public boolean isStatus() {
        return status;
    }


}
