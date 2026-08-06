package com.tap.JEE;

import java.io.IOException;
import java.util.List;

import com.tap.DAOImpl.RestaurantDAOImpl;
import com.tap.model.Restaurant;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/RestaurantServlet")
public class RestaurantServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RestaurantDAOImpl restaurantDAOImpl = new RestaurantDAOImpl();
		List<Restaurant> Allrestaurants = restaurantDAOImpl.getAllRestaurants();
		
		req.setAttribute("Allrestaurants", Allrestaurants);
		
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("restaurants.jsp");
		requestDispatcher.forward(req, resp);
		
	}

}
