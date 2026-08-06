package com.tap.model;

public class Menu {
	
	/*Private variables*/
	
	private int menuId;
	private String itemName;
	private String description;
	private float price;
	private boolean isAvailable;
	private String imagePath;
	private int restaurantId;
	
	/*Constructors*/
	
	public Menu() {
		
		
	}
	
	public Menu(int menuId, String itemName, String description, float price, boolean isAvailable, String imagePath,
			int restaurantId) {
		super();
		this.menuId = menuId;
		this.itemName = itemName;
		this.description = description;
		this.price = price;
		this.isAvailable = isAvailable;
		this.imagePath = imagePath;
		this.restaurantId = restaurantId;
	}
	
	
	
	/*Getters and Setters*/

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public boolean getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}
	
	
	
	/*toString method*/

	@Override
	public String toString() {
		return "Menu [menuId=" + menuId + ", itemName=" + itemName + ", description=" + description + ", price=" + price
				+ ", isAvailable=" + isAvailable + ", imagePath=" + imagePath + ", restaurantId=" + restaurantId + "]";
	}


	
	
	

}
