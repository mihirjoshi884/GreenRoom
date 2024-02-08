package com.mikejuliet.userservice.services;

import com.mikejuliet.userservice.entites.userEntity.Developer;
import com.mikejuliet.userservice.entites.userEntity.otherUserEntities.FinancialDetails;
import org.springframework.stereotype.Service;

@Service
public interface DeveloperService {

    public Developer saveDeveloper(Developer developer);
    public Developer saveFiancialDetails(String userName, FinancialDetails financialDetails);
    public FinancialDetails getFinancialDetailsByUserName(String userName);
    public FinancialDetails updateFinancialDetailsByUserName(String userName, FinancialDetails financialDetails);
    public FinancialDetails deleteFinancialDetailsByUserName(String userName);


}
