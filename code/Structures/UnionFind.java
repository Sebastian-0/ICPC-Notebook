// Used to efficiently build large sets and verify 
// which set a node belongs to 
public class UnionFind {
  public Node find(Node n) {
    if (n.parent != n)
      n.parent = find(n.parent);
    return n.parent;
  }
  
  public void union(Node a, Node b) {
    Node ra = find(a);
    Node rb = find(b);
    if (ra.rank > rb.rank) {
      rb.parent = ra;
    } else if (rb.rank > ra.rank) {
      ra.parent = rb;
    } else {
      ra.parent = rb;
      rb.rank++;
    }
  }
  
  static class Node {
    Node parent = this;
    int rank = 1;
  }
}