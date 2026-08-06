<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.text.SimpleDateFormat" %>
<%
    Map<String, Object> order = (Map<String, Object>) session.getAttribute("lastOrder");
    if (order == null) {
        response.sendRedirect("index.html");
        return;
    }
    SimpleDateFormat sdf = new SimpleDateFormat("MMMM d, yyyy");
    String formattedDate = sdf.format((java.util.Date) order.get("date"));
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Your order has been confirmed on SwadSprint - Track your delivery in real time.">
    <title>SwadSprint | Order Confirmed</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Outfit:wght@300;400;500;600;700;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/orderConfirmation.css">
    <link rel="stylesheet" href="css/premium-system.css">
    <link rel="icon" href="images/logo.jpg"/>
</head>
<body>
    <!-- Floating decorative elements -->
    <div class="floating-shapes">
        <div class="shape shape-1"></div>
        <div class="shape shape-2"></div>
        <div class="shape shape-3"></div>
        <div class="shape shape-4"></div>
        <div class="shape shape-5"></div>
        <div class="shape shape-6"></div>
    </div>

    <!-- Main Container -->
    <div class="page-container">

        <!-- Header / Navigation Bar -->
        <header class="page-header">
            <div class="brand-logo">
                <a href="homePage.html" class="brand-link">
                    <div class="logo-icon">
                        <span class="logo-emoji">🍔</span>
                    </div>
                    <span class="logo-text"><span class="brand-swad">Swad</span><span class="brand-sprint">Sprint</span></span>
                </a>
            </div>
            <nav class="nav-menu">
                <a href="RestaurantServlet" class="nav-item">🍽️ Restaurants</a>
                <a href="cart.jsp" class="nav-item">🛒 Cart</a>
                <a href="OrderHistoryServlet" class="nav-item">📦 Orders</a>
                <a href="userProfile.jsp" class="nav-item">👤 Profile</a>
            </nav>
        </header>

        <!-- Confirmation Card -->
        <section class="confirmation-container">
            <div class="confirmation-card">

                <!-- Success Icon -->
                <div class="success-icon">
                    <span class="success-emoji">🎉</span>
                </div>

                <h1 class="confirmation-heading">Order Placed Successfully!</h1>
                <p class="confirmation-subtitle">Thank you for your order. Your food is being prepared!</p>

                <!-- Order Details -->
                <div class="order-details">
                    <div class="detail-row">
                        <span class="detail-label">Order ID</span>
                        <span class="detail-value"><%= order.get("orderId") %></span>
                    </div>
                    <div class="detail-row">
                        <span class="detail-label">Date</span>
                        <span class="detail-value"><%= formattedDate %></span>
                    </div>
                    <div class="detail-row">
                        <span class="detail-label">Payment Method</span>
                        <span class="detail-value"><%= order.get("paymentMethod") %></span>
                    </div>
                    <div class="detail-row">
                        <span class="detail-label">Status</span>
                        <span class="detail-value"><span class="status-badge status-paid">✅ <%= order.get("status") %></span></span>
                    </div>
                </div>

                <!-- Order Items Summary -->
                <div class="order-items-list">
                    <h3 class="items-list-title">Order Items</h3>
                    <%
                        List<Map<String, Object>> items = (List<Map<String, Object>>) order.get("items");
                        for(Map<String, Object> item : items) {
                    %>
                    <div class="order-item-row">
                        <span class="order-item-name"><%= item.get("name") %> &times; <%= item.get("quantity") %></span>
                        <span class="order-item-price">₹<%= item.get("totalPrice") %></span>
                    </div>
                    <%
                        }
                    %>
                </div>

                <!-- Total -->
                <div class="order-total">
                    <span class="order-total-label">Total Paid</span>
                    <span class="order-total-value">₹<%= order.get("grandTotal") %></span>
                </div>

                <!-- Estimated Delivery -->
                <div class="estimated-delivery">
                    <span class="delivery-icon">🚴</span>
                    <div class="delivery-info">
                        <span class="delivery-label">Estimated Delivery</span>
                        <span class="delivery-time">25 – 30 minutes</span>
                    </div>
                </div>

                <!-- Action Buttons -->
                <div class="confirmation-actions">
                    <a href="OrderHistoryServlet" class="action-btn action-btn-primary">📦 Track Order</a>
                    <a href="homePage.html" class="action-btn action-btn-ghost">🏠 Back to Home</a>
                </div>

            </div>
        </section>

        <!-- Footer -->
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
