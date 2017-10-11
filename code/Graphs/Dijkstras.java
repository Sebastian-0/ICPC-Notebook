public class Dijkstra {
  public class Node implements Comparable<Node> {
    int index;
    List<Road> roads = new ArrayList<>();
    long cost = Long.MAX_VALUE;
    
    public Node(int index) {
      this.index = index;
    }
    
    public int compareTo(Node o) {
      if (cost == o.cost)
        return index - o.index;
      return (cost - o.cost) < 0 ? -1 : 1;
    }
  }
  
  public class Road {
    Node start, end;
    long cost;
    
    public Road(Node start, Node end, long cost) {
      this.start = start;
      this.end = end;
      this.cost = cost;
    }
  }

  public long solve(Node s, Node t) {
    TreeSet<Node> queue = new TreeSet<>();
    s.cost = 0;
    queue.add(s);
    while (!queue.isEmpty()) {
      Node node = queue.pollFirst();
      if (node == t)
        return node.cost;
      
      for (Road road : node.roads) {
        Node other = road.end;
        other = (other == node) ? road.start : other;
        long cost = node.cost + road.cost;
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
