package com.TTS.DbWebAPIs.Entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.aot.generate.GeneratedTypeReference;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;



@NoArgsConstructor
@AllArgsConstructor

@Data
@Table(name = "ACTIVITY")
@Entity
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "activity name can not be blank")
    @Column(unique = true)
    @Size(max = 50, message = "activity name can not exceed 50 characters")
    private String name;

    @Valid()
    @NotNull(message = "user name can not be null")
    @ManyToOne
    @JoinColumn(name= "username", referencedColumnName = "username")
    private User user;//check

//    @ManyToOne
//    @JoinColumn(name = "project_id", referencedColumnName = "id")
//    private Project projectAssociated;//check
//
//    @OneToMany(mappedBy = "activityAssociated")
//    private List<Task> taskAssociated;//check

    @CreatedDate
    private String createdOn;

    @LastModifiedDate
    private String modifiedOn;

}
