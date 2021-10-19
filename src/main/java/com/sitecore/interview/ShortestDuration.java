package com.sitecore.interview;

import com.sitecore.interview.algorithms.FindMinDuration;
import com.sitecore.interview.models.Airport;
import com.sitecore.interview.models.Graph;
import com.sitecore.interview.models.Itinerary;
import com.sitecore.interview.models.Route;
import com.sitecore.interview.utils.GraphLoaderStrategy;

import java.util.Set;
import java.util.HashSet;
import java.util.Optional;
import java.util.List;

public class ShortestDuration {

    public static void main(String[] args) throws Exception {

        String start = null;
        String destination = null;
        String fileLoc = null;

        if (args.length != 1 && args.length != 2) {
            throw new RuntimeException("Wrong input arguments");
        }

        start = args[0].split("-")[0].trim();
        destination = args[0].split("-")[1].trim();

        if (args.length == 2) {
            fileLoc = args[1];
        }

        System.out.println("File loc: " + fileLoc);

        GraphLoaderStrategy graphLoaderStrategy = new GraphLoaderStrategy(fileLoc);
        Graph graph = graphLoaderStrategy.getGraphLoader().getGraph();

        FindMinDuration findMinDuration = new FindMinDuration(graph, new Airport(start), new Airport(destination));
        findMinDuration.generateMinDurationRoutes();

        System.out.println(
                Optional.ofNullable(findMinDuration.generateItinerary()).orElse(new Itinerary(List.of())).toString()
        );

    }
}
