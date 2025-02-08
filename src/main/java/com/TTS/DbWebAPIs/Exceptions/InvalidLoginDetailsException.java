package com.TTS.DbWebAPIs.Exceptions;

public class InvalidLoginDetailsException extends Exception {
    public InvalidLoginDetailsException(String message){
        super(message);
    }
}
