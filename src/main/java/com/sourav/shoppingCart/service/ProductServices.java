package com.sourav.shoppingCart.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.sourav.shoppingCart.repository.CategoryRepo;
import com.sourav.shoppingCart.repository.ProductRepo;
import com.sourav.shoppingCart.model.Category;
import com.sourav.shoppingCart.model.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServices {

	@Autowired
    ProductRepo productRepo;
	@Autowired
    CategoryRepo cateRepo;

	public void updateProductQty(long remainingQty,long productId){

		Optional<Products> product=productRepo.findById(productId);
		Products prod=product.get();
		prod.setQty(remainingQty);
	}
	public List<Products>getAllProducts(){
		return productRepo.findAll();
	}
	public List<Products>getProductsByCategory(long category_id){
		Optional<Category> category=cateRepo.findById(category_id);
		return productRepo.getByCategory(category.get());
	}

	
	public Products getProductsById(long productId) throws Exception {
		return productRepo.findById(productId).orElseThrow(() ->new Exception("Product is not found"));
	}
	public void setP(long category_id,Products product){
		Optional<Category> category=cateRepo.findById(category_id);
		product.setCategory(category.get());
		product.setAdded_on(new Date());
		 productRepo.save(product);
	}
}
