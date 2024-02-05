package com.ecomservice.ecomservice.Service;

import com.ecomservice.ecomservice.Exception.InvalidTitleException;
import com.ecomservice.ecomservice.Exception.ProductNotFoundException;
import com.ecomservice.ecomservice.dto.ProductRequestdto;
import com.ecomservice.ecomservice.dto.ProductResponsedto;
import com.ecomservice.ecomservice.mapper.ProductMapper;
import com.ecomservice.ecomservice.model.Product;
import com.ecomservice.ecomservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service("productService")
public class ProductServiceImpl implements ProductService{
    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponsedto getProductbyid(UUID id) throws ProductNotFoundException {
       Optional<Product> productOptional=  productRepository.findById(id);
       if (productOptional.isEmpty()){
           throw new ProductNotFoundException("Product not found with id "+id);
       }
          Product p=productOptional.get();
          ProductResponsedto productResponsedto= ProductMapper.convertProductToProductResponseDto(p);
        return productResponsedto;
    }

    @Override
    public List<ProductResponsedto> getAllproducts() {
       List<Product> productList=productRepository.findAll();
       List<ProductResponsedto> productResponsedtoList=new ArrayList<>();
       for (Product p:productList){
           productResponsedtoList.add(ProductMapper.convertProductToProductResponseDto(p));
       }
        return productResponsedtoList;
    }
    public ProductResponsedto getProductByTitle(String title) throws ProductNotFoundException {
        if(title==null || title.isEmpty()){
            throw new InvalidTitleException("Title is invalid for "+title);
        }

        Optional<Product> product =productRepository.findByTitle(title);
        if ( product==null){
            throw new ProductNotFoundException("Product not present with title "+title);
        }

        return ProductMapper.convertProductToProductResponseDto(product.get());

    }
    @Override
    public ProductResponsedto createProduct(ProductRequestdto productRequestdto) {
        return null;
    }

    @Override
    public String deleteProduct(int id) {
        return null;
    }
}
