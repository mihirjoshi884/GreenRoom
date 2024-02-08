package com.mikejuliet.userservice.Controller;

import com.mikejuliet.userservice.entites.userEntity.Client;
import com.mikejuliet.userservice.entites.userEntity.Developer;
import com.mikejuliet.userservice.entites.userEntity.otherUserEntities.FinancialDetails;
import com.mikejuliet.userservice.services.ClientService;
import com.mikejuliet.userservice.services.DeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private DeveloperService developerService;

    @Autowired
    private ClientService clientService;

    // Developer Endpoints

    @PostMapping("/developer")
    public ResponseEntity<Developer> saveDeveloper(@RequestBody Developer developer) {
        Developer savedDeveloper = developerService.saveDeveloper(developer);
        return new ResponseEntity<>(savedDeveloper, HttpStatus.CREATED);
    }

    @PostMapping("/developer/{userName}/financial-details")
    public ResponseEntity<Developer> saveDeveloperFinancialDetails(@PathVariable String userName, @RequestBody FinancialDetails financialDetails) {
        Developer savedDeveloper = developerService.saveFiancialDetails(userName, financialDetails);
        return new ResponseEntity<>(savedDeveloper, HttpStatus.CREATED);
    }

    @GetMapping("/developer/{userName}/financial-details")
    public ResponseEntity<FinancialDetails> getDeveloperFinancialDetails(@PathVariable String userName) {
        FinancialDetails financialDetails = developerService.getFinancialDetailsByUserName(userName);
        return new ResponseEntity<>(financialDetails, HttpStatus.OK);
    }

    @PutMapping("/developer/{userName}/financial-details")
    public ResponseEntity<FinancialDetails> updateDeveloperFinancialDetails(@PathVariable String userName, @RequestBody FinancialDetails financialDetails) {
        FinancialDetails updatedFinancialDetails = developerService.updateFinancialDetailsByUserName(userName, financialDetails);
        if (updatedFinancialDetails != null) {
            return new ResponseEntity<>(updatedFinancialDetails, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/developer/{userName}/financial-details")
    public ResponseEntity<FinancialDetails> deleteDeveloperFinancialDetails(@PathVariable String userName) {
        FinancialDetails deletedFinancialDetails = developerService.deleteFinancialDetailsByUserName(userName);
        if (deletedFinancialDetails != null) {
            return new ResponseEntity<>(deletedFinancialDetails, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Client Endpoints

    @PostMapping("/client")
    public ResponseEntity<Client> saveClient(@RequestBody Client client) {
        Client savedClient = clientService.saveClient(client);
        return new ResponseEntity<>(savedClient, HttpStatus.CREATED);
    }

    @PostMapping("/client/{userName}/financial-details")
    public ResponseEntity<Client> saveClientFinancialDetails(@PathVariable String userName, @RequestBody FinancialDetails financialDetails) {
        Client savedClient = clientService.saveFiancialDetails(userName, financialDetails);
        return new ResponseEntity<>(savedClient, HttpStatus.CREATED);
    }

    @GetMapping("/client/{userName}/financial-details")
    public ResponseEntity<FinancialDetails> getClientFinancialDetails(@PathVariable String userName) {
        FinancialDetails financialDetails = clientService.getFinancialDetailsByUserName(userName);
        return new ResponseEntity<>(financialDetails, HttpStatus.OK);
    }

    @PutMapping("/client/{userName}/financial-details")
    public ResponseEntity<FinancialDetails> updateClientFinancialDetails(@PathVariable String userName, @RequestBody FinancialDetails financialDetails) {
        FinancialDetails updatedFinancialDetails = clientService.updateFinancialDetailsByUserName(userName, financialDetails);
        if (updatedFinancialDetails != null) {
            return new ResponseEntity<>(updatedFinancialDetails, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/client/{userName}/financial-details")
    public ResponseEntity<FinancialDetails> deleteClientFinancialDetails(@PathVariable String userName) {
        FinancialDetails deletedFinancialDetails = clientService.deleteFinancialDetailsByUserName(userName);
        if (deletedFinancialDetails != null) {
            return new ResponseEntity<>(deletedFinancialDetails, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
