package com.mikejuliet.userservice.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {


//    @PreAuthorize("hasRole('ROLE_ROLE_ADMIN')")
    @GetMapping ("/test-admin")
    public String getTestAdminDetails(){
        return "this is a testing string for admin";
    }
//    @PreAuthorize("hasRole('ROLE_ROLE_DEVELOPER')")
    @GetMapping("/test-dev")
    public String getTestDevDetails(){
        return "this is a testing string for dev";
    }
//    @PreAuthorize("hasRole('ROLE_ROLE_CLIENT')")
    @GetMapping("/test-client")
    public String getTestClientDetails(){
        return "this is a testing string for client";
    }
}
