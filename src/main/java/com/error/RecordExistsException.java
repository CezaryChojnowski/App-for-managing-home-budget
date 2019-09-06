package com.error;

public class RecordExistsException extends RuntimeException{

    public RecordExistsException(String message) {
        super(message);
    }
}
