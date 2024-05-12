package com.airline.booking_ms.model.entity;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketId;

    private String firstName;

    private String lastName;

    private String email;

    private LocalDate birthday;

    private String fin;

    private String serialNumber;

    private Long toAirlineId;

    private Long fromAirlineId;

    private LocalDateTime arrivalDate;

    private LocalDateTime departureDate;

    private BigDecimal price;

    private Long flightId;

    private Long userId;

    //FIXME pay attention status owing to departure date


    public Ticket(Long ticketId, String firstName, String lastName, String email, LocalDate birthday, String fin, String serialNumber, Long toAirlineId, Long fromAirlineId, LocalDateTime arrivalDate, LocalDateTime departureDate, BigDecimal price, Long flightId, Long userId) {
        this.ticketId = ticketId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthday = birthday;
        this.fin = fin;
        this.serialNumber = serialNumber;
        this.toAirlineId = toAirlineId;
        this.fromAirlineId = fromAirlineId;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.price = price;
        this.flightId = flightId;
        this.userId = userId;
    }

    public Ticket() {
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getFin() {
        return fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public LocalDateTime getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDateTime arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public LocalDateTime getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDateTime departureDate) {
        this.departureDate = departureDate;
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

    public Long getToAirlineId() {
        return toAirlineId;
    }

    public void setToAirlineId(Long toAirlineId) {
        this.toAirlineId = toAirlineId;
    }

    public Long getFromAirlineId() {
        return fromAirlineId;
    }

    public void setFromAirlineId(Long fromAirlineId) {
        this.fromAirlineId = fromAirlineId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId=" + ticketId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", birthday=" + birthday +
                ", fin='" + fin + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", toAirlineId=" + toAirlineId +
                ", fromAirlineId=" + fromAirlineId +
                ", arrivalDate=" + arrivalDate +
                ", departureDate=" + departureDate +
                ", price=" + price +
                ", flightId=" + flightId +
                ", userId=" + userId +
                '}';
    }
}