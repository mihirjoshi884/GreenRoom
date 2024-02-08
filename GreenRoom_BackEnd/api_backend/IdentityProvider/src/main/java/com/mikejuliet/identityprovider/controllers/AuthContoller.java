package com.mikejuliet.identityprovider.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthContoller {

    @GetMapping("/logout")
    public String logout() {
        // Invalidate user session or access token
        SecurityContextHolder.clearContext();

        // Redirect user to a suitable page
        return "redirect:/login";
    }
    @GetMapping("/login/oauth2/code/cognito")
    public void getAuthorizationCode(@RequestParam("code") String code){
        if(code!=null){
            System.out.println("code received "+code);
        }
        else{
            System.out.println("code not recieved");
        }
    }
}
