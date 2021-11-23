package com.sourav.shoppingCart.model;


import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name="checkout_cart")
public class CheckoutCart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private long user_id;
	@OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "product_id")
	Products product;


	//long ;
	private int qty;
	/*double price;*/
	@Column(insertable = false)

	private Date order_date;

	public Date getOrder_date() {
		return order_date;
	}

	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}

	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}

	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	public Products getProduct() {
		return product;
	}
	public void setProduct(Products product) {
		this.product = product;
	}
	
	
}
