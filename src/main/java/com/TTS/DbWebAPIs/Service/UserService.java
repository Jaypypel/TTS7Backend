package com.TTS.DbWebAPIs.Service;


import com.TTS.DbWebAPIs.Entity.User;
import com.TTS.DbWebAPIs.Exceptions.*;
import com.TTS.DbWebAPIs.Repository.UserRepository;
import com.TTS.DbWebAPIs.Util.DateAndTimeConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserServiceInterface {

    @Autowired
    UserRepository userRepository;


    @CacheEvict(value = "users", allEntries = true)
    public void registerUser(User inputUser) throws DatabaseException, UserAlreadyExistsException {

       if( userRepository
               .existsByUsernameOrEmailOrMobileNo(
                       inputUser.getUsername(), inputUser.getEmail(), inputUser.getMobileNo()
               )){

           throw new UserAlreadyExistsException("User already exist by mobile no. :" +
                   inputUser.getMobileNo() + " or email address: " +
                   inputUser.getEmail() + " or username:  " + inputUser.getUsername());}

                   User newUser = UserService.setUser(inputUser);
        userRepository.save(newUser);
    }

    public void areUserCredentialValid(String username, String password) throws InvalidLoginDetailsException, DatabaseException {
        userRepository
                .findByUsernameAndPassword(username, password)
                .orElseThrow(() ->
                        new InvalidLoginDetailsException("Invalid login details ")
                );
    }

    @Override
    public User isUsernameExist(String username) throws UserNotFoundException {
        return userRepository
                .findByUsername(username)
                .orElseThrow(
                        () -> new UserNotFoundException(
                                "User not found by : "+username
                        )
                );
    }


    @Cacheable("username")
    //get a list of username through users list
    public List<String> getUsernameList() throws DatabaseException {

      return userRepository
              .findAll()
              .stream()
              .map(User::getUsername)
              .collect(
                      Collectors
                              .toList()
              );
//        List<String> username = new ArrayList<>();
//        List<User> users = userRepository.findAll();
//        for (User user : users) {
//            username.add(user.getUsername());
//        }
//        return username;
    }


    private static User setUser(User inputUser){
        User newUser = new User();
        newUser.setUsername(inputUser.getUsername());
        newUser.setFullName(inputUser.getFullName());
        newUser.setPassword(inputUser.getPassword());
        newUser.setEmail(inputUser.getEmail());
        newUser.setMobileNo(inputUser.getMobileNo());
        newUser.setCreatedOn(DateAndTimeConfig.getCurrentDateAndTime());
        return newUser;
    }

}
