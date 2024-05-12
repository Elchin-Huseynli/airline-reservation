package com.airline.booking_ms.model.dto.request;

import lombok.Builder;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Builder
@Validated
public class UserRequest {

    @NotNull(message = "FIRSTNAME_URGENT")
    private String firstName;

    @NotNull(message = "LASTNAME_URGENT")
    private String lastName;

    @NotNull(message = "EMAIL_URGENT")
    @Email
    private String email;

    @Past
    private LocalDate birthdate;

    @Pattern(
            regexp = "^[0-9a-zA-Z]{7}$",
            message = "INVALID_FIN"
    )

    private String fin;

    @Pattern(
            regexp = "^(?:AA\\d{7}|AZE\\d{7})$",
            message = "INVALID_SERIAL_NUMBER"
    )
    private String serialNumber;


    public UserRequest() {
    }

    public UserRequest(String firstName, String lastName, String email, LocalDate birthdate, String fin, String serialNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthdate = birthdate;
        this.fin = fin;
        this.serialNumber = serialNumber;
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

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}