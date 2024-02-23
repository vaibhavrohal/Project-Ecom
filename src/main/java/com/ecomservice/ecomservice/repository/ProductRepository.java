package com.ecomservice.ecomservice.repository;

import com.ecomservice.ecomservice.model.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository

public interface ProductRepository extends JpaRepository<Product, UUID> {
    Optional<Product> findByTitle(String title);  //custom method
    List<Product> findAllByTitleContainingIgnoreCase(String title, Pageable pageable);
}
