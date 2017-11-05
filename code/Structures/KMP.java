// Finds patterns in a text
private static class KMP {
  public static int match(String text, String pat) {
    int[] lps = new int[pat.length()];

    int len = 0;
    for (int i = 1; i < lps.length; i++) {
      if (pat.charAt(i) == pat.charAt(len)) {
        len++;
        lps[i] = len;
      } else if (len != 0) {
        len = lps[len-1];
        i--;
      } else {
        lps[i] = 0;
      }
    }
    
    int i = 0;
    int j = 0;
    while (i < text.length()) {
      if (pat.charAt(j) == text.charAt(i)) {
        i++;
        j++;
      }
      if (j == pat.length()) {
        return i-j;
        //j = lps[j-1]; //Uncomment to continue search
      }
      else if (i < text.length() && 
          pat.charAt(j) != text.charAt(i)) {
        if (j != 0)
          j = lps[j-1];
        else
          i++;
      }
    }
    return -1;
  }
}