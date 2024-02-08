package com.mikejuliet.userservice.entites.userEntity;

import com.mikejuliet.userservice.entites.projectEntity.ProjectData;
import com.mikejuliet.userservice.entites.userEntity.otherUserEntities.UserProfessionalDetails;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component @Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Entity @Table(name = "Developer_Table")
public class Developer extends User {

    @Column(unique = true)
    private String developerId;
    @OneToOne(cascade = CascadeType.ALL)
    private UserProfessionalDetails userProfessionalDetails;

    @ManyToOne
    @JoinColumn(name = "project_id")  // Assuming project_id is the foreign key in Developer
    private ProjectData projectData;
}
