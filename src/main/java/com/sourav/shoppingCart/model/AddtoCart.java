package com.sourav.shoppingCart.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;


@Entity
@Table(name = "add_to_cart")
public class AddtoCart {/*
	@Id
	privte id
	@OneToOne(fetch=FetchType.LAZY)
    @JoinColumn (name = "product_id")
	Products product;
	//Long product_id;

*/
	@JsonIgnore
	@MapsId("userId")
	@ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
	@JoinColumn(nullable = false, name = "user_id")
	private User user;

	@JsonIgnore
	@ManyToOne
	@MapsId("productId")
	@JoinColumn(name="product_id")
	private Products product;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Products getProduct() {
		return product;
	}

	public void setProduct(Products product) {
		this.product = product;
	}

	@EmbeddedId
	private MyEntity myEntity;

	public MyEntity getMyEntity() {
		return myEntity;
	}

	public void setMyEntity(MyEntity myEntity) {
		this.myEntity = myEntity;
	}

	private long qty;
	private double price;



	@Column(insertable = false)

	Date added_date;
	


	public Date getAdded_date() {
		return added_date;
	}

	public void setAdded_date(Date added_date) {
		this.added_date = added_date;
	}

	public long getQty() {
		return qty;
	}
	public void setQty(long qty) {
		this.qty = qty;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}


}
