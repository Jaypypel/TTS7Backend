package com.TTS.DbWebAPIs.Service;


import com.TTS.DbWebAPIs.Entity.User;
import com.TTS.DbWebAPIs.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public boolean registerUser(User inputUser){
        User oldUser = userRepository.findByFullNameAndUserNameAndPasswordAndEmailAndMobileNo(inputUser.getFullName(),inputUser.getUsername(), inputUser.getPassword(), inputUser.getEmail(), inputUser.getMobileNo());
        if(oldUser != null){
            return false;
        }

        User newUser = new User();
        newUser.setUsername(inputUser.getUsername());
        newUser.setFullName(inputUser.getFullName());
        newUser.setPassword(inputUser.getPassword());
        newUser.setEmail(inputUser.getEmail());
        newUser.setMobileNo(inputUser.getMobileNo());
        userRepository.save(newUser);
        return true;
    }

    public Optional<User> getUserbyUsernameAndPassword(String username, String password) throws Exception {
       return Optional.ofNullable(userRepository.findByUserNameAndPassword(username,password).orElseThrow(() -> new Exception("invalid details")));
    }


}
