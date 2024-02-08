package com.mikejuliet.userservice.entites.userEntity;

import com.mikejuliet.userservice.entites.userEntity.otherUserEntities.Address;
import com.mikejuliet.userservice.entites.userEntity.otherUserEntities.FinancialDetails;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class User {
    @Id
    private String userId;
    private String firstName;
    private String lastName;
    private int age;
    @Column(unique = true)
    private String username;
    private String password;
    private long phoneNumber;
    @OneToOne
    private Address address;
    @OneToOne
    private FinancialDetails financialDetails;


}
