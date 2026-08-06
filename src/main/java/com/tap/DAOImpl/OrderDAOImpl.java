package com.tap.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.tap.DAO.OrderDAO;
import com.tap.model.Order;
import com.tap.utility.DBConnection;

public class OrderDAOImpl implements OrderDAO{

	@Override
	public int addOrder(Order order) {

		String addOrderQuery = "INSERT INTO `ordertable`(`userId`, `orderDate`, `totalAmount`, `status`, `paymentMethod`, `restaurantId`)"
				+ " VALUES(?, ?, ?, ?, ?, ?)";
		
		Connection connection = DBConnection.getConnection();
		int generatedId = 0;
		
		try {
			
			PreparedStatement pstmt = connection.prepareStatement(addOrderQuery, Statement.RETURN_GENERATED_KEYS);
			
			pstmt.setInt(1, order.getUserId());
			pstmt.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
			pstmt.setFloat(3, order.getTotalAmount());
			pstmt.setString(4, order.getStatus());
			pstmt.setString(5, order.getPaymentMethod());
			pstmt.setInt(6, order.getRestaurantId());
			
			pstmt.executeUpdate();
			
			ResultSet res = pstmt.getGeneratedKeys();
			if (res.next()) {
				generatedId = res.getInt(1);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return generatedId;
	}
	
	
	

	@Override
	public Order getOrder(int orderId) {

		String getOrderQuery = "SELECT * FROM `ordertable` WHERE `orderId`= ?";
		Order order = null;
		Connection connection = DBConnection.getConnection();
		
		try {
			
			PreparedStatement pstmt = connection.prepareStatement(getOrderQuery);
			
			pstmt.setInt(1, orderId);
			
			ResultSet res = pstmt.executeQuery();
			
			while(res.next()) {
				
				int userId = res.getInt("userId");
				int id = res.getInt("orderId");
				Timestamp orderDate = res.getTimestamp("orderDate");
				float totalAmount = res.getFloat("totalAmount");
				String status = res.getString("status");
				String paymentMethod = res.getString("paymentMethod");
				int restaurantId = res.getInt("restaurantId");
				
				order = new Order(userId, id, orderDate, totalAmount, status, paymentMethod, restaurantId);
			}
			
			return order;
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
				
		return null;
	}
	
	

	@Override
	public void updateOrder(Order order) {

		String updateOrderQuery = "UPDATE `ordertable` SET `userId`= ?, `orderDate`= ?, `totalAmount`= ?, `status`= ?, `paymentMethod`= ?, `restaurantId`= ?"
				+ " WHERE `orderId`= ?";
		
		Connection connection = DBConnection.getConnection();
		
		try {
			
			PreparedStatement pstmt = connection.prepareStatement(updateOrderQuery);
			
			pstmt.setInt(1, order.getUserId());
			pstmt.setTimestamp(2, order.getOrderDate());
			pstmt.setFloat(3, order.getTotalAmount());
			pstmt.setString(4, order.getStatus());
			pstmt.setString(5, order.getPaymentMethod());
			pstmt.setInt(6, order.getRestaurantId());
			
			pstmt.setInt(7, order.getOrderId());
			
			pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	

	@Override
	public void deleteOrder(int orderId) {

		String deleteOrderQuery = "DELETE FROM `ordertable` WHERE `orderId`= ?";
		
		Connection connection = DBConnection.getConnection();
		
		try {
			
			PreparedStatement pstmt = connection.prepareStatement(deleteOrderQuery);
			
			pstmt.setInt(1, orderId);
			
			pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	

	@Override
	public List<Order> getAllOrders() {

		String getAllOrdersQuery = "SELECT * FROM `ordertable`";
		
		ArrayList<Order> list = new ArrayList<Order>();
		
		Connection connection = DBConnection.getConnection();
		
		try {
			
			Statement stmt = connection.createStatement();
			
			ResultSet res = stmt.executeQuery(getAllOrdersQuery);
			
			while(res.next()) {
				
				Order order = printAllOrders(res);
				list.add(order);
			}
			
			return list;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}




	@Override
	public List<Order> getOrdersByUserId(int userId) {

		String getOrdersByUserIdQuery = "SELECT * FROM `ordertable` WHERE `userId`= ?";
		
		ArrayList<Order> list = new ArrayList<Order>();
		
		Connection connection = DBConnection.getConnection();
		
		try {
			
			PreparedStatement pstmt = connection.prepareStatement(getOrdersByUserIdQuery);
			
			pstmt.setInt(1, userId);
			
			ResultSet res = pstmt.executeQuery();
			
			while(res.next()) {
				
				Order order = printAllOrders(res);
				list.add(order);
			}
			
			return list;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
	

	@Override
	public List<Order> getOrdersByRestaurantId(int restaurantId) {

		String getOrdersByRestoIdQuery = "SELECT * FROM `ordertable` WHERE `restaurantId`= ?";
		
		ArrayList<Order> list = new ArrayList<Order>();
		
		Connection connection = DBConnection.getConnection();
		
		try {
			
			PreparedStatement pstmt = connection.prepareStatement(getOrdersByRestoIdQuery);
			
			pstmt.setInt(1, restaurantId);
			
			ResultSet res = pstmt.executeQuery();
			
			while(res.next()) {
				
				Order order = printAllOrders(res);
				list.add(order);
			}
			
			return list;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	private Order printAllOrders(ResultSet res) throws SQLException{
		
		int userId = res.getInt("userId");
		int id = res.getInt("orderId");
		Timestamp orderDate = res.getTimestamp("orderDate");
		float totalAmount = res.getFloat("totalAmount");
		String status = res.getString("status");
		String paymentMethod = res.getString("paymentMethod");
		int restaurantId = res.getInt("restaurantId");
		
		Order order = new Order(userId, id, orderDate, totalAmount, status, paymentMethod, restaurantId);
		
		return order;
	}

	
}
