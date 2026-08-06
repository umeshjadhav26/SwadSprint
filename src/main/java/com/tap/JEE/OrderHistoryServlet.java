package com.tap.JEE;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.tap.model.User;
import com.tap.utility.OrderHistoryLoader;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/OrderHistoryServlet")
public class OrderHistoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> sessionOrders = (List<Map<String, Object>>) session.getAttribute("orderHistory");
            if (sessionOrders == null || sessionOrders.isEmpty()) {
                List<Map<String, Object>> dbOrders = OrderHistoryLoader.loadOrderHistory(loggedInUser);
                if (!dbOrders.isEmpty()) {
                    session.setAttribute("orderHistory", dbOrders);
                }
            }
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("orderHistory.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
