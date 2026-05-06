package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StringAdderTest {

    private final StringAdder adder = new StringAdder();

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "  ", "\t", "\n"})
    void add_blankOrEmpty_returnsZero(String input) throws NegativeNumberException {
        assertEquals(0, adder.add(input));
    }

    @ParameterizedTest
    @CsvSource({
            "'1', 1",
            "'4\n2', 6",
            "'\n12', 12",
            "'\t123', 123",
            "' 1 ', 1"
    })
    void add_validStringWithNumber_returnsNumber(String input, int expected) throws NegativeNumberException {
        assertEquals(expected, adder.add(input));
    }

    @ParameterizedTest
    @CsvSource({
            "'1', 1",
            "'1\n42', 43",
            "'1,2', 3",
            "'1,2,3', 6",
            "' 1 , 2 ', 3"
    })
    void add_validInput_returnsSum(String input, int expected) throws NegativeNumberException {
        assertEquals(expected, adder.add(input));
    }

    @ParameterizedTest
    @CsvSource({
            "'//;\n1;2', 3",
            "'//|\n1|2|3', 6",
    })
    void add_specificSeparator_returnsSum(String input, int expected) throws NegativeNumberException {
        assertEquals(expected, adder.add(input));
    }

    @ParameterizedTest
    @CsvSource({
            "'-1', '-1'",
            "'//;\n-1;2', '-1'",
            "'//|\n1|-2|3', '-2'",
    })
    void add_negativeNumber_throwsNegativeNumberException(String input, String output) {
        Throwable exception = assertThrows(NegativeNumberException.class, () -> adder.add(input));
        assertEquals("Negative numbers are not allowed: " + output, exception.getMessage());
    }
}
