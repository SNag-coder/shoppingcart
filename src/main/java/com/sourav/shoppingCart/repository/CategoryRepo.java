package com.sourav.shoppingCart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sourav.shoppingCart.model.Category;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo  extends JpaRepository<Category, Long> {

}
