// Returns topologically sorted nodes with the root
// as the first element
public class TopologicalSort {
  public List<Node> solve(Node[] nodes) {
    Stack<Node> stack = new Stack<>();
    for (Node u : nodes) {
      if (!u.taken)
        doSolve(stack, u);
    }
    List<Node> result = new ArrayList<>(stack);
    Collections.reverse(result);
    return result;
  }
  
  private void doSolve(Stack<Node> stack, Node u) {
    u.taken = true;
    for (Edge e : u.edges) {
      Node v = e.end;
      if (!v.taken)
        doSolve(stack, v);
    }
    stack.push(u);
  }
}