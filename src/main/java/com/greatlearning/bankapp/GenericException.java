package com.greatlearning.bankapp;

/**
 * GenericException for exception handling
 */
public class GenericException extends Exception {
    String errorMessage;

    public GenericException(String message) {
        errorMessage = message;
    }

    public String getMessage() {
        return errorMessage;
    }
}
