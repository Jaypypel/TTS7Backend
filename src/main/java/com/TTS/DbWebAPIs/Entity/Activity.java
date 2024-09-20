package com.TTS.DbWebAPIs.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.aot.generate.GeneratedTypeReference;

import java.time.LocalTime;


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
    private User FK_USER_ID;

    private LocalTime createdOn;

}
