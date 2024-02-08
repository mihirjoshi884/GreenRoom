package com.mikejuliet.userservice.services.serviceImpl;

import com.mikejuliet.userservice.Repositories.DeveloperRepository;
import com.mikejuliet.userservice.entites.userEntity.Developer;
import com.mikejuliet.userservice.entites.userEntity.otherUserEntities.FinancialDetails;
import com.mikejuliet.userservice.services.DeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeveloperServiceImpl implements DeveloperService {

    private static Developer developer;

    @Autowired
    private DeveloperRepository repository;

    @Override
    public Developer saveDeveloper(Developer developer) {
        String devId = UUID.randomUUID().toString();
        developer.setDeveloperId(devId);
        String userId = UUID.randomUUID().toString();
        developer.setUserId(userId);
        String addressId = UUID.randomUUID().toString();
        developer.getAddress().setAddrId(addressId);
        String userName = "Dev."+developer.getUsername();
        developer.setUsername(userName);
        return repository.save(developer);
    }

    @Override
    public Developer saveFiancialDetails(String userName, FinancialDetails financialDetails) {

        developer = repository.findDeveloperByUsername(userName);
        if (developer == null) {
            // Handle the case when the Developer does not exist, throw an exception or return an error response
            // For simplicity, let's throw an IllegalArgumentException
            throw new IllegalArgumentException("Developer with username " + userName + " not found");
        }
        String financialId = UUID.randomUUID().toString();
        developer.getFinancialDetails().setFinancialId(financialId);
        // Associate FinancialDetails with the Developer
        developer.setFinancialDetails(financialDetails);

        // Save the updated Developer entity
        return repository.save(developer);
    }

    @Override
    public FinancialDetails getFinancialDetailsByUserName(String userName) {

        developer = repository.findDeveloperByUsername(userName);
        FinancialDetails responseData = developer.getFinancialDetails();
        return responseData;
    }

    @Override
    public FinancialDetails updateFinancialDetailsByUserName(String userName, FinancialDetails financialDetails) {
        developer = repository.findDeveloperByUsername(userName);
        if (developer == null) {
            // Handle the case when the Developer does not exist, throw an exception or return an error response
            // For simplicity, let's throw an IllegalArgumentException
            throw new IllegalArgumentException("Developer with username " + userName + " not found");
        }

        // Get the existing FinancialDetails
        FinancialDetails existingFinancialDetails = developer.getFinancialDetails();

        // Check if FinancialDetails exists for the Developer
        if (existingFinancialDetails == null) {
            // Handle the case when FinancialDetails does not exist, throw an exception or return an error response
            // For simplicity, let's throw an IllegalArgumentException
            throw new IllegalArgumentException("FinancialDetails not found for Developer with username " + userName);
        }

        // Update the fields of existing FinancialDetails with the new values
        existingFinancialDetails.setBankName(financialDetails.getBankName());
        existingFinancialDetails.setBranchName(financialDetails.getBranchName());
        existingFinancialDetails.setIFSC_code(financialDetails.getIFSC_code());
        existingFinancialDetails.setAccountType(financialDetails.getAccountType());

        // Save the updated Developer entity
        repository.save(developer);

        return existingFinancialDetails;
    }

    @Override
    public FinancialDetails deleteFinancialDetailsByUserName(String userName) {
        developer = repository.findDeveloperByUsername(userName);
        developer.setFinancialDetails(null);
        return null;
    }
}
