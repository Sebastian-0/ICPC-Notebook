# A fast way to factorize a number into primes.
def primes(N):
  factors = []
  i = 2
  while i**2 <=N:
    while N % i == 0:
      N //= i
      factors.append(i)
    i += 1

  if N != 1:
    factors.append(N)
  return factors
