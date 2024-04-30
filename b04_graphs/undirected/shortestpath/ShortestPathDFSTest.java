package b04_graphs.undirected.shortestpath;

import b04_graphs.undirected.UndirectedGraphAdjList;
import util.array.ArrayUtility;

import java.io.File;

public class ShortestPathDFSTest
{
    public static void test(String fileName)
    {
        File file = new File(fileName);
        UndirectedGraphAdjList graph = UndirectedGraphAdjList.readNumberedGraph(file);
        System.out.println("GRAPH: ");
        System.out.println(graph.toString());
        for (int vi = 0; vi < graph.getNumberOfVertices(); vi++)
        {
            ShortestPath sp = new ShortestPathDFS(graph, vi);
            for (int di = 0; di < graph.getNumberOfVertices(); di++)
            {
                if (sp.hasPathTo(di))
                    System.out.println("Path "+vi+" to "+di+" is "
                    + ArrayUtility.toString(sp.getPathTo(di), "{",",","}"));
            }
        }
    }

    public static void main(String[] args)
    {
        test("/home/wheezeus/Desktop/algorithms_and_data_structures/b04_graphs/undirected/files/tinyG.txt");
    }
}
