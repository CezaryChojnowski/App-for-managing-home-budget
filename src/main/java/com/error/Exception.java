package com.error;

public class Exception extends RuntimeException{

    //Exception?? but what Exception? that name means nothing
    //Create some specific Exceptions like UserNotFoundException etc
    public Exception(String message) {
        super(message);
    }
}
