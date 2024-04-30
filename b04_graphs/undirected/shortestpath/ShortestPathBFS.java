package b04_graphs.undirected.shortestpath;

import a01_data_structure.s3_queues.DynamicCapacityQueue;
import b04_graphs.undirected.UndirectedGraph;

public class ShortestPathBFS implements ShortestPath
{


    private UndirectedGraph graph; // graph that will contain the vertices
    private int sourceIndex; // index of the source vertex

    private int[] shortestPathThrough; // array containing the shortest path through to a vertex
    private int[] shortestPathSize;
    private boolean[] marked;

    public ShortestPathBFS(UndirectedGraph graph, int sourceIndex)
    {
        this.graph = graph;
        this.sourceIndex = sourceIndex;
        if (sourceIndex < 0 || sourceIndex >= graph.getNumberOfVertices()) {
            throw new RuntimeException("Invalid vertex index.");
        }
        marked = new boolean[graph.getNumberOfVertices()];
        shortestPathThrough = new int[graph.getNumberOfVertices()];
        shortestPathSize = new int[graph.getNumberOfVertices()];
        for (int i = 0; i < graph.getNumberOfVertices(); i++) {
            marked[i] = false;
            shortestPathThrough[i] = -1;
            shortestPathSize[i] = 0;
        }
        bfs(sourceIndex);
    }

    private void bfs(int sourceIndex)
    {
        // unprocessed is unprocessed vertices
        DynamicCapacityQueue<Integer> unprocessed = new DynamicCapacityQueue<>();
        // automatically put the unprocessed first vertex to the queue
        unprocessed.enqueue(sourceIndex);
        marked[sourceIndex] = true;
        while (!unprocessed.isEmpty()) {
            // pull one item from the unprocessed queue and process it
            int vi = unprocessed.dequeue();
            // take all adjacent vertices
            for (int ai : graph.getAdjacentVerticesIndexesArray(vi)) {
                if (!marked[ai]) { // if it isn't marked, then we need to process it
                    marked[ai] = true;
                    shortestPathSize[ai] = shortestPathSize[vi] + 1;
                    shortestPathThrough[ai] = vi;
                    // because this is a new vertex we need to consider, we need to add it to unprocessed
                    // this replaces the recursive dfs() call in our DFS implementation
                    unprocessed.enqueue(ai);
                }
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
        for (int i = shortestPathSize[vertex], v = vertex; i >= 0; i--)
        { // set the result at i index to the vertex
            result[i] = v;
            // set v to the next vertex in the path or previous node
            v = shortestPathThrough[v];
        }
        // return the int[] result containing vertices on a path to a certain vertex
        return result;
    }
}
