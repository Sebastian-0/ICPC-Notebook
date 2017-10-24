// Renamed Edge.cost -> capacity
public class NetworkFlow {
  // Find minimum s-t cut
  public List<Edge> solveMinCut(Node[] nodes,
      Edge[] edges, int s, int t) {
    List<Edge> result = new LinkedList<>();
    boolean[] visited = new boolean[nodes.length];
    
    solveFlow(nodes, edges, s, t);
    
    Queue<Node> queue = new LinkedList<>();
    queue.add(nodes[s]);
    visited[s] = true;
    while (!queue.isEmpty()) {
      Node u = queue.poll();
      for (Edge e : u.edges) {
        Node v = e.end;
        if (e.capacity > 0 && !visited[v.index]) {
          queue.offer(v);
          visited[v.index] = true;
        }
      }
    }
    
    for (Edge e : edges) {
      if (visited[e.start.index] &&
          !visited[e.end.index]) {
        result.add(e);
      }
    }
    
    return result;
  }
  
  // Find maximum s-t flow
  public long solveFlow(Node[] nodes, Edge[] edges, 
      int s, int t) {
    Edge[] redges = new Edge[edges.length];
    for (int i = 0; i < redges.length; i++) {
      redges[i] = new Edge(i, edges[i].end,
        edges[i].start, 0);
      edges[i].end.edges.add(redges[i]);
    }

    long maxFlow = 0;
    List<Edge> path = new LinkedList<>();
    while (bfs(nodes, path, s, t)) {
      long minFlow = Long.MAX_VALUE;
      for (Edge e : path) {
        minFlow = Math.min(e.capacity, minFlow);
      }
      maxFlow += minFlow;
      for (Edge e : path) {
        Edge re = e == redges[e.index] ?
            edges[e.index] : redges[e.index];
        e.capacity -= minFlow;
        re.capacity += minFlow;
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
      Node u = queue.poll();
      if (u.index == t)
        break;
      for (Edge e : u.edges) {
        Node v = e.end;
        if (e.capacity > 0 && !visited[v.index]) {
          queue.offer(v);
          visited[v.index] = true;
          parent[v.index] = e;
        }
      }
    }
    
    if (visited[t]) {
      path.clear();
      Node n = nodes[t];
      while (n != nodes[s]) {
        path.add(parent[n.index]);
        n = parent[n.index].start;
      }
      return true;
    }
    
    return false;
  }