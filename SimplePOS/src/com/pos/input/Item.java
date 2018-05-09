package com.pos.input;

/**
 * @author Prerana
 *
 */
public class Item {
    public int itemId;
    public String description;
    public double price;
    public int quantity;
    public int threshold;
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getThreshold() {
		return threshold;
	}
	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}

    public Item(String line) {
        String[] fields = line.split(" ");

        this.itemId= Integer.parseInt(fields[0]);
        this.description= fields[1];
        this.price = Double.parseDouble(fields[2]);
        this.quantity = Integer.parseInt(fields[3]);
        this.threshold = Integer.parseInt(fields[4]);
    }
	public Item() {
		// TODO Auto-generated constructor stub
	}

    
    
}
