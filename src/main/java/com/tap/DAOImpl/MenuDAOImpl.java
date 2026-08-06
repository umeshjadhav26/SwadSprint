package com.tap.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tap.DAO.MenuDAO;
import com.tap.model.Menu;
import com.tap.utility.DBConnection;

public class MenuDAOImpl implements MenuDAO{

	@Override
	public void addMenu(Menu menu) {
		
		String addMenuQuery = "INSERT INTO `menu`(`itemName`, `description`, `price`, `isAvailable`, `imagePath`, `restaurantId`)"
				+ " VALUES(?, ?, ?, ?, ?, ?)";
		
		Connection connection = DBConnection.getConnection();
		
		try {
			
			PreparedStatement pstmt = connection.prepareStatement(addMenuQuery);
			
			pstmt.setString(1, menu.getItemName());
			pstmt.setString(2, menu.getDescription());
			pstmt.setFloat(3, menu.getPrice());
			pstmt.setBoolean(4, menu.getIsAvailable());
			pstmt.setString(5, menu.getImagePath());
			pstmt.setInt(6, menu.getRestaurantId());
			
			pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	

	@Override
	public Menu getMenu(int menuId) {
		
		String getMenuQuery = "SELECT * FROM `menu` WHERE `menuId`= ?";
		Menu menu = null;
		Connection connection = DBConnection.getConnection();
		
		try {
			
			PreparedStatement pstmt = connection.prepareStatement(getMenuQuery);
			
			pstmt.setInt(1, menuId);
			
			ResultSet res = pstmt.executeQuery();
			
			while(res.next()) {
				
				int id = res.getInt("menuId");
				String itemName = res.getString("itemName");
				String description = res.getString("description");
				float price = res.getFloat("price");
				boolean isAvailable = res.getBoolean("isAvailable");
				String imagePath = res.getString("imagePath");
				int restaurantId = res.getInt("restaurantId");
				
				menu = new Menu(id, itemName, description, price, isAvailable, imagePath, restaurantId);
			}
			
			return menu;
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
	

	@Override
	public void deleteMenu(int menuId) {
		
		String deleteMenuQuery = "DELETE FROM `menu` WHERE `menuId`= ?";
		
		Connection connection = DBConnection.getConnection();
		
		try {
			
			PreparedStatement pstmt = connection.prepareStatement(deleteMenuQuery);
			
			pstmt.setInt(1, menuId);
			
			pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	

	@Override
	public void updateMenu(Menu menu) {

		String updateMenuQuery = "UPDATE `menu` SET `itemName`= ?, `description`= ?, `price`= ?, `isAvailable`= ?, `imagePath`= ?, `restaurantId`= ?"
				+ " WHERE `menuId`= ?";
		
		Connection connection = DBConnection.getConnection();
		
		try {
			
			PreparedStatement pstmt = connection.prepareStatement(updateMenuQuery);
			
			pstmt.setString(1, menu.getItemName());
			pstmt.setString(2, menu.getDescription());
			pstmt.setFloat(3, menu.getPrice());
			pstmt.setBoolean(4, menu.getIsAvailable());
			pstmt.setString(5, menu.getImagePath());
			pstmt.setInt(6, menu.getRestaurantId());
			
			pstmt.setInt(7, menu.getMenuId());
			
			pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	

	@Override
	public List<Menu> getAllMenus() {

		String getAllMenusQuery = "SELECT * FROM `menu`";
		
		ArrayList<Menu> list = new ArrayList<Menu>();
		
		Connection connection = DBConnection.getConnection();
		
		try {
			
			Statement stmt = connection.createStatement();
			
			ResultSet res = stmt.executeQuery(getAllMenusQuery);
			
			while(res.next()) {
				
				Menu menu = printAllMenus(res);
				list.add(menu);
			}
			
			return list;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	@Override
	public List<Menu> getMenusByRestaurantId(int restaurantId) {
		
		String getMenuByRestoIdQuery = "SELECT * FROM `menu` WHERE `restaurantId`= ?";
		
		ArrayList<Menu> list = new ArrayList<Menu>();
		
		Connection connection = DBConnection.getConnection();
		
		try {
			
			PreparedStatement pstmt = connection.prepareStatement(getMenuByRestoIdQuery);
			
			pstmt.setInt(1, restaurantId);
			
			ResultSet res = pstmt.executeQuery();
			
			while(res.next()) {
				
				Menu menu = printAllMenus(res);
				list.add(menu);
			}
			
			return list;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	

	private Menu printAllMenus(ResultSet res) throws SQLException{

		int id = res.getInt("menuId");
		String itemName = res.getString("itemName");
		String description = res.getString("description");
		float price = res.getFloat("price");
		boolean isAvailable = res.getBoolean("isAvailable");
		String imagePath = res.getString("imagePath");
		int restaurantId = res.getInt("restaurantId");
		
		Menu menu = new Menu(id, itemName, description, price, isAvailable, imagePath, restaurantId);
		
		return menu;
	}	
	
}
