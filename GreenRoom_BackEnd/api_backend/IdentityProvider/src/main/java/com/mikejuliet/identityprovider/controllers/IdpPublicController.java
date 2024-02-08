package com.mikejuliet.identityprovider.controllers;


import com.fasterxml.jackson.databind.JsonNode;
import com.mikejuliet.identityprovider.entities.LoginResponse;
import com.mikejuliet.identityprovider.entities.Response;
import com.mikejuliet.identityprovider.services.AwsCognitoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/idp-auth/public")
public class IdpPublicController {

    @Autowired
    private AwsCognitoServices services;

    @PostMapping("/signup")
    public Response signupApi(@RequestBody JsonNode signupObject){
        String fullName = signupObject.get("fullName").asText();
        String username = signupObject.get("username").asText();
        String email = signupObject.get("email").asText();
        String password = signupObject.get("password").asText();
        String userType = signupObject.get("userType").asText();

        return services.signUp(fullName,username,password,email,userType);
    }

    @PostMapping("/confirm-signup")
    public Response confirmSignupApi(@RequestBody JsonNode confirmSignupObject){
        System.out.println("****************");
        String confirmCode = confirmSignupObject.get("confirmCode").asText();
        String username = confirmSignupObject.get("username").asText();

        return services.confirmSignup(username,confirmCode);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody JsonNode loginObject){
        String username = loginObject.get("username").asText();
        String password = loginObject.get("password").asText();

        return services.login(username,password);
    }
    @PostMapping("/resend-confirm-code")
    public Response resendConfirmCode(@RequestBody JsonNode resendConfirmCode){
        String username = resendConfirmCode.get("username").asText();
        return services.resendConfirmCode(username);
    }
}
