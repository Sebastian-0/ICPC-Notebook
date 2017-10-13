public class Graph {
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
}