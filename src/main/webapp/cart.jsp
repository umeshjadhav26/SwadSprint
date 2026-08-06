<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import = "com.tap.model.*" %>

    
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Your shopping cart on SwadSprint - Review items, apply promo codes, and place your order.">
    <title>SwadSprint | Cart</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Outfit:wght@300;400;500;600;700;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/cart.css">
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
                <a href="cart.jsp" class="nav-item nav-active">🛒 Cart</a>
                <a href="OrderHistoryServlet" class="nav-item">📦 Orders</a>
                <a href="userProfile.jsp" class="nav-item">👤 Profile</a>
            </nav>
        </header>

        <!-- Page Title Section -->
        <section class="page-title-section">
            <h1 id="page-title">Your Cart</h1>
            <p class="page-subtitle">Review your items and place your order</p>
        </section>



                
           <%
                	Cart cart = (Cart)session.getAttribute("cart");
               		
            		float subTotal = 0.0f;
            		int gst = 18;
            		int deliveryFee = 30;
            		float grandTotal = 0.0f;
                		
            		if(cart != null && !cart.getItems().isEmpty()){
            			
            	%>
            	
            	     <!-- Cart Content: Two-Column Layout -->
		        <div class="cart-wrapper">
		
		            <!-- LEFT: Cart Items List -->
		            <section class="cart-items">
            <% 			
	                	int restaurantId = (Integer)session.getAttribute("restaurantId");
	                	String restaurantName = (String)session.getAttribute("restaurantName");
	                	
	                		for(CartItem item : cart.getItems().values()){
	                				
	                			subTotal = subTotal + item.getTotalPrice();
             %>
            
                <!-- Cart Item Card -->
                <div class="cart-item-card" data-menu-id="<%= item.getMenuId() %>" data-unit-price="<%= item.getPrice() %>">
                    <div class="cart-item-card-inner">
                        <div class="item-image">
                            <span class="item-emoji">🍕</span>
                        </div>
                        <div class="item-details">
                            <h3 class="item-name"><%= item.getName() %></h3>
                            <p class="item-restaurant"><%= restaurantName %></p>
                        </div>
                        <div class="qty-controls">
                        
                        		<form action="CartServlet" method="post" class="cart-async-form" target="cart-sync-frame">
                        			<input type="hidden" name="menuId" value="<%= item.getMenuId() %>">
                        			<input type="hidden" name="restaurantId" value="<%= restaurantId %>">
                        			<input type="hidden" name="restaurantName" value="<%= restaurantName %>">
                        			<input type="hidden" name="quantity" value="<%= item.getQuantity() - 1%>" class="form-qty-val-minus">
                        			<input type="hidden" name="action" value="update">
                            		<button type="submit" class="qty-btn qty-minus" data-action="minus">−</button>
                            </form>
                            
                            <span class="qty-count"><%= item.getQuantity() %></span>
                            
                            <form action="CartServlet" method="post" class="cart-async-form" target="cart-sync-frame">
                            		<input type="hidden" name="menuId" value="<%= item.getMenuId() %>">
                        			<input type="hidden" name="restaurantId" value="<%= restaurantId %>">
                        			<input type="hidden" name="restaurantName" value="<%= restaurantName %>">
                        			<input type="hidden" name="quantity" value="<%= item.getQuantity() + 1%>" class="form-qty-val-plus">
                        			<input type="hidden" name="action" value="update">
                            		<button type="submit" class="qty-btn qty-plus" data-action="plus">+</button>
                            </form>
                            
                        </div>
                        <div class="item-price">₹<%= item.getTotalPrice() %></div>
                        
                        <form action="CartServlet" method="post" class="cart-async-form" target="cart-sync-frame">
	                        <input type="hidden" name="menuId" value="<%= item.getMenuId() %>">
	                        <input type="hidden" name="restaurantId" value="<%= restaurantId %>">
	                        <input type="hidden" name="action" value="delete">
	                        <button type="submit" class="remove-btn" title="Remove item" data-action="delete">🗑️</button>
                        </form>
                        
                    </div>
                </div>
                
                <% 	
                		}
	                float getGST = (subTotal * gst) / 100.0f;
	                	grandTotal = subTotal + getGST + deliveryFee;
                	%>
                	
            <div class="add-more-container">
			    <a href="MenuServlet?restaurantId=<%= restaurantId %>" class="add-more-btn">
			        🍽️ Add More Items
			    </a>
			</div>

            </section>
            
            
			<!-- RIGHT: Order Summary -->
              <aside class="summary-card"> 
                <h2 class="summary-title">Order Summary</h2>

                <div class="summary-items-list" style="margin-bottom: 18px; padding-bottom: 14px; border-bottom: 1px dashed var(--clr-border);">
                    <h3 style="font-size: 0.95rem; font-weight: 700; color: var(--clr-text-secondary); margin-bottom: 10px;">Ordered Items:</h3>
                    <% for(CartItem cartItem : cart.getItems().values()){ %>
                        <div class="summary-item-row" data-summary-id="<%= cartItem.getMenuId() %>" style="display: flex; justify-content: space-between; align-items: center; font-size: 0.92rem; margin-bottom: 8px; color: var(--clr-text-primary);">
                            <span>🍔 <%= cartItem.getName() %> <strong class="summary-qty-text" style="color: var(--clr-primary); margin-left: 4px;">&times; <%= cartItem.getQuantity() %></strong></span>
                            <span class="summary-item-price" style="font-weight: 700; color: var(--clr-accent);">₹<%= cartItem.getTotalPrice() %></span>
                        </div>
                    <% } %>
                </div>

                <div class="summary-rows">
                    <div class="summary-row">
                        <span class="summary-label">Subtotal </span>
                        <span class="summary-value" id="cart-subtotal">₹<%= subTotal %></span>
                    </div>
                    <div class="summary-row">
                        <span class="summary-label">Delivery Fee</span>
                        <span class="summary-value">₹<%= deliveryFee %></span>
                    </div>
                    <div class="summary-row">
                        <span class="summary-label">Taxes &amp; Charges</span>
                        <span class="summary-value"><%= gst%>%</span>
                    </div>
                </div>

                <div class="summary-total">
                    <span class="summary-total-label">Total</span>
                    <span class="summary-total-value" id="cart-grandtotal">₹<%= grandTotal %></span>
                </div>

                <!-- Payment Method -->
                <div class="payment-section">
                    <label class="payment-label" for="paymentMethod">💳 Payment Method</label>
                    <select id="paymentMethod" class="form-select">
                        <option value="credit">Credit Card</option>
                        <option value="debit">Debit Card</option>
                        <option value="upi" selected>UPI</option>
                        <option value="wallet">Wallet</option>
                        <option value="cod">Cash on Delivery</option>
                    </select>
                </div>

                <!-- Place Order Button -->
                <form action="PlaceOrderServlet" method="post" style="width:100%;">
                    <input type="hidden" name="paymentMethod" id="hiddenPaymentMethod" value="upi">
                    <button type="submit" class="place-order-btn" style="width:100%; border:none; cursor:pointer;">Place Order →</button>
                </form>
            </aside>  
 
        </div>
	        <%  
	            	}
	            %>
	            
	        <!-- Empty Cart State -->
	        <section class="empty-cart" id="empty-cart-section" style="<%= (cart != null && !cart.getItems().isEmpty()) ? "display:none;" : "" %>">
	            <div class="empty-cart-icon">🛒</div>
	            <h2 class="empty-cart-title">Your cart is empty</h2>
	            <p class="empty-cart-text">Looks like you haven't added anything yet. Explore restaurants and find something delicious!</p>
	            <a href="RestaurantServlet" class="empty-cart-btn">🍽️ Browse Restaurants</a>
	        </section>

        <iframe name="cart-sync-frame" class="cart-sync-frame" title="Cart sync" style="display:none; width:0; height:0; border:0;"></iframe>

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
        var paySelect = document.getElementById('paymentMethod');
        var hiddenPay = document.getElementById('hiddenPaymentMethod');
        if (paySelect && hiddenPay) {
            paySelect.addEventListener('change', function() {
                hiddenPay.value = paySelect.value;
            });
        }

        // Live Real-Time Cart Calculations without Page Reload
        document.addEventListener('DOMContentLoaded', function() {
            var deliveryFee = 30;
            var gstPercent = 18;

            function recalculateTotals() {
                var cards = document.querySelectorAll('.cart-item-card');
                if (cards.length === 0) {
                    var cartWrapper = document.querySelector('.cart-wrapper');
                    if (cartWrapper) cartWrapper.style.display = 'none';
                    var emptySection = document.getElementById('empty-cart-section');
                    if (emptySection) emptySection.style.display = 'flex';
                    return;
                }

                var newSubtotal = 0;
                cards.forEach(function(card) {
                    var countEl = card.querySelector('.qty-count');
                    var unitPrice = parseFloat(card.getAttribute('data-unit-price')) || 0;
                    var qty = parseInt(countEl ? countEl.textContent : '0') || 0;
                    newSubtotal += (unitPrice * qty);
                });

                var gstAmount = (newSubtotal * gstPercent) / 100;
                var newGrandTotal = newSubtotal + gstAmount + deliveryFee;

                var subtotalEl = id('cart-subtotal');
                if (subtotalEl) subtotalEl.textContent = '₹' + newSubtotal.toFixed(1);

                var grandtotalEl = id('cart-grandtotal');
                if (grandtotalEl) grandtotalEl.textContent = '₹' + newGrandTotal.toFixed(1);
            }

            function id(str) { return document.getElementById(str); }

            document.querySelectorAll('.cart-item-card').forEach(function(card) {
                var menuId = card.getAttribute('data-menu-id');
                var unitPrice = parseFloat(card.getAttribute('data-unit-price')) || 0;
                var countEl = card.querySelector('.qty-count');
                var itemPriceEl = card.querySelector('.item-price');
                var minusInput = card.querySelector('.form-qty-val-minus');
                var plusInput = card.querySelector('.form-qty-val-plus');
                var summaryRow = document.querySelector('.summary-item-row[data-summary-id="' + menuId + '"]');

                card.querySelectorAll('button[data-action]').forEach(function(btn) {
                    btn.addEventListener('click', function(e) {
                        var action = btn.getAttribute('data-action');
                        var currentQty = parseInt(countEl.textContent) || 0;
                        var nextQty = currentQty;

                        if (action === 'plus') {
                            nextQty = currentQty + 1;
                        } else if (action === 'minus') {
                            nextQty = currentQty - 1;
                        } else if (action === 'delete') {
                            nextQty = 0;
                        }

                        if (nextQty <= 0) {
                            // Remove card and summary row
                            card.style.transition = 'all 0.3s ease';
                            card.style.opacity = '0';
                            card.style.transform = 'scale(0.95)';
                            setTimeout(function() {
                                card.remove();
                                if (summaryRow) summaryRow.remove();
                                recalculateTotals();
                            }, 300);
                        } else {
                            // Update counts & prices
                            countEl.textContent = nextQty;
                            var newItemTotal = unitPrice * nextQty;
                            if (itemPriceEl) itemPriceEl.textContent = '₹' + newItemTotal.toFixed(1);

                            if (minusInput) minusInput.value = nextQty - 1;
                            if (plusInput) plusInput.value = nextQty + 1;

                            if (summaryRow) {
                                var qtyText = summaryRow.querySelector('.summary-qty-text');
                                var priceText = summaryRow.querySelector('.summary-item-price');
                                if (qtyText) qtyText.innerHTML = '&times; ' + nextQty;
                                if (priceText) priceText.textContent = '₹' + newItemTotal.toFixed(1);
                            }
                            recalculateTotals();
                        }
                    });
                });
            });
        });
    </script>
</body>
</html>
    