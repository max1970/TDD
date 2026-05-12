package org.example.fizzbuzz;

public class NumberIterator {

    private final NumberRenderer renderer;
    private final NumberDisplay display;

    public NumberIterator(NumberRenderer renderer, NumberDisplay display) {
        this.renderer = renderer;
        this.display = display;
    }

    public void displayNumbersUpTo(int max) {
        for (int number = 1; number <= max; number++) {
            display.show(renderer.renderInput(number));
        }
    }
}
