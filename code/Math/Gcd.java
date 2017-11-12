// Calculates the greatest common divisor of a and b
int gcd(int a, int b) {
  while (b > 0) {
    int t = b;
    b = a % b;
    a = t;
  }
  return a;
}

// Calculates the least common multiple of a and b
int lcm(int a, int b) {
  return a / gcd(a, b) * b;
}