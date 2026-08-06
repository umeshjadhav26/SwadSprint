package com.tap.utility;

import java.util.*;
import com.tap.DAOImpl.MenuDAOImpl;
import com.tap.DAOImpl.OrderDAOImpl;
import com.tap.DAOImpl.OrderItemDAOImpl;
import com.tap.DAOImpl.RestaurantDAOImpl;
import com.tap.model.Menu;
import com.tap.model.Order;
import com.tap.model.OrderItem;
import com.tap.model.Restaurant;
import com.tap.model.User;

public class OrderHistoryLoader {

    public static List<Map<String, Object>> loadOrderHistory(User user) {
        List<Map<String, Object>> orderHistoryList = new ArrayList<>();
        if (user == null) return orderHistoryList;

        try {
            OrderDAOImpl orderDAO = new OrderDAOImpl();
            OrderItemDAOImpl orderItemDAO = new OrderItemDAOImpl();
            RestaurantDAOImpl restaurantDAO = new RestaurantDAOImpl();
            MenuDAOImpl menuDAO = new MenuDAOImpl();

            List<Order> dbOrders = orderDAO.getOrdersByUserId(user.getUserId());
            if (dbOrders != null) {
                // newest first
                Collections.reverse(dbOrders);
                for (Order dbOrder : dbOrders) {
                    Map<String, Object> orderMap = new LinkedHashMap<>();
                    orderMap.put("orderId", "#FH-" + dbOrder.getOrderId());
                    orderMap.put("date", dbOrder.getOrderDate());

                    Restaurant restaurant = restaurantDAO.getRestaurant(dbOrder.getRestaurantId());
                    orderMap.put("restaurantName", restaurant != null ? restaurant.getName() : "Restaurant");
                    orderMap.put("paymentMethod", dbOrder.getPaymentMethod());
                    orderMap.put("status", dbOrder.getStatus() != null ? dbOrder.getStatus() : "Paid");
                    orderMap.put("grandTotal", dbOrder.getTotalAmount());

                    List<OrderItem> dbItems = orderItemDAO.getOrderItemsByOrderId(dbOrder.getOrderId());
                    List<Map<String, Object>> itemsList = new ArrayList<>();
                    if (dbItems != null) {
                        for (OrderItem dbItem : dbItems) {
                            Map<String, Object> itemMap = new LinkedHashMap<>();
                            Menu menu = menuDAO.getMenu(dbItem.getMenuId());
                            itemMap.put("name", menu != null ? menu.getItemName() : "Item");
                            itemMap.put("quantity", dbItem.getQuantity());
                            float itemTotal = dbItem.getItemTotal();
                            itemMap.put("totalPrice", itemTotal);
                            itemMap.put("price", dbItem.getQuantity() > 0 ? (itemTotal / dbItem.getQuantity()) : itemTotal);
                            itemsList.add(itemMap);
                        }
                    }
                    orderMap.put("items", itemsList);
                    orderHistoryList.add(orderMap);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderHistoryList;
    }
}
