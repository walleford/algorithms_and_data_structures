package b04_graphs.digraph;
import java.io.File;

public class DigraphAdjListTest
{
    public static void main(String[] args)
    {
        // testing tiny graphs
        File tiny1File= new File("b04_graphs/digraph/files/tinyDG.txt");
        Digraph tiny1Graph = DigraphAdjList.readNumberedGraph(tiny1File);
        System.out.println("TINY GRAPH 1");
        System.out.println(tiny1Graph.toString());
        // testing TINY Dag graphs
        File tiny2File= new File("b04_graphs/digraph/files/tinyDAG.txt");
        Digraph tiny2Graph = DigraphAdjList.readNumberedGraph(tiny2File);
        System.out.println("TINY GRAPH 2");
        System.out.println(tiny2Graph.toString());
        // testing MED graphs
        File mediumFile= new File("b04_graphs/digraph/files/mediumDG.txt");
        Digraph mediumGraph = DigraphAdjList.readNumberedGraph(mediumFile);
        System.out.println("MEDIUM GRAPH");
        System.out.println(mediumGraph.toString());

    }
}
