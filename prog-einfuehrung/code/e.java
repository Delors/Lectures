BigInteger fak(int n) {
    if (n == 0) 
      return BigInteger.valueOf(1);
    else {
      var bn = BigInteger.valueOf(n);
      return fak(n-1).multiply(bn);
    }
  }

/**
 * Approximates the value "e" by
 * performing the given number of
 * steps.
 */
BigDecimal e(int steps) {
    BigDecimal e = BigDecimal.ZERO;
    while (steps >= 0) {
        e = e.add(BigDecimal.ONE.divide(
                new BigDecimal(fak(steps)),
                MathContext.DECIMAL128));
        steps--;
    }
    return e;
}

void main() {
    IO.println(e(100));
}