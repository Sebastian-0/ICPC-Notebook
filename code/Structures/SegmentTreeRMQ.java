// Calculates max/min/sum of a range
public class SegmentTreeRMQ {
  public int[] segmentTree;
  public int length;
  
  public SegmentTreeRMQ(int[] input) {
    length = input.length;
    int x = (int) Math.ceil(
        Math.log(length) / Math.log(2));
    int size = 2 * (int)Math.pow(2, x) - 1;
    segmentTree = new int[size];
    construct(input, 0, length-1, 0);
  }
  
  private int construct(int[] input, int low, 
      int hi, int i) {
    if (low >= input.length)
      return Integer.MAX_VALUE; //or min / 0
    if (low == hi) {
      segmentTree[i] = input[low];
      return input[low];
    }
    int mid = (low + hi) / 2;
    //can replace with max / sum
    segmentTree[i] = Math.min(
        construct(input, low, mid, 2*i + 1),
        construct(input, mid+1, hi, 2*i + 2));
    return segmentTree[i];
  }

  public int rmq(int low, int hi) {
    return find(0, length-1, low, hi, 0);
  }
  
  private int find(int segLow, int segHi, 
      int queryLow, int queryHi, int i) {
    if (queryLow <= segLow && queryHi >= segHi)
      return segmentTree[i];
    if (queryLow > segHi || queryHi < segLow)
      return Integer.MAX_VALUE; //or min / 0
    int mid = (segLow + segHi) / 2;
    return Math.min( //or max / sum
      find(segLow, mid, queryLow, queryHi, 2*i + 1),
      find(mid+1, segHi, queryLow, queryHi, 2*i + 2));
  }
  
  public void update(int index, int value) {
    index = segmentTree.length/2 + index;
    segmentTree[index] = value;
    while (index > 0) {
      index = (index - 1) / 2;
      //or max / sum
        segmentTree[index] = Math.min(
            segmentTree[index*2+1],
            segmentTree[index*2+2]);
    }
  }
}
