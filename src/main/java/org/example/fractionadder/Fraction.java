package org.example.fractionadder;

public class Fraction {
    private final int numerator;
    private final int denominator;

    public Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public Fraction(int whole) {
        this.numerator = whole;
        this.denominator = 1;
    }

    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    @Override
    public String toString() {
        if (denominator == 1) {
            return Integer.toString(numerator);
        }
        return numerator + "/" + denominator;
    }
}
