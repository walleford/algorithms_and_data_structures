package b04_graphs.undirected;

import java.util.Iterator;

public interface UndirectedGraph
{
    // VERTICES
    public int getNumberOfVertices();

    public void addVertex();
    public void removeVertex(int vertexIndex);

    // EDGES
    public int getNumberOfEdges();
    public int getNumberOfAdjacentEdges(int vertexIndex);
    public Iterator<Integer> getAdjacentVerticesIndexesIterator(int vertexIndex);
    public int[] getAdjacentVerticesIndexesArray(int vertexIndex);
    public void addEdge(int vertex1Index, int vertex2Index);
    public void removeEdge(int vertex1Index, int vertex2Index);
    // tests whether or not an edge between two vertices exists in the graph
    public boolean hasEdge(int vertex1Index, int vertex2Index);
}
