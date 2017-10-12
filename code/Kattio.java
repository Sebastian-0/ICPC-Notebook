class Kattio {
  private BufferedReader r;
  private String line;
  private StringTokenizer st;
  private String token;
  
  public Kattio(InputStream i) {
    r = new BufferedReader(new InputStreamReader(i));
  }

  public boolean hasMoreTokens() {
    return peekToken() != null;
  }
  public int getInt() {
    return Integer.parseInt(nextToken());
  }
  public double getDouble() {
    return Double.parseDouble(nextToken());
  }
  public long getLong() {
    return Long.parseLong(nextToken());
  }
  public String getWord() {
    String ans = peekToken();
    token = null;
    return ans;
  }

  private String peekToken() {
    if (token == null)
      try {
        while (st == null || !st.hasMoreTokens()) {
          line = r.readLine();
          if (line == null) return null;
          st = new StringTokenizer(line);
        }
        token = st.getWord();
      } catch (IOException e) { }
    return token;
  }
}
