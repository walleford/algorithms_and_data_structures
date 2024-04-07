package b04_graphs.undirected;
import java.io.File;
import util.array.ArrayUtility;
public class ConnectedComponentDFSTest
{
    public static void test(String fileName)
    {
        File tinyFile = new File(fileName);
        UndirectedGraphAdjList graph = UndirectedGraphAdjList.readNumberedGraph(tinyFile);
        System.out.println("GRAPH: ");
        System.out.println(graph.toString());
        for (int vi = 0; vi < graph.getNumberOfVertices(); vi++)
        {
            ConnectedComponentDFS cc = new ConnectedComponentDFS(graph, vi);
            System.out.println(vi + ":" + ArrayUtility.toString(cc.getConnectedComponent(),"{",",","}"));
        }
    }

    public static void main(String[] args)
    {
        //test("C:\\Users\\Jordan\\ait512\\b04_graphs\\undirected\\files\\largeG.txt");
        test("C:\\Users\\Jordan\\ait512\\b04_graphs\\undirected\\files\\tinyG.txt");
        //test("C:\\Users\\Jordan\\ait512\\b04_graphs\\undirected\\files\\mediumG.txt");

    }
}
