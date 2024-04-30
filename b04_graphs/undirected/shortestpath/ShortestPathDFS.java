package b04_graphs.undirected.shortestpath;

import b04_graphs.undirected.UndirectedGraph;

public class ShortestPathDFS implements ShortestPath
{

    private UndirectedGraph graph;
    private int sourceIndex;

    //
    private int[] shortestPathThrough;
    // holds a value for how many vertexes to go through to get from source to the specific index
    private int[] shortestPathSize;
    // denotes whether a vertex has been visited already or not
    private boolean[] marked;

    public ShortestPathDFS(UndirectedGraph graph, int sourceIndex)
    {
        this.graph = graph;
        this.sourceIndex = sourceIndex;
        if (sourceIndex < 0 || sourceIndex >= graph.getNumberOfVertices())
        {
            throw new RuntimeException("Invalid vertex index: " + sourceIndex);
        }

        marked = new boolean[graph.getNumberOfVertices()];
        shortestPathThrough = new int[graph.getNumberOfVertices()];
        shortestPathSize = new int[graph.getNumberOfVertices()];
        for (int i = 0; i < graph.getNumberOfVertices(); i++)
        {
            marked[i] = false;
            shortestPathThrough[i] = -1;
            shortestPathSize[i] = 0;
        }
        dfs(sourceIndex);
    }

    /**
     * Depth first search for finding the shortest path
     * @param vi - a given vertex index within the graph
     */
    private void dfs(int vi)
    {
        marked[vi] = true;
        for (int ai: graph.getAdjacentVerticesIndexesArray(vi))
        {
            // if the adjacent vertex isn't marked
            // OR is the adjacent vertex has a greater shortest path size
            if (!marked[ai] || shortestPathSize[ai]>shortestPathSize[vi]+1)
            {
                shortestPathSize[ai]=shortestPathSize[vi]+1;
                shortestPathThrough[ai]=vi;
                dfs(ai);
            }
        }
    }

    @Override
    public UndirectedGraph getGraph()
    {
        return graph;
    }

    @Override
    public int getSourceVertex()
    {
        return sourceIndex;
    }

    @Override
    public boolean hasPathTo(int vertex)
    {
        // if the shortest path size is 0, that means there is no path from source to given vertex
        return shortestPathSize[vertex] > 0;
    }

    @Override
    public int[] getPathTo(int vertex)
    {
        // initialize result as an array of the same size as the shortest path from source to the vertex
        int[] result = new int[shortestPathSize[vertex]+1];
        for (int i = shortestPathSize[vertex], v = vertex;i >= 0; i--)
        { // set the result at i index to the vertex
            result[i] = v;
            // set v to the next vertex in the path or previous node
            v = shortestPathThrough[v];
        }
        // return the int[] result containing vertices on a path to a certain vertex
        return result;
    }
}
