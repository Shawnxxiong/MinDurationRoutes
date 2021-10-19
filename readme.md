## Algorithm

The algorithm used under the hood is Dijkstra's find shortest path [algorithm](https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm).

The algorithm starts from source node (airport), and will visit every node that is connected to the starting node directly (adjacent/neighbour node).

When visiting each neighbour node, it will see if below 2 information need to be updated:
1. The current accumulative duration of the node
2. Pick the node that gives lowest accumulative duration.

### Psuedo code

```
// initialize
Map<Airport, Integer> accumulativeDuration // airport -> duration
Set<Airport> toVisitNext // a list of nodes to visit
Set<Route> solution

accumulativeDuration.put(startNode, 0)
toVisitNext.add(startNode)

// find minimum duration route

while(toVisitNext.size > 0) {
  Airport toVisit = getMinDuration(accumulativeDuraion, toVisitNext); // Decide which one to visit by their current accumulative duration
  toVisitNext.remove(toVisit)
  
  // Get adjacent neighbours of the node
  List<Airports> adjacentAirports = getAdjacentAirports(toVisit)
  
  for (Airport adjacentAirport: adjacentAirports) {
    if (getCurrentDuration(adjacentAirport) > getCurrentDuration(toVisit) + getDistanceBetween(adjacentAirport, toVisit)) {
      
      // update accumulative duration of the adjacent node
      accumulativeDuration.put(adjacentAirport, getCurrentDuration(toVisit) + getDistanceBetween(adjacentAirport, toVisit))
      
      // update the ultimate solution if a route with less duration.
        // 1. delete the route that pointing towards the adjacent node
        // 2. add the current route toVisit -> adjacentAirport
      
      // put the node into next visit list
      toVisitNext.add(adjacentAirports)
      
    }
  }
}

```

### Time complexity

Since this approach is node based, and there are 2 level of loops (outer loop going through all the nodes, inner loop pick next node to visit based on duration).
So it is `O(N^2)`, with N being the number of nodes.

### Other approaches

There are other alternatives such as [Bellman-ford's](https://en.wikipedia.org/wiki/Bellman%E2%80%93Ford_algorithm) and [Floyd Warshall's](https://en.wikipedia.org/wiki/Floyd%E2%80%93Warshall_algorithm).
But I found Dijkstra's approach most efficient in terms of time complexity.

## Running

In order to get the result, you'll need either 1 or 2 input arguments.

If only 1 argument is given, it'll be deemed as the start and destination airports. It has to be in the format of: `{start_airport}-{desitnation_airport}`.
For example `DUB-SYD`. It's case-sensitive.

If 2 arguments are provided, the first argument will be deemed as the start and destination airports, in the aforementioned format.
The second argument will be the absolute file location of the graph, in `csv` format (e.g. `/Users/shawnxxiong/develop/graph.csv`).
The delimiter of the csv file has to be comma `,`.

## Running in local

### Prerequisites
jdk 11
Apache Maven (I'm using version 3.6.3), but any version above 3.6.0 should be ok. For example 3.8.3 is being used when building docker image.

### Run
`cd /{project_dir} && java -jar target/interview-1.0-SNAPSHOT.jar {input_args}`

Example:

```java -jar target/interview-1.0-SNAPSHOT.jar DUB-SYD```

or

```java -jar target/intewview-1.0-SNAPSHOT.jar DUB-SYD /Users/shanwxxiong/develop/routes.csv```

## Running in docker

### Prerequisites
Install docker from [here](https://docs.docker.com/get-docker).

### Run
1. Create docker image by command `docker build -f /path/to/Dockerfile {jar_name} .`
   
Example:
    Go to the project directory then run `docker build -f Dockerfile interview .`. Please note this step might take up to couple of minutes especially the image is being built for the first time.

2. Run app `docker run -it {jar_name} {input_args}`
   
Example:

```docker run -it interview DUB-SYD```

or

```docker run -it interview DUB-SYD /Users/shawnxxiong/develop/routes.csv```