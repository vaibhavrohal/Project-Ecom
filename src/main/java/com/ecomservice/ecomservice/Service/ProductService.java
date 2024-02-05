package com.ecomservice.ecomservice.Service;

import com.ecomservice.ecomservice.Exception.ProductNotFoundException;
import com.ecomservice.ecomservice.dto.ProductRequestdto;
import com.ecomservice.ecomservice.dto.ProductResponsedto;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    ProductResponsedto getProductbyid(UUID id) throws ProductNotFoundException;
    List<ProductResponsedto> getAllproducts();

    ProductResponsedto createProduct (ProductRequestdto productRequestdto);

    String deleteProduct(int id);

    ProductResponsedto getProductByTitle(String title) throws ProductNotFoundException;
}
