// Returns the cost from node s to t
public class Dijkstra {
  public long solve(Node s, Node t) {
    TreeSet<Node> queue = new TreeSet<>();
    s.cost = 0;
    queue.add(s);
    while (!queue.isEmpty()) {
      Node u = queue.pollFirst();
      if (u == t)
        return u.cost;
      
      for (Edge e : u.edges) {
        Node v = e.end == u ? e.start : e.end;
        long cost = u.cost + e.cost;
        if (cost < v.cost) {
          queue.remove(v);
          v.cost = cost;
          queue.add(v);
        }
      }
    }
    
    return -1;
  }
}
