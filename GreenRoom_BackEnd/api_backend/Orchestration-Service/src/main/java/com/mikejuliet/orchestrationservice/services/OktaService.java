package com.mikejuliet.orchestrationservice.services;

import org.springframework.http.HttpStatus;

public interface OktaService {

    public HttpStatus createOktaUser(String firstName,String lastName, String userName,String Password);
}
