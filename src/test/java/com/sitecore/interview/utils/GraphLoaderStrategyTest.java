package com.sitecore.interview.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GraphLoaderStrategyTest {

    GraphLoaderStrategy classUnderTest;

    @Test
    void return_correct_strategy_when_fileLoc_specified() {
        classUnderTest = new GraphLoaderStrategy("random_loc");
        assertTrue(classUnderTest.getGraphLoader() instanceof GraphLoaderFromFile);
    }

    @Test
    void return_correct_strategy_when_fileLoc_not_specified() {
        classUnderTest = new GraphLoaderStrategy();
        assertTrue(classUnderTest.getGraphLoader() instanceof GraphLoaderDefault);
    }
}
