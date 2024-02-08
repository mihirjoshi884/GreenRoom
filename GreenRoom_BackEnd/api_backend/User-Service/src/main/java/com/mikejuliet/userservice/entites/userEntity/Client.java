package com.mikejuliet.userservice.entites.userEntity;


import com.mikejuliet.userservice.entites.projectEntity.Project_MetaData;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component @Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Entity @Table(name = "Client_Table")
public class Client extends User{

    @Column(unique = true)
    private String clientId;

    @OneToMany(mappedBy = "projectClient")
    private List<Project_MetaData> projects;

}
