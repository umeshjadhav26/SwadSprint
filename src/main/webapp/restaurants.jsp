<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.List, com.tap.model.Restaurant"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description"
	content="Browse restaurants on FoodHub - Discover top-rated local dining options and order online.">
<title>FoodHub | Restaurants</title>
<!-- Google Fonts Outfit -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Outfit:wght@300;400;500;600;700;800&display=swap"
	rel="stylesheet">
<!-- Font Awesome for Icons -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
<!-- GSAP for advanced animations -->

<link rel="stylesheet" href="css/restaurants.css">
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
					</div> <span class="logo-text">FoodHub</span>
				</a>
			</div>
			<nav class="nav-menu">
				<!-- <a href="index.html" class="nav-item"><i class="fa-solid fa-house"></i> Home</a> --> 
				<!-- <a href="RestaurantServlet" class="nav-item nav-active"><i class="fa-solid fa-utensils"></i> Restaurants</a> --> 
				<a href="cart.jsp" class="nav-item"><i class="fa-solid fa-cart-shopping"></i> Cart</a> 
				<a href="OrderHistoryServlet" class="nav-item"><i class="fa-solid fa-clock-rotate-left"></i> Orders</a> 
				<a href="userProfile.jsp" class="nav-item"><i class="fa-regular fa-user"></i> Profile</a>
			</nav>
			<div class="nav-search" role="search">
				<i class="fa-solid fa-magnifying-glass" aria-hidden="true"></i>
				<input type="search" class="search-input" aria-label="Search restaurants" placeholder="Search kitchens or cuisines">
			</div>
		</header>

		<!-- Page Title Section -->
	 <section class="page-title-section">
			<span class="page-badge">⚡ predictive dispatch active</span>
			<h1 id="page-title">Explore Gastronomy</h1>
			<p class="page-subtitle">Instantly order from top-rated kitchen hubs prepared for immediate route scheduling</p>
		</section> 

		<!-- Search & Filter Bar -->  
		<section class="search-bar-container" aria-label="Restaurant filters">
			<div class="filter-pills">
				<button class="filter-pill filter-active">All Hubs</button>
				<button class="filter-pill">🍕 Italian</button>
				<button class="filter-pill">🍛 Indian</button>
				<button class="filter-pill">🍣 Japanese</button>
				<button class="filter-pill">🍔 American</button>
				<button class="filter-pill">🥗 Healthy</button>
			</div>
		</section>   

		<!-- Restaurant Cards Grid -->
		<section class="restaurants-grid">

			<%
			List<Restaurant> Allrestaurants = (List<Restaurant>) request.getAttribute("Allrestaurants");

			for (Restaurant restaurant : Allrestaurants) {
			%>

			<a href="MenuServlet?restaurantId=<%=restaurant.getRestaurantId()%>">
				<div class="restaurant-card">
					<div class="card-image">
						<img src="<%=restaurant.getImagePath()%>" alt="<%=restaurant.getName()%>" class="restaurant-image"> 
						<span class="card-favorite" aria-hidden="true"><i class="fa-regular fa-heart"></i></span>
					</div>
					<div class="card-body">
						<span class="card-badge restaurant-status <%=restaurant.getIsActive() ? "badge-open" : "badge-closed"%>">
							<%=restaurant.getIsActive() ? "Open" : "Closed"%>
						</span>
						<div class="card-header-row">
							<h3 class="card-name"><%=restaurant.getName()%></h3>
							<span class="card-rating"><i
								class="fa-solid fa-star star-icon"></i> <%=restaurant.getRating()%></span>
						</div>
						<p class="card-cuisine"><%=restaurant.getCuisineType()%></p>
						<div class="card-footer">
							<span class="card-delivery"><i
								class="fa-regular fa-clock clock-icon"></i> <%=restaurant.getDeliveryTime()%>
								mins</span> <span class="card-order-btn"> View Menu <i
								class="fa-solid fa-arrow-right"></i>
							</span>
						</div>
					</div>
				</div>
			</a> <%  
}
%>
			
		</section>

		<!-- Footer -->
		<footer class="page-footer">
			<p>&copy; 2026 FoodHub Inc. All rights reserved. Designed for the
				future of taste.</p>
		</footer>

	</div>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.12.5/gsap.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.12.5/ScrollTrigger.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/three.js/r128/three.min.js"></script>
	<script src="js/premium-ui.js"></script>
</body>
</html>
