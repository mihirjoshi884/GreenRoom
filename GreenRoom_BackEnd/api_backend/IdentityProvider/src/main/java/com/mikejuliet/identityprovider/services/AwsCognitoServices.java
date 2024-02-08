package com.mikejuliet.identityprovider.services;

import com.amazonaws.services.cognitoidp.model.*;
import com.mikejuliet.identityprovider.configuration.CognitoClient;
import com.mikejuliet.identityprovider.entities.LoginResponse;
import com.mikejuliet.identityprovider.entities.Response;
import com.mikejuliet.identityprovider.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AttributeType;
import org.springframework.http.HttpStatus;
import software.amazon.awssdk.services.cognitoidentityprovider.model.CognitoIdentityProviderException;
import software.amazon.awssdk.services.cognitoidentityprovider.model.ConfirmSignUpRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.SignUpRequest;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class AwsCognitoServices {


    private CognitoClient client = new CognitoClient();

    private static Response response = new Response();

    public Response signUp(String fullName,String username,String password,String email,String userType){
        try{
            SignUpResult result = client.signUp(fullName,username,password,email,userType);
            if(result != null){
                response.setStatusCode(200);
                response.setBody("CONFIRMATION CODE IS SENT TO YOUR EMAIL");
            }else{
                response.setStatusCode(400);
                response.setBody("try again after some time");
            }
        }catch (Exception e){
            response.setStatusCode(500);
            response.setBody(e.getMessage());
        }
        return response;
    }

    public Response confirmSignup(String username, String confirmCode){
        try{
            ConfirmSignUpResult result = client.confirmSignUpResult(username,confirmCode);
            if(result != null){
                response.setStatusCode(200);
                response.setBody("user account registration is confirmed");
            }else{
                response.setStatusCode(400);
                response.setBody("something went wrong");
            }
        }catch (Exception e){
            response.setStatusCode(500);
            response.setBody(e.getMessage());
        }
        return response;
    }
    public LoginResponse login(String username, String password){
        LoginResponse loginResponse = new LoginResponse();
        try{
            Map<String,String> tokens = client.login(username,password);
            if(tokens != null){
                loginResponse.setStatusCode(200);
                loginResponse.setAccessToken(tokens.get("accessToken"));
                loginResponse.setIdToken(tokens.get("idToken"));
                loginResponse.setRefreshToken(tokens.get("refreshToken"));
                try{
                    User userResult = client.getUserDetails(username);
                    loginResponse.setUserGroup(
                            client.getUserGroup(username)
                            .getGroups()
                            .get(0).getGroupName());
                    loginResponse.setUser(userResult);
                }catch (Exception e){
                    loginResponse.setBody(e.getMessage());
                }
                loginResponse.setBody("user is authenticated");
            }else {
                loginResponse.setStatusCode(400);
                loginResponse.setBody("Please try again after sometime");
            }
        }catch (Exception e){
            loginResponse.setStatusCode(500);
            loginResponse.setBody(e.getMessage());
        }
        return loginResponse;
    }
    public Response resendConfirmCode(String username){
        try{
            ResendConfirmationCodeResult result = client.resendConfirmationCodeResult(username);
            if(result != null){
                response.setStatusCode(200);
                response.setBody(result.getCodeDeliveryDetails().toString());
            }else{
                response.setStatusCode(400);
                response.setBody("something went wrong");
            }
        }catch (Exception e){
            response.setStatusCode(500);
            response.setBody(e.getMessage());
        }
        return response;
    }
}
