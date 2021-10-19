package com.sitecore.interview.models;

import java.util.Objects;

public class Airport {

    private String iata;

    public Airport(String iata) {
        this.iata = iata;
    }

    public String getIata() {
        return iata;
    }

    public void setIata(String iata) {
        this.iata = iata;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airport airport = (Airport) o;
        return iata.equals(airport.iata);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iata);
    }
}
