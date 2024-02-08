package com.mikejuliet.userservice.Repositories;

import com.mikejuliet.userservice.entites.userEntity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {


}
