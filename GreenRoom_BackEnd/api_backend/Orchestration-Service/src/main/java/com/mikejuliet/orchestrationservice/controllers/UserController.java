package com.mikejuliet.orchestrationservice.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/greenroom/public-api")
public class UserController {


    @PostMapping("/register")
    public HttpStatus registerUser(@RequestBody Map<String,Object> userData){
        System.out.println(userData);
        return HttpStatus.OK;
    }

}