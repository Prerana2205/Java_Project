package com.pos.frame;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.pos.input.FileReference;
import com.pos.input.Inventory;
import com.pos.input.Item;

import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import java.awt.event.ActionEvent;

/**
 * @author Prerana
 *
 */
public class InventoryManagementFrame extends JInternalFrame {

	private Inventory inventory = new Inventory();
	private int itemId = 0;

	String decnumberregex = "\\d+(\\.\\d{1,2})?";
	String numberregex = "\\d+";
	JFormattedTextField textFieldItemDescription;
	JFormattedTextField textFieldQuantity;
	JFormattedTextField itemIdTextField;
	JFormattedTextField thresholdTextField;
	JFormattedTextField textFieldItemPrice;
	JFormattedTextField textFieldRemoveItemID;
	JFormattedTextField textFieldUpdateItemID;
	JFormattedTextField textFieldUpdateQuantity;

	public InventoryManagementFrame() {
		
		setTitle("Inventory Management");
		setClosable(true);
		setBounds(12, 20, 920, 840);
		getContentPane().setLayout(null);
		
		JButton btnAddItem = new JButton("Add Item");
		btnAddItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addNewItem();
			}
		});
		btnAddItem.setBounds(64, 215, 138, 25);
		getContentPane().add(btnAddItem);
		
		JButton btnUpdateQuantity = new JButton("Update ");
		btnUpdateQuantity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Inventory inventory = new Inventory();
				inventory.incrementQuantity(Integer.parseInt(textFieldUpdateItemID.getText()), Integer.parseInt(textFieldUpdateQuantity.getText()));
			}
		});
		btnUpdateQuantity.setBounds(419, 182, 138, 25);
		getContentPane().add(btnUpdateQuantity);
		
		JLabel lblItemid = new JLabel("ItemId");
		lblItemid.setBounds(22, 17, 56, 16);
		getContentPane().add(lblItemid);
		
		JLabel label = new JLabel("Item Description");
		label.setBounds(22, 50, 116, 16);
		getContentPane().add(label);
		
		 textFieldItemDescription = new JFormattedTextField();
		textFieldItemDescription.setBounds(150, 46, 88, 25);
		getContentPane().add(textFieldItemDescription);
		
		JLabel label_1 = new JLabel("Item Price");
		label_1.setBounds(22, 79, 75, 16);
		getContentPane().add(label_1);
		
		 textFieldItemPrice = new JFormattedTextField();
		textFieldItemPrice.setBounds(150, 75, 88, 25);
		getContentPane().add(textFieldItemPrice);
		
		JLabel label_2 = new JLabel("Quantity");
		label_2.setBounds(22, 112, 56, 16);
		getContentPane().add(label_2);
		
		 textFieldQuantity = new JFormattedTextField();
		textFieldQuantity.setBounds(150, 108, 88, 25);
		getContentPane().add(textFieldQuantity);
	
		
		 itemIdTextField = new JFormattedTextField();
		itemIdTextField.setEditable(false);
		itemIdTextField.setBounds(150, 13, 88, 25);
		itemIdTextField.setValue(Integer.valueOf(inventory.getItemId()));
		itemId =  inventory.getItemId();
		getContentPane().add(itemIdTextField);
		
		JLabel lblThreshold = new JLabel("Threshold");
		lblThreshold.setBounds(22, 148, 105, 16);
		getContentPane().add(lblThreshold);
		
		 thresholdTextField = new JFormattedTextField();
		thresholdTextField.setBounds(150, 144, 88, 25);
		getContentPane().add(thresholdTextField);
		
		JLabel label_3 = new JLabel("ItemId");
		label_3.setBounds(369, 21, 56, 16);
		getContentPane().add(label_3);
		
		textFieldUpdateItemID = new JFormattedTextField();
		textFieldUpdateItemID.setBounds(497, 17, 88, 25);
		getContentPane().add(textFieldUpdateItemID);
		
		JFormattedTextField formattedTextField_1 = new JFormattedTextField();
		formattedTextField_1.setBounds(497, 50, 88, 25);
		getContentPane().add(formattedTextField_1);
		
		JLabel label_4 = new JLabel("Item Description");
		label_4.setBounds(369, 54, 116, 16);
		getContentPane().add(label_4);
		
		JLabel label_5 = new JLabel("Item Price");
		label_5.setBounds(369, 83, 75, 16);
		getContentPane().add(label_5);
		
		JFormattedTextField formattedTextField_2 = new JFormattedTextField();
		formattedTextField_2.setBounds(497, 79, 88, 25);
		getContentPane().add(formattedTextField_2);
		
		textFieldUpdateQuantity = new JFormattedTextField();
		textFieldUpdateQuantity.setBounds(497, 112, 88, 25);
		getContentPane().add(textFieldUpdateQuantity);
		
		JLabel label_6 = new JLabel("Quantity");
		label_6.setBounds(369, 116, 56, 16);
		getContentPane().add(label_6);
		
		JLabel label_7 = new JLabel("Threshold");
		label_7.setBounds(369, 152, 105, 16);
		getContentPane().add(label_7);
		
		JFormattedTextField formattedTextField_4 = new JFormattedTextField();
		formattedTextField_4.setBounds(497, 148, 88, 25);
		getContentPane().add(formattedTextField_4);
		
		JLabel label_8 = new JLabel("ItemId");
		label_8.setBounds(22, 274, 56, 16);
		getContentPane().add(label_8);
		
		textFieldRemoveItemID = new JFormattedTextField();
		textFieldRemoveItemID.setBounds(150, 270, 88, 25);
		getContentPane().add(textFieldRemoveItemID);
		
		JButton btnRemoveItem = new JButton("Remove Item");
		btnRemoveItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inventory.removeItem(Integer.parseInt(textFieldRemoveItemID.getText()));
				
			}
		});
		btnRemoveItem.setBounds(64, 315, 138, 25);
		getContentPane().add(btnRemoveItem);
		
		JLabel label_9 = new JLabel("ItemId");
		label_9.setBounds(369, 275, 56, 16);
		getContentPane().add(label_9);
		
		JFormattedTextField textFieldOrderItemID = new JFormattedTextField();
		textFieldOrderItemID.setBounds(497, 271, 88, 25);
		getContentPane().add(textFieldOrderItemID);
		
		JLabel label_10 = new JLabel("Quantity");
		label_10.setBounds(369, 308, 56, 16);
		getContentPane().add(label_10);
		
		JFormattedTextField textFieldOrderQuantity = new JFormattedTextField();
		textFieldOrderQuantity.setBounds(497, 304, 88, 25);
		getContentPane().add(textFieldOrderQuantity);
		
		JButton btnPlaceOrder = new JButton("Place Order");
		btnPlaceOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				inventory.placeOrder(Integer.parseInt(textFieldOrderItemID.getText()), Integer.parseInt(textFieldOrderQuantity.getText()));
			}
		});
		btnPlaceOrder.setBounds(419, 342, 138, 25);
		getContentPane().add(btnPlaceOrder);
		
		JLabel lblSupplier = new JLabel("Supplier");
		lblSupplier.setBounds(22, 181, 105, 16);
		getContentPane().add(lblSupplier);
		
		JFormattedTextField formattedTextField = new JFormattedTextField();
		formattedTextField.setBounds(150, 177, 88, 25);
		getContentPane().add(formattedTextField);
		

	}

	public void addNewItem() {
		
		if((!(textFieldItemDescription.getText().isEmpty() && textFieldItemPrice.getText().isEmpty() && textFieldQuantity.getText().isEmpty() && thresholdTextField.getText().isEmpty()))
				&& ((textFieldQuantity.getText().matches(numberregex)) && (thresholdTextField.getText().matches(numberregex)) && textFieldItemPrice.getText().matches(decnumberregex))){
			
			int itemFieldQuantity = getIntegerValue(textFieldQuantity);
			int thresholdQuantity = getIntegerValue(thresholdTextField);
			double itemPrice =  Double.valueOf(textFieldItemPrice.getText());
			String itemDesc = textFieldItemDescription.getText();

			Item item = new Item();
			item.setItemId(itemId);
			item.setDescription(itemDesc);
			item.setPrice(itemPrice);
			item.setQuantity(itemFieldQuantity);
			item.setThreshold(thresholdQuantity);
			boolean success = inventory.addInventory(item);
			if(success){
				JOptionPane.showMessageDialog(null, "Item Added Succesfuly");
				textFieldItemDescription.setText("");
				textFieldItemPrice.setText("");
				textFieldQuantity.setText("");
				thresholdTextField.setText("");
				itemIdTextField.setText(String.valueOf(inventory.getItemId()));
			}
			
		}else{
			JOptionPane.showMessageDialog(null, "Enter all fields");
		}	
		
		
	}
	
	public int getIntegerValue(JFormattedTextField field){
		return Integer.valueOf(field.getText());
	}
}
