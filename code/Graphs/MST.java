public class MinimumSpanningTree {
  // Returns the edges of the MST
  public List<Edge> solve(Node[] nodes, Node start) {
    Edge[] source = new Edge[nodes.length];
    TreeSet<Node> queue = new TreeSet<>();
    start.cost = 0;
    queue.add(start);
    while (!queue.isEmpty()) {
      Node u = queue.pollFirst();
      u.taken = true;
      for (Edge e : u.edges) {
        Node v = e.end == u ? e.start : e.end;
        if (e.cost < v.cost && !v.taken) {
          queue.remove(v);
          v.cost = e.cost;
          source[v.index] = e;
          queue.add(v);
        }
      }
    }
    
    List<Edge> mst = new ArrayList<>();
    for (Edge e : source) {
      if (e != null)
        mst.add(e);
    }

    return mst;
  }