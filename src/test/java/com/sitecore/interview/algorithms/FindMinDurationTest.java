package com.sitecore.interview.algorithms;

import com.sitecore.interview.algorithms.FindMinDuration;
import com.sitecore.interview.models.Airport;
import com.sitecore.interview.models.Graph;
import com.sitecore.interview.models.Itinerary;
import com.sitecore.interview.models.Route;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindMinDurationTest {

    static Graph completeGraph;
    static Graph nonConnectedGraph;
    static Graph cyclicGraph;

    FindMinDuration findMinDuration;

    @BeforeAll
    static void beforeAll() {
        Set<Route> routes = new HashSet<>();

        routes.add(new Route(new Airport("DUB"), new Airport("LHR"), 1));
        routes.add(new Route(new Airport("DUB"), new Airport("CDG"), 2));
        routes.add(new Route(new Airport("CDG"), new Airport("BOS"), 6));
        routes.add(new Route(new Airport("CDG"), new Airport("BKK"), 9));
        routes.add(new Route(new Airport("ORD"), new Airport("LAS"), 2));
        routes.add(new Route(new Airport("LHR"), new Airport("NYC"), 5));
        routes.add(new Route(new Airport("NYC"), new Airport("LAS"), 3));
        routes.add(new Route(new Airport("BOS"), new Airport("LAX"), 4));
        routes.add(new Route(new Airport("LHR"), new Airport("BKK"), 9));
        routes.add(new Route(new Airport("BKK"), new Airport("SYD"), 11));
        routes.add(new Route(new Airport("LAX"), new Airport("LAS"), 2));
        routes.add(new Route(new Airport("DUB"), new Airport("ORD"), 6));
        routes.add(new Route(new Airport("LAX"), new Airport("SYD"), 13));
        routes.add(new Route(new Airport("LAS"), new Airport("SYD"), 14));

        completeGraph = new Graph(routes);

        Set<Route> nonConnectedRoutes = new HashSet<>();
        nonConnectedRoutes.add(new Route(new Airport("DUB"), new Airport("LHR"), 1));
        nonConnectedRoutes.add(new Route(new Airport("CDG"), new Airport("BKK"), 9));

        nonConnectedGraph = new Graph(nonConnectedRoutes);

        Set<Route> cyclicRoutes = new HashSet<>();

        cyclicRoutes.add(new Route(new Airport("DUB"), new Airport("LHR"), 1));
        cyclicRoutes.add(new Route(new Airport("LHR"), new Airport("CDG"), 1));
        cyclicRoutes.add(new Route(new Airport("CDG"), new Airport("DUB"), 1));

        cyclicGraph = new Graph(cyclicRoutes);
    }

    @Test
    void findMinDurationPath_Happy() {
        Airport start = new Airport("DUB");
        Airport dest = new Airport("SYD");

        findMinDuration = new FindMinDuration(completeGraph, start, dest);

        List<Route> expectedRoute = List.of(
                new Route(new Airport("DUB"), new Airport("LHR"), 1),
                new Route(new Airport("LHR"), new Airport("BKK"), 9),
                new Route(new Airport("BKK"), new Airport("SYD"), 11)
        );

        Integer expectedTime = 21;

        findMinDuration.generateMinDurationRoutes();
        Itinerary actualItinerary = findMinDuration.generateItinerary();
        List<Route> actualRoute = actualItinerary.getItinerary();
        Integer actualTime = actualItinerary.getTotalTime();

        assertEquals(expectedRoute, actualRoute);
        assertEquals(expectedTime, actualTime);
    }

    @Test
    void findMinDurationPath_happy_cyclic() {
        Airport start = new Airport("DUB");
        Airport dest = new Airport("CDG");

        findMinDuration = new FindMinDuration(cyclicGraph, start, dest);

        List<Route> expectedRoute = List.of(
                new Route(new Airport("DUB"), new Airport("LHR"), 1),
                new Route(new Airport("LHR"), new Airport("CDG"), 1)
        );

        Integer expectedTime = 2;

        findMinDuration.generateMinDurationRoutes();
        Itinerary actualItinerary = findMinDuration.generateItinerary();
        List<Route> actualRoute = actualItinerary.getItinerary();
        Integer actualTime = actualItinerary.getTotalTime();

        assertEquals(expectedRoute, actualRoute);
        assertEquals(expectedTime, actualTime);
    }

    @Test
    void findMinDurationPath_unhappy_noPathFound() {
        Airport start = new Airport("DUB");
        Airport dest = new Airport("BKK");

        findMinDuration = new FindMinDuration(nonConnectedGraph, start, dest);

        List<Route> expectedRoute = null;

        findMinDuration.generateMinDurationRoutes();
        Itinerary actualItinerary = findMinDuration.generateItinerary();

        assertEquals(expectedRoute, actualItinerary);
    }

}
