package org.example.stringadder;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Sums comma-separated integers in a string. Empty or blank input sums to 0.
 */
public class StringAdder {

    private final Display display;

    public StringAdder(Display display) {
        this.display = display;
    }

    public int add(String numbers) throws NegativeNumberException {
        if (numbers == null || numbers.isBlank()) {
            return 0;
        }

        String currentNumbers = numbers;
        String separator = ",|\n"; // default regex: comma or newline
        if (numbers.startsWith("//")) {
            int newSeparatorIndex = numbers.indexOf("//") + 2;
            String sepChar = Character.toString(numbers.charAt(newSeparatorIndex));
            separator = Pattern.quote(sepChar); // escape any regex metacharacters
            currentNumbers = numbers.substring(newSeparatorIndex + 2);
        }

        String[] tokens = Arrays.stream(currentNumbers.split(separator))
                .filter(s -> !s.isEmpty())
                .toArray(String[]::new);

        for (String token : tokens) {
            if (token.trim().startsWith("-")) {
                throw new NegativeNumberException("Negative numbers are not allowed: " + token.trim());
            }
        }

        if (tokens[0].trim().startsWith("-")) {
            throw new NegativeNumberException("Negative numbers are not allowed: " + tokens[0].trim());
        }

        int sum = Arrays.stream(tokens)
                .map(String::trim)
                .mapToInt(Integer::parseInt)
                .sum();

        if (tokens.length >= 2) {
            String operandsJoined = Arrays.stream(tokens)
                    .map(String::trim)
                    .collect(Collectors.joining(" + "));
            display.show(operandsJoined + " = " + sum);
        }

        return sum;
    }
}
