package com.airline.common_notification.model.dto.response;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public class TicketResponse {

    private Long ticketId;

    private String firstName;

    private String lastName;

    private String email;

    private AirlineResponse from;

    private AirlineResponse to;

    private LocalDateTime arrivalDate;

    private LocalDateTime departureDate;

    private BigDecimal price;

    private Long flightId;

    public TicketResponse() {
    }

    public TicketResponse(Long ticketId, String firstName, String lastName, String email, AirlineResponse from, AirlineResponse to, LocalDateTime arrivalDate, LocalDateTime departureDate, BigDecimal price, Long flightId) {
        this.ticketId = ticketId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.from = from;
        this.to = to;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.price = price;
        this.flightId = flightId;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public void setArrivalDate(LocalDateTime arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public void setDepartureDate(LocalDateTime departureDate) {
        this.departureDate = departureDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public LocalDateTime getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDateTime(LocalDateTime departureDate) {
        this.departureDate = departureDate;
    }

    public LocalDateTime getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDateTime(LocalDateTime arrivalDate) {
        this.arrivalDate = arrivalDate;
    }


    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    @Override
    public String toString() {
        return "TicketResponse{" +
                "ticketId=" + ticketId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", from=" + from +
                ", to=" + to +
                ", arrivalDate=" + arrivalDate +
                ", departureDate=" + departureDate +
                ", price=" + price +
                ", flightId=" + flightId +
                '}';
    }
}