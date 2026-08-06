package com.tap.model;

public class CartItem {
	
	private String name;
	private int menuId;
	private int restaurantId;
	private float price;
	private int quantity;
	
	
	public CartItem() {
		
	}


	public CartItem(String name, int menuId, int restaurantId, float price, int quantity) {
		super();
		this.name = name;
		this.menuId = menuId;
		this.restaurantId = restaurantId;
		this.price = price;
		this.quantity = quantity;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getMenuId() {
		return menuId;
	}


	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}


	public int getRestaurantId() {
		return restaurantId;
	}


	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}


	public float getPrice() {
		return price;
	}


	public void setPrice(float price) {
		this.price = price;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	@Override
	public String toString() {
		return "CartItem [name=" + name + ", menuId=" + menuId + ", restaurantId=" + restaurantId + ", price=" + price
				+ ", quantity=" + quantity + "]";
	}
	
	
	public float getTotalPrice() {
		return quantity * price;
	}

}
