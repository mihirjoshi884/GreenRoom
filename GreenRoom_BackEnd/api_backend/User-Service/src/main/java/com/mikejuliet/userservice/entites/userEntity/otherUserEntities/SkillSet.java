package com.mikejuliet.userservice.entites.userEntity.otherUserEntities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "skill_set")
public class SkillSet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int SkId;
    @Column(unique = true)
    private String skillName;
    @ManyToOne
    private UserSkillSet userSkillSet;


}
