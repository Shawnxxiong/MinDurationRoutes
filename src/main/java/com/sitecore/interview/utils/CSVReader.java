package com.sitecore.interview.utils;

import com.sitecore.interview.exceptions.ErrorInputGraphException;
import com.sitecore.interview.models.Airport;
import com.sitecore.interview.models.Graph;
import com.sitecore.interview.models.Route;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;

public class CSVReader {

    private final String COMMA_DELIMITER = ",";
    private final Integer ELEMENTS_PER_LINE = 3;

    public Graph createGraphFromFile(String fileLoc) throws IOException, ErrorInputGraphException {
        Set<Route> routes = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileLoc))) {
            String line;
            while((line = br.readLine()) != null) {
                routes.add(createRouteFromLine(line));
            }
        }

        return new Graph(routes);
    }

    public Route createRouteFromLine(String line) throws ErrorInputGraphException {
        Route route;
        String[] values = line.split(COMMA_DELIMITER);
        if (values.length != ELEMENTS_PER_LINE) {
            throw new ErrorInputGraphException("Every line must has 3 elements: start, destination, duration");
        }

        try {
            route = new Route(new Airport(values[0]), new Airport(values[1]), Integer.valueOf(values[2]));
        } catch (NumberFormatException ex) {
            throw new ErrorInputGraphException("Duration format is wrong: " + values[2]);
        }

        return route;
    }
}
