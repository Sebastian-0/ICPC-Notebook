public class TopologicalSort {
  public Stack<Node> solve(Node[] nodes) {
    Stack<Node> result = new Stack<>();
    for (Node node : nodes) {
      if (!node.taken)
        doSolve(result, node);
    }
    return result;
  }
  
  private void doSolve(Stack<Node> result, Node node) {
    node.taken = true;
    for (Edge edge : node.edges) {
      Node other = edge.end;
      if (!other.taken)
        doSolve(result, other);
    }
    result.push(node);
  }
}