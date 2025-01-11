package com.dangvis.amazing;

import java.util.Map;

import com.dangvis.amazing.exception.ProductNotAvailableException;
import com.dangvis.amazing.partner.Buyer;
import com.dangvis.amazing.product.Book;

public class AmazinApp {
	public static final String TUNG_ID = "td01";
	public static final String NHI_ID = "ND98";
	
	public static void main(String[] args) {
		Shop myAmazingShop = new Shop();
		
		myAmazingShop.addProduct(new Book("978", "Physik Lehrbuch"));
		myAmazingShop.addProduct(new Book("313", "Kurzlehrbuch Medizinische Psychologie"));
		myAmazingShop.addProduct(new Book("366", "MuskelRevolution: Konzepte und Rezepte"));
		
		Buyer tung = new Buyer(TUNG_ID, "Dang");
		myAmazingShop.addBuyer(tung);
		Buyer nhi = new Buyer(NHI_ID, "Do");
		myAmazingShop.addBuyer(nhi);
		
		myAmazingShop.buyVersion2(TUNG_ID, "313");
		ShoppingCart tungShoppingCart = myAmazingShop.getShoppingCartVersion2(TUNG_ID);
		printShoppingCart(tungShoppingCart);
		
		try {
			myAmazingShop.buyVersion2(TUNG_ID, "3134");	
		} catch (ProductNotAvailableException ex) {
			System.out.println(ex.getMessage());
		}
		
		myAmazingShop.buyVersion2(TUNG_ID, "366");
		tungShoppingCart = myAmazingShop.getShoppingCartVersion2(TUNG_ID);
		printShoppingCart(tungShoppingCart);
			
		myAmazingShop.buyVersion2(NHI_ID, "978", 10);
		printShoppingCart(myAmazingShop.getShoppingCartVersion2(NHI_ID));		
		
	}
	
	static void printShoppingCart(ShoppingCart shoppingCartToPrint) {
		if (shoppingCartToPrint == null)
			System.out.println("There is no product in the shopping cart");
		else {
			System.out.println("Products in shopping cart " + shoppingCartToPrint.getShoppingCartId() +
							   " of buyer: " + shoppingCartToPrint.getBuyer() );
			Map<String, Integer> products = shoppingCartToPrint.getProducts();
			for (String key : products.keySet() ) {
			   System.out.println(key + " : " + products.get(key).intValue() );
			}
		}
	}

}
