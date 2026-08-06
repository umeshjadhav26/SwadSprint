package com.tap.utility;

import com.tap.model.Restaurant;
import com.tap.DAOImpl.RestaurantDAOImpl;

public class RestaurantTest {

	public static void main(String[] args) {
		

//ADD RESTAURANT:-
		
//		Restaurant restaurant = new Restaurant("Hotel Mandya", "South Indian", 3, "BTM Layout", 3.5f, true, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTI5yZSz0t8sBeGLb3lAWoliN8vPtfvbHkConD9se3jlQ&s=10", 1);
//		
//		RestaurantDAOImpl restaurantDAOImpl = new RestaurantDAOImpl();
//		restaurantDAOImpl.addRestaurant(restaurant);
//		
//		System.out.println("Restaurant added");
		
		
//GET RESTAURANT:-
		
//		RestaurantDAOImpl restaurantDAOImpl = new RestaurantDAOImpl();
//		
//		Restaurant restaurant = restaurantDAOImpl.getRestaurant(1);
//		System.out.println(restaurant);
		
		
//UPDATE RESTAURANT:-
		
		RestaurantDAOImpl restaurantDAOImpl = new RestaurantDAOImpl();
		
		Restaurant restaurant = restaurantDAOImpl.getRestaurantByName("Hotel Mandya");
		
		restaurant.setImagePath("C:\\Users\\umesh\\Downloads\\Hotel_Mandya.jpg");
		
		restaurantDAOImpl.updateRestaurant(restaurant);
		
		System.out.println("restaurant updated");
		
	}

}
