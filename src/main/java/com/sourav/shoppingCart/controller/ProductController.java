package com.sourav.shoppingCart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sourav.shoppingCart.model.Products;
import com.sourav.shoppingCart.service.*;

@RestController
@RequestMapping("product")
public class ProductController {
	@Autowired
	ProductServices ProductServices;
	
	@GetMapping("getAll")
	public List<Products> getAllPRoducts(){
		return ProductServices.getAllProducts();
	}
	@GetMapping("getProductsByCategory")
	public List<Products> getProductsByCategory(@RequestParam long category_id){

		return ProductServices.getProductsByCategory(category_id);
	}
	@PostMapping("addProductToInventory")
	public void addProduct(@RequestParam long category_id,@RequestBody Products product){
		ProductServices.setP(category_id,product);
	}
}
