package org.example.stringadder;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StringAdderTest {

    private static final Display NO_OP_DISPLAY = message -> {
    };

    private final StringAdder adder = new StringAdder(NO_OP_DISPLAY);

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

    @ParameterizedTest
    @CsvSource({
            "'1,2', '1 + 2 = 3'",
            "'1,2,3', '1 + 2 + 3 = 6'",
            "'//;\n1;2', '1 + 2 = 3'",
    })
    void add_validInput_displaysOperandsJoinedWithPlusAndSum(String input, String expectedDisplay)
            throws NegativeNumberException {
        CapturingDisplay display = new CapturingDisplay();
        StringAdder calculator = new StringAdder(display);

        calculator.add(input);

        assertEquals(expectedDisplay, display.lastMessage());
    }

    /** Test double: records the last message passed to {@link Display#show(String)}. */
    private static final class CapturingDisplay implements Display {
        private String lastMessage;

        @Override
        public void show(String message) {
            this.lastMessage = message;
        }

        String lastMessage() {
            return lastMessage;
        }
    }
}
