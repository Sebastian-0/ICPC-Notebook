private static class BIT { 
  long[] tree;
  public BIT(int size) {
    tree = new long[size+1];
  }
  long sum(int index)
  {
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