package com.mikejuliet.orchestrationservice.services.serviceimpl;

import com.mikejuliet.orchestrationservice.services.OktaService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
@Service
public class OktaServiceImpl implements OktaService {

    @Value("${okta.oauth2.issuer}")
    private String oktaIssuer;

    @Value("${okta.oauth2.client-id}")
    private String oktaClientId;

    @Value("${okta.oauth2.client-secret}")
    private String oktaClientSecret;


    @Override
    public HttpStatus createOktaUser(String firstName, String lastName, String userName, String Password) {

        return null;
    }
}
