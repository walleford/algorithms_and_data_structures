package b04_graphs.digraph;

import java.util.Iterator;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * A directed graph implementation using adjacency lists.
 * Implements the digraph interface methods
 *
 * @author Jordan Wallingsford
 */
public class DigraphAdjList implements Digraph
{
    // used to calculate when to half the capacity of the graph to save memory
    private static final double HALF_CAPACITY_TRIGGER = 0.2;

    // initial capacity of the adjacent vertices
    private static final int INITIAL_ADJACENCY_CAPACITY = 10;

    // initial empty array
    private static final int[] EMPTY_ARRAY = new int[0];

    // number of vertices/edges in graph
    private int numberOfVertices;
    private int numberOfEdges;

    // minimum capacity for a graph
    private int minimumCapacity;

    // holds count of adjacent vertices both in/out
    private int[] verticesOutDegrees;
    private int[] verticesInDegrees;

    // lists used to hold adjacent vertices index both in/out
    private int[][] adjacencyOutLists;
    private int[][] adjacencyInLists;

    public DigraphAdjList(int numberOfVertices)
    {
        this.numberOfVertices = numberOfVertices;
        this.numberOfEdges = 0;
        this.minimumCapacity = numberOfVertices;
        this.verticesOutDegrees = new int[numberOfVertices];
        this.verticesInDegrees = new int[numberOfVertices];
        this.adjacencyInLists = new int[numberOfVertices][];
        this.adjacencyOutLists = new int[numberOfVertices][];
        for (int i=0; i < numberOfVertices; i++)
        {
            verticesInDegrees[i] = 0;
            verticesOutDegrees[i] = 0;
            adjacencyOutLists[i] = EMPTY_ARRAY;
            adjacencyInLists[i] = EMPTY_ARRAY;
        }
    }

    private void doubleVertexCapacity()
    {
        changeVertexCapacity(verticesOutDegrees.length * 2);
    }

    private void halfVertexCapacity()
    {
        changeVertexCapacity(verticesOutDegrees.length / 2);
    }

    /**
     * changes a graphs capacity of vertices
     * sets the vertices degrees and adjacency in/out lists to a new capacity
     */
    private void changeVertexCapacity(int newCapacity)
    {
        int[] newVerticesDegrees = new int[newCapacity];
        for (int i = 0; i < numberOfVertices; i++)
        {
            newVerticesDegrees[i] = verticesOutDegrees[i];
        }
        verticesOutDegrees = newVerticesDegrees;
        int[][] newAdjacencies = new int[newCapacity][];
        for (int i = 0; i < numberOfVertices; i++)
        {
            newAdjacencies[i] = adjacencyOutLists[i];
        }
        adjacencyOutLists = newAdjacencies;

        newVerticesDegrees = new int[newCapacity];
        for (int i = 0; i < numberOfVertices; i++)
        {
            newVerticesDegrees[i] = verticesInDegrees[i];
        }

        newAdjacencies = new int[newCapacity][];
        for (int i = 0; i < numberOfVertices; i++)
        {
            newAdjacencies[i] = adjacencyInLists[i];
        }
        adjacencyInLists = newAdjacencies;
    }

    public int getNumberOfVertices() {
        return this.numberOfVertices;
    }

    @Override
    public void addVertex() {
        // check for size correction if needed
        if (numberOfVertices==verticesInDegrees.length) {
            doubleVertexCapacity();
        }
        // initializing empty arrays for adjacent vertices for new index
        adjacencyOutLists[numberOfVertices] = EMPTY_ARRAY;
        verticesOutDegrees[numberOfVertices] = 0;
        adjacencyInLists[numberOfVertices] = EMPTY_ARRAY;
        verticesInDegrees[numberOfVertices] = 0;
        numberOfVertices++;
    }

