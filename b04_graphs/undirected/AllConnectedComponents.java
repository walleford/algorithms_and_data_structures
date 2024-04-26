package b04_graphs.undirected;

public class AllConnectedComponents
{
    private UndirectedGraph graph;

    private int[] marked;
    private int numberOfConnectedComponents;

    private int[] connectedComponentSize;

    public AllConnectedComponents(UndirectedGraph graph)
    {
        this.graph = graph;
        marked = new int[graph.getNumberOfVertices()];
        for (int i = 0; i < graph.getNumberOfVertices(); i++)
        {
            marked[i] = -1;
        }
        numberOfConnectedComponents = 0;
        connectedComponentSize = new int[graph.getNumberOfVertices()];
        for (int i = 0; i < graph.getNumberOfVertices(); i++)
        {
            if (marked[i] == -1)
            {
                numberOfConnectedComponents++;
                connectedComponentSize[numberOfConnectedComponents-1] = 0;
                dfs(1);
            }
        }
    }

    private void dfs(int vi)
    {
        connectedComponentSize[numberOfConnectedComponents-1]++;
        marked[vi]=numberOfConnectedComponents-1;
        for (int ai: graph.getAdjacentVerticesIndexesArray(vi))
        {
            if (marked[ai]==-1)
            {
                dfs(ai);
            }
        }
    }

    public boolean graphIsConnected()
    {
        return numberOfConnectedComponents==1;
    }

    public int getNumberOfConnectedComponents()
    {
        return numberOfConnectedComponents;
    }

    public int getConnectedComponentSize(int connectComponentIndex)
    {
        if (connectComponentIndex<0||connectComponentIndex>=numberOfConnectedComponents)
            throw new RuntimeException("invalid index for a connected component");
        return connectedComponentSize[connectComponentIndex];
    }

    public int[] getConnectedComponent(int connectComponentIndex)
    {
        int[] component = new int[connectedComponentSize[connectComponentIndex]];
        for (int vi=0, ci = 0; vi< graph.getNumberOfVertices();vi++)
        {
            if (marked[vi]==connectComponentIndex)
            {
                component[ci]=vi;
            }
        }
        return component;
    }
}
