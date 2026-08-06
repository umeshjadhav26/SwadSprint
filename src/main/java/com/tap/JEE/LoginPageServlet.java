package com.tap.JEE;

import java.io.IOException;

import org.mindrot.jbcrypt.BCrypt;

import com.tap.DAOImpl.UserDAOImpl;
import com.tap.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/loginpage")
public class LoginPageServlet extends HttpServlet {

	private static final int MAX_LOGIN_ATTEMPTS = 3;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String email = req.getParameter("email");
		String password = req.getParameter("password");
		HttpSession session = req.getSession();
		UserDAOImpl userDAOImpl = new UserDAOImpl();
		User user = userDAOImpl.getUserByUserEmail(email);

		if (user == null) {
			req.setAttribute("loginError", "We could not find an account with that email address.");
			req.getRequestDispatcher("loginPage.jsp").forward(req, resp);
			return;
		}

		if (BCrypt.checkpw(password, user.getPassword())) {
			session.removeAttribute("failedLoginAttempts");
			session.setAttribute("loggedInUser", user);
			java.util.List<java.util.Map<String, Object>> orderHistory = com.tap.utility.OrderHistoryLoader.loadOrderHistory(user);
			session.setAttribute("orderHistory", orderHistory);
			resp.sendRedirect("RestaurantServlet");
			return;
		}

		Integer failedAttempts = (Integer) session.getAttribute("failedLoginAttempts");
		int attemptsUsed = failedAttempts == null ? 1 : failedAttempts + 1;
		session.setAttribute("failedLoginAttempts", attemptsUsed);
		int attemptsRemaining = MAX_LOGIN_ATTEMPTS - attemptsUsed;

		if (attemptsRemaining > 0) {
			req.setAttribute("loginError", "Incorrect password. " + attemptsRemaining
					+ (attemptsRemaining == 1 ? " attempt" : " attempts") + " remaining.");
		} else {
			req.setAttribute("loginError", "Your login is temporarily blocked after 3 unsuccessful attempts.");
			req.setAttribute("loginBlocked", true);
		}

		req.getRequestDispatcher("loginPage.jsp").forward(req, resp);
	}
}
