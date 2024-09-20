package com.TTS.DbWebAPIs.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    private String username;

    private String password;

    private   String email;

    private String mobileNo;

    private LocalTime time;

    @ManyToMany(mappedBy = "FK_Authentication_ID")
    private Set<Project> projectId = new HashSet<>();


    
}
