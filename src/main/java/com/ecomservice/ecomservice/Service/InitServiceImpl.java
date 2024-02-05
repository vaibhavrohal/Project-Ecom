package com.ecomservice.ecomservice.Service;

import com.ecomservice.ecomservice.model.Category;
import com.ecomservice.ecomservice.model.Order;
import com.ecomservice.ecomservice.model.Price;
import com.ecomservice.ecomservice.model.Product;
import com.ecomservice.ecomservice.repository.CategoryRepository;
import com.ecomservice.ecomservice.repository.OrderRepository;
import com.ecomservice.ecomservice.repository.PriceRepository;
import com.ecomservice.ecomservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InitServiceImpl implements InitService{
   private CategoryRepository categoryRepository;
   private PriceRepository priceRepository;
   private ProductRepository productRepository;
   private OrderRepository orderRepository;

    public InitServiceImpl(CategoryRepository categoryRepository, PriceRepository priceRepository, ProductRepository productRepository, OrderRepository orderRepository) {
        this.categoryRepository = categoryRepository;
        this.priceRepository = priceRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public void initialise() {
        Category c=new Category();
        c.setCategoryName("Electronics");
        categoryRepository.save(c);

      Price priceoneplusopen=new Price();
      priceoneplusopen.setAmount(140000);
      priceoneplusopen.setDiscount(0);
      priceoneplusopen.setCurrency("Rs");
      priceRepository.save(priceoneplusopen);

        Price priceOnePlusWatch=new Price();
        priceOnePlusWatch.setAmount(14000);
        priceOnePlusWatch.setDiscount(0);
        priceOnePlusWatch.setCurrency("Rs");
        priceRepository.save(priceOnePlusWatch);


     Product p=new Product();
     p.setTitle("OnePlusOpen");
     p.setDescription("oneplus 1st foldable phone");
     p.setImage("dadsdsf");
     p.setPrice(priceoneplusopen);
     p.setCategory(c);
     productRepository.save(p);

        Product p2=new Product();
        p2.setTitle("OnePlusWatch");
        p2.setDescription("oneplus's most valuable watch");
        p2.setImage("dadsdsfssd");
        p2.setPrice(priceOnePlusWatch);
        p2.setCategory(c);
        p2=productRepository.save(p2);

       Order order=new Order();
      List<Product> orderlist=new ArrayList<>();
      orderlist.add(p);
      orderlist.add(p2);
      order.setProductsList(orderlist);
        orderRepository.save(order);

    }
}
