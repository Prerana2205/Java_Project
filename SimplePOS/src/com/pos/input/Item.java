package com.pos.input;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Prerana
 *
 */
public class Item {
	private int itemId;
	private String description;
	private double price;
	private int quantity;
	private int threshold;
	private String supplier;
	private String placeDate = "##-##-####";
	/**
	 * @return the placeDate
	 */
	public String getPlaceDate() {
		if(isPlaceOrder()) {
			this.placeDate = new SimpleDateFormat(datePattern).format(new Date());;
		}
		return placeDate;
	}

	private int quantityOrderPlaced =0;
	/**
	 * @return the quantityOrderPlaced
	 */
	public int getQuantityOrderPlaced() {
		return quantityOrderPlaced;
	}

	private boolean placeOrder = false;
	/**
	 * @return the placeOrder
	 */
	public boolean isPlaceOrder() {
		return placeOrder;
	}

	/**
	 * @param placeOrder the placeOrder to set
	 */
	public void setPlaceOrder(boolean placeOrder) {
		this.placeOrder = placeOrder;
	}

	String datePattern = "MM-dd-yyyy";

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

	/**
	 * @return the supplier
	 */
	public String getSupplier() {
		return supplier;
	}

	/**
	 * @param supplier
	 *            the supplier to set
	 */
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	

	/**
	 * @param quantityOrderPlaced the quantityOrderPlaced to set
	 */
	public void setQuantityOrderPlaced(int quantityOrderPlaced) {
		if(isPlaceOrder()) {
			this.quantityOrderPlaced = quantityOrderPlaced;
		}else {
			this.quantityOrderPlaced =0;
		}
	}

	

	public Item(String line) {
		String[] fields = line.split(" ");

		this.itemId = Integer.parseInt(fields[0]);
		this.description = fields[1];
		this.price = Double.parseDouble(fields[2]);
		this.quantity = Integer.parseInt(fields[3]);
		this.threshold = Integer.parseInt(fields[4]);
		this.supplier = fields[5];
		this.quantityOrderPlaced = Integer.parseInt(fields[6]);
		this.placeDate = fields[7];

	}

	public Item() {
		// TODO Auto-generated constructor stub
	}

}
