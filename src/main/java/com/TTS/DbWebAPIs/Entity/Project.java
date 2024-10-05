package com.TTS.DbWebAPIs.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String projectCode;

    private String name;

    @ManyToOne
    @JoinColumn(name= "activity_id", referencedColumnName = "id")
    private Activity activitiesAssociated;//check

    @ManyToMany
    @JoinTable(
            name = "projectBelongsToUser",
        joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> usersAssociated = new HashSet<>();//check

    //added new
    private LocalTime createdOn;
}
