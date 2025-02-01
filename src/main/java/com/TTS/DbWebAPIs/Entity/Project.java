package com.TTS.DbWebAPIs.Entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Setter;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "PROJECT")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,name = "project_code")
    @NotBlank(message = "The project code can not be accepted blank")
    @Size(max = 50 , message = "The project code can not exceed 50 characters")
    private String projectCode;

    @NotBlank
    @Size(max = 100, message = "The project code can not exceed 100 characters")
    private String name;

    @ManyToOne
    @JoinColumn(name= "activity_id", referencedColumnName = "id")
    @Valid
    @NotNull
    private Activity activitiesAssociated;//check


    @ManyToOne
    @JoinColumn(name = "FK_AUTHENTICATION_USER_ID", referencedColumnName = "username")
    @Valid
    @NotNull
    private User user;
//    @ManyToMany
//    @JoinTable(
//            name = "projectBelongsToUser",
//            joinColumns = @JoinColumn(name = "project_id"),
//            inverseJoinColumns = @JoinColumn(name = "user_id")
//    )
//    private Set<User> usersAssociated = new HashSet<>();//check

    //added new
    private String createdOn;
}