package com.TTS.DbWebAPIs.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Setter
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    @Column(unique = true)
    private String username;

    private String password;

    private   String email;

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


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                ", createdOn=" + createdOn +
                '}';
    }
}
