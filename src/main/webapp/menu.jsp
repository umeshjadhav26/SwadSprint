<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.List, com.tap.model.Menu, com.tap.model.Restaurant, com.tap.model.Cart, com.tap.model.CartItem" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Browse restaurant menus on SwadSprint. Add delicious items to your cart and check out instantly.">
    <title>SwadSprint | Restaurant Menu</title>
    <!-- Google Fonts Outfit -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Outfit:wght@300;400;500;600;700;800&display=swap" rel="stylesheet">
    <!-- Font Awesome for Icons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    <!-- GSAP for advanced animations -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.12.5/gsap.min.js"></script>
    <link rel="stylesheet" href="css/menu.css">
    <link rel="stylesheet" href="css/premium-system.css">
    <link rel="icon" href="images/logo.jpg"/>
</head>
<body>
    <!-- Shifting Ambient Glow Background -->
    <div class="ambient-glow">
        <div class="glow-sphere glow-sky"></div>
        <div class="glow-sphere glow-cyan"></div>
    </div>

    <!-- Space Grid Lines -->
    <div class="space-grid"></div>

    <!-- Main Container -->
    <div class="page-container">

        <!-- Header / Navigation Bar -->
        <header class="page-header">
            <div class="brand-logo">
                <a href="homePage.html" class="brand-link">
                    <div class="logo-icon-glow">
                        <span class="logo-emoji-spin">🍔</span>
                    </div>
                    <span class="logo-text"><span class="brand-swad">Swad</span><span class="brand-sprint">Sprint</span></span>
                </a>
            </div>
            <nav class="nav-menu">
                <!-- <a href="index.html" class="nav-item"><i class="fa-solid fa-house"></i> Home</a> -->
                <a href="RestaurantServlet" class="nav-item"><i class="fa-solid fa-utensils"></i> Restaurants</a>
                <a href="cart.jsp" class="nav-item"><i class="fa-solid fa-cart-shopping"></i> Cart</a>
                <a href="OrderHistoryServlet" class="nav-item"><i class="fa-solid fa-clock-rotate-left"></i> Orders</a>
                <a href="userProfile.jsp" class="nav-item"><i class="fa-regular fa-user"></i> Profile</a>
            </nav>
        </header>

        <!-- Back Link -->
        <div class="back-link-row">
            <a href="RestaurantServlet" class="back-link"><i class="fa-solid fa-arrow-left"></i> Back to Restaurants</a>
        </div>

        <!-- Restaurant Info Banner (Glassmorphic) -->
   <% 
        Restaurant restaurant = (Restaurant)request.getAttribute("restaurant");
   	%>
        <section class="restaurant-banner">
            <div class="menu-item-image">
                    <img src="<%= restaurant.getImagePath() %>" alt="<%= restaurant.getName() %>">
             </div>
            <div class="banner-info">
                <h1 id="restaurant-name"><%= restaurant.getName() %></h1>
                <p class="banner-cuisine"><%=restaurant.getCuisineType() %></p>
                <div class="banner-meta">
                    <span class="meta-item"><i class="fa-solid fa-star star-icon"></i><%=restaurant.getRating() %></span>
                    <span class="meta-divider">|</span>
                    <span class="meta-item"><i class="fa-regular fa-clock clock-icon"></i><%=restaurant.getDeliveryTime() %></span>
                    <span class="meta-divider">|</span>
                    <span class="card-badge <%=restaurant.getIsActive() ? "badge-open" : "badge-closed"%>">
						<%=restaurant.getIsActive() ? "Open" : "Closed"%>
					</span>
                </div>
            </div>
        </section>

        <!-- Menu Items List -->
        <section class="menu-grid menu-list" aria-label="Restaurant menu items">
        
        <%
        		Cart cart = (Cart) session.getAttribute("cart");
        		List<Menu> allMenusByRestaurant = (List<Menu>)request.getAttribute("allMenusByRestaurant");
        		for(Menu menu : allMenusByRestaurant){
        			int quantityInCart = 0;
        			boolean isItemInCart = false;
        			if (cart != null && cart.getItems() != null && cart.getItems().containsKey(menu.getMenuId())) {
        				CartItem cartItem = cart.getItems().get(menu.getMenuId());
        				quantityInCart = cartItem.getQuantity();
        				if (quantityInCart > 0) {
        					isItemInCart = true;
        				}
        			}
        
        %>
            <!-- Menu Item Card -->
            <div class="menu-item-card menu-list-item">
                <div class="menu-item-body">
                    <div class="item-header">
                        <span class="veg-mark" aria-label="Vegetarian item"></span>
                        <h3 class="item-name"><%= menu.getItemName()%></h3>
                    </div>
                    <p class="item-description"><%= menu.getDescription()%></p>
                    <div class="item-footer">
                        <span class="item-price">₹<%= menu.getPrice()%></span>
                    </div>
                </div>
                <div class="menu-item-media">
                    <img src="<%= menu.getImagePath() %>" alt="<%= menu.getItemName() %>" loading="lazy">
                    <form action="CartServlet" method="post" class="menu-add-form <%= isItemInCart ? "is-added" : "" %>" target="cart-sync-frame">
                        <input type="hidden" name="menuId" value="<%= menu.getMenuId()%>">
                        <input type="hidden" name="restaurantId" value="<%= menu.getRestaurantId()%>">
                        <input type="hidden" name="restaurantName" value="<%= restaurant.getName()%>">
                        <input type="hidden" name="quantity" value="<%= isItemInCart ? quantityInCart : 1 %>" class="cart-quantity-input">
                        <input type="hidden" name="action" value="<%= isItemInCart ? "update" : "add" %>" class="cart-action-input">
                        <button type="button" class="add-to-cart-btn menu-add-btn" data-cart-add data-item-name="<%= menu.getItemName()%>">ADD</button>
                        <div class="menu-qty-stepper" aria-label="Item quantity controls">
                            <button type="button" class="menu-qty-btn" data-cart-minus aria-label="Decrease quantity">-</button>
                            <span class="menu-qty-count"><%= isItemInCart ? quantityInCart : 1 %></span>
                            <button type="button" class="menu-qty-btn" data-cart-plus aria-label="Increase quantity">+</button>
                        </div>
                    </form>
                </div>
            </div>
            
        <% } %>

        </section>

        <!-- Footer -->
        <footer class="page-footer">
            <p>&copy; 2026 SwadSprint Inc. All rights reserved. Designed for the future of taste.</p>
        </footer>

    </div>

    <!-- Floating View Cart Bar (Direct child of body for 100% fixed viewport screen positioning) -->
    <aside class="menu-cart-bar" aria-live="polite">
        <strong><span id="menu-cart-count">0</span> <span id="menu-cart-label">item added</span></strong>
        <button type="button" class="menu-view-cart" id="menu-view-cart">View Cart <i class="fa-solid fa-bag-shopping" aria-hidden="true"></i></button>
    </aside>
    <iframe name="cart-sync-frame" class="cart-sync-frame" title="Cart sync"></iframe>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.12.5/ScrollTrigger.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/three.js/r128/three.min.js"></script>
    <script src="js/premium-ui.js"></script>
</body>
</html>
