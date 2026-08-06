package com.tap.utility;

import java.util.List;

import com.tap.DAOImpl.UserDAOImpl;
import com.tap.model.User;

public class UserTest {

	public static void main(String[] args) {
		
		
//ADD USER:-
//		//Create the object of User class to initialize the values to store in the database.
//		User user = new User("Umesh","umesh@gmail.com","Umesh@123","Bangalore","Admin");
//		User user = new User("Shradha","shradha@gmail.com","Shradha@123","Bangalore","customer");
//		User user = new User("Nitin","nitin@gmail.com","Nitin@123","Delhi","customer");
//		User user = new User("Rishabh","rishabh@gmail.com","Rishabh@123","Bihar","customer");
//		
//		//create the object of UserDAOImpl class to access the Thread operation methods, so i can able to add data in db
//		UserDAOImpl userDAOImpl = new UserDAOImpl();
//		
//		//for addUser() method.
//		userDAOImpl.addUser(user);
//		
//		System.out.println("User added");
		
		
		
		
//GET USER:-	
//		UserDAOImpl UserDAOImpl = new UserDAOImpl();
//		
//		User user = UserDAOImpl.getUser(1);
//		System.out.println(user);
		
		
		
		
//UPDATE USER:-			
//		UserDAOImpl userDAOImpl = new UserDAOImpl();
//		
//		User user = userDAOImpl.getUser(5);
//		
//		user.setEmail("Tomar@gmail.com");
//		
//		userDAOImpl.updateUser(user);
//		
//		System.out.println("user updated");
		
		
		

//DELETE USER:-
		UserDAOImpl userDAOImpl = new UserDAOImpl();
		
		userDAOImpl.deleteUser(3);
		
		System.out.println("User deleted");
		
		
		
		
//GET ALL USERS:-
//		UserDAOImpl userDAOImpl = new UserDAOImpl();
//		
//		List<User> user = userDAOImpl.getAllUser();
//		System.out.println(user);
	}

}
