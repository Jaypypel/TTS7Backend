package com.TTS.DbWebAPIs.Service;

import com.TTS.DbWebAPIs.Entity.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UserServiceInterface {
    User registerUser(User inputUser) throws SQLException;
    Optional<User> getUserbyUsernameAndPassword(String username, String password) throws Exception;
    List<String> getUsernameList() throws SQLException;
}
