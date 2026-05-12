package org.example.fizzbuzz;

import org.example.stringadder.NegativeNumberException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FizzBuzzTest {

    public final FizzBuzz fizzBuzz = new FizzBuzz();


    @Test
    void test_should_render_value_for_number()  {
        assertEquals("1", fizzBuzz.renderInput(1));
    }

    @Test
    void test_should_render_fizz_for_3() {
        assertEquals("fizz", fizzBuzz.renderInput(3));
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 6, 9, 12})
    void test_should_render_fizz_for_multiples_of_3(Integer input) {
        assertEquals("fizz", fizzBuzz.renderInput(input));
    }

    @Test
    void test_should_render_fizz_for_5() {
        assertEquals("buzz", fizzBuzz.renderInput(5));
    }

    @ParameterizedTest
    @ValueSource(ints = {5, 10, 20})
    void test_should_render_fizz_for_multiples_of_5(Integer input) {
        assertEquals("buzz", fizzBuzz.renderInput(input));
    }

    @Test
    void test_should_render_fizz_buzz_for_15() {
        assertEquals("fizzbuzz", fizzBuzz.renderInput(15));
    }

    @ParameterizedTest
    @ValueSource(ints = {15, 30, 45})
    void test_should_render_fizz_buzz_for_numbers_divisible_by_3_and_5(Integer input) {
        assertEquals("fizzbuzz", fizzBuzz.renderInput(input));
    }
}
