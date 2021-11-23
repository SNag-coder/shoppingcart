package com.sourav.shoppingCart.controller;

import java.util.List;
import java.util.Optional;

import com.sourav.shoppingCart.dto.AddToCartDto;
import com.sourav.shoppingCart.repository.AddToCartRepo;
import com.sourav.shoppingCart.repository.ProductRepo;
import com.sourav.shoppingCart.repository.UserRepository;
import com.sourav.shoppingCart.controller.RequestPojo.ApiResponse;
import com.sourav.shoppingCart.model.User;
import com.sourav.shoppingCart.service.ProductServices;
import com.sourav.shoppingCart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sourav.shoppingCart.model.AddtoCart;


@RestController
@RequestMapping("addToCart")
public class AddtoCartController {
	@Autowired
	ProductServices productServices;
	@Autowired
	AddToCartRepo addToCartRepo;
	@Autowired
	UserRepository userRepository;
	@Autowired
	ProductRepo productRepo;
	@Autowired
	CartService cartService;
	@PostMapping("addProductToCart")
  	public ResponseEntity<?>addCartwithProduct(@RequestBody AddToCartDto addCartRequest) {
		try {





			cartService.addCart(addCartRequest);
			return new ResponseEntity("product added",HttpStatus.ACCEPTED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity((new ApiResponse(e.getMessage())).getMessage(),HttpStatus.BAD_REQUEST);
		}
		
   }
	
	@PutMapping("updateQtyForCart")
  	public ResponseEntity<?> updateQtyForCart(@RequestBody AddToCartDto addCartRequest) {
		try {



			 cartService.updateQtyByCartId(addCartRequest);/*
			 List<AddtoCart> obj = cartService.getCartByUserId(userId);*/
			return new ResponseEntity("cart updated",HttpStatus.ACCEPTED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity((new ApiResponse(e.getMessage())).getMessage(),HttpStatus.BAD_REQUEST);
		}
		
   }
	
	
	@DeleteMapping("removeAllProductsFromCart")
  	public ResponseEntity<?> removeCartwithProductIdAndUserId(@RequestParam long userId) {
		try {
Optional<User> user=userRepository.findById(userId);


			cartService.removeCartByUser(user.get());
			return new ResponseEntity<>("Item removed from cart",HttpStatus.ACCEPTED);
		}catch(Exception e) {
				return new ResponseEntity((new ApiResponse(e.getMessage())).getMessage(),HttpStatus.BAD_REQUEST);
		}		
   }


	@GetMapping("getCartsByUserId")
	public ResponseEntity<List<AddtoCart>> getCartsByUser(@RequestParam long userId) {
		try {
			Optional<User> user=userRepository.findById(userId);
			return ResponseEntity.ok(cartService.getCartByUser(user.get()));
		}catch(Exception e) {
			return new ResponseEntity(new ApiResponse(e.getMessage()),HttpStatus.ACCEPTED);
		}
	}


}
