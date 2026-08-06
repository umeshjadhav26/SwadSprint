package com.tap.model;

public class OrderItem {
	
	private int orderId;
	private int orderItemId;
	private int quantity;
	private float itemTotal;
	private int menuId;
	
	
	public OrderItem() {
		
		
	}
	
	public OrderItem(int orderId, int orderItemId, int quantity, float itemTotal, int menuId) {
		super();
		this.orderId = orderId;
		this.orderItemId = orderItemId;
		this.quantity = quantity;
		this.itemTotal = itemTotal;
		this.menuId = menuId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getItemTotal() {
		return itemTotal;
	}

	public void setItemTotal(float itemTotal) {
		this.itemTotal = itemTotal;
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	@Override
	public String toString() {
		return "OrderItem [orderId=" + orderId + ", orderItemId=" + orderItemId + ", quantity=" + quantity
				+ ", itemTotal=" + itemTotal + ", menuId=" + menuId + "]";
	}

	
	
}
