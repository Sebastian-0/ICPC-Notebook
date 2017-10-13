public class FloydWarshall {
  // Adjacency matrix; Integer.MAX_VALUE means no edge 
  public int[][] solve(int[][] adjacency) {
    int V = adjacency.length;
    int[][] dist = new int[V][V];
    for (int i = 0; i < V; i++)
      for (int j = 0; j < V; j++)
        dist[i][j] = adjacency[i][j];

    for (int k = 0; k < V; k++) {
      for (int i = 0; i < V; i++) {
        for (int j = 0; j < V; j++) {
          if (dist[i][k] != Integer.MAX_VALUE &&
              dist[k][j] != Integer.MAX_VALUE &&
              dist[i][k] + dist[k][j] < dist[i][j]) {
            dist[i][j] = dist[i][k] + dist[k][j];
          }
        }
      }
    }
    return dist;
  }
}