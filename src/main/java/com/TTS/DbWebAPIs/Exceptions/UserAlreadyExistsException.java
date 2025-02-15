package com.TTS.DbWebAPIs.Exceptions;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException(String  message){
        super(message);
    }
}
