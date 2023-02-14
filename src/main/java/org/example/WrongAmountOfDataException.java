package org.example;

public class WrongAmountOfDataException extends RuntimeException {
    public WrongAmountOfDataException(String s) {
        super(s);
    }
}