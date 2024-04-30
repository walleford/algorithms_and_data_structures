package b04_graphs.undirected.shortestpath;

import a02_analysis.s1_time.Stopwatch;
import a02_analysis.s1_time.TimeAnalysis;
import b04_graphs.undirected.UndirectedGraphAdjList;

import java.io.File;
import java.util.Date;

public class ShortestPathAnalysis
{

    public static void testGraph(String fileName, int numberOfExecutions)
    {
        Stopwatch watch = new Stopwatch();
        UndirectedGraphAdjList graph = null;
        TimeAnalysis taToCreateGraph = new TimeAnalysis("create", numberOfExecutions);
        TimeAnalysis taForShortestPathDFS= new TimeAnalysis("DFS", numberOfExecutions);
        TimeAnalysis taForShortestPathBFS  = new TimeAnalysis("BFS", numberOfExecutions);
        for (int trial = 0; trial < numberOfExecutions; trial++)
        {
            watch.startWatch();
            File file = new File(fileName);
            graph = UndirectedGraphAdjList.readNumberedGraph(file);
            long timeToCreateGraph = watch.elapsedTime();
            taToCreateGraph.add(timeToCreateGraph);

            watch.startWatch();
            ShortestPath spDFS = new ShortestPathDFS(graph, 0);
            long timeForShortestPathDFS = watch.elapsedTime();
            taForShortestPathDFS.add(timeForShortestPathDFS);

            watch.startWatch();
            ShortestPath spBFS = new ShortestPathBFS(graph, 0);
            long timeForShortestPathBFS = watch.elapsedTime();
            taForShortestPathBFS.add(timeForShortestPathBFS);
        }

        System.out.println("Graph with "
                + graph.getNumberOfVertices() + " vertices and "
                + graph.getNumberOfEdges() + " edges");
        System.out.println("Create Graph: "+taToCreateGraph.getMeanTime());
        System.out.println("Shortest Path DFS: "+taForShortestPathDFS.getMeanTime());
        System.out.println("Shortest Path BFS: "+taForShortestPathBFS.getMeanTime());
    }

    public static void randomTest(int numberOfVertices, int numberOfEdges, int numberOfExecutions)
    {
        Stopwatch watch = new Stopwatch();
        UndirectedGraphAdjList graph = null;
        TimeAnalysis taToCreateGraph = new TimeAnalysis("create", numberOfExecutions);
        TimeAnalysis taForShortestPathDFS= new TimeAnalysis("DFS", numberOfExecutions);
        TimeAnalysis taForShortestPathBFS  = new TimeAnalysis("BFS", numberOfExecutions);
        for (int trial = 0; trial < numberOfExecutions; trial++)
        {
            watch.startWatch();
            graph.createRandomGraph(numberOfVertices, numberOfEdges);
            long timeToCreateGraph = watch.elapsedTime();
            taToCreateGraph.add(timeToCreateGraph);

            watch.startWatch();
            for (int si = 0; si < graph.getNumberOfVertices(); si++)
            {
                ShortestPath spDFS = new ShortestPathDFS(graph, si);
            }
            long timeForShortestPathDFS = watch.elapsedTime();
            taForShortestPathDFS.add(timeForShortestPathDFS);

            watch.startWatch();
            for (int si = 0; si < graph.getNumberOfVertices(); si++)
            {
                ShortestPath spBFS = new ShortestPathBFS(graph, 0);
            }
            long timeForShortestPathBFS = watch.elapsedTime();
            taForShortestPathBFS.add(timeForShortestPathBFS);

            System.out.println("NoV   NoE  TtoC   DFS   BFS");
            System.out.printf("%3d %5d %5.0f %5.0f %5.0f \n",
                    graph.getNumberOfVertices(),
                    graph.getNumberOfEdges(),
                    taToCreateGraph.getMeanTime(),
                    taForShortestPathDFS.getMeanTime(),
                    taForShortestPathBFS.getMeanTime());
        }
    }

    public static void randomAnalysis(int minVertices, int maxVertices, int increment, int edgesPerVertex,int numberOfExecutions)
    {
        for (int numberOfVertices = minVertices; numberOfVertices <= maxVertices; numberOfVertices += increment)
        {
            randomTest(numberOfVertices, (numberOfVertices * edgesPerVertex)/2, numberOfExecutions);
        }
    }

    public static void main(String[] args)
    {
        System.out.println("D13 BFS - Task 4 - Jordan Wallingsford");
        Date date = new Date();
        System.out.println("Executed On: " + date.toString());
        System.out.println("--------------------------");
//        testGraph("/home/wheezeus/Desktop/algorithms_and_data_structures/b04_graphs/undirected/files/mediumG.txt",
//                    31);
        randomAnalysis(50,500,50,10,31);
    }
}
