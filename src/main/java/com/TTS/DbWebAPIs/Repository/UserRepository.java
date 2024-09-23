package com.TTS.DbWebAPIs.Repository;

import com.TTS.DbWebAPIs.Entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByFullNameAndUsernameAndPasswordAndEmailAndMobileNo(String fullName, String username, String password, String email, String MobileNo);
    Optional<User> findByUsernameAndPassword(String username,String password);
    String findByUsername(String username);


}
