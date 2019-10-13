package com.homeBudget.configuration.error;

public class RecordExistsException extends RuntimeException{

    public RecordExistsException(String message) {
        super(message);
    }
}
