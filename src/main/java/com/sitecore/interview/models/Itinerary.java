package com.sitecore.interview.models;

import java.util.List;

public class Itinerary {

    List<Route> itinerary;
    Integer totalTime;

    public Itinerary(List<Route> routes) {
        this.itinerary = routes;
    }

    public Integer getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Integer totalTime) {
        this.totalTime = totalTime;
    }

    public List<Route> getItinerary() {
        return itinerary;
    }

    public void setItinerary(List<Route> itinerary) {
        this.itinerary = itinerary;
    }

    public String generateItinerary() {
        StringBuilder sb = new StringBuilder();
        return sb.toString();
    }

    @Override
    public String toString() {
        if (this.itinerary == null || this.itinerary.size() == 0) {
            return "No route found";
        }

        StringBuilder itineraryStr = new StringBuilder();
        for (Route route: this.itinerary) {
            itineraryStr.append(route.toString() + System.lineSeparator());
        }
        itineraryStr.append("time: ").append(this.totalTime);

        return itineraryStr.toString();
    }
}
