package com.mikejuliet.userservice.entites.userEntity.otherUserEntities;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Entity @Table(name = "financial_details")
public class FinancialDetails {

    @Id
    private String financialId;
    private String bankName;
    private String branchName;
    private String IFSC_code;
    private String AccountType;
}
