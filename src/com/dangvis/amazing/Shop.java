package com.dangvis.amazing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.dangvis.amazing.exception.ProductNotAvailableException;
import com.dangvis.amazing.partner.Buyer;
import com.dangvis.amazing.partner.Seller;
import com.dangvis.amazing.payment.Payment;
import com.dangvis.amazing.product.Product;

/**
 * This is a framework allowing you to create you own shop. The main
 * functionalities: - managing products - managing sellers, buyers - supports
 * payments and invoices.
 */
public class Shop {
	private Map<String, Product> products;
	private Map<String, Seller> sellers;
	private Map<String, Buyer> buyers;
	private Map<String, Collection<String>> shoppingCart;
	private Map<String, Payment> payments;
	
	private Map<String, ShoppingCart> betterShoppingCart;

	public Shop() {
		products = new HashMap<String, Product>();
		sellers = new HashMap<String, Seller>();
		buyers = new HashMap<String, Buyer>();
		shoppingCart = new HashMap<String, Collection<String>>();
		
		betterShoppingCart = new HashMap<String, ShoppingCart>();
		payments = new HashMap<String, Payment>();
	}

	/**
	 * add a product to the shop.
	 * 
	 * @param newProduct: the product you want to add to the shop
	 */
	public void addProduct(Product newProduct) {
		if (this.products.get(newProduct.getId()) == null)
			this.products.put(newProduct.getId(), newProduct);
	}

	public void addSeller(Seller newSeller) {
		if (this.sellers.get(newSeller.getPersonID()) == null)
			this.sellers.put(newSeller.getPersonID(), newSeller);
	}

	public void addBuyer(Buyer newBuyer) {
		if (this.buyers.get(newBuyer.getPersonID()) == null)
			this.buyers.put(newBuyer.getPersonID(), newBuyer);
	}

	public Map<String, Product> getProducts() {
		return products;
	}

	public Map<String, Seller> getSellers() {
		return sellers;
	}

	public Map<String, Buyer> getBuyers() {
		return buyers;
	}

	public Product searchProduct(String productID) {
		return this.products.get(productID);
	}

	public Seller searchSeller(String sellerID) {
		return this.sellers.get(sellerID);
	}

	/**
	 * allows buyer buys a product
	 * 
	 * @param buyerID
	 * @param productId
	 */
	public void buy(String buyerID, String productId) {
		if (isProductAvailable(productId)) {
			Collection<String> productsOfBuyer;
			if (this.shoppingCart.get(buyerID) == null) {
				productsOfBuyer = new ArrayList<String>();
				productsOfBuyer.add(productId);
				this.shoppingCart.put(buyerID, productsOfBuyer);
			} else {
				productsOfBuyer = this.shoppingCart.get(buyerID);
				productsOfBuyer.add(productId);
			}
		} // todo else?
	}
	
	public void buyVersion2(String buyerID, String productId) throws ProductNotAvailableException  {
		buyVersion2(buyerID, productId, 1);
	}

	public void buyVersion2(String buyerID, String productId, int quantity) throws ProductNotAvailableException {
		if (isProductAvailable(productId)) {
			ShoppingCart shoppingCartOfBuyer = this.betterShoppingCart.get(buyerID);
			if (shoppingCartOfBuyer == null) {
				shoppingCartOfBuyer = new ShoppingCart(buyerID);
				shoppingCartOfBuyer.addProduct(productId, quantity);
				this.betterShoppingCart.put(buyerID, shoppingCartOfBuyer);
			} else {
				shoppingCartOfBuyer.addProduct(productId, quantity);
			}
		} else throw new ProductNotAvailableException("product not available: " + productId);

	}

	public Collection<String> getShoppingCart(String buyerID) {
		return this.shoppingCart.get(buyerID);
	}
	
	public ShoppingCart getShoppingCartVersion2(String buyerID) {
		return this.betterShoppingCart.get(buyerID);
	}
	
	public void pay(String buyerID, UUID shoppingCartID) {
		Payment p = new Payment(buyerID, shoppingCartID);
		this.payments.put(buyerID, p);
	}
	
	public boolean isPayedByBuyer(String buyerID) {
		return this.payments.get(buyerID) == null ? false: true ;
	}

	private boolean isProductAvailable(String productId) {
		return this.products.get(productId) == null ? false : true;
	}
}
