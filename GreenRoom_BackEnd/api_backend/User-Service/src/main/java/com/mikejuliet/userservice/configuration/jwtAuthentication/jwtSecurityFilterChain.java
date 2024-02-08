package com.mikejuliet.userservice.configuration.jwtAuthentication;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.oauth2.jose.jws.SignatureAlgorithm.ES512;
import static org.springframework.security.oauth2.jose.jws.SignatureAlgorithm.RS512;

@Configuration
@EnableWebSecurity
public class jwtSecurityFilterChain {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {

        security.csrf(csrf-> csrf.disable())
                .authorizeHttpRequests(
                        request-> request.requestMatchers("/api/client").hasAnyAuthority("ROLE_CLIENT","ROLE_ADMIN")
                                .requestMatchers("/api/dev/**").hasAnyAuthority("Role_DEVELOPER","ROLE_ADMIN")
                                .anyRequest().authenticated()

                )
                .oauth2ResourceServer( oauth2-> oauth2
                        .jwt(jwt->
                                jwt.jwtAuthenticationConverter(customJwtAuthenticationConverter())
                        )
                );
        return security.build();
    }


    private JwtAuthenticationConverter customJwtAuthenticationConverter(){
        JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        grantedAuthoritiesConverter.setAuthoritiesClaimName("authorities");
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
        return jwtAuthenticationConverter;
    }


}
