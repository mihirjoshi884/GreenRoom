package com.mikejuliet.identityprovider.configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import com.amazonaws.services.cognitoidp.model.*;
import com.mikejuliet.identityprovider.entities.User;
import com.nimbusds.oauth2.sdk.AuthorizationCodeGrant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.cognitoidentityprovider.model.CognitoIdentityProviderException;
import software.amazon.awssdk.services.cognitoidentityprovider.model.ResendConfirmationCodeResponse;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.beans.ConstructorProperties;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class CognitoClient {

    private final AWSCognitoIdentityProvider client = createCognitoClient();

    private final String clientId = "5sj8uv1hedfht2dvfgdlmt12mp";
    private final String userPool = "us-east-1_rrtfXfje7";
    private final String clientSecret = "1d0tj39r69en7cik48ul7sbptpj4ct7bbn2qhdjq29aluk01f08b";

    private final String accessKey = "AKIA3AGNT34OFSSTT74U";
    private final String secretAccessKey = "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";


    private final  String ADMIN_GROUP = "ROLE_ADMIN";
    private final  String DEVELOPER_GROUP = "ROLE_DEVELOPER";
    private final  String CLIENT_GROUP = "ROLE_CLIENTS";


    public AWSCognitoIdentityProvider createCognitoClient(){
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey,this.secretAccessKey);
        AWSCredentialsProvider credentialsProvider = new AWSStaticCredentialsProvider(credentials);
        return AWSCognitoIdentityProviderClientBuilder.standard()
                .withCredentials(credentialsProvider)
                .withRegion(Regions.US_EAST_1)
                .build();
    }
    public SignUpResult  signUp(String fullName,
                                String username,
                                String password,
                                String email,
                                String userType){

        SignUpRequest request = new SignUpRequest()
                .withSecretHash(generateSecretHash(clientId,clientSecret,username))
                .withUserAttributes(new AttributeType().withName("email").withValue(email)
//                        new AttributeType().withName("userType").withValue(userType)
//                        new AttributeType().withName("fullName").withValue(fullName)
                        )
                .withClientId(clientId).withUsername(username).withPassword(password);

        SignUpResult result = client.signUp(request);
        addUserToGroup(username,userType);
        return result;
    }

    public ConfirmSignUpResult confirmSignUpResult(String username,
                                                   String confirmCode){

        ConfirmSignUpRequest request = new ConfirmSignUpRequest()
                .withSecretHash(generateSecretHash(clientId,clientSecret,username))
                .withClientId(clientId).withUsername(username).withConfirmationCode(confirmCode);
        ConfirmSignUpResult result = client.confirmSignUp(request);
        return result;
    }

    public ResendConfirmationCodeResult resendConfirmationCodeResult(String username){
        ResendConfirmationCodeRequest request = new ResendConfirmationCodeRequest()
                .withClientId(clientId)
                .withUsername(username)
                .withSecretHash(generateSecretHash(clientId,clientSecret,username));
        ResendConfirmationCodeResult result = client.resendConfirmationCode(request);
        return result;
    }


    public Map<String, String> login(String username, String password) {
        try {
            Map<String, String> authParameter = new LinkedHashMap<>() {{
                put("USERNAME", username);
                put("PASSWORD", password);
                put("SECRET_HASH", generateSecretHash(clientId, clientSecret, username));
                put("SCOPE", "openid profile email com.greenroom.userservice/userservice.READ com.greenroom.userservice/userservice.WRITE");
            }};

            AdminInitiateAuthRequest authRequest = new AdminInitiateAuthRequest()
                    .withAuthFlow(AuthFlowType.ADMIN_USER_PASSWORD_AUTH)
                    .withUserPoolId(userPool)
                    .withClientId(this.clientId)
                    .withAuthParameters(authParameter);

            AdminInitiateAuthResult authResult = client.adminInitiateAuth(authRequest);
            AuthenticationResultType resultType = authResult.getAuthenticationResult();


            return new LinkedHashMap<String, String>() {{
                put("idToken", resultType.getIdToken());
                put("accessToken", resultType.getAccessToken());
                put("refreshToken", resultType.getRefreshToken());
            }};
        } catch (CognitoIdentityProviderException e) {
            System.out.println("Cognito Error: " + e.awsErrorDetails().errorMessage());
            throw e; // rethrow the exception if needed
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
            throw e; // rethrow the exception if needed
        }
    }

    protected   AdminAddUserToGroupResult addUserToGroup(String username, String userType){
        AdminAddUserToGroupRequest request = new AdminAddUserToGroupRequest();
        if(userType.equals("developer")){
            request.withUserPoolId(this.userPool)
                    .withUsername(username)
                    .withGroupName(this.DEVELOPER_GROUP);
        }
        else{
            request.withUserPoolId(this.userPool)
                    .withUsername(username)
                    .withGroupName(this.CLIENT_GROUP);
        }

        AdminAddUserToGroupResult result = client.adminAddUserToGroup(request);
        return result;
    }
    public User getUserDetails(String username){
        AdminGetUserRequest request = new AdminGetUserRequest()
                .withUsername(username)
                .withUserPoolId(userPool);
        AdminGetUserResult result = client.adminGetUser(request);

        User resultantUser = new User();
                resultantUser.setCognitoUserId(result.getUserAttributes().get(0).getValue());
                resultantUser.setUsername(result.getUsername());
                resultantUser.setEmail(result.getUserAttributes().get(2).getValue());
        return resultantUser;
    }
    public AdminListGroupsForUserResult getUserGroup(String username){
        AdminListGroupsForUserRequest request = new AdminListGroupsForUserRequest()
                .withUsername(username)
                .withUserPoolId(userPool);
        AdminListGroupsForUserResult results = client.adminListGroupsForUser(request);
        return results;
    }
    protected static String generateSecretHash(String userPoolClientId, String userPoolClientSecret, String userName) {
        final String HMAC_SHA256_ALGORITHM = "HmacSHA256";

        SecretKeySpec signingKey = new SecretKeySpec(
                userPoolClientSecret.getBytes(StandardCharsets.UTF_8),
                HMAC_SHA256_ALGORITHM);
        try {
            Mac mac = Mac.getInstance(HMAC_SHA256_ALGORITHM);
            mac.init(signingKey);
            mac.update(userName.getBytes(StandardCharsets.UTF_8));
            byte[] rawHmac = mac.doFinal(userPoolClientId.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(rawHmac);
        } catch (Exception e) {
            throw new RuntimeException("Error while calculating ");
        }
    }
}
