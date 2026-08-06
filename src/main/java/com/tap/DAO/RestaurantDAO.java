package com.tap.DAO;

import com.tap.model.Restaurant;

import java.util.List;

public interface RestaurantDAO {
	
	void addRestaurant(Restaurant restaurant);
	Restaurant getRestaurant(int restaurantId);
	void deleteRestaurant(int restaurantId);
	void updateRestaurant(Restaurant restaurant);
	List<Restaurant> getAllRestaurants();
	Restaurant getRestaurantByName(String name);
	
	List<Restaurant> getRestaurantsByUserId(int userId);
}
