package org.example.stringadder;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StringAdderDisplayMockTest {

    @Mock
    private Display display;

    @Test
    void add_twoCommaSeparatedNumbers_displayReceivesFormattedEquation() throws NegativeNumberException {
        StringAdder calculator = new StringAdder(display);

        calculator.add("1,2");

        verify(display).show("1 + 2 = 3");
    }
}
