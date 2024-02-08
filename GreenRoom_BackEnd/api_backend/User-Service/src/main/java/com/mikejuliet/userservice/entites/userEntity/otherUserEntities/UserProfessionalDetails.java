package com.mikejuliet.userservice.entites.userEntity.otherUserEntities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_professional_details")
public class UserProfessionalDetails {
    @Id
    private String UPDid;
    private String userId;
    private String aboutYou;
    private String experience;
    @OneToOne
    private UserSkillSet userSkillSet;


}
