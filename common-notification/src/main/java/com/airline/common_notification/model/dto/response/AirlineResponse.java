package com.airline.common_notification.model.dto.response;



import lombok.Builder;

import java.util.Objects;

@Builder
public class AirlineResponse {

    private Long id;
    private String country;
    private String name;

    public AirlineResponse(Long id, String country, String name) {
        this.id = id;
        this.country = country;
        this.name = name;
    }

    public AirlineResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AirlineResponse that = (AirlineResponse) o;
        return Objects.equals(id, that.id) && Objects.equals(country, that.country) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, country, name);
    }

    @Override
    public String toString() {
        return "AirlineResponse{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
