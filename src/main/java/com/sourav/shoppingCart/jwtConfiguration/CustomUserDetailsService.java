package com.sourav.shoppingCart.jwtConfiguration;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sourav.shoppingCart.model.User;
import com.sourav.shoppingCart.service.*;


@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String mobile)
            throws UsernameNotFoundException {
    	try {
            User user = userService.findByMobile(mobile);   
            return UserPrincipal.create(user);
    	}catch(Exception e) {
	   		throw new UsernameNotFoundException("User not found with Mobile : " + mobile);
	   	}
    }


    @Transactional
    public UserDetails loadUserById(Long id) {
    	try {
   		 User user = userService.getUserDetailById(id);
   	        return UserPrincipal.create(user);
	   	}catch(Exception e) {
	   		throw new UsernameNotFoundException("User not found with id : " + id);
	   	}   

    }
   


}
