package com.TTS.DbWebAPIs.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany
    @JoinColumn(name = "activity_id", referencedColumnName = "id")
    private List<Long> FK_Activity_ID;

    @ManyToMany
    @JoinTable(
            name = "projectBelongsToUser",
        joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<String> FK_Authentication_ID = new HashSet<>();


}
