public class Dijkstra {
  public long solve(Node s, Node t) {
    TreeSet<Node> queue = new TreeSet<>();
    s.cost = 0;
    queue.add(s);
    while (!queue.isEmpty()) {
      Node node = queue.pollFirst();
      if (node == t)
        return node.cost;
      
      for (Edge edge : node.edges) {
        Node other = edge.end;
        other = (other == node) ? edge.start : other;
        long cost = node.cost + edge.cost;
        if (cost < other.cost) {
          queue.remove(other);
          other.cost = cost;
          queue.add(other);
        }
      }
    }
    
    return -1;
  }
}
