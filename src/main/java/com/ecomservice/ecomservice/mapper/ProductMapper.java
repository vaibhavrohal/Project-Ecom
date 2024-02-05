package com.ecomservice.ecomservice.mapper;

import com.ecomservice.ecomservice.dto.FakestoreRequestdto;
import com.ecomservice.ecomservice.dto.FakestoreResponsedto;
import com.ecomservice.ecomservice.dto.ProductRequestdto;
import com.ecomservice.ecomservice.dto.ProductResponsedto;
import com.ecomservice.ecomservice.model.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductMapper {
    public static FakestoreRequestdto convertProductRequestdtoinFakestoreRequestdto(ProductRequestdto productRequestdto){
        FakestoreRequestdto fakestoreRequestdto =new FakestoreRequestdto();
        fakestoreRequestdto.setCategory(productRequestdto.getCategory());
        fakestoreRequestdto.setTitle(productRequestdto.getTitle());
        fakestoreRequestdto.setPrice(productRequestdto.getPrice());
        fakestoreRequestdto.setImage(productRequestdto.getImage());
        fakestoreRequestdto.setDescription(productRequestdto.getDescription());
        return fakestoreRequestdto;
    }

    public static ProductResponsedto convertFakestoreResponsedtoinProductResponedto (FakestoreResponsedto fakestoreResponsedto){
        ProductResponsedto productResponsedto=new ProductResponsedto();
        productResponsedto.setDescription(fakestoreResponsedto.getDescription());
        productResponsedto.setCategory(fakestoreResponsedto.getCategory());
        productResponsedto.setTitle(fakestoreResponsedto.getTitle());
        productResponsedto.setPrice(fakestoreResponsedto.getPrice());
        productResponsedto.setImage(fakestoreResponsedto.getImage());
        productResponsedto.setId(fakestoreResponsedto.getId());
        return productResponsedto;
    }

    public static ProductResponsedto convertProductToProductResponseDto(Product product){
        ProductResponsedto productResponsedto=new ProductResponsedto();
        productResponsedto.setImage(product.getImage());
        productResponsedto.setId(product.getId());
        productResponsedto.setTitle(product.getTitle());
        productResponsedto.setCategory(product.getCategory().getCategoryName());
        productResponsedto.setPrice(product.getPrice().getAmount());
        productResponsedto.setDescription(product.getDescription());
        return productResponsedto;
    }
}
