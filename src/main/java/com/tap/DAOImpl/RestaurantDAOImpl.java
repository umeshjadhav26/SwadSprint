package com.tap.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tap.DAO.RestaurantDAO;
import com.tap.model.Restaurant;
import com.tap.utility.DBConnection;

public class RestaurantDAOImpl implements RestaurantDAO {
	
	@Override
	public void addRestaurant(Restaurant restaurant) {
		
		String addQuery = "INSERT INTO `restaurant`(`name`, `cuisineType`, `deliveryTime`, `address`, `rating`, `isActive`, `imagePath`, `userId`)"
				+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		
		Connection connection = DBConnection.getConnection();
		
		try {
			
			PreparedStatement pstmt = connection.prepareStatement(addQuery);
			
			pstmt.setString(1, restaurant.getName());
			pstmt.setString(2, restaurant.getCuisineType());
			pstmt.setInt(3, restaurant.getDeliveryTime());
			pstmt.setString(4, restaurant.getAddress());
			pstmt.setFloat(5, restaurant.getRating());
			pstmt.setBoolean(6, restaurant.getIsActive());
			pstmt.setString(7, restaurant.getImagePath());
			pstmt.setInt(8, restaurant.getUserId());
			
			pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	

	@Override
	public Restaurant getRestaurant(int restaurantId) {

		String getQuery = "SELECT * FROM `restaurant` WHERE `restaurantId` = ?";
		
		Restaurant restaurant = null;
		Connection connection = DBConnection.getConnection();
		
		try {
			
			PreparedStatement pstmt = connection.prepareStatement(getQuery);
			
			pstmt.setInt(1, restaurantId);
			
			ResultSet res = pstmt.executeQuery();
			
			while(res.next()) {
				
				int id = res.getInt("restaurantId");
				String name = res.getString("name");
				String cuisineType = res.getString("cuisineType");
				int deliveryTime = res.getInt("deliveryTime");
				String address = res.getString("address");
				float rating = res.getFloat("rating");
				boolean isActive = res.getBoolean("isActive");
				String imagePath = res.getString("imagePath");
				int userId = res.getInt("userId");
				
				restaurant = new Restaurant(id, name, cuisineType, deliveryTime, address, rating, isActive, imagePath, userId);
				
			}
			return restaurant;
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public void deleteRestaurant(int restaurantId) {

		String deleteQuery = "DELETE FROM `restaurant` WHERE `restaurantId` = ?";
		
		Connection connection = DBConnection.getConnection();
		
		try {
			
			PreparedStatement pstmt = connection.prepareStatement(deleteQuery);
			
			pstmt.setInt(1, restaurantId);
			
			pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	

	@Override
	public void updateRestaurant(Restaurant restaurant) {

		String updateQuery = "UPDATE `restaurant` SET `name`= ?, `cuisineType`= ?, `deliveryTime`= ?, `address`= ?, `rating`= ?, `isActive`= ?, `imagePath`= ?, `userId`= ?"
				+ " WHERE `restaurantId`= ?";
		
		Connection connection = DBConnection.getConnection();
		
		try {
			
			PreparedStatement pstmt = connection.prepareStatement(updateQuery);
			
			pstmt.setString(1, restaurant.getName());
			pstmt.setString(2, restaurant.getCuisineType());
			pstmt.setInt(3, restaurant.getDeliveryTime());
			pstmt.setString(4, restaurant.getAddress());
			pstmt.setFloat(5, restaurant.getRating());
			pstmt.setBoolean(6, restaurant.getIsActive());
			pstmt.setString(7, restaurant.getImagePath());
			pstmt.setInt(8, restaurant.getUserId());
			
			pstmt.setInt(9, restaurant.getRestaurantId());
			
			pstmt.executeUpdate();
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	

	@Override
	public List<Restaurant> getAllRestaurants() {

		String getAllRestaurantsQuery = "SELECT * FROM `restaurant`";
		
		ArrayList<Restaurant> list = new ArrayList<Restaurant>();
		
		Connection connection = DBConnection.getConnection();
		
		try {
			
			Statement stmt = connection.createStatement();
			
			ResultSet res = stmt.executeQuery(getAllRestaurantsQuery);
			
			while(res.next()) {
				
				Restaurant restaurant = printAllRestaurants(res);
				list.add(restaurant);
			}
			
			return list;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
	@Override
	public List<Restaurant> getRestaurantsByUserId(int userId) {

		String getRestaurantsByuserIdQuery = "SELECT * FROM `restaurant` WHERE `userId` = ?";
		
		ArrayList<Restaurant> list = new ArrayList<Restaurant>();
		
		Connection connection = DBConnection.getConnection();
		
		try {
			
			PreparedStatement pstmt = connection.prepareStatement(getRestaurantsByuserIdQuery);
			
			pstmt.setInt(1, userId);
			
			ResultSet res = pstmt.executeQuery();
			
			while(res.next()) {
				
				Restaurant restaurant = printAllRestaurants(res);
				list.add(restaurant);
			}
			
			return list;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	private Restaurant printAllRestaurants(ResultSet res) throws SQLException {
		
		int id = res.getInt("restaurantId");
		String name = res.getString("name");
		String cuisineType = res.getString("cuisineType");
		int deliveryTime = res.getInt("deliveryTime");
		String address = res.getString("address");
		float rating = res.getFloat("rating");
		boolean isActive = res.getBoolean("isActive");
		String imagePath = res.getString("imagePath");
		int userId = res.getInt("userId");
		
		Restaurant restaurant = new Restaurant(id, name, cuisineType, deliveryTime, address, rating, isActive, imagePath, userId);
		
		return restaurant;
	}
	



	@Override
	public Restaurant getRestaurantByName(String name) {

		String getByNameQuery = "SELECT * FROM `restaurant` WHERE `name` = ?";
		
		Restaurant restaurant = null;
		Connection connection = DBConnection.getConnection();
		
		try {
			
			PreparedStatement pstmt = connection.prepareStatement(getByNameQuery);
			
			pstmt.setString(1, name);
			
			ResultSet res = pstmt.executeQuery();
			
			while(res.next()) {
				
				int id = res.getInt("restaurantId");
				String restaurantName = res.getString("name");
				String cuisineType = res.getString("cuisineType");
				int deliveryTime = res.getInt("deliveryTime");
				String address = res.getString("address");
				float rating = res.getFloat("rating");
				boolean isActive = res.getBoolean("isActive");
				String imagePath = res.getString("imagePath");
				int userId = res.getInt("userId");
				
				restaurant = new Restaurant(id, restaurantName, cuisineType, deliveryTime, address, rating, isActive, imagePath, userId);
				
			}
			
			return restaurant;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
}
