// Solves Ax = b by computing x = A^-1 * b
public class Gauss {
  private static final double THRESHOLD = 0.000001;

  // A: NxN and b: Nx1 => x: Nx1
  public double[] solve(double[][] A, double[] b) {
    int N = A.length;
    // Rescale (scaled pivoting), skip if not needed!
    for (int i = 0; i < N; i++) {
      double max = -Double.MAX_VALUE;
      for (int j = 0; j < N; j++) {
        max = Math.max(max, Math.abs(A[i][j]));
      }
      if (max < THRESHOLD)
        return null;  // Not full rank
      
      for (int j = 0; j < N; j++) {
        A[i][j] /= max;
      }
      b[i] /= max;
    }
    
    // Forward propagation
    for (int i = 0; i < N; i++) {
      // Find largest pivot
      int biggestIdx = i;
      for (int j = i; j < N; j++) {
        if (Math.abs(A[j][i]) >
            Math.abs(A[biggestIdx][i]))
          biggestIdx = j;
      }
      
      if (biggestIdx != i) { // Swap if necessary
        double[] tmps = A[biggestIdx];
        A[biggestIdx] = A[i];
        A[i] = tmps;
        double tmp = b[biggestIdx];
        b[biggestIdx] = b[i];
        b[i] = tmp;
      }
      
      double pivot = A[i][i];
      if (Math.abs(pivot) < THRESHOLD)
        return null;  // Not full rank
      
      for (int j = i+1; j < N; j++) {
        double mult = A[j][i]/pivot;
        for (int k = 0; k < N; k++) {
          A[j][k] -= mult * A[i][k];
        }
        b[j] -= mult * b[i];
      }
    }
    
    // Backwards substitution
    double[] X = new double[N];
    for (int i = N-1; i >= 0; i--) {
      for (int j = i+1; j < N; j++) {
        b[i] -= A[i][j]*X[j];
      }
      X[i] = b[i]/A[i][i];
    }
    
    return X;
  }
}