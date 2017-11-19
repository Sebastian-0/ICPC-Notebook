// Returns the cost from node s to all nodes (by index)
public class BellmanFord {
  public long[] solve(Node[] nodes, Edge[] edges, int s) {
    int V = nodes.length;
    long[] dist = new long[V];
    for (int i = 0; i < dist.length; i++)
      dist[i] = Long.MAX_VALUE;
    dist[s] = 0;

    for (int i = 0; i < V-1; i++) {
      for (Edge e : edges) {
        int n1 = e.start.index;
        int n2 = e.end.index;
        if (dist[n1] != Long.MAX_VALUE && 
            dist[n2] > dist[n1] + e.cost)
          dist[n2] = dist[n1] + e.cost;
      }
    }

    for (Edge e : edges) {
      int n1 = e.start.index;
      int n2 = e.end.index;
      if (dist[n1] != Long.MAX_VALUE && 
          dist[n2] > dist[n1] + e.cost)
        return null; // Negative cycle found!
    }
    return dist;
  }
}