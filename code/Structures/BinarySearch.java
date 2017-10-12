public int search(int[] data, int target) {
  int l = 0;
  int r = data.length - 1;
  while (l < r) {
    int m = (l+r)/2;
    if (data[m] < target)
      l = m+1;
    else if (data[m] > target)
      r = m-1;
    else
      return m;
  }
  return -1;
}