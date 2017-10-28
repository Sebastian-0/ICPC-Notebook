public class GrahamScan {
  // Finds the convex hull of an array of points
  public Point[] solve(Point[] points) {
    int N = points.length;
    Point minY = points[0];
    int index = 0;
    for (int i = 0; i < N; i++) {
      Point p = points[i];
      if (p.y < minY.y ||
          p.y == minY.y && p.x < minY.x) {
        minY = p;
        index = i;
      }
    }
    points[index] = points[N-1];
    points[N-1] = minY;
    
    Point.root = minY;
    Arrays.sort(points, 0, N-1);
    
    Point[] H = new Point[N+1];
    H[0] = points[N-2];
    H[1] = minY;
    for (int i = 2; i < N+1; i++) {
      H[i] = points[i-2];
    }
    
    int M = 1;
    for (int i = 2; i <= N; i++) {
      while (Point.cross(H[M-1], H[M], H[i]) <= 0) {
        if (M > 1)
          M--;
        else if (i == N)
          break;
        else
          i++;
      }
      
      M++;
      Point tmp = H[i];
      H[i] = H[M];
      H[M] = tmp;
    }
    
    return Arrays.copyOfRange(H, 0, M);
  }
  
  static class Point implements Comparable<Point> {
    static Point root;
    double x, y;
    
    public Point(double x, double y) {
      this.x = x;
      this.y = y;
    }
    
    @Override
    public int compareTo(Point o) {
      int cross = cross(this, root, o);
      if (cross == 0) {
        return distSq(root, this) > distSq(root, o) ?
            1 : -1;
      }
      return cross;
    }
    
    static int cross(Point A, Point R, Point B) {
      double x1 = A.x - R.x;
      double x2 = B.x - R.x;
      double y1 = A.y - R.y;
      double y2 = B.y - R.y;
      return (int) -Math.signum(x1*y2 - x2*y1);
    }
    
    static double distSq(Point A, Point B) {
      double dx = A.x - B.x;
      double dy = A.y - B.y;
      return dx * dx + dy * dy;
    }
  }
}