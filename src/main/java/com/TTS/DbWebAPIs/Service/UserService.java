package com.TTS.DbWebAPIs.Service;


import com.TTS.DbWebAPIs.Entity.User;
import com.TTS.DbWebAPIs.Repository.UserRepository;
import com.TTS.DbWebAPIs.Util.DateAndTimeConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService implements UserServiceInterface {

    @Autowired
    UserRepository userRepository;

    public User registerUser(User inputUser) throws SQLException {
        User oldUser = userRepository.findByFullNameAndUsernameAndPasswordAndEmailAndMobileNo(inputUser.getFullName(),inputUser.getUsername(), inputUser.getPassword(), inputUser.getEmail(), inputUser.getMobileNo());
        if(oldUser != null){
            return null;
        }

        User newUser = new User();
        newUser.setUsername(inputUser.getUsername());
        newUser.setFullName(inputUser.getFullName());
        newUser.setPassword(inputUser.getPassword());
        newUser.setEmail(inputUser.getEmail());
        newUser.setMobileNo(inputUser.getMobileNo());
        newUser.setCreatedOn(DateAndTimeConfig.getCurrentDateAndTime());
        return userRepository.save(newUser);
    }

    public Optional<User> getUserbyUsernameAndPassword(String username, String password) throws Exception {
       return Optional.ofNullable(userRepository.findByUsernameAndPassword(username,password).orElseThrow(() -> new Exception("invalid details")));
    }

    //get a list of username through users list
    public List<String> getUsernameList()throws SQLException{
        List<String> username = new ArrayList<>();
        List<User> users = userRepository.findAll();
        for (User user : users) {
            username.add(user.getUsername());
        }
        return username;
    }


}
