package com.mikejuliet.userservice.services.serviceImpl;

import com.mikejuliet.userservice.Repositories.ClientRepository;
import com.mikejuliet.userservice.entites.userEntity.Client;
import com.mikejuliet.userservice.entites.userEntity.otherUserEntities.FinancialDetails;
import com.mikejuliet.userservice.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository repository;
    private static Client client;
    @Override
    public Client saveClient(Client client) {
        String clientId = UUID.randomUUID().toString();
        client.setClientId(clientId);
        String userId = UUID.randomUUID().toString();
        client.setUserId(userId);
        String addressId = UUID.randomUUID().toString();
        client.getAddress().setAddrId(addressId);
        String userName = "Cli."+client.getUsername();
        client.setUsername(userName);
        return repository.save(client);
    }

    @Override
    public Client saveFiancialDetails(String userName, FinancialDetails financialDetails) {
        client = repository.findClientByUsername(userName);
        if (client == null) {
            // Handle the case when the Developer does not exist, throw an exception or return an error response
            // For simplicity, let's throw an IllegalArgumentException
            throw new IllegalArgumentException("client with username " + userName + " not found");
        }
        String financialId = UUID.randomUUID().toString();
        client.getFinancialDetails().setFinancialId(financialId);
        // Associate FinancialDetails with the Developer
        client.setFinancialDetails(financialDetails);

        // Save the updated Developer entity
        return repository.save(client);
    }

    @Override
    public FinancialDetails getFinancialDetailsByUserName(String userName) {
        client = repository.findClientByUsername(userName);
        FinancialDetails responseData = client.getFinancialDetails();
        return responseData;
    }

    @Override
    public FinancialDetails updateFinancialDetailsByUserName(String userName, FinancialDetails financialDetails) {
        client = repository.findClientByUsername(userName);
        if (client == null || client.getFinancialDetails() == null) {
            // Handle the case when the Client or FinancialDetails does not exist
            return null;
        }

        // Get the existing FinancialDetails
        FinancialDetails existingFinancialDetails = client.getFinancialDetails();

        // Update the fields of existing FinancialDetails with the new values
        existingFinancialDetails.setBankName(financialDetails.getBankName());
        existingFinancialDetails.setBranchName(financialDetails.getBranchName());
        existingFinancialDetails.setIFSC_code(financialDetails.getIFSC_code());
        existingFinancialDetails.setAccountType(financialDetails.getAccountType());

        // Save the updated Client entity
        repository.save(client);

        return existingFinancialDetails;
    }

    @Override
    public FinancialDetails deleteFinancialDetailsByUserName(String userName) {
        client = repository.findClientByUsername(userName);
        if (client == null || client.getFinancialDetails() == null) {
            // Handle the case when the Client or FinancialDetails does not exist
            return null;
        }

        // Get the existing FinancialDetails
        FinancialDetails existingFinancialDetails = client.getFinancialDetails();

        // Disassociate FinancialDetails from Client
        client.setFinancialDetails(null);

        // Save the updated Client entity
        repository.save(client);

        return existingFinancialDetails;
    }
}
