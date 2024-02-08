package com.mikejuliet.userservice.entites.projectEntity;

import com.mikejuliet.userservice.entites.userEntity.Developer;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Entity @Table(name = "Project_Data")
public class ProjectData {
    @Id
    private String ProjectId;
    private String description;
    private String Requirements;

    @OneToMany(mappedBy = "projectData")
    private List<Developer> developers;
}
