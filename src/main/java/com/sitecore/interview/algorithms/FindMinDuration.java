package com.sitecore.interview.algorithms;

import com.sitecore.interview.models.Airport;
import com.sitecore.interview.models.Graph;
import com.sitecore.interview.models.Itinerary;
import com.sitecore.interview.models.Route;

import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Collections;
import java.util.stream.Collectors;

public class FindMinDuration {

    private Set<Route> minDurationRoutes;

    private Set<Route> allRoutes;

    private Set<Airport> toVisit;

    private Map<Airport, Integer> accumulativeDuration;
    private Map<Airport, Airport> predecessorsMap;

    private Airport start;
    private Airport destination;

    public FindMinDuration(Graph graph, Airport start, Airport destination) {
        this.allRoutes = graph.getRoutes();
        this.toVisit = new HashSet<>();
        this.accumulativeDuration = new HashMap<>();
        this.minDurationRoutes = new HashSet<>();
        this.predecessorsMap = new HashMap<>();

        this.start = start;
        this.destination = destination;
    }

    public void generateMinDurationRoutes() {
        accumulativeDuration.put(start, 0);
        toVisit.add(start);

        while(toVisit.size() > 0) {
            Airport airportToVisit = getMinDurationAirport(toVisit);
            toVisit.remove(airportToVisit);
            findMinDuration(airportToVisit);
        }
    }

    private void findMinDuration(Airport airport) {
        List<Airport> neighbourAirports = getNeighbourAirports(airport);
        for (Airport neighbourAirport: neighbourAirports) {
            Route currentRoute = allRoutes.stream()
                    .filter(route -> route.getStart().equals(airport) && route.getDestination().equals(neighbourAirport))
                    .findFirst().orElseThrow(() -> new RuntimeException("Should not happen"));

            if (getCurrentDistance(neighbourAirport) > getCurrentDistance(airport) + currentRoute.getDuration()) {
                accumulativeDuration.put(neighbourAirport, getCurrentDistance(airport) + currentRoute.getDuration());
                this.predecessorsMap.put(neighbourAirport, airport);
                this.minDurationRoutes.removeAll(this.minDurationRoutes.stream().filter(r -> r.getDestination().equals(neighbourAirport)).collect(Collectors.toList()));
                this.minDurationRoutes.add(currentRoute);
                this.toVisit.add(neighbourAirport);
            }
        }
    }

    private Airport getMinDurationAirport(Set<Airport> airports) {
        Airport minDurationAirport = airports.stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Should not happen"));

        for (Airport airport: airports) {
            if (getCurrentDistance(airport) < getCurrentDistance(minDurationAirport)) {
                minDurationAirport = airport;
            }
        }

        return minDurationAirport;
    }

    private List<Airport> getNeighbourAirports(Airport airport) {
        return this.allRoutes.stream()
                .filter(route -> route.getStart().equals(airport))
                .map(route -> route.getDestination())
                .collect(Collectors.toList());
    }

    private Integer getCurrentDistance(Airport airport) {
        return Optional.ofNullable(this.accumulativeDuration.get(airport)).orElse(Integer.MAX_VALUE);
    }

    public Itinerary generateItinerary() {
        List<Route> routes = new LinkedList<>();

        Route route =
                this.minDurationRoutes.stream()
                        .filter(r -> r.getDestination().equals(destination))
                        .findAny()
                        .orElse( null);

        if (route == null) {
            return null;
        }

        routes.add(route);

        while(!route.getStart().equals(start)) {
            Airport predecessorRouteDest = route.getStart();
            route = this.minDurationRoutes.stream()
                    .filter(r -> r.getDestination().equals(predecessorRouteDest))
                    .findFirst().orElseThrow(() -> new RuntimeException("Should not happen"));
            routes.add(route);
        }

        Collections.reverse(routes);
        Itinerary itinerary = new Itinerary(routes);
        itinerary.setTotalTime(accumulativeDuration.get(destination));

        return itinerary;

    }
}
