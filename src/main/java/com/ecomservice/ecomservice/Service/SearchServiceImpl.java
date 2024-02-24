package com.ecomservice.ecomservice.Service;

import com.ecomservice.ecomservice.dto.ProductResponsedto;
import com.ecomservice.ecomservice.mapper.ProductMapper;
import com.ecomservice.ecomservice.model.Product;
import com.ecomservice.ecomservice.model.SortParam;
import com.ecomservice.ecomservice.repository.ProductRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class SearchServiceImpl implements SearchService{
    private ProductRepository productRepository;

    public SearchServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductResponsedto> searchProducts(String query, int pageNumber, int pageSize, List<SortParam> sortParamList) {
//        Sort sort=Sort.by("title").ascending().and(
//                Sort.by("price").ascending().and(
//                        Sort.by("rating").descending()
//                )
//        );
        //create sort object

        Sort sort=null;
        if(sortParamList.get(0).getSortType().equals("ASC")) sort=Sort.by(sortParamList.get(0).getSortParamName()).ascending();
         else sort=Sort.by(sortParamList.get(0).getSortParamName()).descending();
         for (int i=1;i<sortParamList.size();i++){
             if(sortParamList.get(i).getSortType().equals("ASC")) sort=sort.and(Sort.by(sortParamList.get(i).getSortParamName()).ascending());
             else sort=sort.and(Sort.by(sortParamList.get(i).getSortParamName()).descending());
         }
        //pagerequest is the object of pageable
        PageRequest pageRequest=PageRequest.of(pageNumber,pageSize,sort);
        List<Product> productList=productRepository.findAllByTitleContainingIgnoreCase(query,pageRequest);
        List<ProductResponsedto> productResponsedtoList=new ArrayList<>();

        for (Product p:productList){
            productResponsedtoList.add(ProductMapper.convertProductToProductResponseDto(p));
        }

        return productResponsedtoList;
    }
}
