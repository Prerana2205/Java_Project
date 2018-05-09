package com.pos.input;
import javax.print.attribute.TextSyntax;

import com.pos.input.Inventory;
import com.pos.input.Item;

import junit.framework.TestCase;

public class InventoryTest extends TestCase {

	private Inventory inventory = new Inventory();
	
	public void testReadInventory(){
	
	System.out.println(inventory.getItemId());
	}
	
	public void testWriteItems(){
		Item item = createItem();
		inventory.addInventory(item);
	}

	private Item createItem() {
		// TODO Auto-generated method stub
		Item item = new Item();
		item.setItemId(inventory.getItemId());
		item.setDescription("Wallet");
		item.setPrice(50);
		item.setQuantity(15);
		item.setThreshold(5);
		item.setSupplier("COSTCO");
		item.setQuantityOrderPlaced(10);
		item.setPlaceOrder(false);
		
		return item;
	}
	
	public void testRemoveItem() {
		inventory.removeItem(2);
	}
}