    public void removeVertex(int vertexIndex) {
        if (vertexIndex<0||vertexIndex>=numberOfVertices) {
            throw new RuntimeException("Invalid index for a vertex: "+vertexIndex);
        }
        // removing the edges for index -> adjacent
        for(int adj:getAdjacentOutVerticesIndexesArray(vertexIndex)) {
            removeEdge(vertexIndex, adj);
        }
        // removing the edges for adjacent -> index
        for(int adj:getAdjacentInVerticesIndexesArray(vertexIndex)) {
            removeEdge(adj, vertexIndex);
        }
        // changing the index the last index for all lists
        verticesOutDegrees[vertexIndex] = verticesOutDegrees[numberOfVertices-1];
        adjacencyOutLists[vertexIndex] = adjacencyOutLists[numberOfVertices-1];
        verticesInDegrees[vertexIndex] = verticesInDegrees[numberOfVertices-1];
        adjacencyInLists[vertexIndex] = adjacencyInLists[numberOfVertices-1];
        // decrementing the number of vertices since we are removing one
        numberOfVertices--;
        // testing for minimum capacity for cutting size in half
        if (verticesOutDegrees.length >= 2 * minimumCapacity
                && HALF_CAPACITY_TRIGGER * verticesOutDegrees.length>numberOfVertices) {
            halfVertexCapacity();
        }
        for(int i=0; i<numberOfVertices; i++) {
            for(int j=0; j<verticesOutDegrees[i]; j++) {
                if (adjacencyOutLists[i][j]==numberOfVertices) {
                    adjacencyOutLists[i][j]=vertexIndex;
                }
            }
        }
        for(int i=0; i<numberOfVertices; i++) {
            for(int j=0; j<verticesInDegrees[i]; j++) {
                if (adjacencyInLists[i][j]==numberOfVertices) {
                    adjacencyInLists[i][j]=vertexIndex;
                }
            }
        }
    }

    @Override
    public int getNumberOfEdges() {
        return numberOfEdges;
    }

    @Override
    public int getNumberOfAdjacentOutEdges(int vertexIndex) {
        // returns count of adjacent edges in terms of vertexIndex -> vertices
        return verticesOutDegrees[vertexIndex];
    }

    @Override
    public int getNumberOfAdjacentInEdges(int vertexIndex) {
        // returns count of adjacent edges in term of vertices -> vertexIndex
        return verticesInDegrees[vertexIndex];
    }

    public boolean hasEdge(int vertex1Index, int vertex2Index) {
        for (int ae=0; ae<verticesOutDegrees[vertex1Index]; ae++) {
            if (vertex2Index==adjacencyOutLists[vertex1Index][ae]) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<Integer> getAdjacentOutVerticesIndexesIterator(int vertexIndex) {
        return new Iterator<Integer>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return index < verticesOutDegrees[vertexIndex];
            }

            @Override
            public Integer next() {
                if (hasNext())
                    return adjacencyOutLists[vertexIndex][index++];
                throw new RuntimeException("No more elements.");
            }

        };
    }

    @Override
    public Iterator<Integer> getAdjacentInVerticesIndexesIterator(int vertexIndex) {
        return new Iterator<Integer>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return index < verticesInDegrees[vertexIndex];
            }

