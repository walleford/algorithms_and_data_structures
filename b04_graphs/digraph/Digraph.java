package b04_graphs.digraph;
import java.util.Iterator;

public interface Digraph
{
    // VERTICES
    /**
     * return the number of vertices within the graph
     * @return - int count of vertices in graph
     */
    public int getNumberOfVertices();

    /**
     * adds a vertex to a graph. Will have the index V and the graph will have V + 1 vertices after
     */
    public void addVertex();

    /**
     * removes a specified vertex, vertexIndex, from the graph at the index given.
     * @param vertexIndex - int index of a specific vertex in the graph
     */
    public void removeVertex(int vertexIndex);

    // EDGES

    /**
     * returns the number of edges in the graph (v-w and w-v)
     * @return - int count of edges in graph
     */
    public int getNumberOfEdges();

    /**
     * adds a designated edge to the graph with origin vertex1Index and destination vertex2Index
     * @param vertex1Index - int index of origin vertex
     * @param vertex2Index - int index of destination index
     */
    public void addEdge(int vertex1Index, int vertex2Index);

    /**
     * removes designated edge with source vertex1Index to destination vertex2Index
     *
     * @param vertex1Index - int source index of vertex
     * @param vertex2Index - int destination index of vertex
     */
    public void removeEdge(int vertex1Index, int vertex2Index);

    /**
     * returns true if an edge exists with source vertex1Index and destination vertex2Index
     * @param vertex1Index - int source index of vertex
     * @param vertex2Index - int destination index of vertex
     * @return - true if exists, false if otherwise
     */
    public boolean hasEdge(int vertex1Index, int vertex2Index);

    // Iterators of adjacents
    /**
     * returns the number of adjacent edges leaving this vertex
     * @param vertexIndex - int source vertex
     * @return - int count of edges
     */
    public int getNumberOfAdjacentOutEdges(int vertexIndex);

    /**
     * returns the number of adjacent edges coming in to this vertex
     * @param vertexIndex - source vertex
     * @return - int count of edges
     */
    public int getNumberOfAdjacentInEdges(int vertexIndex);

    /**
     * returns iterator object of vertices indexes from source vertex out (vi ->)
     * @param vertexIndex - int source index
     * @return Iterator object
     */
    public Iterator<Integer> getAdjacentOutVerticesIndexesIterator(int vertexIndex);

    /**
     * array of indices of vertices leaving this source
     * @param vertexIndex - int source index
     * @return int[] array of vertices
     */
    public int[] getAdjacentOutVerticesIndexesArray(int vertexIndex);

    /**
     * returns iterator object of vertices indices coming to source vertex (vertex -> sourceIndex)
     * @param vertexIndex - int source index
     * @return Iterator object
     */
    public Iterator<Integer> getAdjacentInVerticesIndexesIterator(int vertexIndex);

    /**
     * array of indices of vertices entering at this source
     * @param vertexIndex - int source index
     * @return int[] array of vertices
     */
    public int[] getAdjacentInVerticesIndexesArray(int vertexIndex);
}
