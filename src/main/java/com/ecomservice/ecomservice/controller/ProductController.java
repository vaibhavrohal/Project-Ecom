package com.ecomservice.ecomservice.controller;

import com.ecomservice.ecomservice.Exception.InvalidTokenException;
import com.ecomservice.ecomservice.Exception.ProductNotFoundException;
import com.ecomservice.ecomservice.client.UserServiceClient;
import com.ecomservice.ecomservice.dto.*;
import com.ecomservice.ecomservice.Service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;
import java.util.UUID;

@RestController
public class ProductController {
    private final ProductService productService; //immutable so made it final.
    private final UserServiceClient userServiceClient;

    @Autowired  //this is optional now writing Autowired for counstructor
    public ProductController(@Qualifier("productService") ProductService productService,UserServiceClient userServiceClient) {
        this.productService = productService;
        this.userServiceClient=userServiceClient;
    }

    /*
        //field injection
        @Autowired
        @Qualifier("fakestoreproductservice")
       private ProductService productService;


         */
    @GetMapping("/products")
    public ResponseEntity getProducts(@RequestHeader("token")String token) throws Exception{
   /*
        ProductResponsedto p1=new ProductResponsedto();
        p1.setCategory("electronics");
        p1.setId(1);
        p1.setTitle("Ayansh");
        p1.setPrice(135000);
        p1.setImage("image uploaded");
        p1.setDescription("1st fold of one plus");

        ProductResponsedto p2=new ProductResponsedto();
        p2.setCategory("electronics");
        p2.setId(2);
        p2.setTitle("one plus 11");
        p2.setPrice(75000);
        p2.setImage("image uploaded");
        p2.setDescription("new phone from one plus");

        List<ProductResponsedto> response= Arrays.asList(p1,p2);
        return ResponseEntity.ok(response);


    */
        validateUser(token);
      List<ProductResponsedto> response=productService.getAllproducts();
        return ResponseEntity.ok(response);
    }
    /* this is hardcoaded id, we want to get result with particular id.
    @GetMapping("/products/1")
    public ResponseEntity getsingleproduct(){
        ProductResponsedto response =productService.getProductbyid(1);
        return ResponseEntity.ok(response);
    }

     */

    @GetMapping ("/products/{id}")
        public ResponseEntity getProductbyId (@PathVariable("id") UUID id,@RequestHeader("token")String token)throws ProductNotFoundException {
        ProductResponsedto productResponsedto=productService.getProductbyid(id);
        return ResponseEntity.ok(productResponsedto);
    }
    @GetMapping ("/products/title/{title}")
    public ResponseEntity getProductfromTitle (@PathVariable("title") String title,@RequestHeader("token") String token)throws ProductNotFoundException {
        ProductResponsedto productResponsedto=productService.getProductByTitle(title);
        return ResponseEntity.ok(productResponsedto);
    }
    @PostMapping ("/products")
    public ResponseEntity createProduct (@RequestBody ProductRequestdto productRequestdto,@RequestHeader("token") String token){
           ProductResponsedto responsedto=productService.createProduct(productRequestdto);
           //return ResponseEntity.ok().header("Custom header","vaibhav").body(responsedto);
        return ResponseEntity.ok(responsedto);
    }
    @DeleteMapping("/products/{id}")
    public ResponseEntity deleteProduct(@PathVariable ("id")int id,@RequestHeader("token") String token){
      String response=productService.deleteProduct(id);
       return ResponseEntity.ok(response);
    }
    private void validateUser(String token) throws  Exception{
        String [] chunks =token.split("\\.");
        Base64.Decoder decoder=Base64.getUrlDecoder();
        String payload=new String(decoder.decode(chunks[1]));
        ObjectMapper mapper=new ObjectMapper();
        JwtPayloadDto jwtPayload=mapper.readValue(payload,JwtPayloadDto.class);
        int userId= jwtPayload.getUserId();
        ValidateTokenDTO validateTokenDTO=new ValidateTokenDTO(userId,token);
        String result=userServiceClient.validateToken(validateTokenDTO);
        if(!result.contains(SessionStatus.ACTIVE.name())){
           throw new InvalidTokenException("Token is not valid");
        }
    }
}
