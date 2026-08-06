package com.tap.DAO;

import com.tap.model.User;
import java.util.List;

public interface UserDAO {
	
	int addUser(User user);
	User getUser(int userId);
	void updateUser(User user);
	void deleteUser(int userId);
	List<User> getAllUser();
	User getUserByUsername(String userName);
	User getUserByUserEmail(String userEmail);
	
}
