import java.util.HashMap;

public class Inventory {

    public HashMap<Integer, Item> items;

    public Inventory() {
        items = new File().readItems();
    }

    public void incrementQuantity(int itemId, int quantity) {
        items.get(itemId).Quantity += quantity;
    }

    public void decrementQuantity(int itemId, int quantity) {
        int available = items.get(itemId).Quantity;
        int threshold = items.get(itemId).Threshold;
        int newAvailableQuantity = available - quantity;

        if (newAvailableQuantity >= 0) {
            items.get(itemId).Quantity = newAvailableQuantity;
        } else {
            // Show an error message
            // Not enough items in stock
        }

        if (threshold < newAvailableQuantity) {
            // Show a message: "Order has been placed"
            // A background process will place an order
        }
    }

    public void updateInventory() {
        new File().writeItems(items);
    }
}
