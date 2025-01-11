package com.dangvis.amazing.payment;

import java.util.UUID;

import com.dangvis.amazing.partner.Buyer;

public class Payment {

	private String buyerID;
	private UUID shoppingCartId;
	private double amount;
	private String paymentMethod;
	
	public Payment(String buyerID, UUID shoppingCartId) {
		this.setBuyerID(buyerID);
		this.setShoppingCardId(shoppingCartId);
	}
	
	public String getBuyerID() {
		return buyerID;
	}
	private void setBuyerID(String buyerID) {
		this.buyerID = buyerID;
	}
	public UUID getShoppingCardId() {
		return shoppingCartId;
	}
	private void setShoppingCardId(UUID shoppingCardId) {
		this.shoppingCartId = shoppingCardId;
	}
	
	
}