            @Override
            public Integer next() {
                if (hasNext())
                    return adjacencyInLists[vertexIndex][index++];
                throw new RuntimeException("No more elements.");
            }

        };
    }

    @Override
    public int[] getAdjacentOutVerticesIndexesArray(int vertexIndex) {
        if (vertexIndex<0||vertexIndex>=numberOfVertices) {
            throw new RuntimeException("Invalid index for a vertex: "+vertexIndex);
        }
        int[] result = new int[verticesOutDegrees[vertexIndex]];
        for(int i=0; i<verticesOutDegrees[vertexIndex]; i++) {
            result[i]=adjacencyOutLists[vertexIndex][i];
        }
        return result;
    }

    @Override
    public int[] getAdjacentInVerticesIndexesArray(int vertexIndex) {
        if (vertexIndex<0||vertexIndex>=numberOfVertices) {
            throw new RuntimeException("Invalid index for a vertex: "+vertexIndex);
        }
        int[] result = new int[verticesInDegrees[vertexIndex]];
        for(int i=0; i<verticesInDegrees[vertexIndex]; i++) {
            result[i]=adjacencyInLists[vertexIndex][i];
        }
        return result;
    }

    @Override
    public void addEdge(int vertex1Index, int vertex2Index) {
        if (vertex1Index<0 || vertex1Index>=numberOfVertices) {
            throw new RuntimeException("Invalid index for the first vertex.");
        }
        if (vertex2Index<0 || vertex2Index>=numberOfVertices) {
            throw new RuntimeException("Invalid index for the second vertex.");
        }
        addDirectedEdge(vertex1Index, vertex2Index);
        numberOfEdges++;
    }

    private void addDirectedEdge(int vertex1Index, int vertex2Index) {
        // check if the current adjacency list is full
        if (verticesOutDegrees[vertex1Index]==adjacencyOutLists[vertex1Index].length) {
            // if full double the capacity
            doubleAdjacencyOutCapacity(vertex1Index);
        }
        // add the vertex2Index at the end of the adjacency list of vertex1
        // and increase the degree of vertex 1
        adjacencyOutLists[vertex1Index][verticesOutDegrees[vertex1Index]++] = vertex2Index;

        // check if the current adjacency list is full
        if (verticesInDegrees[vertex2Index]==adjacencyInLists[vertex2Index].length) {
            // if full double the capacity
            doubleAdjacencyInCapacity(vertex2Index);
        }
        // add the vertex2Index at the end of the adjacency list of vertex1
        // and increase the degree of vertex 1
        adjacencyInLists[vertex2Index][verticesInDegrees[vertex2Index]++] = vertex1Index;
    }

    @Override
    public void removeEdge(int vertex1Index, int vertex2Index) {
        if (vertex1Index<0 || vertex1Index>=numberOfVertices) {
            throw new RuntimeException("Invalid index for the first vertex.");
        }
        if (vertex2Index<0 || vertex2Index>=numberOfVertices) {
            throw new RuntimeException("Invalid index for the second vertex.");
        }
        removeDirectedEdge(vertex1Index, vertex2Index);
        numberOfEdges--;
    }

    private void removeDirectedEdge(int vertex1Index, int vertex2Index) {
        for (int i=0; i<verticesOutDegrees[vertex1Index]; i++) {
            if (adjacencyOutLists[vertex1Index][i]==vertex2Index) {
                adjacencyOutLists[vertex1Index][i]=adjacencyOutLists[vertex1Index][verticesOutDegrees[vertex1Index]-1];
            }
        }
        verticesOutDegrees[vertex1Index]--;
        if (verticesOutDegrees[vertex1Index] >= 2 * INITIAL_ADJACENCY_CAPACITY
                && HALF_CAPACITY_TRIGGER * adjacencyOutLists[vertex1Index].length >verticesOutDegrees[vertex1Index]) {
            halfAdjacencyOutCapacity(vertex1Index);
        }
        for (int i=0; i<verticesInDegrees[vertex2Index]; i++) {
            if (adjacencyInLists[vertex2Index][i]==vertex1Index) {
                adjacencyInLists[vertex2Index][i]=adjacencyInLists[vertex2Index][verticesInDegrees[vertex2Index]-1];
            }
        }
        verticesInDegrees[vertex2Index]--;
        if (verticesInDegrees[vertex2Index] >= 2 * INITIAL_ADJACENCY_CAPACITY
                && HALF_CAPACITY_TRIGGER * adjacencyInLists[vertex2Index].length >verticesInDegrees[vertex2Index]) {
            halfAdjacencyInCapacity(vertex2Index);
        }

    }

    // methods for changing the size of our adjacency in/out lists

    private void doubleAdjacencyOutCapacity(int vertexIndex) {
        int capacity = INITIAL_ADJACENCY_CAPACITY;
        if (adjacencyOutLists[vertexIndex] != EMPTY_ARRAY) {
            capacity = adjacencyOutLists[vertexIndex].length * 2;
        }
        changeAdjacencyOutCapacity(vertexIndex, capacity);
    }

    private void halfAdjacencyOutCapacity(int vertexIndex) {
        changeAdjacencyOutCapacity(vertexIndex, adjacencyOutLists[vertexIndex].length / 2);
    }

    private void changeAdjacencyOutCapacity(int vertexIndex, int newCapacity) {
        int [] newAdjacencyList = new int[newCapacity];
        for(int i=0; i<verticesOutDegrees[vertexIndex]; i++) {
            newAdjacencyList[i] = adjacencyOutLists[vertexIndex][i];
        }
        adjacencyOutLists[vertexIndex] = newAdjacencyList;
    }

    private void doubleAdjacencyInCapacity(int vertexIndex) {
        int capacity = INITIAL_ADJACENCY_CAPACITY;
        if (adjacencyInLists[vertexIndex] != EMPTY_ARRAY) {
            capacity = adjacencyInLists[vertexIndex].length * 2;
        }
        changeAdjacencyInCapacity(vertexIndex, capacity);
    }

    private void halfAdjacencyInCapacity(int vertexIndex) {
        changeAdjacencyInCapacity(vertexIndex, adjacencyInLists[vertexIndex].length / 2);
    }

    private void changeAdjacencyInCapacity(int vertexIndex, int newCapacity) {
        int [] newAdjacencyList = new int[newCapacity];
        for(int i=0; i<verticesInDegrees[vertexIndex]; i++) {
            newAdjacencyList[i] = adjacencyInLists[vertexIndex][i];
        }
        adjacencyInLists[vertexIndex] = newAdjacencyList;
    }

    /**
     * reads a directed graph in from a file using java.util.io.FILE
     * @param file - full path to specified file as a string
     * @return DigraphAdjList of a graph containing the contents of the file
     */
    public static DigraphAdjList readNumberedGraph(File file) {
        Scanner scanner;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Invalid file.");
        }
        int numberOfVertices = scanner.nextInt();
        int numberOfEdges = scanner.nextInt();
        DigraphAdjList graph = new DigraphAdjList(numberOfVertices);
        for(int i=0; i<numberOfEdges; i++) {
            //if (i%1000==0) System.out.println("Edge:"+i);
            int v1 = scanner.nextInt();
            int v2 = scanner.nextInt();
            graph.addEdge(v1, v2);
        }
        scanner.close();
        return graph;
    }

    // STRING OPERATIONS

    // returns the set of vertices in a graph as a string
    public String verticesSetToString() {
        String result = "{0.."+(numberOfVertices-1)+"}";
        return result;
    }

    // returns the set of edges in a graph as a string
    public String edgesSetToString() {
        String result = "{";
        String separator="";
        for(int vi=0;vi<numberOfVertices;vi++) {
            for(int ei=0; ei<verticesOutDegrees[vi]; ei++) {
                int ai=adjacencyOutLists[vi][ei];
                result += separator+"("+vi+","+ai+")";
                separator=",";
            }

        }
        result+="}";
        return result;
    }

    // returns the entire graph as a string
    public String toString() {
        String result = "G("
                +verticesSetToString()
                +","
                +edgesSetToString()
                +")";

        return result;
    }

    /**
     * randomly generates a graph based on specified numbers of vertices and edges
     * @param numberOfVertices - int number of vertices in graph
     * @param numberOfEdges - int number of edges in graph
     * @return DigraphAdjList implementation of the specified graph
     */
    public static DigraphAdjList createRandomGraph(int numberOfVertices, int numberOfEdges) {
        if (numberOfEdges>(numberOfVertices-1)*numberOfVertices/2) {
            numberOfEdges=(numberOfVertices-1)*numberOfVertices/2;
        }
        DigraphAdjList graph = new DigraphAdjList(numberOfVertices);
        for (int ei=0; ei<numberOfEdges; ei++) {
            boolean created=false;
            while (!created) {
                int v1 = (int)(Math.random()*numberOfVertices);
                int v2 = (int)(Math.random()*numberOfVertices);
                if (v1!=v2&&!graph.hasEdge(v1, v2)) {
                    graph.addEdge(v1, v2);
                    created=true;
                }
            }
        }

        return graph;
    }
}
