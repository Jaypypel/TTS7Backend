package com.TTS.DbWebAPIs.Service;

import com.TTS.DbWebAPIs.Entity.User;
import com.TTS.DbWebAPIs.Exceptions.InvalidLoginDetailsException;
import com.TTS.DbWebAPIs.Exceptions.UserAlreadyExistsException;

import java.sql.SQLException;
import java.util.List;

public interface UserServiceInterface {
    void registerUser(User inputUser) throws SQLException, UserAlreadyExistsException;
    void areUserCredentialValid(String username, String password) throws SQLException, InvalidLoginDetailsException;
    User isUsernameExist(String username) throws Exception;
    List<String> getUsernameList() throws SQLException;
}
