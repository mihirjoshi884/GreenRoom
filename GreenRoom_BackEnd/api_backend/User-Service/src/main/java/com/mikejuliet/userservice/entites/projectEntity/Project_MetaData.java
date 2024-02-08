package com.mikejuliet.userservice.entites.projectEntity;

import com.mikejuliet.userservice.entites.userEntity.Client;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity @Table(name = "Project_MetaData")
public class Project_MetaData {
    @Id
    private String PMDid;
    @OneToOne
    private ProjectData projectData;
    private String projectName;
    @ManyToOne
    @JoinColumn(name = "userId")  // Assuming userId is the foreign key in Project_MetaData
    private Client projectClient;
    private String ownerName;
    private int no_of_invitees;
    private int no_of_employees;
    private int no_of_applicants;

}
