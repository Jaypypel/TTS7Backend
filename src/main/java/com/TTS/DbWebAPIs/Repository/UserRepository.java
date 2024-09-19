package com.TTS.DbWebAPIs.Repository;

import com.TTS.DbWebAPIs.Entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByFullNameAndUserNameAndPasswordAndEmailAndMobileNo(String fullName, String userName, String password, String email, String MobileNo);
    Optional<User> findByUserNameAndPassword(String userName,String password);
}
