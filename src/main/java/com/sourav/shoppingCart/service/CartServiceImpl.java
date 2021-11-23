package com.sourav.shoppingCart.service;

import java.util.List;
import java.util.Optional;

import com.sourav.shoppingCart.dto.AddToCartDto;
import com.sourav.shoppingCart.repository.AddToCartRepo;
import com.sourav.shoppingCart.repository.CheckoutRepo;
import com.sourav.shoppingCart.repository.ProductRepo;
import com.sourav.shoppingCart.repository.UserRepository;
import com.sourav.shoppingCart.model.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {
	@Autowired
	ProductRepo productRepo;

	@Autowired
	UserRepository userRepository;
	@Autowired
    AddToCartRepo addCartRepo;
	@Autowired
    CheckoutRepo checkOutRepo;
	@Autowired
	ProductServices proServices;
    private static final Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);

	@Override
	public void addCart(AddToCartDto add) throws Exception {

		try {
			Optional<User> user = userRepository.findById(add.getUserId());

			Optional<Products> product = productRepo.findById(add.getProductId());
			long quantity = product.get().getQty() - add.getQuantity();
			product.get().setQty(quantity);
			productRepo.save(product.get());
			AddtoCart cart = new AddtoCart();
			MyEntity cartKey= new MyEntity();
			cartKey.setPrdouctId(add.getProductId());
			cartKey.setUserId(add.getUserId());
			cart.setMyEntity(cartKey);

			cart.setProduct(product.get());
			cart.setUser(user.get());
			cart.setQty(add.getQuantity());

			addCartRepo.save(cart);


		}catch(Exception e) {
			e.printStackTrace();
			logger.error(""+e.getMessage());
			throw new Exception(e.getMessage());
		}
		
	}
	@Override
	public List<AddtoCart> getCartByUser(User user) {
		return addCartRepo.getCartByUser(user);
	}



	@Override
	public void removeCartByUser( User user) {
		Optional<List<AddtoCart>>addtoCart= Optional.ofNullable(addCartRepo.getCartByUser(user));
		List<AddtoCart> adddd=addtoCart.get();

		List<Products> products=proServices.getAllProducts();
		int i=0;
		while(i<adddd.size()&& i<products.size()){proServices.updateProductQty(adddd.get(i).getQty()+products.get(i).getQty(),products.get(i).getId());i++;}
		while(i<products.size()){proServices.updateProductQty(products.get(i).getQty(),products.get(i).getId());i++;}
		addCartRepo.deleteAllCartByUser(user);

	}

	@Override
	public void updateQtyByCartId(AddToCartDto add) throws Exception {

		Optional<User> user = userRepository.findById(add.getUserId());
		Optional<Products> product = productRepo.findById(add.getProductId());
		AddtoCart cart = addCartRepo.findByUserAndProduct(user, product);
		long quantity = product.get().getQty() + cart.getQty() - add.getQuantity();


		product.get().setQty(quantity);
		cart.setQty(add.getQuantity());
		addCartRepo.save(cart);

	}



	@Override
	public List<CheckoutCart> getAllCheckoutByUserId(long userId) {
		return checkOutRepo.getByuserId(userId);
	}

	@Override
	public List<CheckoutCart> saveProductsForCheckout(List<CheckoutCart> tmp) throws Exception {
		try {
			long user_id = tmp.get(0).getUser_id();
			Optional<User> user=userRepository.findById(user_id);
			if(tmp.size() >0) {
				checkOutRepo.saveAll(tmp);
				this.removeCartByUser(user.get());
				return this.getAllCheckoutByUserId(user_id);
			}	
			else {
				throw  new Exception("Should not be empty");
			}
		}catch(Exception e) {
			throw new Exception("Error while checkout "+e.getMessage());
		}
		
	}




}
