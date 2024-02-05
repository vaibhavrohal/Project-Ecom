package com.ecomservice.ecomservice.repository;

import com.ecomservice.ecomservice.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
