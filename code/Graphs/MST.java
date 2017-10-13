public class MinimumSpanningTree {
  public long solve(Node start) {
    long result = 0;
    TreeSet<Node> queue = new TreeSet<>();
    start.cost = 0;
    queue.add(start);
    while (!queue.isEmpty()) {
      Node node = queue.pollFirst();
      node.taken = true;
      result += node.cost;
      for (Edge edge : node.edges) {
        Node other = edge.end == node ?
          edge.start : edge.end;
        if (edge.cost < other.cost && !other.taken) {
          queue.remove(other);
          other.cost = edge.cost;
          queue.add(other);
        }
      }
    }

    return result;
  }
}