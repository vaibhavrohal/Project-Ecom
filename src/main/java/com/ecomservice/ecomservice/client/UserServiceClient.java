package com.ecomservice.ecomservice.client;

import com.ecomservice.ecomservice.dto.FakestoreRequestdto;
import com.ecomservice.ecomservice.dto.FakestoreResponsedto;
import com.ecomservice.ecomservice.dto.ValidateTokenDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;
// made a client for userservice. to talk to user service from product service
// to validate the tokens and other requests.
@Component
public class UserServiceClient {
    private RestTemplateBuilder restTemplateBuilder;
    //for removing the url and shifting to the properties
   private String userServiceApiUrl;
   //field injection..but prefer constructor injection. here doing both for example.
   // @Value("${fakestore.api.path.product}")
    @Value("${userservice.api.path.validate}")
   private String userServiceValidatePath;



    public UserServiceClient(RestTemplateBuilder restTemplateBuilder, @Value("${userservice.api.url}") String userServiceApiUrl) {
         this.restTemplateBuilder=restTemplateBuilder;
         this.userServiceApiUrl=userServiceApiUrl;
    }

    public String validateToken(ValidateTokenDTO validateTokenDTO) {
        String validateTokenURL = userServiceApiUrl+userServiceValidatePath;

        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<String> response = restTemplate.postForEntity(validateTokenURL,validateTokenDTO, String.class);
        return response.getBody();
    }

}