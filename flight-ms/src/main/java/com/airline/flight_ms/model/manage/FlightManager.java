package com.airline.flight_ms.model.manage;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@Builder
@Slf4j
public class FlightManager {

    private BigDecimal initialPrice;
    private Long flightId;
    private BigDecimal ticketPrice;
    private int availableSeats;
    private int saleTicketCount;
    private int maxSeats;

    public FlightManager(BigDecimal initialPrice, Long flightId, BigDecimal ticketPrice, int availableSeats, int saleTicketCount, int maxSeats) {
        this.initialPrice = initialPrice;
        this.flightId = flightId;
        this.ticketPrice = ticketPrice;
        this.availableSeats = availableSeats;
        this.saleTicketCount = saleTicketCount;
        this.maxSeats = maxSeats;
    }

    public FlightManager() {
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public BigDecimal getMinTicketPrice() {

        return initialPrice.multiply(BigDecimal.valueOf(0.25)).setScale(2, RoundingMode.HALF_UP);
    }
    public BigDecimal getMaxTicketPrice() {

        return initialPrice.multiply(BigDecimal.valueOf(5)).setScale(2, RoundingMode.HALF_UP);
    }

    public void setTicketPrice(BigDecimal ticketPrice) {
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

    public BigDecimal getInitialPrice() {
        return initialPrice;
    }

    public void setInitialPrice(BigDecimal initialPrice) {
        this.initialPrice = initialPrice;
    }


    public void updateTicketPrice() {

        if (availableSeats <= 0) {
            return;
        }

        log.info("Başlangıç fiyatı: {}", ticketPrice);
        log.error("initial price {}", initialPrice);

        BigDecimal stc = saleTicketCount > 10 ? BigDecimal.valueOf(0.1)
                : saleTicketCount >= 2 ? BigDecimal.valueOf(0.06)
                : saleTicketCount == 1 ? BigDecimal.valueOf(0.03)
                : saleTicketCount == 0 ? BigDecimal.valueOf(-0.1)
                : BigDecimal.valueOf(1);

        log.error( "stc {}", stc);

        BigDecimal seats = (BigDecimal.valueOf(maxSeats).multiply(BigDecimal.valueOf(0.05))
                .compareTo(BigDecimal.valueOf(availableSeats)) > 0) ? BigDecimal.valueOf(0.23)
                : (BigDecimal.valueOf(maxSeats).multiply(BigDecimal.valueOf(0.1))
                .compareTo(BigDecimal.valueOf(availableSeats)) > 0) ? BigDecimal.valueOf(0.18)
                : (BigDecimal.valueOf(maxSeats).multiply(BigDecimal.valueOf(0.2))
                .compareTo(BigDecimal.valueOf(availableSeats)) > 0) ? BigDecimal.valueOf(0.11)
                : (BigDecimal.valueOf(maxSeats).multiply(BigDecimal.valueOf(0.3))
                .compareTo(BigDecimal.valueOf(availableSeats)) > 0) ? BigDecimal.valueOf(0.06)
                : BigDecimal.valueOf(0);
        log.error( "seats {}", seats);

        if (saleTicketCount == 0){
            seats = BigDecimal.valueOf(0);
        }

        BigDecimal priceChange = ticketPrice.multiply(stc).add(ticketPrice.multiply(seats));
        ticketPrice = ticketPrice.add(priceChange).setScale(2, RoundingMode.HALF_UP);
        log.error("priceChange {}",priceChange);
        if (ticketPrice.compareTo(getMaxTicketPrice()) >= 0) {
            ticketPrice = getMaxTicketPrice();
            log.error("if priceChange {}",ticketPrice);

        } else if (ticketPrice.compareTo(getMinTicketPrice()) <= 0) {
            ticketPrice = getMinTicketPrice();
            log.error("else if priceChange {}",ticketPrice);

        }

        log.info("Yeni fiyat: {}", ticketPrice);
    }


    @Override
    public String toString() {
        return "FlightManager{" +
                "initialPrice=" + initialPrice +
                ", flightId=" + flightId +
                ", ticketPrice=" + ticketPrice +
                ", availableSeats=" + availableSeats +
                ", saleTicketCount=" + saleTicketCount +
                ", maxSeats=" + maxSeats +
                '}';
    }
}