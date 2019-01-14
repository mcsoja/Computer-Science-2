/* file: Rational.java
   author: CS2 Staff

   CSCI 1102 Computer Science 2

   An implementation of a simple ADT of rational numbers.
   See Sedgewick & Wayne problem 16 Section 1.2.
*/

// Molly Soja
// Pset 2
// 9.12.18

public class RationalC implements Rational {
  private int numerator;
  private int denominator;

  public RationalC(int num, int den) {
    // check of one of the denominators is zero
    if (den == 0) {
      throw new ArithmeticException ("Denominator is 0- one of the fractions is undefined.");
      }

    // gcd function
    int gcdfrac = gcd(num, den);
    this.numerator = num / gcdfrac;
    this.denominator = den / gcdfrac;

    // put negative sign in numerator
    if (this.denominator < 0) {
      this.numerator = - this.numerator;
      this.denominator = - this.denominator;
      }
  }

  // Euclid's Algorithm
  private int gcd(int n, int d) {
    if(d == 0) { return n; }
    return gcd(d, n%d);
    }

  // return numerator
  public int getNumerator() {
    return this.numerator;
    }

  // return denominator
  public int getDenominator() {
    return this.denominator;
    }

  // compares two rational numbers to see which is bigger
  public int compareTo(Rational b) {
    Rational a = this;
    int num1 = a.getNumerator() * b.getDenominator();
    int num2 = a.getDenominator() * b.getNumerator();
    if (num1 < num2) return - 1;
    if (num1 > num2) return + 1;
      return 0;
    }

  // addition
  public Rational plus(Rational b) {
    //cross multiply, add them together, and add result
    int newNum = (this.getNumerator() * b.getDenominator()) + (this.getDenominator() * b.getNumerator());
    int newDem = this.getDenominator() * b.getDenominator();
    //call new RationalC on both
    return new RationalC (newNum, newDem);
  }

  // subtraction
  public Rational subtract(Rational b) {
    Rational tempb = new RationalC(-b.getNumerator(), b.getDenominator());
      return this.plus(tempb);
    }

  // multiplication
  public Rational multiply(Rational b) {
      return new RationalC(this.getNumerator() * b.getNumerator(), this.getDenominator() * b.getDenominator());
    }

  // division
  public Rational divide(Rational b) {
    if(b.getDenominator() == 0) {
      System.out.format("denominator is zero");
    }
    Rational tempb = new RationalC(b.getDenominator(), b.getNumerator());
      return this.multiply(tempb);
    }

  // equal
  public boolean equal(Rational b) {
    if(this.getNumerator() == b.getNumerator() && this.getDenominator() == b.getDenominator()) {
      return true; }
    else {
      return false;
    }
}
  // string
  public String toString() {
    if (this.getDenominator() == 1) {
      return this.getNumerator() + "";
      }
    else {
        return this.getNumerator() + "/" + this.getDenominator();
      }
  }


  // Some unit testing.
  //
public static void main(String[] args) {
    Rational
      r1 = new RationalC(3, 9),
      r2 = new RationalC(2, 10),
      r3 = r1.plus(r2),
      r4 = r1.subtract(r2),
      r5 = r1.multiply(r2),
      r6 = r1.divide(r2);
    int r7 = r1.compareTo(r2);

    Rational
      r8 = new RationalC(-5, 15),
      r9 = new RationalC(40, 2500),
      r10 = r8.plus(r9),
      r11 = r8.subtract(r9),
      r12 = r8.multiply(r9),
      r13 = r8.divide(r9);
    int r14 = r8.compareTo(r9);

    System.out.format("%s + %s = %s%n", r1, r2, r3);             // 8/15
    System.out.format("%s - %s = %s%n", r1, r2, r4);             // 2/15
    System.out.format("%s * %s = %s%n", r1, r2, r5);             // 1/15
    System.out.format("%s / %s = %s%n", r1, r2, r6);             // 5/3
    System.out.format("%s.compareTo(%s) = %s%n", r1, r2, r7);    // 1
    System.out.println(""); //blank line
    System.out.format("%s + %s = %s%n", r8, r9, r10);             // -119/375
    System.out.format("%s - %s = %s%n", r8, r9, r11);             // -131/275
    System.out.format("%s * %s = %s%n", r8, r9, r12);             // -2/375
    System.out.format("%s / %s = %s%n", r8, r9, r13);             // -125/6
    System.out.format("%s.compareTo(%s) = %s%n", r8, r9, r14);    // -1
  }
}
