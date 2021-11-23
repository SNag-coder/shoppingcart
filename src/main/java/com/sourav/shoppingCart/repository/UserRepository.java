package com.sourav.shoppingCart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sourav.shoppingCart.model.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByMobile(String mobile);
}
