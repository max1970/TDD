package org.example.fractionadder;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FractionAdderTest {

    @Test
    public void test_should_add_fraction() {
        Fraction fraction1 = new Fraction(1,5);
        Fraction fraction2 = new Fraction(1,5);
        FractionAdder fractionAdder = new FractionAdder(fraction1, fraction2);

        assertEquals("2/5", fractionAdder.addFractions(fraction1, fraction2), "Adding fractions");
    }

    @Test
    public void test_should_add_fractions_and_round_whole_number() {
        Fraction fraction1 = new Fraction(3,5);
        Fraction fraction2 = new Fraction(3,5);
        FractionAdder fractionAdder = new FractionAdder(fraction1, fraction2);

        assertEquals("1 1/5", fractionAdder.addFractions(fraction1, fraction2), "Adding fractions to result whole number and fraction");
    }

    @Test
    public void should_test_add_fractions_and_round_whole_number_without_remaining_fraction() {
        Fraction fraction1 = new Fraction(2,5);
        Fraction fraction2 = new Fraction(3,5);
        FractionAdder fractionAdder = new FractionAdder(fraction1, fraction2);

        assertEquals("1", fractionAdder.addFractions(fraction1, fraction2), "Adding fractions to result whole number and fraction");

    }

    @Test
    public void should_test_add_fractions_with_different_denominator() {
        Fraction fraction1 = new Fraction(2,4);
        Fraction fraction2 = new Fraction(3,5);
        FractionAdder fractionAdder = new FractionAdder(fraction1, fraction2);

        assertEquals("1 1/10", fractionAdder.addFractions(fraction1, fraction2), "Adding fractions with different denominator");
    }

    @Test
    public void should_test_add_fraction_and_simplify_fraction() {

    }
}
