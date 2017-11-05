// lis([1, 2, 5, 3]) == [1, 2, 3]
public class LongestIncreasingSubsequence { 
  public int[] solve(int[] values) {
    int N = values.length;
    int[] indices = new int[N];
    int[] parents = new int[N];
    int top = 0;

    for (int i = 1; i < N; i++) {
      int v = values[i];
      int l = 0;
      int r = top;
      while (l <= r) {
        int m = (l+r+1)/2;
        if (values[indices[m]] < v)
          l = m+1;
        else
          r = m-1;
      }
      indices[l] = i;
      if (l > 0)
        parents[i] = indices[l-1];
      top = Math.max(top, l);
    }
    
    int[] lis = new int[top+1];
    int ind = indices[top];
    for (int i = top; i >= 0; i--) {
      lis[i] = values[ind]; // = ind; to get indices
      ind = parents[ind];
    }
    return lis;
  }
}