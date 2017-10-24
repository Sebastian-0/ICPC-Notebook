public class TopologicalSort {
  // The root node is at the top of the stack
  public Stack<Node> solve(Node[] nodes) {
    Stack<Node> result = new Stack<>();
    for (Node u : nodes) {
      if (!u.taken)
        doSolve(result, u);
    }
    return result;
  }
  
  private void doSolve(Stack<Node> result, Node u) {
    u.taken = true;
    for (Edge e : u.edges) {
      Node v = e.end;
      if (!v.taken)
        doSolve(result, v);
    }
    result.push(u);
  }
}