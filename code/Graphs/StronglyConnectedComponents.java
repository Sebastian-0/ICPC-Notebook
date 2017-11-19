// Finds all cycles (SCCs) in a graph
public class StronglyConnectedComponents {
  Stack<Node> stack;  
  int nextIndex = 1;
  int[] indices; // 0 => uninitialized
  int[] lowLink; // 0 => uninitialized
  List<Node[]> sscs; 

  public List<Node[]> solve(Node[] nodes) {
    stack = new Stack<>();
    sscs = new LinkedList<>();
    indices = new int[nodes.length];
    lowLink = new int[nodes.length];
    for (Node node : nodes) {
      if (indices[node.index] == 0) {
        stronglyConnected(node);
      }
    }
    return sscs;
  }
  
  private void stronglyConnected(Node u) {
    indices[u.index] = nextIndex;
    lowLink[u.index] = nextIndex++;
    u.taken = true;
    stack.push(u);
    
    for (Edge e : u.edges) {
      Node v = e.end;
      if (indices[v.index] == 0) {
        stronglyConnected(v);
        lowLink[u.index] = Math.min(lowLink[u.index],
                                    lowLink[v.index]);
      } else if (v.taken) {
        lowLink[u.index] = Math.min(lowLink[u.index],
                                    indices[v.index]);
      }
    }
    
    if (lowLink[u.index] == indices[u.index]) {
      List<Node> ssc = new LinkedList<>();
      Node v;
      do {
        v = stack.pop();
        v.taken = false;
        ssc.add(v);
      } while(u != v);
      sscs.add(ssc.toArray(new Node[0]));
    }
  }
}