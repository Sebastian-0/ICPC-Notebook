public class Graph {
  public class Node implements Comparable<Node> {
    int index;
    List<Edge> edges = new ArrayList<>();
    long cost = Long.MAX_VALUE;
    boolean taken;

    public Node(int idx) {
      index = idx;
    }

    public int compareTo(Node o) {
      if (cost == o.cost)
        return index - o.index;
      return (cost - o.cost) < 0 ? -1 : 1;
    }
  }

  public class Edge {
    int index;
    Node start, end;
    long cost;

    public Edge(int idx, Node s, Node e, long c) {
      index = idx;
      start = s;
      end = e;
      cost = c;
    }
  }
}