package com.airline.user_ms.model.dto.request;

import lombok.Builder;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;
import java.time.LocalDate;


@Builder
@Validated
public class UserRequest {

    @NotBlank(message = "FIRST_NAME_IS_URGENT")
    private String firstName;

    @NotBlank(message = "LAST_NAME_IS_URGENT")
    private String lastName;

    @NotBlank(message = "USERNAME_IS_URGENT")
    private String username;

    @NotBlank(message = "PASSWORD_IS_NOT_VALID")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "PASSWORD_REGEX"
    )
    private String password;

    @Pattern(
            regexp = "^(\\+[0-9]{1,4}[-.\\s]?)?((\\([0-9]{1,4}\\))|[0-9]{1,4})[-.\\s]?[0-9]{1,14}$",
            message = "INVALID_PHONE"
    )
    private String phoneNumber;

    @NotBlank(message = "EMAIL_IS_URGENT")
    @Email(message = "EMAIL_IS_NOT_VALID")
    private String email;

    @Past(message = "INVALID_BIRTHDATE")
    private LocalDate birthdate;

    @Pattern(
            regexp = "^[0-9a-zA-Z]{7}$",
            message = "INVALID_FIN"
    )

    private String fin;

    @Pattern(
            regexp = "^(?:AA\\d{7}|AZE\\d{8})$",
            message = "INVALID_SERIAL_NUMBER"
    )
    private String serialNumber;


    public UserRequest(String firstName, String lastName, String username, String password, String phoneNumber, String email, LocalDate birthdate, String fin, String serialNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.birthdate = birthdate;
        this.fin = fin;
        this.serialNumber = serialNumber;
    }

    public UserRequest() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public void setFin(String finCode) {
        this.fin = finCode;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }


    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "UserRequest{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", birthdate=" + birthdate +
                ", fin='" + fin + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                '}';
    }
}
