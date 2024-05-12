package com.airline.common_ms.model.dao;

import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

public class Airline {
    public static Airline HEYDAR_ALIYEV = new Airline(1L, "Azerbaijan", "Heydar Alyev Airport");
    public static Airline FUZULI = new Airline(2L, "Azerbaijan", "Fuzili Airport");
    public static Airline GANCA = new Airline(3L, "Azerbaijan", "Ganca Airport");
    public static Airline SABIHA_GOKCEN = new Airline(4L, "Turkey", "Sabiha Gokcen Airport");
    public static Airline ISTANBUL = new Airline(5L, "Turkey", "Istanbul Airport");
    public static Airline ANKARA_ESENBOGA = new Airline(6L, "Turkey", "Ankara Esenboga Airport");
    public static Airline FRANCE_PARIS = new Airline(7L, "France", "Paris-charles De Gaulle");

    private Long id;
    private String country;
    private String name;

    Airline(Long id, String country, String name) {
        this.id = id;
        this.country = country;
        this.name = name;
    }

    public Airline() {
    }



    public static List<Airline> values = new CopyOnWriteArrayList<>();

    static {
        addAllAirlines();
    }

    @SneakyThrows
    private static void addAllAirlines() {
        Field[] fields = Airline.class.getDeclaredFields();

        for (Field field : fields) {
            if (field.getType().equals(Airline.class)) {
                addAirline((Airline) field.get(null));
            }
        }
    }

    private static void addAirline(Airline airline) {
        values.add(airline);
    }

    public Long getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Id: " + id +
                "\nCountry: " + country +
                "\nName: " + name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airline airline = (Airline) o;
        return Objects.equals(id, airline.id) && Objects.equals(country, airline.country) && Objects.equals(name, airline.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, country, name);
    }
}
