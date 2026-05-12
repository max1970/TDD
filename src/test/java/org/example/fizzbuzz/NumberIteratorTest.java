package org.example.fizzbuzz;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NumberIteratorTest {

    /**
     * The iterator counts 1 … max. For each number it asks the renderer for text,
     * then passes that text to the display. This test uses tiny “fake” objects that
     * just remember what happened, so we can check the order without real FizzBuzz or console I/O.
     */
    @Test
    void displayNumbersUpTo_rendersThenShowsEachNumberInOrder() {
        RecordingRenderer renderer = new RecordingRenderer();
        RecordingDisplay display = new RecordingDisplay();
        NumberIterator iterator = new NumberIterator(renderer, display);

        iterator.displayNumbersUpTo(3);

        assertEquals(List.of(1, 2, 3), renderer.numbersSeenByRenderer());
        assertEquals(List.of("1", "2", "3"), display.textsShown());
    }

    @Test
    void displayNumbersUpTo_100_rendersFizzBuzzForEachNumber() {
        FizzBuzz fizzBuzz = new FizzBuzz();
        RecordingDisplay display = new RecordingDisplay();
        NumberIterator iterator = new NumberIterator(fizzBuzz, display);

        List<String> expected = new ArrayList<>();
        for (int n = 1; n <= 100; n++) {
            expected.add(fizzBuzz.renderInput(n));
        }

        iterator.displayNumbersUpTo(100);

        assertEquals(100, display.textsShown().size(),"Number of iterations");
        assertEquals(expected, display.textsShown(),"Rendered values (100)");
    }

    /** Pretends to be a {@link NumberRenderer}: remembers each input, returns simple text. */
    private static final class RecordingRenderer implements NumberRenderer {
        private final List<Integer> numbers = new ArrayList<>();

        @Override
        public String renderInput(int input) {
            numbers.add(input);
            return Integer.toString(input);
        }

        List<Integer> numbersSeenByRenderer() {
            return Collections.unmodifiableList(numbers);
        }
    }

    /** Pretends to be a {@link NumberDisplay}: remembers every string passed to {@link NumberDisplay#show}. */
    private static final class RecordingDisplay implements NumberDisplay {
        private final List<String> texts = new ArrayList<>();

        @Override
        public void show(String value) {
            texts.add(value);
            System.out.println(value);
        }

        List<String> textsShown() {
            return Collections.unmodifiableList(texts);
        }
    }
}
