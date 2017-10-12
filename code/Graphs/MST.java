public class MinimumSpanningTree {
  public class Node implements Comparable<Node> {
    int index;
    List<Edge> edges = new ArrayList<>();
    long cost = Long.MAX_VALUE;
    boolean taken;

    public Node(int index) {
      this.index = index;
    }

    public int compareTo(Node o) {
      if (cost == o.cost)
        return index - o.index;
      return (cost - o.cost) < 0 ? -1 : 1;
    }
  }

  public class Edge {
    Node start, end;
    long cost;

    public Edge(Node start, Node end, long cost) {
      this.start = start;
      this.end = end;
      this.cost = cost;
    }
  }

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
        Node other = edge.end;
        other = (other == node) ? edge.start : other;
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