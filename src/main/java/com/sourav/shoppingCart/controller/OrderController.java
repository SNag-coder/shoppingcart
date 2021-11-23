package com.sourav.shoppingCart.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.sourav.shoppingCart.service.*;

import com.sourav.shoppingCart.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sourav.shoppingCart.controller.RequestPojo.ApiResponse;
import com.sourav.shoppingCart.model.AddtoCart;
import com.sourav.shoppingCart.model.CheckoutCart;

@RestController
@RequestMapping("order")
public class OrderController {
	@Autowired
	UserService userService;
	@Autowired
	CartService cartService;
	@Autowired
	ProductServices proService;
	@PostMapping("checkout_order")
  	public ResponseEntity<?> checkout_order(@RequestBody HashMap<String,String> addCartRequest) {
		try {


			long user_Id = Long.parseLong(addCartRequest.get("userId"));
			User user=userService.getUserDetailById(user_Id);
				List<AddtoCart> cartItems = cartService.getCartByUser(user);
				List<CheckoutCart> tmp = new ArrayList<CheckoutCart>();
				for(AddtoCart addCart : cartItems) {
					CheckoutCart cart = new CheckoutCart();
					cart.setUser_id(addCart.getMyEntity().getUserId());
					cart.setProduct(proService.getProductsById(addCart.getMyEntity().getPrdouctId()));
					cart.setQty((int) addCart.getQty());
					tmp.add(cart);
				}
				cartService.saveProductsForCheckout(tmp);
				return new ResponseEntity("order Placed",HttpStatus.OK);

		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity((new ApiResponse(e.getMessage())).getMessage(),HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("getOrdersByUserId")
		public ResponseEntity<?> getOrdersByUserId(@RequestParam long userId) {
		try {
			List<CheckoutCart> check=cartService.getAllCheckoutByUserId(userId);
			return new ResponseEntity(check, HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity((new ApiResponse(e.getMessage())).getMessage(),HttpStatus.BAD_REQUEST);
		}
		
	}
}
