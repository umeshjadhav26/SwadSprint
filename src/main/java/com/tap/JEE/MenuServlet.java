package com.tap.JEE;

import java.io.IOException;
import java.util.List;

import com.tap.DAOImpl.MenuDAOImpl;
import com.tap.DAOImpl.RestaurantDAOImpl;
import com.tap.model.Menu;
import com.tap.model.Restaurant;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/MenuServlet")
public class MenuServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int id = Integer.parseInt(req.getParameter("restaurantId"));
		
		MenuDAOImpl menuDAOImpl = new MenuDAOImpl();
		List<Menu> allMenusByRestaurant = menuDAOImpl.getMenusByRestaurantId(id);
		
        RestaurantDAOImpl restaurantDAOImpl = new RestaurantDAOImpl();
        Restaurant restaurant = restaurantDAOImpl.getRestaurant(id);
		
		req.setAttribute("restaurant", restaurant);
		
		req.setAttribute("allMenusByRestaurant", allMenusByRestaurant);
		
		RequestDispatcher rd = req.getRequestDispatcher("menu.jsp");
		rd.forward(req, resp);
	
	}

}
