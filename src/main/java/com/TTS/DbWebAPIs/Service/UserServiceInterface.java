package com.TTS.DbWebAPIs.Service;

import com.TTS.DbWebAPIs.Entity.User;
import com.TTS.DbWebAPIs.Exceptions.DatabaseException;
import com.TTS.DbWebAPIs.Exceptions.InvalidLoginDetailsException;
import com.TTS.DbWebAPIs.Exceptions.UserAlreadyExistsException;
import com.TTS.DbWebAPIs.Exceptions.UserNotFoundException;

import java.sql.SQLException;
import java.util.List;

public interface UserServiceInterface {
    void registerUser(User inputUser) throws UserAlreadyExistsException, DatabaseException;
    void areUserCredentialValid(String username, String password) throws DatabaseException, InvalidLoginDetailsException;
    User isUsernameExist(String username) throws UserNotFoundException, DatabaseException;
    List<String> getUsernameList() throws DatabaseException;
}
