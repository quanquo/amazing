package com.dangvis.amazing;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ShoppingCart {
	private UUID shoppingCartId;
	private String buyer;
	private Map<String, Integer> products;

	public ShoppingCart(String buyerId) {
		shoppingCartId = UUID.randomUUID();
		this.buyer = buyerId;
		this.products = new HashMap<String, Integer>();
	}
	
	public void addProduct(String productId) {
		this.addProduct(productId, 1);
	}
	
	public void addProduct(String productId, int quantity) {
		Integer quantityOfProduct = this.products.get(productId);
		if (quantityOfProduct == null)
			this.products.put(productId, quantity);
		else {
			this.products.remove(productId);
			this.products.put(productId, Integer.valueOf(quantity + quantityOfProduct.intValue() ) );
		}
	}
	
	public Map<String, Integer> getProducts() {
		return this.products;
	}
	
	public UUID getShoppingCartId()  {
		return this.shoppingCartId;
	}
	
	public String getBuyer()  {
		return this.buyer;
	}
}
