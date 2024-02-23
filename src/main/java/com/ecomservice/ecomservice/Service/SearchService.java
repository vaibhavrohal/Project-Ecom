package com.ecomservice.ecomservice.Service;

import com.ecomservice.ecomservice.dto.ProductResponsedto;
import com.ecomservice.ecomservice.model.SortParam;

import java.util.List;

public interface SearchService {
    List<ProductResponsedto> searchProducts(String query, int pageNumber, int pageSize, List<SortParam> sortParamList);


}
