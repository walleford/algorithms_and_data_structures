package b04_graphs.undirected;
import javax.swing.text.html.MinimalHTMLWriter;
import java.io.File;
import java.util.Iterator;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class UndirectedGraphAdjList implements UndirectedGraph
{

    // data structure variables

    // when the array is at 1/5 capacity, this is used for the trigger to half the array capacity
    private static final double HALF_CAPACITY_INTEGER = 0.2;
    // initial capacity for neighbors of a vertex
    private static final int INITIAL_ADJACENCY_CAPACITY = 10;
    private static final int[] EMPTY_ARRAY = new int[0];
    private int numberOfVertices;
    private int minimumCapacity;
    private int numberOfEdges;
    // list of count of adjacent edges to a specific vertexIndex
    private int[] verticesDegrees;
    // list of connected vertices for each vertex
    private int[][] adjacencyLists;

    // constructors for interface

    /**
     * intialization of the original adjacency list graph
     *
     * @param numberOfVertices - initial number of vertices to put in the graph
     */
    public UndirectedGraphAdjList(int numberOfVertices)
    {
        this.numberOfVertices = numberOfVertices;
        this.numberOfEdges = 0;
        this.minimumCapacity = numberOfVertices;
        this.verticesDegrees = new int[numberOfVertices];
        this.adjacencyLists = new int[numberOfVertices][];
        for (int i = 0; i < numberOfVertices; i++)
        {
            verticesDegrees[i]=0;
            adjacencyLists[i] = EMPTY_ARRAY;
        }
    }

    @Override
    public int getNumberOfVertices()
    {
        return this.numberOfVertices;
    }

    @Override
    public void addVertex()
    {
        // double the size if we have reached the capacity
        if (numberOfVertices==verticesDegrees.length)
        {
            doubleVertexCapacity();
        }
        // add an empty array onto the end of the adj list for this new vertex
        adjacencyLists[numberOfVertices] = EMPTY_ARRAY;
        // set the degree for this new vertex to 0
        verticesDegrees[numberOfVertices] = 0;
        // increase number of vertices
        numberOfVertices++;
    }

    @Override
    public void removeVertex(int vertexIndex)
    {
        if (vertexIndex<0||vertexIndex>=numberOfVertices)
        {
            throw new RuntimeException("Invalid index for a vertex: "+vertexIndex);
        }
        // remove all the edges associated with this vertex
        for (int adj: getAdjacentVerticesIndexesArray(vertexIndex))
        {
            removeEdge(vertexIndex, adj);
        }
        verticesDegrees[vertexIndex] = verticesDegrees[numberOfVertices-1];
        adjacencyLists[vertexIndex] = adjacencyLists[numberOfVertices-1];
        numberOfVertices--;
        // half capacity when vertex degrees is less than double the min capacity
        // AND when the degrees length is under 1/5 of the number of vertices
        if (verticesDegrees.length >= 2 * minimumCapacity
                && HALF_CAPACITY_INTEGER * verticesDegrees.length > numberOfVertices)
        {
            halfVertexCapacity();
        }
        // after we want to find all instances of numberOfVertices in the adj list and renumber the last one with vertex
        // index
        for (int i = 0; i < numberOfVertices; i++)
        {
            for(int j = 0; j < verticesDegrees[i]; j++)
            {
                if (adjacencyLists[i][j] == numberOfVertices)
                {
                    adjacencyLists[i][j] = vertexIndex;
                }
            }
        }
    }

    private void doubleVertexCapacity()
    {
        changeVertexCapacity(verticesDegrees.length * 2);
    }

    private void halfVertexCapacity()
    {
        changeVertexCapacity(verticesDegrees.length / 2);
    }

    private void changeVertexCapacity(int newCapacity)
    {
        int [] newVerticesDegrees = new int[newCapacity];
        for (int i = 0; i < numberOfVertices; i ++)
        {
            newVerticesDegrees[i] = verticesDegrees[i];
        }
        verticesDegrees = newVerticesDegrees;
        int[][] newAdjacencies = new int[newCapacity][];
        for (int i = 0; i<numberOfVertices;i++)
        {
            newAdjacencies[i]=adjacencyLists[i];
        }
        adjacencyLists = newAdjacencies;
    }

    @Override
    public int getNumberOfEdges()
    {
        return numberOfEdges;
    }

    @Override
    public int getNumberOfAdjacentEdges(int vertexIndex)
    {
        // returns the number of edges that are considered incident to a specific vertex
        return verticesDegrees[vertexIndex];
    }

    @Override
    public Iterator<Integer> getAdjacentVerticesIndexesIterator(int vertexIndex)
    {
        return new Iterator<Integer>()
        {
            int index = 0;
            @Override
            public boolean hasNext()
            {
                return index < verticesDegrees[vertexIndex];
            }

            @Override
            public Integer next()
            {
                if (hasNext())
                    return adjacencyLists[vertexIndex][index++];
                throw new RuntimeException("no more elements");
            }
        };
    }

    /**
     * return each adjacent vertex to a specific indexVertex as an array of indexes of vertices
     * @param vertexIndex - specific index of a vertex
     * @return - int[] array of indexes of vertices
     */
    @Override
    public int[] getAdjacentVerticesIndexesArray(int vertexIndex)
    {
        if (vertexIndex<0 || vertexIndex >= numberOfVertices)
            throw new RuntimeException("Invalid Index for a vertex: "+vertexIndex);
        int[] result = new int[verticesDegrees[vertexIndex]];
        for (int i = 0; i < verticesDegrees[vertexIndex]; i ++)
        {
            result[i] = adjacencyLists[vertexIndex][i];
        }
        return result;
    }

    @Override
    public void addEdge(int vertex1Index, int vertex2Index)
    {
        if (vertex1Index<0 || vertex1Index >= numberOfVertices)
            throw new RuntimeException("Invalid Index for the first vertex: "+vertex1Index);
        if (vertex2Index<0 || vertex2Index >= numberOfVertices)
            throw new RuntimeException("Invalid Index for the second vertex: " + vertex2Index);
        addDirectedEdge(vertex1Index, vertex2Index);
        addDirectedEdge(vertex2Index, vertex1Index);
        numberOfEdges++;
    }

    private void addDirectedEdge(int vertex1Index, int vertex2Index)
    {
        if (verticesDegrees[vertex1Index]==adjacencyLists[vertex1Index].length)
        {
            doubleAdjacencyCapacity(vertex1Index);
        }
        // add the index of the new vertex to the end of the adjacency list of the first index
        adjacencyLists[vertex1Index][verticesDegrees[vertex1Index]++] = vertex2Index;
    }

    private void doubleAdjacencyCapacity(int vertexIndex)
    {
        int capacity = INITIAL_ADJACENCY_CAPACITY;
        if  (adjacencyLists[vertexIndex] != EMPTY_ARRAY)
        {
            capacity = adjacencyLists[vertexIndex].length * 2;
        }
        changeAdjacencyCapacity(vertexIndex, capacity);
    }

    @Override
    public void removeEdge(int vertex1Index, int vertex2Index)
    {
        if (vertex1Index<0 || vertex1Index >= numberOfVertices)
            throw new RuntimeException("Invalid Index for the first vertex: "+vertex1Index);
        if (vertex2Index<0 || vertex2Index >= numberOfVertices)
            throw new RuntimeException("Invalid Index for the second vertex: " + vertex2Index);
        // remove edge v-w
        removeDirectedEdge(vertex1Index, vertex2Index);
        // remove edge w-v
        removeDirectedEdge(vertex2Index, vertex1Index);
        numberOfEdges--;
    }

    private void removeDirectedEdge(int vertex1Index, int vertex2Index)
    {
        for (int i = 0; i < verticesDegrees[vertex1Index]; i++)
        {
            if (adjacencyLists[vertex1Index][i] == vertex2Index)
            {
                adjacencyLists[vertex1Index][i] = adjacencyLists[vertex1Index][verticesDegrees[vertex1Index]];
            }
        }
        verticesDegrees[vertex1Index]--;
        if (verticesDegrees[vertex1Index] >= 2 * INITIAL_ADJACENCY_CAPACITY
            && HALF_CAPACITY_INTEGER * adjacencyLists[vertex1Index].length > verticesDegrees[vertex1Index])
        {
            halfAdjacencyCapacity(vertex1Index);
        }
    }

    private void halfAdjacencyCapacity(int vertexIndex)
    {
        changeAdjacencyCapacity(vertexIndex, adjacencyLists[vertexIndex].length / 2);
    }

    private void changeAdjacencyCapacity(int vertexIndex, int newCapacity)
    {
        int [] newAdjacencyList = new int[newCapacity];
        for (int i = 0; i < verticesDegrees[vertexIndex]; i++)
        {
            newAdjacencyList[i] = adjacencyLists[vertexIndex][i];
        }
        adjacencyLists[vertexIndex] = newAdjacencyList;
    }

    public static UndirectedGraphAdjList readNumberedGraph(File file)
    {
        Scanner scanner;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Invalid File");
        }
        int numberOfVertices = scanner.nextInt();
        int numberOfEdges = scanner.nextInt();
        UndirectedGraphAdjList graph = new UndirectedGraphAdjList(numberOfVertices);
        for (int i = 0; i < numberOfEdges; i++)
        {
            int v1 = scanner.nextInt();
            int v2 = scanner.nextInt();
            graph.addEdge(v1, v2);
        }
        scanner.close();
        return graph;
    }

    public String verticesSetToString()
    {
        String result = "{1..."+numberOfVertices+"}";
        return result;
    }

    public String edgesSetToString()
    {
        String result = "{";
        String separator = "";
        for (int vi = 0; vi < numberOfVertices;vi++)
        {
            for (int ei = 0; ei < verticesDegrees[vi]; ei++)
            {
                int ai = adjacencyLists[vi][ei];
                if (vi<=ai)
                {
                    result += separator + "(" + vi + ", " + ai + ")";
                    separator = ", ";
                }
            }
        }
        result += "}";
        return result;
    }

    public String toString()
    {
        String result = "G("
                + verticesSetToString()
                + ","
                + edgesSetToString()
                + ")";
        return result;


    }
}
