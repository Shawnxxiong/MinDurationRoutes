package com.sitecore.interview.models;

import java.util.List;
import java.util.Set;

public class Graph {

    private Set<Route> routes;

    public Graph(Set<Route> routes) {
        this.routes = routes;
    }

    public Set<Route> getRoutes() {
        return routes;
    }
}
