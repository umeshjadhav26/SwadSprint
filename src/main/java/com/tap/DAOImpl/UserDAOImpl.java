package com.tap.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.tap.DAO.UserDAO;
import com.tap.model.User;
import com.tap.utility.DBConnection;

public class UserDAOImpl implements UserDAO{
	
	@Override
	public int addUser(User user) {
		
		String INSERT_QUERY = "INSERT INTO user(userName, email, password, address, role, createdDate, lastLoginDate)"
				+ " values(?, ?, ?, ?, ?, ?, ?)";
		
		Connection connection = DBConnection.getConnection();
		
		try {
			
			PreparedStatement pstmt = connection.prepareStatement(INSERT_QUERY);
			
			pstmt.setString(1,  user.getUserName());
			pstmt.setString(2,  user.getEmail());
			pstmt.setString(3,  user.getPassword());
			pstmt.setString(4,  user.getAddress());
			pstmt.setString(5,  user.getRole());
			pstmt.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
			pstmt.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
			
			int i = pstmt.executeUpdate();
			
			return i;
//			System.out.println(i);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	
	
	

	@Override
	public User getUser(int userId) {
		
		String GET_QUERY = "SELECT * FROM `user` WHERE `userId` = ?";
		
		User user = null;
		Connection connection = DBConnection.getConnection();
		
		try {
			PreparedStatement pstmt = connection.prepareStatement(GET_QUERY);
			
			pstmt.setInt(1, userId);
			
			ResultSet res = pstmt.executeQuery();
			
			while(res.next()) {
				
				int id = res.getInt("userId");
				String name = res.getString("userName");
				String email = res.getString("email");
				String password = res.getString("password");
				String address = res.getString("address");
				String role = res.getString("role");
				Timestamp createdDate = res.getTimestamp("createdDate");
				Timestamp lastLoginDate = res.getTimestamp("lastLoginDate");
				
				user = new User(id, name, email, password, address, role, createdDate, lastLoginDate);
			}
			
			return user;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	
	}
	
	
	
	

	@Override
	public void updateUser(User user) {
		
		String UPDATE_QUERY = "UPDATE `user` SET `userName` = ?, `email` = ?, `password` = ?, `address` = ?, `lastLoginDate` = ?"
				+ " WHERE `userId` = ?";

		Connection connection = DBConnection.getConnection();
		try {
			
			PreparedStatement pstmt = connection.prepareStatement(UPDATE_QUERY);
			
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getEmail());
			pstmt.setString(3, user.getPassword());
			pstmt.setString(4, user.getAddress());
			pstmt.setTimestamp(5, user.getLastLoginDate());
			
			pstmt.setInt(6, user.getUserId());
			
			pstmt.executeUpdate();
			
//			System.out.println(i);
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	

	@Override
	public void deleteUser(int userId) {

		String DELETE_QUERY = "DELETE FROM `user` WHERE `userId` = ?";
		
		Connection connection = DBConnection.getConnection();
		
		try {
			
			PreparedStatement pstmt = connection.prepareStatement(DELETE_QUERY);
			
			pstmt.setInt(1, userId);
			
			pstmt.executeUpdate();
			
//			System.out.println(i);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	
	
	
	
	@Override
	public List<User> getAllUser() {
		
		String GET_ALL_USERS_QUERY = "SELECT * FROM `user`";
		
		ArrayList<User> list = new ArrayList<User>();

		Connection connection = DBConnection.getConnection();
		
		try {
			
			Statement stmt = connection.createStatement();
			
			ResultSet res = stmt.executeQuery(GET_ALL_USERS_QUERY);
			
			while(res.next()) {
				
				User user = printAllUsers(res);
				list.add(user);
			}
			
			return list;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public User printAllUsers(ResultSet res) throws SQLException{
			
			int id = res.getInt("userId");
			String name = res.getString("userName");
			String email = res.getString("email");
			String password = res.getString("password");
			String address = res.getString("address");
			String role = res.getString("role");
			Timestamp createdDate = res.getTimestamp("createdDate");
			Timestamp lastLoginDate = res.getTimestamp("lastLoginDate");
			
			User user = new User(id, name, email, password, address, role, createdDate, lastLoginDate);
			
			return user;
			
	}
	
	
	
	
	
	@Override
	public User getUserByUsername(String username) {
		
		User user = null;
		String QUERY = "SELECT * FROM `user` WHERE `userName` = ?";
		
		Connection connection = DBConnection.getConnection();
		
		try {
			
			PreparedStatement pstmt = connection.prepareStatement(QUERY);
			
			pstmt.setString(1, username);
			
			ResultSet res = pstmt.executeQuery();
			
			if(res.next()) {
				
				user = new User();
				user.setUserId(res.getInt("userId"));
				user.setUserName(res.getString("userName"));
				user.setEmail(res.getString("email"));
				user.setPassword(res.getString("password"));
				user.setAddress(res.getString("address"));
				user.setRole(res.getString("role"));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
	
	
	
	
	@Override
	public User getUserByUserEmail(String userEmail) {
		
		User user = null;
		String GET_BY_EMAIL = "SELECT * FROM `user` WHERE `email` = ?";
		
		Connection connection = DBConnection.getConnection();
		
		try {
			
			PreparedStatement pstmt = connection.prepareStatement(GET_BY_EMAIL);
			
			pstmt.setString(1, userEmail);
			
			ResultSet res = pstmt.executeQuery();
			
			if(res.next()) {
				
				user = new User();
				user.setUserId(res.getInt("userId"));
				user.setUserName(res.getString("userName"));
				user.setEmail(res.getString("email"));
				user.setPassword(res.getString("password"));
				user.setAddress(res.getString("address"));
				user.setRole(res.getString("role"));
				user.setCreatedDate(res.getTimestamp("createdDate"));
				user.setLastLoginDate(res.getTimestamp("lastLoginDate"));
				
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}

}
