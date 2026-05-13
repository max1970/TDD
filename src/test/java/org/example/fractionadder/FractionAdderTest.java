package org.example.fractionadder;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FractionAdderTest {

    @Nested
    @DisplayName("Same denominator")
    class SameDenominator {
        @Test
        void adds_two_proper_fractions() {
            FractionAdder adder = new FractionAdder(new Fraction(1, 5), new Fraction(1, 5));
            assertEquals("2/5", adder.add());
        }

        @Test
        void extracts_whole_part_when_sum_is_improper() {
            FractionAdder adder = new FractionAdder(new Fraction(3, 5), new Fraction(3, 5));
            assertEquals("1 1/5", adder.add());
        }

        @Test
        void sums_to_exact_whole_with_no_fractional_remainder() {
            FractionAdder adder = new FractionAdder(new Fraction(2, 5), new Fraction(3, 5));
            assertEquals("1", adder.add());
        }
    }

    @Nested
    @DisplayName("Different denominators")
    class DifferentDenominators {
        @Test
        void finds_common_denominator_and_mixed_number() {
            FractionAdder adder = new FractionAdder(new Fraction(2, 4), new Fraction(3, 5));
            assertEquals("1 1/10", adder.add());
        }
    }

    @Nested
    @DisplayName("Simplification")
    class Simplification {
        @Test
        void reduces_result_to_lowest_terms() {
            FractionAdder adder = new FractionAdder(new Fraction(1, 6), new Fraction(1, 6));
            assertEquals("1/3", adder.add());
        }
    }
}
