package b04_graphs.undirected;
import java.io.File;
public class UndirectedGraphAdjListTest
{
    public static void main(String[] args)
    {
        File tinyFile = new File("C:\\Users\\Jordan\\ait512\\b04_graphs\\undirected\\files\\largeG.txt");
        UndirectedGraphAdjList tinyGraph = UndirectedGraphAdjList.readNumberedGraph(tinyFile);
        File mediumFile = new File("C:\\Users\\Jordan\\ait512\\b04_graphs\\undirected\\files\\mediumG.txt");
        UndirectedGraphAdjList mediumGraph = UndirectedGraphAdjList.readNumberedGraph(mediumFile);
        File largeFile = new File("C:\\Users\\Jordan\\ait512\\b04_graphs\\undirected\\files\\tinyG.txt");
        UndirectedGraphAdjList largeGraph = UndirectedGraphAdjList.readNumberedGraph(largeFile);
        System.out.println("TINY GRAPH");
        System.out.println(tinyGraph.toString());
        System.out.println("MEDIUM GRAPH");
        System.out.println(mediumGraph.toString());
        System.out.println("LARGE GRAPH");
        System.out.println(largeGraph.toString());
    }
}
