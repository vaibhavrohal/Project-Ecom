package com.ecomservice.ecomservice.client;

import com.ecomservice.ecomservice.dto.FakestoreRequestdto;
import com.ecomservice.ecomservice.dto.FakestoreResponsedto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Component
public class FakeStoreAPIClient {
    //for removing the url and shifting to the properties
   private String fakeStoreApiurl;
   //field injection..but prefer constructor injection. here doing both for example.
    @Value("${fakestore.api.path.product}")
   private String fakeStoreApiPathproduct;

    public FakeStoreAPIClient(@Value("${fakestore.api.url}") String fakeStoreApiurl) {
        this.fakeStoreApiurl=fakeStoreApiurl;
    }

    public FakestoreResponsedto creatProductbyID(FakestoreRequestdto fakestoreRequestdto) {
        String fakestoreurl = fakeStoreApiurl+fakeStoreApiPathproduct;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<FakestoreResponsedto> response = restTemplate.postForEntity(fakestoreurl, fakestoreRequestdto, FakestoreResponsedto.class);
        return response.getBody();
    }

    public FakestoreResponsedto getproductbyid(UUID id) {
        String fakestoreurl = fakeStoreApiurl+fakeStoreApiPathproduct + "/"+id;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<FakestoreResponsedto> response = restTemplate.getForEntity(fakestoreurl, FakestoreResponsedto.class);
        return response.getBody();
    }
    public List<FakestoreResponsedto> getAllproducts(){
        String fakestoreurl =fakeStoreApiurl+fakeStoreApiPathproduct;
        RestTemplate restTemplate=new RestTemplate();
        ResponseEntity<FakestoreResponsedto[]> response=restTemplate.getForEntity(fakestoreurl,FakestoreResponsedto[].class);
        // rest template returns object in array form.i.e array of fakestoreresponse object.
        //now we need to convert array to list.

        return List.of(response.getBody());
    }
    public String deleteproductbyId(int id){
        String fakestoreurl = fakeStoreApiurl+fakeStoreApiPathproduct+"/"+id;
        RestTemplate restTemplate=new RestTemplate();
        restTemplate.delete(fakestoreurl);
        return "succesfully deleted the product with id "+id;
    }
}