package com.tap.model;

public class Restaurant {
	
	/*Private Members*/
	
	private int restaurantId;
	private String name;
	private String cuisineType;
	private int deliveryTime;
	private String address;
	private float rating;
	private boolean isActive;
	private String imagePath;
	private int userId;
	
	/*Constructors*/
	
	
	public Restaurant() {
		
	}
	
	
	public Restaurant(int restaurantId, String name, String cuisineType, int deliveryTime, String address, float rating,
			boolean isActive, String imagePath, int userId) {
		super();
		this.restaurantId = restaurantId;
		this.name = name;
		this.cuisineType = cuisineType;
		this.deliveryTime = deliveryTime;
		this.address = address;
		this.rating = rating;
		this.isActive = isActive;
		this.imagePath = imagePath;
		this.userId = userId;
	}
	
	
	public Restaurant(String name, String cuisineType, int deliveryTime, String address, float rating,
			boolean isActive, String imagePath, int userId) {
		super();
		this.name = name;
		this.cuisineType = cuisineType;
		this.deliveryTime = deliveryTime;
		this.address = address;
		this.rating = rating;
		this.isActive = isActive;
		this.imagePath = imagePath;
		this.userId = userId;
	}
	
	
	
	public Restaurant(String name, String cuisineType, int deliveryTime, String address, float rating, boolean isActive,
			String imagePath) {
		super();
		this.name = name;
		this.cuisineType = cuisineType;
		this.deliveryTime = deliveryTime;
		this.address = address;
		this.rating = rating;
		this.isActive = isActive;
		this.imagePath = imagePath;
	}
	
	

	/*Setters and Getters*/
	
	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCuisineType() {
		return cuisineType;
	}

	public void setCuisineType(String cuisineType) {
		this.cuisineType = cuisineType;
	}

	public int getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(int deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
	
	/*toString method*/

	@Override
	public String toString() {
		return "Restaurant [restaurantId=" + restaurantId + ", name=" + name + ", cuisineType=" + cuisineType
				+ ", deliveryTime=" + deliveryTime + ", address=" + address + ", rating=" + rating + ", isActive="
				+ isActive + ", imagePath=" + imagePath + ", userId=" + userId + "]";
	}

}
