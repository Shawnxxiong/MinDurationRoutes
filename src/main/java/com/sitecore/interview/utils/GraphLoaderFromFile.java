package com.sitecore.interview.utils;

import com.sitecore.interview.exceptions.ErrorInputGraphException;
import com.sitecore.interview.models.Graph;

import java.io.IOException;

public class GraphLoaderFromFile extends GraphLoaderStrategy implements IGraphLoader {

    public GraphLoaderFromFile(String fileLoc) {
        super(fileLoc);
    }

    @Override
    public Graph getGraph() throws IOException, ErrorInputGraphException {
        CSVReader csvReader = new CSVReader();
        return csvReader.createGraphFromFile(super.getFileLoc());
    }
}
