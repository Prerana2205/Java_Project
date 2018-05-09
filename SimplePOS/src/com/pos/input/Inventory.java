package com.pos.input;

import java.util.HashMap;


public class Inventory {

    public HashMap<Integer, Item> items;

    public Inventory() {
        items = new FileReference().readItems();
    }

    public void incrementQuantity(int itemId, int quantity) {
        Item item = items.get(itemId);
        int newQuantity = item.getQuantity() + quantity;
        item.setQuantity(newQuantity);
    }

    public void decrementQuantity(int itemId, int quantity) {
    	
    	Item item =items.get(itemId);
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

    public int getItemId(){
    	return items.size()+1;
    }
    public boolean updateInventory(Item item) {
    	items.put(item.getItemId(), item);
        new FileReference().writeItems(items);
        return true;
    }
}
