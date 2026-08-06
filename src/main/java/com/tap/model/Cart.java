package com.tap.model;

import java.util.HashMap;
import java.util.Map;

public class Cart {
	
	static Map<Integer, CartItem> items;	//<menuId, CartItem>
	
	public Cart() {
		items = new HashMap<Integer, CartItem>();
	}

	public Map<Integer, CartItem> getItems() {
		return items;
	}

	public static void addCartItem(CartItem cartItem) {
		
		int menuId = cartItem.getMenuId();
		
		if(!items.containsKey(menuId)) {
			items.put(menuId, cartItem);
		}
	}
	
	
	public static void updateItem(int menuId, int quantity) {
		
		if(items.containsKey(menuId)) {
			
			if(quantity <= 0) {
				items.remove(menuId);
			}
			else {
				CartItem cartItem = items.get(menuId);
				cartItem.setQuantity(quantity);
			}
		}
		
	}

	public static void removeItem(int menuId) {

		items.remove(menuId);
	}

}
