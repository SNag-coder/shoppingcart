package com.sourav.shoppingCart.service;

import java.util.HashMap;
import java.util.Optional;

import com.sourav.shoppingCart.repository.UserRepository;
import com.sourav.shoppingCart.model.User;
import com.sourav.shoppingCart.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
    UserRepository userRepo;
	
	@Override
	public User findByMobile(String mobile) throws Exception {
		return userRepo.findByMobile(mobile).orElseThrow(()->new Exception("User Not found.."));
	}
	@Override
    public User getss(long usee){
	Optional<User> user;
	user = userRepo.findById(usee);
	User us =user.get();
	return us;}
	@Override
	public User getUserDetailById(long userId) throws Exception {
		
		return userRepo.findById(userId).orElseThrow(()->new Exception("User Not found.."));
	}

	@Override
	public User signUpUser(HashMap<String, String> signupRequest) throws Exception {
		try {
			if(userRepo.findByMobile(signupRequest.get("mobile")).isPresent()) {
				throw new Exception("User is already registered with Mobile No.");
			}
			User user = new User();
			user.setName(signupRequest.get("name"));
			user.setEmail(signupRequest.get("email"));
			user.setMobile(signupRequest.get("mobile"));
			user.setPassword(signupRequest.get("password"));
			userRepo.save(user);
			return user;
		}catch(Exception e) {
			throw new Exception(e.getMessage());
		}
		
	}
}
