package com.TTS.DbWebAPIs.Entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalTime;

@Data
@Entity
@Table(name = "TASK")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100, message = "task name can not exceed 100 characters")
    private String name;

    @ManyToOne
    @JoinColumn(name = "activity_id", referencedColumnName = "id")
    @Valid
    @NotNull
    private Activity activityAssociated;//check

    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName= "username")
    @Valid
    @NotNull
    private User user;//check
    //added
    private String createdOn;

}
