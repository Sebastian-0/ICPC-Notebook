// Returns an adjacency matrix containing the costs
// between each pair of nodes
public class FloydWarshall {
  // Adjacency matrix; Long.MAX_VALUE means no edge 
  public long[][] solve(long[][] adjacency) {
    int V = adjacency.length;
    long[][] dist = new long[V][V];
    for (int i = 0; i < V; i++)
      for (int j = 0; j < V; j++)
        dist[i][j] = adjacency[i][j];

    for (int k = 0; k < V; k++) {
      for (int i = 0; i < V; i++) {
        for (int j = 0; j < V; j++) {
          if (dist[i][k] != Long.MAX_VALUE &&
              dist[k][j] != Long.MAX_VALUE &&
              dist[i][k] + dist[k][j] < dist[i][j]) {
            dist[i][j] = dist[i][k] + dist[k][j];
          }
        }
      }
    }
    
    for (int i = 0; i < V; i++) {
      if (dist[i][i] < 0)
        return null; // Negative cycle found!
    }
    return dist;
  }