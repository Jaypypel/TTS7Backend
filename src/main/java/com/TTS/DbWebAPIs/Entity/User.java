package com.TTS.DbWebAPIs.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Full name can not be empty")
    @Size(max = 100, min= 10, message = "your full name needs to be between 10 and 100 characters")
    private String fullName;

    @Column(unique = true)
    @NotBlank(message = "Username name can not be empty")
    @Size(min = 4 , max=15, message = "username must be between 4 and 15 characters")
    private String username;

    @NotBlank(message = "Password can not be blank")
    @Size(min = 8, max=64)
    private String password;

    @Column(unique = true)
    @Email(message = "the entered email id is not correct format")
    @NotBlank
    private String email;

    @Column(unique = true)
    private String mobileNo;

    private String createdOn;

//    @ManyToMany(mappedBy = "usersAssociated",cascade = CascadeType.ALL)
//    private Set<Project> projectId = new HashSet<>();//check

//    @OneToMany(mappedBy = "user")
//    private List<DailyTimeShare> userAssociated;//check
//
//    @OneToMany(mappedBy = "user")
//    private List<Task> task;//check
//
//    @OneToMany(mappedBy = "taskOwnerUserID", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<TaskManagement> taskOwner;//check
//
//    @OneToMany(mappedBy = "taskReceivedUserID")
//    private List<TaskManagement> taskReceiver;//check
//
//    @OneToMany(mappedBy = "userId")
//    private List<TimeShareOtherActivity> otherActivitiesAssociated;//check
//
//    @OneToMany(mappedBy = "user")
//    private List<Activity> userActivity;//check
}
