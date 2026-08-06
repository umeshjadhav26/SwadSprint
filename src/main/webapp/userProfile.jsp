<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="com.tap.model.User, java.text.SimpleDateFormat" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Manage your SwadSprint profile - Update your details, view stats, and customize your account.">
    <title>SwadSprint | My Profile</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Outfit:wght@300;400;500;600;700;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/userProfile.css">
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
                <!-- <a href="index.html" class="nav-item">🏠 Home</a> -->
                <a href="RestaurantServlet" class="nav-item">🍽️ Restaurants</a>
                <a href="cart.jsp" class="nav-item">🛒 Cart</a>
                <a href="OrderHistoryServlet" class="nav-item">📦 Orders</a>
                <a href="userProfile.jsp" class="nav-item nav-active">👤 Profile</a>
            </nav>
        </header>

        <!-- Page Title Section -->
        <section class="page-title-section">
            <h1 id="page-title">My Profile</h1>
            <p class="page-subtitle">Manage your account details and preferences</p>
        </section>

        <!-- Profile Content — Two Column Layout -->
        <section class="profile-wrapper">

            <!-- LEFT: Profile Overview Card -->
            
            <%
	            User user = (User) session.getAttribute("loggedInUser");
	
	            if(user == null){
	                response.sendRedirect("loginPage.jsp");
	                return;
	            }
            %>
            
			<div class="profile-overview">
			
			    <div class="avatar-circle">
			        <span class="avatar-letter">
			            <%= Character.toUpperCase(user.getUserName().charAt(0)) %>
			        </span>
			    </div>
			
			    <h2 class="profile-name">
			        <%= user.getUserName() %>
			    </h2>
			
			    <p class="profile-email">
			        <%= user.getEmail() %>
			    </p>
			
			    <span class="role-badge">
			        <%= user.getRole() %>
			    </span>
			    
			    <%
    					SimpleDateFormat sdf = new SimpleDateFormat("MMMM yyyy");
				%>
			
			    <div class="member-box">
			        <span class="member-label">Member Since</span>
			        <span class="member-value">
			            <%= sdf.format(user.getCreatedDate()) %>
			        </span>
			    </div>
			
			    <div class="quick-actions">

				    <a href="OrderHistoryServlet" class="quick-action">
				        📦
				        <span>My Orders</span>
				    </a>
				
				    <a href="#" class="quick-action">
				        📍
				        <span>Saved Addresses</span>
				    </a>
				
				    <a href="LogoutServlet" class="quick-action logout">
				        🚪
				        <span>Logout</span>
				    </a>
				
				</div>
			
			</div>

            <!-- RIGHT: Personal Information -->
			<div class="edit-card">
			
			    <div class="card-header">
			        <h2 class="edit-title">👤 Personal Information</h2>
			    </div>
			
			    <form action="updateProfile" method="post" class="edit-form">
			
			        <!-- Name -->
			        <div class="info-row">
			            <label>👤 Full Name</label>
			            <input
			                type="text"
			                id="username"
			                name="name"
			                class="form-input"
			                value="<%=user.getUserName()%>"
			                readonly>
			        </div>
			
			        <!-- Email -->
			        <div class="info-row">
			            <label>📧 Email</label>
			            <input
			                type="email"
			                class="form-input"
			                value="<%=user.getEmail()%>"
			                readonly>
			        </div>
			
			        <!-- Address -->
			        <div class="info-row">
			            <label>📍 Address</label>
			            <textarea
			                id="address"
			                name="address"
			                class="form-input form-textarea"
			                readonly><%=user.getAddress()%></textarea>
			        </div>
			
			        <!-- Role -->
			        <div class="info-row">
			            <label>🎭 Role</label>
			            <input
			                type="text"
			                class="form-input"
			                value="<%=user.getRole()%>"
			                readonly>
			        </div>
			
			        <!-- Password -->
			        <div class="info-row">
			
			            <label>🔒 Password</label>
			
			            <input
			                type="password"
			                id="password"
			                name="password"
			                class="form-input"
			                value="password"
			                readonly>
			
			        </div>
			
			        <!-- Member Since -->
			        <div class="info-row">
			            <label>📅 Member Since</label>
			            <input
			                type="text"
			                class="form-input"
			                value="<%= sdf.format(user.getCreatedDate()) %>"
			                readonly>
			        </div>
			        
			        <div class="profile-btn-wrapper">

					    <button
					        type="button"
					        id="editBtn"
					        class="save-btn"
					        onclick="enableEdit()">
					
					        ✏️ Edit Profile
					
					    </button>
					
					</div>
			
			        <div class="form-actions" id="actionButtons" style="display:none;">
			
			            <button class="save-btn" type="submit">
			                💾 Save Changes
			            </button>
			
			            <button
			                type="button"
			                class="delete-btn"
			                onclick="cancelEdit()">
			
			                Cancel
			
			            </button>
			
			        </div>
			
			    </form>
			
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
    
    <script>

		let originalName = "";
		let originalAddress = "";
		
		function enableEdit() {
		
		    originalName = document.getElementById("username").value;
		    originalAddress = document.getElementById("address").value;
		
		    document.getElementById("username").removeAttribute("readonly");
		    document.getElementById("address").removeAttribute("readonly");
		    document.getElementById("password").removeAttribute("readonly");
		
		    document.getElementById("password").value = "";
		
		    document.getElementById("actionButtons").style.display = "flex";
		
		    document.getElementById("editBtn").style.display = "none";
		}
		
		function cancelEdit() {
		
		    document.getElementById("username").value = originalName;
		    document.getElementById("address").value = originalAddress;
		
		    document.getElementById("username").setAttribute("readonly", true);
		    document.getElementById("address").setAttribute("readonly", true);
		    document.getElementById("password").setAttribute("readonly", true);
		
		    document.getElementById("password").value = "password";
		
		    document.getElementById("actionButtons").style.display = "none";
		
		    document.getElementById("editBtn").style.display = "inline-flex";
		}

</script>
</body>
</html>
