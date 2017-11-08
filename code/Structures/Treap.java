// A randomly balanced binary search tree
public class Treap<T extends Comparable<T>> {
  Node root;
  
  // Adds key to this treap
  public void add(T key) {
    Node n = new Node(key);
    root = add(root, n);
  }
  private Node add(Node curr, Node newNode) {
    if (curr == null)
      return newNode;
    if (curr.priority > newNode.priority) {
      List<Node> res = split(curr, newNode.value);
      newNode.left = res.get(0);
      newNode.right = res.get(1);
      curr = newNode;
    } else if (curr.value.compareTo(newNode.value)
        <= 0) {
      curr.right = add(curr.right, newNode);
    } else {
      curr.left = add(curr.left, newNode);
    }
    updateSize(curr);
    return curr;
  }
  
  // Removes key from this treap, true on success
  public boolean remove(T key) {
    int s = size();
    root = remove(root, key);
    return size() < s;
  }
  private Node remove(Node curr, T key) {
    if (curr == null)
      return null;
    int comp = curr.value.compareTo(key);
    if (comp == 0) {
      curr = merge(curr.left, curr.right);
    } else if (comp < 0) {
      curr.right = remove(curr.right, key);
    } else {
      curr.left = remove(curr.left, key);
    }
    updateSize(curr);
    return curr;
  }
  
  // Merges this with a treap with larger elements
  public void merge(Treap<T> larger) { 
    root = merge(root, larger.root);
  }
  private Node merge(Node l, Node r) {
    if (l == null || r == null)
      return l != null ? l : r;
    
    if (l.priority < r.priority) {
      l.right = merge(l.right, r);
      updateSize(l);
      return l;
    } else {
      r.left = merge(l, r.left);
      updateSize(r);
      return r;
    }
  }
  
  // Returns values that <= SP, leaves > SP behind
  public Treap<T> split(T splitPoint) { 
    Treap<T> left = new Treap<>();
    List<Node> result = split(root, splitPoint);
    left.root = result.get(0);
    root = result.get(1);
    return left;
  }
  private List<Node> split(Node tree, T key) {
      Node l = null;
      Node r = null;
      if (tree != null) {
        if (tree.value.compareTo(key) <= 0) {
          List<Node> res = split(tree.right, key);
          tree.right = res.get(0);
          r = res.get(1);
          l = tree;
        } else {
          List<Node> res = split(tree.left, key);
          tree.left = res.get(1);
          r = tree;
          l = res.get(0);
        }
        updateSize(tree);
      }
      
      List<Node> landr = new ArrayList<>(2); 
      landr.add(l);
      landr.add(r);
      return landr;
  }
  
  // Returns the size of this treap
  public int size() {
    return size(root);
  }
  private int size(Node n) {
    return n != null ? n.size : 0;
  }
  private void updateSize(Node node) {
    if (node != null)
      node.size = size(node.left) + size(node.right)+1;
  }
  
  // Returns whether or not this treap contains key
  public boolean contains(T key) {
    return contains(root, key);
  }
  private boolean contains(Node curr, T key) {
    if (curr == null)
      return false;
    int comp = curr.value.compareTo(key);
    if (comp < 0)
      return contains(curr.right, key);
    if (comp > 0)
      return contains(curr.left, key);
    return true;
  }
  
  class Node {
    T value;
    double priority;
    int size = 1;
    Node left, right;
    
    public Node(T value) {
      this.value = value;
      priority = Math.random();
    }
  }
}