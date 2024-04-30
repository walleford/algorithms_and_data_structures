package b04_graphs.undirected.shortestpath;

import b04_graphs.undirected.UndirectedGraph;

public interface ShortestPath
{
    // get the specified graph
    public UndirectedGraph getGraph();
    // for a given graph, get the source vertex
    public int getSourceVertex();
    // check whether there is a path to a specific vertex from a source
    public boolean hasPathTo(int vertex);
    // returns the indexes of the vertices along the path from source to destination
    public int[] getPathTo(int vertex);
}
