package com.TTS.DbWebAPIs.Exceptions;

import java.sql.SQLException;

public class DatabaseException extends SQLException {
    public DatabaseException(String msg){
        super(msg);
    }
}
