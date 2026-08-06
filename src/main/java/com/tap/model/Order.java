package com.tap.model;

import java.sql.Timestamp;

public class Order {
	
	private int userId;
	private int orderId;
	private Timestamp orderDate;
	private float totalAmount;
	private String status;
	private String paymentMethod;
	private int restaurantId;
	
	
	public Order() {
		
		
	}
	
	public Order(int userId, int orderId, Timestamp orderDate, float totalAmount, String status,
			String paymentMethod, int restaurantId) {
		super();
		this.userId = userId;
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.totalAmount = totalAmount;
		this.status = status;
		this.paymentMethod = paymentMethod;
		this.restaurantId = restaurantId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public Timestamp getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}

	public float getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	@Override
	public String toString() {
		return "Ordertable [userId=" + userId + ", orderId=" + orderId + ", orderDate=" + orderDate + ", totalAmount="
				+ totalAmount + ", status=" + status + ", paymentMethod=" + paymentMethod + ", restaurantId="
				+ restaurantId + "]";
	}
	
	

}
