package com.TTS.DbWebAPIs.Exceptions;

public class DatabaseException extends RuntimeException{
    public DatabaseException(String msg, Throwable cause){
        super(msg,cause);
    }
}
