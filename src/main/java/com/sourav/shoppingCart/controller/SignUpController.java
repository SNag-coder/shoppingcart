package com.sourav.shoppingCart.controller;

import java.util.HashMap;

import com.sourav.shoppingCart.controller.RequestPojo.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sourav.shoppingCart.model.User;
import com.sourav.shoppingCart.service.*;

@RestController
@RequestMapping("api/signup")
public class SignUpController {
	@Qualifier("userServiceImpl")
	@Autowired
	  UserService userservice;
	@RequestMapping("user")
	public ResponseEntity<?> userLogin(@RequestBody HashMap<String,String> signupRequest) {
		try {

			User user = userservice.signUpUser(signupRequest);
			return  new ResponseEntity("user signed up", HttpStatus.ACCEPTED);
		}catch(Exception e ) {
			return new ResponseEntity((new ApiResponse(e.getMessage())).getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
}
