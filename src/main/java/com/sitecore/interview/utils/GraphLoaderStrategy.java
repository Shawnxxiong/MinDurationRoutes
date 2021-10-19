package com.sitecore.interview.utils;

public class GraphLoaderStrategy {

    private String fileLoc;

    public GraphLoaderStrategy() {

    }

    public GraphLoaderStrategy(String fileLoc) {
        this.fileLoc = fileLoc;
    }

    public String getFileLoc() {
        return fileLoc;
    }

    public IGraphLoader getGraphLoader() {
        if (this.fileLoc != null) {
            return new GraphLoaderFromFile(this.fileLoc);
        } else {
            return new GraphLoaderDefault();
        }
    }

}
