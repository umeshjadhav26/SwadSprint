<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
String loginError = (String) request.getAttribute("loginError");
boolean loginBlocked = Boolean.TRUE.equals(request.getAttribute("loginBlocked"));
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Log in to your SwadSprint account to order delicious meals delivered fast to your doorstep.">
    <title>SwadSprint | Log In</title>

    <!-- Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Barlow+Condensed:wght@400;500;700&family=Inter:wght@300;400;500;600;700&display=swap" rel="stylesheet">

    <!-- Icons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">

    <!-- Styles: shared first, then page-specific -->
    <link rel="stylesheet" href="css/logInPage.css">
    <link rel="stylesheet" href="css/premium-system.css">
    <link rel="icon" href="images/logo.jpg"/>
</head>
<body>

    <!-- ========== Sticky Navigation ========== -->
    <nav class="nav-bar">
        <div class="nav-container">
            <a href="index.html" class="nav-logo"><span class="brand-swad">Swad</span><span class="brand-sprint">Sprint</span></a>

            <div class="nav-links">
                <a href="index.html" class="nav-link">
                    <i class="fa-solid fa-house"></i>
                    <span>Home</span>
                </a>
            </div>

            <div class="nav-actions">
                <a href="registerForm.html" class="btn-primary btn-sm">Sign Up</a>
            </div>
        </div>
    </nav>

    <main class="auth-layout">
        <section class="auth-showcase auth-menu-showcase" aria-label="Popular SwadSprint picks">
            <div class="auth-copy">
                <span class="auth-kicker">POPULAR NEAR YOU</span>
                <h1>Fresh picks waiting for you.</h1>
                <p>Sign in to continue your meal, reorder favourites, and explore kitchens around you.</p>
            </div>
            <div class="auth-food-list" aria-hidden="true">
                <article class="auth-food-card">
                    <img src="images/menu/biryani.jpg" alt="">
                    <div><span>Chef's pick</span><strong>Royal Biryani</strong></div>
                </article>
                <article class="auth-food-card">
                    <img src="images/menu/burger.jpg" alt="">
                    <div><span>Popular</span><strong>Signature Burger</strong></div>
                </article>
                <article class="auth-food-card">
                    <img src="images/menu/pasta.jpg" alt="">
                    <div><span>Fresh today</span><strong>Truffle Pasta</strong></div>
                </article>
            </div>
        </section>

        <section class="auth-form-panel login-container">
        <div class="card">
            <!-- Brand Header -->
            <div class="brand-header">
                <div class="logo-box">
                    <i class="fa-solid fa-utensils"></i>
                </div>
                <h1 id="brand-title"><span class="brand-swad">Swad</span><span class="brand-sprint">Sprint</span></h1>
                <p class="brand-tagline">Welcome back! Log in to continue</p>
            </div>

            <% if (loginError != null) { %>
                <div class="auth-alert <%= loginBlocked ? "auth-alert-blocked" : "" %>" role="alert">
                    <i class="fa-solid <%= loginBlocked ? "fa-lock" : "fa-circle-exclamation" %>"></i>
                    <span><%= loginError %></span>
                </div>
            <% } %>

            <!-- Login Form -->
            <form class="login-form" action="loginpage" method="POST">
                <!-- Email Field -->
                <div class="form-group">
                    <label for="user-email" class="form-label">
                        <i class="fa-solid fa-envelope"></i>
                        Email Address
                    </label>
                    <input
                        type="email"
                        id="user-email"
                        name="email"
                        class="form-input"
                        placeholder="Enter your email"
                        required
                        autocomplete="email"
                    >
                </div>

                <!-- Password Field -->
                <div class="form-group">
                    <label for="user-password" class="form-label">
                        <i class="fa-solid fa-lock"></i>
                        Password
                    </label>
                    <div class="password-wrapper">
                        <input
                            type="password"
                            id="user-password"
                            name="password"
                            class="form-input"
                            placeholder="Enter your password"
                            required
                            minlength="6"
                            autocomplete="current-password"
                        >
                        <!-- <button type="button" class="toggle-password" aria-label="Toggle password visibility">
                            <i class="fa-solid fa-eye"></i>
                        </button> -->
                    </div>
                </div>

                <!-- Remember Me & Forgot Password -->
                <div class="form-options">
                    <label class="remember-me" for="remember-check">
                        <input type="checkbox" id="remember-check" name="remember">
                        <span class="custom-checkbox"></span>
                        Remember me
                    </label>
                    <a href="#" class="forgot-link">Forgot Password?</a>
                </div>

                <!-- Submit Button -->
                <button type="submit" class="btn-primary btn-full" <%= loginBlocked ? "disabled" : "" %>>
                    Log In
                    <i class="fa-solid fa-arrow-right"></i>
                </button>

                <!-- Divider -->
                <div class="divider">
                    <span class="divider-line"></span>
                    <span class="divider-text">or</span>
                    <span class="divider-line"></span>
                </div>

                <!-- Social Login -->
                <div class="social-login">
                    <button type="button" class="social-btn google-btn">
                        <i class="fa-brands fa-google"></i>
                        Google
                    </button>
                    <button type="button" class="social-btn facebook-btn">
                        <i class="fa-brands fa-facebook-f"></i>
                        Facebook
                    </button>
                </div>

                <!-- Sign Up Link -->
                <p class="signup-link">
                    Don't have an account? <a href="registerForm.html">Sign Up</a>
                </p>
            </form>
        </div>
        </section>
    </main>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.12.5/gsap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.12.5/ScrollTrigger.min.js"></script>
    <script src="js/premium-ui.js"></script>
</body>
</html>
