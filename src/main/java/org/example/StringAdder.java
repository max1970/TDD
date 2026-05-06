package org.example;

import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Sums comma-separated integers in a string. Empty or blank input sums to 0.
 */
public class StringAdder {

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

        for(String token: tokens) {
            if(token.trim().startsWith("-")) {
                throw new NegativeNumberException("Negative numbers are not allowed: " + token.trim());
            }
        }

        if(tokens[0].trim().startsWith("-")) {
            throw new NegativeNumberException("Negative numbers are not allowed: " + tokens[0].trim());
        }

        if (tokens.length == 1) {
            String trimmed = tokens[0].trim();
            return Integer.parseInt(trimmed);
        } else {
            return Arrays.stream(tokens)
                    .map(String::trim)
                    .mapToInt(Integer::parseInt)
                    .sum();
        }
    }
}
