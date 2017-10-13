// Finds k'th smallest element in array[l..r]
public static int kthSmallest(int[] array, 
    int low, int hi, int k) {
  if (k > 0 && k <= hi - low + 1) {
    int pos = partition(array, low, hi);
    if (pos - low == k - 1)
      return array[pos];
    if (pos - low > k - 1)
      return kthSmallest(array, low, pos - 1, k);
    return kthSmallest(array, pos+1, hi, k+low-pos-1);
  }
  return Integer.MAX_VALUE;
}

static void swap(int[] array, int i, int j) {
  int temp = array[i];
  array[i] = array[j];
  array[j] = temp;
}

static int partition(int[] array, int low, int hi) {
  int n = hi - low + 1;
  int pivot = (int) (Math.random() * n);
  swap(array, low + pivot, hi);

  int x = array[hi], i = low;
  for (int j = low; j < hi; j++) {
    if (array[j] <= x) {
      swap(array, i, j);
      i++;
    }
  }
  swap(array, i, hi);
  return i;
}
