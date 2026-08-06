package com.tap.JEE;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

import com.tap.DAOImpl.OrderDAOImpl;
import com.tap.DAOImpl.OrderItemDAOImpl;
import com.tap.model.Cart;
import com.tap.model.CartItem;
import com.tap.model.Order;
import com.tap.model.OrderItem;
import com.tap.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/PlaceOrderServlet")
public class PlaceOrderServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        String restaurantName = (String) session.getAttribute("restaurantName");
        String paymentMethod = req.getParameter("paymentMethod");
        if (paymentMethod == null) paymentMethod = "UPI";

        if (cart == null || cart.getItems().isEmpty()) {
            resp.sendRedirect("cart.jsp");
            return;
        }

        // Build order summary map
        Map<String, Object> order = new LinkedHashMap<>();
        String orderId = "#FH-" + System.currentTimeMillis() % 100000000;
        order.put("orderId", orderId);
        order.put("date", new Timestamp(System.currentTimeMillis()));
        order.put("restaurantName", restaurantName != null ? restaurantName : "Restaurant");
        order.put("paymentMethod", paymentMethod);
        order.put("status", "Paid");

        // Copy cart items into order
        List<Map<String, Object>> items = new ArrayList<>();
        float subtotal = 0;
        for (CartItem item : cart.getItems().values()) {
            Map<String, Object> itemMap = new LinkedHashMap<>();
            itemMap.put("name", item.getName());
            itemMap.put("quantity", item.getQuantity());
            itemMap.put("price", item.getPrice());
            itemMap.put("totalPrice", item.getTotalPrice());
            items.add(itemMap);
            subtotal += item.getTotalPrice();
        }
        order.put("items", items);

        float gst = (subtotal * 18) / 100.0f;
        float deliveryFee = 30;
        float grandTotal = subtotal + gst + deliveryFee;
        order.put("subtotal", subtotal);
        order.put("gst", gst);
        order.put("deliveryFee", deliveryFee);
        order.put("grandTotal", grandTotal);

        // Save order to MySQL Database if user is logged in
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            try {
                int userId = loggedInUser.getUserId();
                Integer sessionRestId = (Integer) session.getAttribute("restaurantId");
                int restaurantId = sessionRestId != null ? sessionRestId : 1;

                Order dbOrder = new Order(userId, 0, new Timestamp(System.currentTimeMillis()), grandTotal, "Paid", paymentMethod, restaurantId);
                OrderDAOImpl orderDAO = new OrderDAOImpl();
                int dbOrderId = orderDAO.addOrder(dbOrder);

                if (dbOrderId > 0) {
                    order.put("orderId", "#FH-" + dbOrderId);
                    OrderItemDAOImpl orderItemDAO = new OrderItemDAOImpl();
                    for (CartItem item : cart.getItems().values()) {
                        OrderItem dbItem = new OrderItem(dbOrderId, 0, item.getQuantity(), item.getTotalPrice(), item.getMenuId());
                        orderItemDAO.addOrderItem(dbItem);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Store in session order history
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> orderHistory = (List<Map<String, Object>>) session.getAttribute("orderHistory");
        if (orderHistory == null) {
            orderHistory = new ArrayList<>();
        }
        orderHistory.add(0, order); // newest first
        session.setAttribute("orderHistory", orderHistory);

        // Store current order for confirmation page
        session.setAttribute("lastOrder", order);

        // Clear cart
        session.removeAttribute("cart");
        session.removeAttribute("restaurantId");

        // Forward to confirmation page
        resp.sendRedirect("orderConfirmation.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
