package com.TTS.DbWebAPIs.Exceptions;

public class InternalServerException extends RuntimeException{

    public InternalServerException(String message){
        super(message);
    }
}
