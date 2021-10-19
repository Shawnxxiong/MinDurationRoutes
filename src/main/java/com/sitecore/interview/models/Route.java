package com.sitecore.interview.models;

import java.util.Objects;

public class Route {

    private Airport start;
    private Airport destination;

    private Integer duration;

    public Route(Airport start, Airport destination, Integer duration) {
        this.start = start;
        this.destination = destination;
        this.duration = duration;
    }

    public Airport getStart() {
        return start;
    }

    public void setStart(Airport start) {
        this.start = start;
    }

    public Airport getDestination() {
        return destination;
    }

    public void setDestination(Airport destination) {
        this.destination = destination;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return start.equals(route.start) && destination.equals(route.destination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, destination, duration);
    }

    @Override
    public String toString() {
        return this.getStart().getIata() + " --- " + this.getDestination().getIata() + "(" + this.getDuration() + ")";
    }
}
