package com.sourav.shoppingCart.repository;

import java.util.List;

import com.sourav.shoppingCart.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sourav.shoppingCart.model.Products;
@Repository
public interface ProductRepo extends JpaRepository<Products, Long> {
	@Query("Select pro FROM Products pro WHERE pro.category=:cat_id")
	List<Products> getByCategory(@Param("cat_id") Category cat_id);

}
