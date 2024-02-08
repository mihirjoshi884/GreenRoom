package com.mikejuliet.identityprovider.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.Map;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Response {

    private int statusCode;
    private String body;
    private Map<String,String> headers;
}
