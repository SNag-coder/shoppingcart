package com.sourav.shoppingCart.repository;


import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.sourav.shoppingCart.model.Products;
import com.sourav.shoppingCart.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.sourav.shoppingCart.model.AddtoCart;
@Repository
public interface AddToCartRepo extends JpaRepository<AddtoCart,Long> {

	

	@Modifying
    @Transactional
	void deleteAllCartByUser(User user);
	List<AddtoCart> getCartByUser(User user);
	AddtoCart findByUserAndProduct(Optional<User> user, Optional<Products> product);
}
