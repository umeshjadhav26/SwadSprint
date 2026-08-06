package com.tap.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tap.DAO.OrderItemDAO;
import com.tap.model.OrderItem;
import com.tap.utility.DBConnection;

public class OrderItemDAOImpl implements OrderItemDAO{

	@Override
	public void addOrderItem(OrderItem orderItem) {

		String addOrderItemQuery = "INSERT INTO `orderitem`(`orderId`, `quantity`, `itemTotal`, `menuId`)"
				+ " VALUES(?, ?, ?, ?)";
		
		Connection connection = DBConnection.getConnection();
		
		try {
			
			PreparedStatement pstmt = connection.prepareStatement(addOrderItemQuery);
			
			pstmt.setInt(1, orderItem.getOrderId());
			pstmt.setInt(2, orderItem.getQuantity());
			pstmt.setFloat(3, orderItem.getItemTotal());
			pstmt.setInt(4, orderItem.getMenuId());
			
			pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	

	@Override
	public OrderItem getOrderItem(int orderItemId) {
		String getOrderItemQuery = "SELECT * FROM `orderitem` WHERE `orderItemId`= ?";
		OrderItem orderItem = null;
		Connection connection = DBConnection.getConnection();
		
		try {
			
			PreparedStatement pstmt = connection.prepareStatement(getOrderItemQuery);
			
			pstmt.setInt(1, orderItemId);
			
			ResultSet res = pstmt.executeQuery();
			
			while(res.next()) {
				
				int orderId = res.getInt("orderId");
				int id = res.getInt("orderItemId");
				int quantity = res.getInt("quantity");
				float itemTotal = res.getFloat("itemTotal");
				int menuId = res.getInt("menuId");
				
				orderItem = new OrderItem(orderId, id, quantity, itemTotal, menuId);
			}
			
			return orderItem;
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	

	@Override
	public void updateOrderItem(OrderItem orderItem) {

		String updateOrderItemQuery = "UPDATE `orderitem` SET `orderId`= ?, `quantity`= ?, `itemTotal`= ?, `menuId`= ?"
				+ " WHERE `orderItemId`= ?";
		
		Connection connection = DBConnection.getConnection();
		
		try {
			
			PreparedStatement pstmt = connection.prepareStatement(updateOrderItemQuery);
			
			pstmt.setInt(1, orderItem.getOrderId());
			pstmt.setInt(2, orderItem.getQuantity());
			pstmt.setFloat(3, orderItem.getItemTotal());
			pstmt.setInt(4, orderItem.getMenuId());
			
			pstmt.setInt(5, orderItem.getOrderItemId());
			
			pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	

	@Override
	public void deleteOrderItem(int orderItemId) {

		String deleteOrderItemQuery = "DELETE FROM `orderitem` WHERE `orderItemId`= ?";
		
		Connection connection = DBConnection.getConnection();
		
		try {
			
			PreparedStatement pstmt = connection.prepareStatement(deleteOrderItemQuery);
			
			pstmt.setInt(1, orderItemId);
			
			pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	

	@Override
	public List<OrderItem> getAllOrderItems() {

		String getAllOrderItemsQuery = "SELECT * FROM `orderitem`";
		
		ArrayList<OrderItem> list = new ArrayList<OrderItem>();
		
		Connection connection = DBConnection.getConnection();
		
		try {
			
			Statement stmt = connection.createStatement();
			
			ResultSet res = stmt.executeQuery(getAllOrderItemsQuery);
			
			while(res.next()) {
				
				OrderItem orderItem = printAllOrderItems(res);
				list.add(orderItem);
			}
			
			return list;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}




	@Override
	public List<OrderItem> getOrderItemsByOrderId(int orderId) {

		String getOrderItemsByOrderIdQuery = "SELECT * FROM `orderitem` WHERE `orderId`= ?";
		
		ArrayList<OrderItem> list = new ArrayList<OrderItem>();
		
		Connection connection = DBConnection.getConnection();
		
		try {
			
			PreparedStatement pstmt = connection.prepareStatement(getOrderItemsByOrderIdQuery);
			
			pstmt.setInt(1, orderId);
			
			ResultSet res = pstmt.executeQuery();
			
			while(res.next()) {
				
				OrderItem orderItem = printAllOrderItems(res);
				list.add(orderItem);
			}
			
			return list;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	

	@Override
	public List<OrderItem> getOrderItemsByMenuId(int menuId) {

		String getOrderItemsByMenuIdQuery = "SELECT * FROM `orderitem` WHERE `menuId`= ?";
		
		ArrayList<OrderItem> list = new ArrayList<OrderItem>();
		
		Connection connection = DBConnection.getConnection();
		
		try {
			
			PreparedStatement pstmt = connection.prepareStatement(getOrderItemsByMenuIdQuery);
			
			pstmt.setInt(1, menuId);
			
			ResultSet res = pstmt.executeQuery();
			
			while(res.next()) {
				
				OrderItem orderItem = printAllOrderItems(res);
				list.add(orderItem);
			}
			
			return list;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	private OrderItem printAllOrderItems(ResultSet res) throws SQLException{

		int orderId = res.getInt("orderId");
		int id = res.getInt("orderItemId");
		int quantity = res.getInt("quantity");
		float itemTotal = res.getFloat("itemTotal");
		int menuId = res.getInt("menuId");
		
		OrderItem orderItem = new OrderItem(orderId, id, quantity, itemTotal, menuId);
		
		return orderItem;
	}

}
