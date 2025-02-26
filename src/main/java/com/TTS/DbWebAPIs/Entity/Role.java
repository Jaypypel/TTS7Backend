package com.TTS.DbWebAPIs.Entity;

import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Role {
    private Long id;
    private String name;
    @ManyToMany(mappedBy = "User")
    List<User> users = new ArrayList<>();
 }
