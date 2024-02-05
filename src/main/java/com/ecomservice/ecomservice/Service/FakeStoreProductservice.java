package com.ecomservice.ecomservice.Service;

import com.ecomservice.ecomservice.Exception.ProductNotFoundException;
import com.ecomservice.ecomservice.client.FakeStoreAPIClient;
import com.ecomservice.ecomservice.dto.FakestoreRequestdto;
import com.ecomservice.ecomservice.dto.FakestoreResponsedto;
import com.ecomservice.ecomservice.dto.ProductRequestdto;
import com.ecomservice.ecomservice.dto.ProductResponsedto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.ecomservice.ecomservice.mapper.ProductMapper.*;
import static com.ecomservice.ecomservice.utils.ProductUtils.*;

@Service("fakestoreproductservice")
public class FakeStoreProductservice implements ProductService{
    private RestTemplateBuilder restTemplateBuilder;
    private FakeStoreAPIClient fakeStoreAPIClient;
   // private ProductMapper productMapper;
    @Autowired

    public FakeStoreProductservice(RestTemplateBuilder restTemplateBuilder, FakeStoreAPIClient fakeStoreAPIClient) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.fakeStoreAPIClient = fakeStoreAPIClient;
       // this.productMapper = productMapper;
    }

   // we can make methods in product mapper class as static  and call methods directly.


    @Override
    public ProductResponsedto getProductbyid(UUID id) throws ProductNotFoundException {
       // String allproductsurl="https://fakestoreapi.com/products/"+id;
        //RestTemplate resttemplate=restTemplateBuilder.build();
        //RestTemplate resttemplate=new RestTemplate();
        //ResponseEntity<ProductResponsedto> productresponse=resttemplate.getForEntity(allproductsurl, ProductResponsedto.class);
         FakestoreResponsedto fakestoreResponsedto=fakeStoreAPIClient.getproductbyid(id);
         //if get productby id method returns null. i.e thereisno product available.error coming is internal server error. which i snot correct.
        //so we check..

        if (isNull(fakestoreResponsedto)) {
            throw new ProductNotFoundException("product not found with id "+id);
        }
         ProductResponsedto productResponsedto=convertFakestoreResponsedtoinProductResponedto(fakestoreResponsedto);
        return productResponsedto;
    }

    @Override
    public List<ProductResponsedto> getAllproducts() {
        //String allproducturl="https://fakestoreapi.com/products";
       // RestTemplate restTemplate=new RestTemplate();
       // ResponseEntity <List> response=restTemplate.getForEntity(allproducturl,List.class);//List.class is used to tell return type class.as eraser erase the type of class at runtime.
        //List<ProductResponsedto> products=response.getBody();
         List<FakestoreResponsedto> fakestoreResponsedtoList=fakeStoreAPIClient.getAllproducts();
        List <ProductResponsedto> productResponsedtoList=new ArrayList<>();

        for (FakestoreResponsedto a:fakestoreResponsedtoList){
            productResponsedtoList.add(convertFakestoreResponsedtoinProductResponedto(a));
        }
         return productResponsedtoList  ;
    }

    @Override
    public ProductResponsedto getProductByTitle(String title) throws ProductNotFoundException {
        return null;
    }

    @Override
    public ProductResponsedto createProduct(ProductRequestdto productRequestdto) {
        //String url="https://fakestoreapi.com/products";
       // RestTemplate restTemplate=new RestTemplate();
        //ResponseEntity <ProductResponsedto> response=restTemplate.postForEntity(url,productRequestdto,ProductResponsedto.class);
        // we can even remove product mapper class, can call method directly.by importing static methods.

        FakestoreRequestdto fakestoreRequestdto=convertProductRequestdtoinFakestoreRequestdto(productRequestdto);
        FakestoreResponsedto fakestoreResponsedto=fakeStoreAPIClient.creatProductbyID(fakestoreRequestdto);

        ProductResponsedto productResponsedto=convertFakestoreResponsedtoinProductResponedto(fakestoreResponsedto);
        //we can not pass the productRequestdto here, for this we need a mapper.
        //mappres are classes responsible for conversion of classes.
        return productResponsedto;
    }

    @Override
    public String deleteProduct(int id) {
       // String url="https://fakestoreapi.com/products/"+id;
       // RestTemplate restTemplate=new RestTemplate();
        //restTemplate.delete(url); //delete method is void in resttemplate
        return fakeStoreAPIClient.deleteproductbyId(id);

    }
}
