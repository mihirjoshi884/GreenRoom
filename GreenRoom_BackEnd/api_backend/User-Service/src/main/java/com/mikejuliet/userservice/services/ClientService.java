package com.mikejuliet.userservice.services;


import com.mikejuliet.userservice.entites.userEntity.Client;
import com.mikejuliet.userservice.entites.userEntity.otherUserEntities.FinancialDetails;
import org.springframework.stereotype.Service;

@Service
public interface ClientService {
    public Client saveClient(Client client);
    public Client saveFiancialDetails(String userName, FinancialDetails financialDetails);
    public FinancialDetails getFinancialDetailsByUserName(String userName);
    public FinancialDetails updateFinancialDetailsByUserName(String userName, FinancialDetails financialDetails);
    public FinancialDetails deleteFinancialDetailsByUserName(String userName);

}
