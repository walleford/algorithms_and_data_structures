package b04_graphs.undirected;

public class ConnectedComponentDFS
{
    private UndirectedGraph graph;
    private int vertexIndex;
    private boolean[] marked;
    private int count;

    public ConnectedComponentDFS(UndirectedGraph graph, int vertexIndex)
    {
        this.graph = graph;
        this.vertexIndex = vertexIndex;

        if (vertexIndex<0 || vertexIndex>= graph.getNumberOfVertices())
        {
            throw new RuntimeException("invalid vertex index");
        }
        marked = new boolean[graph.getNumberOfVertices()];
        for (int i = 0; i < graph.getNumberOfVertices(); i++)
        {
            marked[i] = false;
        }
        count = 0;
        dfs(vertexIndex);
    }

    private void dfs(int vi)
    {
        count++;
        marked[vi] = true;
        for (int ai: graph.getAdjacentVerticesIndexesArray(vi))
        {
            if (!marked[ai])
            {
                dfs(ai);
            }
        }
    }

    public boolean graphIsConnected()
    {
        return count == graph.getNumberOfVertices();
    }

    public int getConnectedComponenetSize()
    {
        return count;
    }

    public int[] getConnectedComponent()
    {
        int[] component = new int[count];
        for (int vi = 0, ci = 0; vi < graph.getNumberOfVertices(); vi++)
        {
            if (marked[vi])
            {
                component[ci++]=vi;
            }
        }
        return component;
    }
}
