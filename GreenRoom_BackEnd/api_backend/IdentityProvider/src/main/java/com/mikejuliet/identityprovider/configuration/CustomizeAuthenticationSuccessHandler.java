package com.mikejuliet.identityprovider.configuration;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class CustomizeAuthenticationSuccessHandler implements AuthenticationSuccessHandler {


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        for(GrantedAuthority authority : authentication.getAuthorities()){
            DefaultOidcUser defaultOidcUser = (DefaultOidcUser) authentication.getPrincipal();
            Map<String, Object> userAttributes = defaultOidcUser.getAttributes();
            System.out.println(userAttributes);

        }

    }
}
