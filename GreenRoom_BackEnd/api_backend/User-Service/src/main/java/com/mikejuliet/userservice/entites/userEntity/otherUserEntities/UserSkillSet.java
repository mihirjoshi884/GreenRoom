package com.mikejuliet.userservice.entites.userEntity.otherUserEntities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "userSkillSet")
public class UserSkillSet {
    @Id
    private String userSKid;
    private String userId;
    //@OneToMany(cascade = CascadeType.ALL,mappedBy = "userSkillSet")
    @OneToMany(cascade = CascadeType.ALL)
    private List<SkillSet> skillSets;

}
