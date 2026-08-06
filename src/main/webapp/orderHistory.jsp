<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.text.SimpleDateFormat" %>
<%
    List<Map<String, Object>> orderHistory = (List<Map<String, Object>>) session.getAttribute("orderHistory");
    SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy, h:mm a");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="View your order history on SwadSprint - Track past and current orders, reorder your favorites.">
    <title>SwadSprint | Order History</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Outfit:wght@300;400;500;600;700;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/orderHistory.css">
    <link rel="stylesheet" href="css/premium-system.css">
    <link rel="icon" href="images/logo.jpg"/>
</head>
<body>
    <div class="floating-shapes">
        <div class="shape shape-1"></div>
        <div class="shape shape-2"></div>
        <div class="shape shape-3"></div>
        <div class="shape shape-4"></div>
        <div class="shape shape-5"></div>
        <div class="shape shape-6"></div>
    </div>
    <div class="page-container">
        <header class="page-header">
            <div class="brand-logo">
                <a href="homePage.html" class="brand-link">
                    <div class="logo-icon"><span class="logo-emoji">🍔</span></div>
                    <span class="logo-text"><span class="brand-swad">Swad</span><span class="brand-sprint">Sprint</span></span>
                </a>
            </div>
            <nav class="nav-menu">
                <a href="RestaurantServlet" class="nav-item">🍽️ Restaurants</a>
                <a href="cart.jsp" class="nav-item">🛒 Cart</a>
                <a href="OrderHistoryServlet" class="nav-item nav-active">📦 Orders</a>
                <a href="userProfile.jsp" class="nav-item">👤 Profile</a>
            </nav>
        </header>

        <section class="page-title-section">
            <h1 id="page-title">Order History</h1>
            <p class="page-subtitle">Track your past and current orders in one place</p>
        </section>

        <section class="orders-list">
<%
    if(orderHistory == null || orderHistory.isEmpty()) {
%>
            <div style="text-align:center; padding: 40px; color: var(--text-muted);">
                <h2>No Orders Found</h2>
                <p>You haven't placed any orders yet.</p>
                <br>
                <a href="RestaurantServlet" class="action-btn action-btn-primary" style="display:inline-block; padding:12px 24px; background:var(--primary); color:white; border-radius:30px; text-decoration:none; font-weight:600;">Browse Restaurants</a>
            </div>
<%
    } else {
        for(Map<String, Object> order : orderHistory) {
            String dateStr = sdf.format((java.util.Date)order.get("date"));
            String status = (String) order.get("status");
            String statusClass = status.equalsIgnoreCase("Paid") ? "status-paid" : "status-processing";
            
            List<Map<String, Object>> items = (List<Map<String, Object>>) order.get("items");
%>
            <div class="order-card">
                <div class="order-card-header">
                    <div class="order-meta">
                        <span class="order-id"><%= order.get("orderId") %></span>
                        <span class="order-date">🕒 <%= dateStr %></span>
                    </div>
                    <span class="status-badge <%= statusClass %>"><%= status %></span>
                </div>
                <div class="order-card-body">
                    <div class="order-restaurant">
                        <span class="restaurant-emoji">🍽️</span>
                        <div class="restaurant-info">
                            <span class="restaurant-name"><%= order.get("restaurantName") %></span>
                            <span class="restaurant-cuisine">Payment Method: <%= order.get("paymentMethod") %></span>
                        </div>
                    </div>
                    <div class="order-items-detail" style="margin-top: 14px; padding: 14px; background: rgba(255, 107, 22, 0.06); border-radius: 12px; border: 1px solid rgba(255, 107, 22, 0.18);">
                        <h4 style="margin: 0 0 10px 0; font-size: 0.88rem; color: #d9531e; font-weight: 700; text-transform: uppercase; letter-spacing: 0.5px;">ITEMS ORDERED:</h4>
                        <ul style="list-style: none; padding: 0; margin: 0; display: flex; flex-direction: column; gap: 8px;">
                        <%
                            if (items != null && !items.isEmpty()) {
                                for (Map<String, Object> item : items) {
                        %>
                            <li style="display: flex; justify-content: space-between; align-items: center; font-size: 0.95rem; padding-bottom: 6px; border-bottom: 1px dashed rgba(0,0,0,0.1);">
                                <span style="color: #111111; font-weight: 600;"><i class="fa-solid fa-utensils" style="color: #ff5a36; margin-right: 6px; font-size: 0.8rem;"></i> <%= item.get("name") %> <strong style="color: #ff5a36; margin-left: 4px;">&times; <%= item.get("quantity") %></strong></span>
                                <span style="font-weight: 700; color: #d97706;">₹<%= item.get("totalPrice") %></span>
                            </li>
                        <%
                                }
                            }
                        %>
                        </ul>
                    </div>
                </div>
                <div class="order-card-footer">
                    <div class="order-footer-left">
                        <span class="order-total">Total: ₹ <%= order.get("grandTotal") %></span>
                    </div>
                    <div class="order-actions">
                        <a href="RestaurantServlet" class="view-details-btn">Reorder</a>
                    </div>
                </div>
            </div>
<%
        }
    }
%>
        </section>

        <footer class="page-footer">
            <p>&copy; 2026 SwadSprint Inc. All rights reserved. Made with ❤️ for food lovers.</p>
        </footer>
    </div>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.12.5/gsap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.12.5/ScrollTrigger.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/three.js/r128/three.min.js"></script>
    <script src="js/premium-ui.js"></script>
</body>
</html>
