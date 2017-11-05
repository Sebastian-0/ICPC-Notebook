// Calculates sums for index 0-i, 
// good if both calculated and updated often
private static class BinaryIndexTree { 
  long[] tree;
  public BinaryIndexTree(int size) {
    tree = new long[size+1];
  }
  long sum(int index) {
    long sum = 0;
    index++;
    while (index > 0) {
      sum += tree[index];
      index -= index & (-index);
    }
    return sum;
  }
  void update(int index, int delta) {
    index++;
    while (index < tree.length) {
       tree[index] += delta;
       index += index & (-index);
    }
  }
}