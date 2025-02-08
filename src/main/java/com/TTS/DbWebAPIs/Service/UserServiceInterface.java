package com.TTS.DbWebAPIs.Service;

import com.TTS.DbWebAPIs.Entity.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UserServiceInterface {
    User registerUser(User inputUser) throws SQLException;
    Optional<User> areUserCredentialValid(String username, String password) throws SQLException;
    Optional<User> isUsernameExist(String username) throws Exception;
    List<String> getUsernameList() throws SQLException;
}
