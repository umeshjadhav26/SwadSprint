package com.tap.JEE;

import java.io.IOException;

import com.tap.DAOImpl.MenuDAOImpl;
import com.tap.model.Cart;
import com.tap.model.CartItem;
import com.tap.model.Menu;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		
		Cart cart = (Cart)session.getAttribute("cart");
		Integer restaurantId = (Integer)session.getAttribute("restaurantId");		
		Integer newRestaurantId = Integer.parseInt(req.getParameter("restaurantId"));
		
		String restaurantName = req.getParameter("restaurantName");

		session.setAttribute("restaurantName", restaurantName);
		
		if(cart == null || restaurantId == null || restaurantId != newRestaurantId) {
			cart = new Cart();
			session.setAttribute("cart", cart);
			session.setAttribute("restaurantId", newRestaurantId);
		}
		
		
		String action = req.getParameter("action");
		
		if(action.equals("add")) 
		{
			addItemToCart(req, cart);
		}
		else if(action.equals("update")) 
		{
			updateItemToCart(req, cart);
		}
		else if(action.equals("delete"))
		{
			removeItemFromCart(req, cart);
		}
		
//		resp.sendRedirect("cart.jsp");
		
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("cart.jsp");
		requestDispatcher.forward(req, resp);
	}
	
	
	private void addItemToCart(HttpServletRequest req, Cart cart) {
		
		int menuId = Integer.parseInt(req.getParameter("menuId"));
		int quantity = Integer.parseInt(req.getParameter("quantity"));
		
		MenuDAOImpl menuDAOImpl = new MenuDAOImpl();
		Menu menu = menuDAOImpl.getMenu(menuId);
		
		if(menu != null) {
			
			CartItem cartItem = new CartItem(menu.getItemName(), 
					menuId, 	menu.getRestaurantId(), 	menu.getPrice(), quantity);
			
			Cart.addCartItem(cartItem);
		}
		
	}
	
	
	
	private void updateItemToCart(HttpServletRequest req, Cart cart) {
		
		int menuId = Integer.parseInt(req.getParameter("menuId"));
		int quantity = Integer.parseInt(req.getParameter("quantity"));
		
		Cart.updateItem(menuId, quantity);
		
	}
	


	private void removeItemFromCart(HttpServletRequest req, Cart cart) {

		int menuId = Integer.parseInt(req.getParameter("menuId"));
		
		Cart.removeItem(menuId);
		
	}
	
}
