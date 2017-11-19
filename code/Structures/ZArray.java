// Finds patterns in text, also constructs a z-array
private static class ZArray {
  static int search(String text, String pat) {
    // Note: replace $ if found in text or pat!
    String str = pat + "$" + text;
    int[] z = getZArray(str);
    
    for (int i = 1; i < z.length; i++) {
      if (z[i] == pat.length())
        return i-1-pat.length();// Return or collect
                                // when equal
    }
    return -1;
  }

  static int[] getZArray(String str) {
    int[] z = new int[str.length()];
    int low, hi, k;
    low = hi = 0;
    for (int i = 1; i < str.length(); i++) {
      if (i > hi) {
        low = hi = i;
        while (hi < str.length() && 
            str.charAt(hi-low) == str.charAt(hi))
          hi++;
        z[i] = hi-low;
        hi--;
      } else {
        k = i-low;
        if (z[k] <= hi-i)
          z[i] = z[k];
        else {
          low = i;
          while (hi < str.length() && 
              str.charAt(hi-low) == str.charAt(hi))
            hi++;
          z[i] = hi-low;
          hi--;
        }
      }
    }
    return z;
  }
}
