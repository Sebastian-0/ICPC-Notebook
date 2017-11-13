// Calculates #combinations (n over k)
long nCr(int n, int k) {
    if (n < k)
      return 0;
    
  if (k > n / 2)
    k = n - k;
  long ans = 1;
  for (int i = 1; i <= k; i++) {
    ans *= n - k + i;
    ans /= i;
  }
  return ans;
}

// Calculates #permutations
long nPr(int n, int k) {
    if (n < k)
      return 0;
    
  long ans = 1;
  for (int i = 1; i <= k; i++) {
    ans *= n - k + i;
  }
  return ans;
}