package com.sourav.shoppingCart.service;


import java.util.List;

import com.sourav.shoppingCart.dto.AddToCartDto;
import com.sourav.shoppingCart.model.AddtoCart;
import com.sourav.shoppingCart.model.CheckoutCart;
import com.sourav.shoppingCart.model.User;


public interface CartService {
	void addCart(AddToCartDto add) throws Exception;
	void updateQtyByCartId(AddToCartDto add) throws Exception;
	List<AddtoCart> getCartByUser(User user);

	void removeCartByUser(User user);


	List<CheckoutCart> getAllCheckoutByUserId(long userId);
	List<CheckoutCart> saveProductsForCheckout(List<CheckoutCart> tmp)  throws Exception;


	//CheckOutCart
}
