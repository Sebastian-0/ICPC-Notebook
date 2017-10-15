// Renamed Edge.cost -> capacity
public class NetworkFlow {
  // Find minimum cut
  public List<Edge> solveMinCut(Node[] nodes,
      Edge[] edges, int s, int t) {
    List<Edge> result = new LinkedList<>();
    boolean[] visited = new boolean[nodes.length];
    
    solveFlow(nodes, edges, s, t);
    
    Queue<Node> queue = new LinkedList<>();
    queue.add(nodes[s]);
    visited[s] = true;
    while (!queue.isEmpty()) {
      Node node = queue.poll();
      for (Edge edge : node.edges) {
        Node other = edge.end;
        if (edge.capacity > 0 &&
            !visited[other.index]) {
          queue.offer(other);
          visited[other.index] = true;
        }
      }
    }
    
    for (Edge edge : edges) {
      if (visited[edge.start.index] &&
          !visited[edge.end.index]) {
        result.add(edge);
      }
    }
    
    return result;
  }
  
  // Find maximum flow
  public long solveFlow(Node[] nodes, Edge[] edges,
      int s, int t) {
    Edge[] redges = new Edge[edges.length];
    for (int i = 0; i < redges.length; i++) {
      Node n1 = edges[i].end;
      Node n2 = edges[i].start;
      redges[i] = new Edge(i, n1, n2, 0);
      edges[i].end.edges.add(redges[i]);
    }

    long maxFlow = 0;
    List<Edge> path = new LinkedList<>();
    while (bfs(nodes, path, s, t)) {
      long minFlow = Long.MAX_VALUE;
      for (Edge edge : path) {
        minFlow = Math.min(edge.capacity, minFlow);
      }
      maxFlow += minFlow;
      for (Edge edge : path) {
        Edge redge = edge == redges[edge.index] ?
            edges[edge.index] : redges[edge.index];
        edge.capacity -= minFlow;
        redge.capacity += minFlow;
      }
    }
    return maxFlow;
  }
  
  private boolean bfs(Node[] nodes, List<Edge> path,
      int s, int t) {
    boolean[] visited = new boolean[nodes.length];
    Edge[] parent = new Edge[nodes.length]; 
    Queue<Node> queue = new LinkedList<>();
    queue.offer(nodes[s]);
    while (!queue.isEmpty()) {
      Node node = queue.poll();
      if (node.index == t)
        break;
      for (Edge edge : node.edges) {
        Node other = edge.end;
        if (edge.capacity > 0 &&
            !visited[other.index]) {
          queue.offer(other);
          visited[other.index] = true;
          parent[other.index] = edge;
        }
      }
    }
    
    if (visited[t]) {
      path.clear();
      Node current = nodes[t];
      while (current != nodes[s]) {
        path.add(parent[current.index]);
        current = parent[current.index].start;
      }
      return true;
    }
    
    return false;
  }
}