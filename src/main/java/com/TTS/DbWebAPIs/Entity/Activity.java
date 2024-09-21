package com.TTS.DbWebAPIs.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.aot.generate.GeneratedTypeReference;

import java.time.LocalTime;
import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor

@Data
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name= "user_id", referencedColumnName = "id")
    private User user;//check

    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    private Project projectAssociated;//check

    @OneToMany(mappedBy = "activityAssociated")
    private List<Task> taskAssociated;//check

    private LocalTime createdOn;

}
