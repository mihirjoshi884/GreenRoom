package com.mikejuliet.identityprovider.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.oauth2.core.oidc.user.OidcUserAuthority;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final CustomizeAuthenticationSuccessHandler customizeAuthenticationSuccessHandler;
    public SecurityConfiguration(CustomizeAuthenticationSuccessHandler customizeAuthenticationSuccessHandler){
        this.customizeAuthenticationSuccessHandler= new CustomizeAuthenticationSuccessHandler();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable())
                .authorizeHttpRequests(requests->
                        requests.requestMatchers("/idp-auth/public/**").permitAll()
                                .requestMatchers("/idp-auth/private/**").authenticated())
                .oauth2Login(oauth->
                        oauth.redirectionEndpoint(endpoint-> endpoint.baseUri("/login/oauth2/code/cognito"))
                                .userInfoEndpoint(userInfoEndpointConfig -> userInfoEndpointConfig.userAuthoritiesMapper(userAuthoritiesMapper()))
                                .successHandler(customizeAuthenticationSuccessHandler)
                )
                .logout(logout -> logout.logoutSuccessUrl("/logout"));
        return httpSecurity.build();
    }


    @Bean
    public GrantedAuthoritiesMapper userAuthoritiesMapper() {
        return (authorities) -> {
            Set<GrantedAuthority> mappedAuthorities = new HashSet<>();

            try {
                OidcUserAuthority oidcUserAuthority = (OidcUserAuthority) new ArrayList<>(authorities).get(0);

                mappedAuthorities = ((ArrayList<?>) oidcUserAuthority.getAttributes().get("cognito:groups")).stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role)).collect(Collectors.toSet());
            } catch (Exception exception) {
                System.out.println("Not Authorized!");

                System.out.println(exception.getMessage());
            }

            return mappedAuthorities;
        };
    }
}
