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
		inventory.updateInventory(item);
	}

	private Item createItem() {
		// TODO Auto-generated method stub
		Item item = new Item();
		item.setItemId(inventory.getItemId());
		item.setDescription("Purse");
		item.setPrice(50);
		item.setQuantity(15);
		item.setThreshold(5);
		return item;
	}
}
