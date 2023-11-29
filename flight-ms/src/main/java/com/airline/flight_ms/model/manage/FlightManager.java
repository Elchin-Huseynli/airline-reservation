package com.airline.flight_ms.model.manage;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Builder
@Slf4j
public class FlightManager {

    private Long flightId;
    private double ticketPrice;
    private int availableSeats;
    private int saleTicketCount;
    private int maxSeats;

    public FlightManager(Long flightId, double ticketPrice, int availableSeats, int saleTicketCount, int maxSeats) {
        this.flightId = flightId;
        this.ticketPrice = ticketPrice;
        this.availableSeats = availableSeats;
        this.saleTicketCount = saleTicketCount;
        this.maxSeats = maxSeats;
    }

    public FlightManager(double ticketPrice, int availableSeats, int saleTicketCount, int maxSeats) {
        this.ticketPrice = ticketPrice;
        this.availableSeats = availableSeats;
        this.saleTicketCount = saleTicketCount;
        this.maxSeats = maxSeats;
    }

    public FlightManager() {
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public int getSaleTicketCount() {
        return saleTicketCount;
    }

    public void setSaleTicketCount(int saleTicketCount) {
        this.saleTicketCount = saleTicketCount;
    }

    public int getMaxSeats() {
        return maxSeats;
    }

    public void setMaxSeats(int maxSeats) {
        this.maxSeats = maxSeats;
    }

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }


    public void updateTicketPrice() {
        log.info("Başlangıç fiyatı: {}", ticketPrice);

        double stc = saleTicketCount > 10 ? 0.09 : saleTicketCount >= 2 ? 0.05
                : saleTicketCount == 1 ? 0.02 : -0.1;


        double seats = (maxSeats * 0.05 > availableSeats) ? 0.23
                : (maxSeats * 0.1 > availableSeats) ? 0.18 : (maxSeats * 0.2 > availableSeats) ? 0.11
                : (maxSeats * 0.3 > availableSeats) ? 0.06 : 0;



        ticketPrice = ticketPrice + (ticketPrice * stc) + (ticketPrice * seats);
        log.info("Yeni fiyat: {}", ticketPrice);

    }
}