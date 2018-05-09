package com.pos.frame;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.pos.input.Inventory;
import com.pos.input.Item;

import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
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
	JFormattedTextField itemIdTextField ;
	JFormattedTextField thresholdTextField;
	JFormattedTextField textFieldItemPrice;
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
		btnAddItem.setBounds(64, 182, 138, 25);
		getContentPane().add(btnAddItem);
		
		JButton btnUpdateQuantity = new JButton("Update Quantity");
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
		
		JFormattedTextField formattedTextField = new JFormattedTextField();
		formattedTextField.setBounds(497, 17, 88, 25);
		getContentPane().add(formattedTextField);
		
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
		
		JFormattedTextField formattedTextField_3 = new JFormattedTextField();
		formattedTextField_3.setBounds(497, 112, 88, 25);
		getContentPane().add(formattedTextField_3);
		
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
		label_8.setBounds(22, 295, 56, 16);
		getContentPane().add(label_8);
		
		JLabel label_9 = new JLabel("Item Description");
		label_9.setBounds(22, 328, 116, 16);
		getContentPane().add(label_9);
		
		JFormattedTextField formattedTextField_5 = new JFormattedTextField();
		formattedTextField_5.setBounds(150, 324, 88, 25);
		getContentPane().add(formattedTextField_5);
		
		JFormattedTextField formattedTextField_6 = new JFormattedTextField();
		formattedTextField_6.setBounds(150, 291, 88, 25);
		getContentPane().add(formattedTextField_6);
		
		JButton btnRemoveItem = new JButton("Remove Item");
		btnRemoveItem.setBounds(64, 371, 138, 25);
		getContentPane().add(btnRemoveItem);
		
		
		
		

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
