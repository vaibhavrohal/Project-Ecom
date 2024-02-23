package com.ecomservice.ecomservice.controller;

import com.ecomservice.ecomservice.Service.SearchService;
import com.ecomservice.ecomservice.dto.ProductResponsedto;
import com.ecomservice.ecomservice.dto.SearchRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {
     SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }
  @PostMapping
//    public List<ProductResponsedto> searchProducts(@RequestBody SearchRequestDto requestDto){
//       List<ProductResponsedto> productResponsedtoList= searchService.searchProducts(requestDto.getTitle(), requestDto.getPageNumber(), requestDto.getPageSize());
//        return productResponsedtoList;
//    }
// we shall convert it to page object. which gives us lot of additional information, incomparision to List.

  public Page<ProductResponsedto> searchProducts(@RequestBody SearchRequestDto requestDto){
     List<ProductResponsedto> productResponsedtoList= searchService.searchProducts(requestDto.getTitle(), requestDto.getPageNumber(), requestDto.getPageSize(),requestDto.getSortParamList());

     Page<ProductResponsedto> productResponsedtoPage=new PageImpl<>(productResponsedtoList);
     return productResponsedtoPage;
   }

}
