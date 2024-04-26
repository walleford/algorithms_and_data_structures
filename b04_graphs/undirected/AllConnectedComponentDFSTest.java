package b04_graphs.undirected;

import util.array.ArrayUtility;

import java.io.File;

public class AllConnectedComponentDFSTest
{
    public static void test(String fileName)
    {
        File tinyFile = new File(fileName);
        UndirectedGraphAdjList graph = UndirectedGraphAdjList.readNumberedGraph(tinyFile);
        System.out.println("GRAPH: ");
        System.out.println(graph.toString());
        AllConnectedComponents allCC = new AllConnectedComponents(graph);

        System.out.println("ALL CONNECTED COMPONENTS:");
        for (int cci = 0; cci < allCC.getNumberOfConnectedComponents(); cci++)
        {
            System.out.println(cci + ":" + ArrayUtility.toString(allCC.getConnectedComponent(cci),"{",",","}"));
        }
    }

    public static void main(String[] args)
    {
        //test("C:\\Users\\Jordan\\ait512\\b04_graphs\\undirected\\files\\largeG.txt");
        test("/home/wheezeus/Desktop/algorithms_and_data_structures/b04_graphs/undirected/files/tinyG.txt");
        //test("C:\\Users\\Jordan\\ait512\\b04_graphs\\undirected\\files\\mediumG.txt");

    }
}
