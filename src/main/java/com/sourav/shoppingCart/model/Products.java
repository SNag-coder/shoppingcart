package com.sourav.shoppingCart.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Table(name="products")
public class Products {

	@Id
	private long id;

	public long getQty() {
		return qty;
	}

	public void setQty(long qty) {
		this.qty = qty;
	}

	@ManyToOne
	@JoinColumn(name="cat_id")
			@JsonIgnore
	private Category category;

	public List<AddtoCart> getCarts() {
		return carts;
	}

	public void setCarts(List<AddtoCart> carts) {
		this.carts = carts;
	}

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	private List<AddtoCart> carts;
	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	private long qty;

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	private String name;
	private long price;
	@Column(insertable = false)
	private Date added_on;

	public Date getAdded_on() {
		return added_on;
	}

	public void setAdded_on(Date added_on) {
		this.added_on = added_on;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
}
