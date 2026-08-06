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

@WebServlet("/updateProfile")
public class UpdateProfileServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null) {
            resp.sendRedirect("loginPage.jsp");
            return;
        }

        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            resp.sendRedirect("loginPage.jsp");
            return;
        }

        String name = req.getParameter("name");
        String address = req.getParameter("address");
        String password = req.getParameter("password");

        if (name != null && !name.trim().isEmpty()) {
            user.setUserName(name.trim());
        }

        if (address != null) {
            user.setAddress(address.trim());
        }

        if (password != null && !password.trim().isEmpty() && !password.equals("password")) {
            String hashedPassword = BCrypt.hashpw(password.trim(), BCrypt.gensalt());
            user.setPassword(hashedPassword);
        }

        UserDAOImpl userDAO = new UserDAOImpl();
        userDAO.updateUser(user);

        session.setAttribute("loggedInUser", user);
        resp.sendRedirect("userProfile.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
