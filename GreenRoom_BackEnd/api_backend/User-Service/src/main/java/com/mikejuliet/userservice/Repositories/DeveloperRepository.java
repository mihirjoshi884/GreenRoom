package com.mikejuliet.userservice.Repositories;

import com.mikejuliet.userservice.entites.userEntity.Developer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer,String> {

    public Developer findDeveloperByUsername(String userName);
}
