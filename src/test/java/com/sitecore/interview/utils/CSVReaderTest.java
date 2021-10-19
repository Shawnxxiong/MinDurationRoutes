package com.sitecore.interview.utils;

import com.sitecore.interview.exceptions.ErrorInputGraphException;
import com.sitecore.interview.models.Airport;
import com.sitecore.interview.models.Graph;
import com.sitecore.interview.models.Route;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CSVReaderTest {

    CSVReader classUnderTest = new CSVReader();

    @Test
    void create_from_line_happy_path() throws Exception {
        String validLine = "DUB,CDG,10";
        Route expected = new Route(new Airport("DUB"), new Airport("CDG"), 10);
        Route actual = classUnderTest.createRouteFromLine(validLine);

        assertEquals(expected, actual);
    }

    @Test
    void create_from_line_unhappy_wrong_number_of_args() {
        String invalidLine = "DUB,CDG";
        assertThrows(ErrorInputGraphException.class, () -> classUnderTest.createRouteFromLine(invalidLine));
    }

    @Test
    void create_from_line_unhappy_wrong_number_format() {
        String invalidLine = "DUB,CDG,SYD";
        assertThrows(ErrorInputGraphException.class, () -> classUnderTest.createRouteFromLine(invalidLine));
    }

    @Test
    void create_from_line_unhappy_wrong_delimiter() {
        String invalidLine = "DUB CDG SYD";
        assertThrows(ErrorInputGraphException.class, () -> classUnderTest.createRouteFromLine(invalidLine));
    }

    @Test
    void create_from_file_happy_path() throws Exception {
        Set<Route> expectedRoutes = new HashSet<>();
        expectedRoutes.add(new Route(new Airport("DUB"), new Airport("LHR"), 10));
        expectedRoutes.add(new Route(new Airport("BKK"), new Airport("SYD"), 16));

        Graph expected = new Graph(expectedRoutes);

        String routesCSV = "routes.csv";
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(routesCSV).getFile());

        Graph actual = classUnderTest.createGraphFromFile(file.getAbsolutePath());

        assertEquals(expected.getRoutes(), actual.getRoutes());
    }
}
