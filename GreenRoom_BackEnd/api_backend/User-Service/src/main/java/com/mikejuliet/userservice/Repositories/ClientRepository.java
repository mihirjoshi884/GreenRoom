package com.mikejuliet.userservice.Repositories;

import com.mikejuliet.userservice.entites.userEntity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository <Client,String> {

    public Client findClientByUsername(String userName);

}
