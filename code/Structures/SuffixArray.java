public class SuffixArray {
  private String text;
  private Suffix[] suffixes;
  
  public SuffixArray(String text) {
    this.text = text;
    int N = text.length();
    suffixes = new Suffix[N];
    for (int i = 0; i < N; i++) {
      Suffix s = new Suffix();
      s.index = i;
      s.rank[0] = text.charAt(i);
      s.rank[1] = (N - i) < 2 ? -1 : text.charAt(i+1);
      suffixes[i] = s;
    }
    
    Arrays.sort(suffixes);
    
    int[] index = new int[N];
    for (int i = 2; i < N; i *= 2) {
      int prevRank = suffixes[0].rank[0];
      suffixes[0].rank[0] = 0;
      index[suffixes[0].index] = 0;
      for (int j = 1; j < N; j++) {
        Suffix suffix = suffixes[j];
        Suffix prevSuffix = suffixes[j-1];
        if (suffix.rank[0] == prevRank &&
            suffix.rank[1] == prevSuffix.rank[1]) {
          prevRank = suffix.rank[0];
          suffix.rank[0] = prevSuffix.rank[0];
        } else {
          prevRank = suffix.rank[0];
          suffix.rank[0] = prevSuffix.rank[0] + 1;
        }
        index[suffix.index] = j;
      }
      
      for (int j = 0; j < N; j++) {
        int nextIndex = suffixes[j].index + 2;
        suffixes[j].rank[1] = nextIndex < N ?
            suffixes[index[nextIndex]].rank[0] : -1;
      }
      
      Arrays.sort(suffixes);
    }
  } 
  
  private static class Suffix implements
      Comparable<Suffix> {
    int index;
    int[] rank = { 0, 0 };
    
    @Override
    public int compareTo(Suffix o) {
      if (rank[0] != o.rank[0])
        return rank[0] - o.rank[0];
      if (rank[1] != o.rank[1])
        return rank[1] - o.rank[1];
      return index - o.index;
    }
  }
}