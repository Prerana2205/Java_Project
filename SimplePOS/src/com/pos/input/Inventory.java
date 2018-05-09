package com.pos.input;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

public class Inventory {

	public LinkedHashMap<Integer, Item> items;

	public Inventory() {
		items = new FileReference().readItems();
	}

	public void incrementQuantity(int itemId, int quantity) {
		Item item = items.get(itemId);
		int newQuantity = item.getQuantity() + quantity;
		item.setQuantity(newQuantity);
	}

	public void decrementQuantity(int itemId, int quantity) {

		Item item = items.get(itemId);
		int available = item.getQuantity();
		int newAvailableQuantity = available - quantity;
		int threshold = item.getThreshold();

		if (newAvailableQuantity >= 0) {
			item.setQuantity(newAvailableQuantity);
		} else {
			// Show an error message
			// Not enough items in stock
		}

		if (threshold < newAvailableQuantity) {
			// Show a message: "Order has been placed"
			// A background process will place an order
		}
	}

	public void placeOrder(int itemId, int quantity) {
		Item item = items.get(itemId);
		item.setPlaceOrder(true);
		item.setQuantityOrderPlaced(quantity);
		new FileReference().writeItems(items);

	}

	public int getItemId() {
		int itemId =0;
		if(items.size() >0) {
			Set<Integer> iSet = items.keySet();
			Integer intSet[] =(Integer []) iSet.toArray(new Integer[iSet.size()]);
			itemId= intSet[intSet.length -1];
		}
	//	int itemId =(Integer)items.keySet().toArray()[items.size() -1];
		
		return itemId + 1;
	}

	public boolean addInventory(Item item) {
		items.put(item.getItemId(), item);
		new FileReference().writeItems(items);
		return true;
	}

	public void removeItem(int itemId) {
		items.remove(itemId);
		new FileReference().writeItems(items);
	}
}
