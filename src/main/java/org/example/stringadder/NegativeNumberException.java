package org.example.stringadder;

public class NegativeNumberException extends Exception{
    public NegativeNumberException() {
        super("Negative numbers are not allowed.");
    }

    public NegativeNumberException(String message) {
        super(message);
    }
}
