package com.sitecore.interview.utils;

import com.sitecore.interview.models.Airport;
import com.sitecore.interview.models.Graph;
import com.sitecore.interview.models.Route;

import java.util.HashSet;
import java.util.Set;

public class GraphLoaderDefault extends GraphLoaderStrategy implements IGraphLoader {

    private final Graph defaultGraph;

    public GraphLoaderDefault() {
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

        defaultGraph = new Graph(routes);
    }

    @Override
    public Graph getGraph() {
        return defaultGraph;
    }

}
