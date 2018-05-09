package com.pos.frame;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.awt.event.ActionEvent;

/**
 * @author Prerana
 *
 */
public class ReturnFrame extends JInternalFrame {

	String username;
	JScrollPane sPane;
	JTable table;
	DefaultTableModel model;
	JFormattedTextField textFieldGrandTotal;
	JFormattedTextField textFieldTotalItems;
	JFormattedTextField textFieldCashReturned;
	JFormattedTextField textFieldItemId;
	JFormattedTextField textFieldItemPrice;
	JFormattedTextField textFieldItemQty;
	JFormattedTextField textFieldInvoice;
	Object[] row = new Object[6];
	int itemCount = 0;

	String itemDesc;// =item Name/desc
	double itemPrice = 0;
	double itemTotal = 0;

	public ReturnFrame(String username, int registerNumber) {

		setTitle("Create Return");
		setClosable(true);
		setBounds(12, 20, 920, 840);
		getContentPane().setLayout(null);

		JLabel lblInvoice = new JLabel("Invoice #");
		lblInvoice.setBounds(23, 69, 56, 16);
		getContentPane().add(lblInvoice);

		JLabel lblItemId = new JLabel("Item ID");
		lblItemId.setBounds(23, 98, 56, 16);
		getContentPane().add(lblItemId);

		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setBounds(23, 156, 56, 16);
		getContentPane().add(lblQuantity);

		JLabel lblItemPrice = new JLabel("Item Price");
		lblItemPrice.setBounds(23, 127, 75, 16);
		getContentPane().add(lblItemPrice);

		JLabel lblTotalPrice = new JLabel("Cash Returned");
		lblTotalPrice.setBounds(23, 397, 94, 16);
		getContentPane().add(lblTotalPrice);

		JButton btnProcessReturn = new JButton("Process Return");
		btnProcessReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				processReturn();
			}
		});
		btnProcessReturn.setBounds(23, 348, 168, 25);
		getContentPane().add(btnProcessReturn);

		textFieldCashReturned = new JFormattedTextField();
		textFieldCashReturned.setEditable(false);
		textFieldCashReturned.setBounds(113, 393, 88, 25);
		getContentPane().add(textFieldCashReturned);

		textFieldItemQty = new JFormattedTextField();
		textFieldItemQty.setBounds(103, 152, 88, 25);
		getContentPane().add(textFieldItemQty);

		textFieldItemPrice = new JFormattedTextField();
		textFieldItemPrice.setEditable(false);
		textFieldItemPrice.setBounds(103, 123, 88, 25);
		getContentPane().add(textFieldItemPrice);

		textFieldItemId = new JFormattedTextField();
		textFieldItemId.setBounds(103, 94, 88, 25);
		getContentPane().add(textFieldItemId);

		textFieldInvoice = new JFormattedTextField();
		textFieldInvoice.setBounds(103, 65, 88, 25);
		getContentPane().add(textFieldInvoice);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textFieldItemId.getText().length() == 0 || textFieldItemQty.getText().length() == 0
						|| textFieldInvoice.getText().length() == 0)
					JOptionPane.showMessageDialog(null, "Enter all the details");
				else if (Double.valueOf(textFieldItemQty.getText()) > 0.0) {
					// check invoice no in invoice/
					if (findInvoice())
						// check itemid and item present in items.txt

						addItemsToTable(textFieldItemId.getText(), Double.valueOf(textFieldItemQty.getText()));
				} else
					JOptionPane.showMessageDialog(null, "Enter a Valid Quantity of Item");

			}
		});
		btnAdd.setBounds(20, 194, 97, 25);
		getContentPane().add(btnAdd);

		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					int selectedRowIndex = table.getSelectedRow();
					model.removeRow(selectedRowIndex);
				} catch (Exception ex) {
					// System.out.println(ex.getMessage());
					JOptionPane.showMessageDialog(null, "Select a row to remove");
				}

			}
		});
		btnRemove.setBounds(20, 232, 97, 25);
		getContentPane().add(btnRemove);

		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetTextFields();

			}
		});
		btnReset.setBounds(20, 270, 97, 25);
		getContentPane().add(btnReset);

		JPanel panel = new JPanel();
		panel.setBounds(206, 45, 512, 374);
		getContentPane().add(panel);

		panel.setBackground(new Color(204, 255, 255));
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.setLayout(null);

		sPane = new JScrollPane();
		sPane.setBounds(0, 0, 512, 341);
		panel.add(sPane);
		table = new JTable();
		sPane.setViewportView(table);
		table.setFocusable(false);

		table.setFont(new Font("Georgia", Font.PLAIN, 13));
		table.getTableHeader().setFont(new Font("Georgia", Font.BOLD, 10));
		model = new DefaultTableModel(new Object[][] {},
				new String[] { "Item #", "Item ID", "Item Description", "Price", "Quantity", "Total" });
		// sPane.setViewportView(table);
		table.setModel(model);

		textFieldGrandTotal = new JFormattedTextField();
		textFieldGrandTotal.setEditable(false);
		textFieldGrandTotal.setBounds(406, 339, 94, 22);
		panel.add(textFieldGrandTotal);

		JLabel lblGrandTotal = new JLabel("GRAND TOTAL");
		lblGrandTotal.setBounds(319, 342, 94, 16);
		panel.add(lblGrandTotal);

		JLabel lblTotalItems = new JLabel("Total Items");
		lblTotalItems.setBounds(148, 342, 94, 19);
		panel.add(lblTotalItems);

		textFieldTotalItems = new JFormattedTextField();
		textFieldTotalItems.setEditable(false);
		textFieldTotalItems.setBounds(219, 339, 94, 22);
		panel.add(textFieldTotalItems);

		this.username = username;

	}

	// process return : updates items.txt ( adds item quantity returned to
	// existing quantity )
	protected void processReturn() {
		// TODO Auto-generated method stub
		File itemsList = new File("Items.txt");
		String[] item;
		Scanner input;
		// boolean isFound = false;
		try {
			input = new Scanner(itemsList);
			String newLine;
			double itemQty;
			while (input.hasNextLine()) {
				newLine = input.nextLine();
				item = newLine.split("\\W+");

				for (int i = 0; i < table.getRowCount(); i++) {

					if (item[0].equalsIgnoreCase(table.getValueAt(i, 0).toString())) {
						item[2].replaceAll(item[2],
								Double.toString(Double.parseDouble(item[2]) + ((Double) table.getValueAt(i, 3))));

					}

				}
				input.close();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean findInvoice() {

		// check if Invoice is present in the folder
		File folder = new File("Invoices/");
		String fileName = "";
		int currentNo = Integer.valueOf(textFieldInvoice.getText());
		int startNo = 0;
		String[] fileN;
		File[] listOfFiles = folder.listFiles();
		boolean isfound = false;
		boolean returnStatus = false;
		File f = null;
		if (listOfFiles != null) {
			for (File fileEntry : listOfFiles) {
				if (fileEntry.isDirectory()) {
					// listFilesForFolder(fileEntry);
				} else {
					System.out.println(fileEntry.getName());

					fileName = fileEntry.getName();
					f = fileEntry;
					fileN = fileName.split("\\.");
					startNo = Integer.parseInt(fileN[0]);
					if (currentNo == startNo)
						break;
				}
			}
		} else
			JOptionPane.showMessageDialog(null, "No invoice yet");
		if (currentNo == startNo) {
			// JOptionPane.showMessageDialog(null, "Return Processed");
			isfound = true;
			// return true;
		} else {
			JOptionPane.showMessageDialog(null, "Not a valid invoice number");
			isfound = false;
			// return false;
		}
		if (isfound == true) {
			// Invoice is found so check its quantity in the invoice
			Scanner input;
			String[] itemDetails;

			try {
				input = new Scanner(f);
				String newLine;
				boolean itemFound = false;
				boolean itemQuantityFound = false;
				input.nextLine();
				input.nextLine();
				input.nextLine();
				while (input.hasNextLine()) {
					textFieldItemPrice.setText("0");
					itemQuantityFound = false;
					itemFound = false;
					returnStatus = false;
					newLine = input.nextLine();
					itemDetails = newLine.split("\\W+");
					if (itemDetails.length > 0) {
						if (itemDetails[2].equalsIgnoreCase(textFieldItemId.getText())) {
							// item present in the Itemss.txt
							textFieldItemPrice.setText(itemDetails[4] + "." + itemDetails[5]);

							itemFound = true;

							if (Integer.parseInt(itemDetails[6]) >= (Integer.parseInt(textFieldItemQty.getText()))) {
								itemQuantityFound = true;
								returnStatus = true;
								break;
							} else {
								// JOptionPane.showMessageDialog(null, "Quantity
								// entered doesnt match the invoice quantity");
							}
						}

					}
				}
				if (itemFound == false) {
					JOptionPane.showMessageDialog(null, "No item  found in the invoice");
				} else if (itemQuantityFound == false && itemFound == true)
					JOptionPane.showMessageDialog(null, "Quantity entered doesnt match the invoice quantity");

			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Could not process return");
			}

		}
		return returnStatus;
	}

	public void resetTextFields() {

		// reset all text boxes
		// textFieldCashReceived.setValue(0);
		textFieldCashReturned.setValue(0);
		textFieldGrandTotal.setValue(0);
		// setReceiptNumber();
		textFieldTotalItems.setValue(0);
		textFieldItemId.setValue(0);
		textFieldInvoice.setValue(0);
		textFieldItemPrice.setValue(0);
		textFieldItemQty.setValue(1);

		// Remove all table rows

		model.setRowCount(0);

	}

	public void addItemsToTable(String itemId, double itemQuantity) {
		// TODO Auto-generated method stub

		File itemsList = new File("Items.txt");
		String[] item;
		Scanner input;
		boolean isFound = false;
		try {
			input = new Scanner(itemsList);
			String newLine;
			while (input.hasNextLine()) {
				newLine = input.nextLine();
				item = newLine.split("\\W+");

				if (item[0].equals(itemId)) {
					// itemCount++;
					itemDesc = item[1];
					itemPrice = Double.parseDouble(item[2]);
					itemTotal = itemPrice * itemQuantity;
					row[0] = table.getRowCount() + 1;
					row[1] = itemId;
					row[2] = itemDesc;
					row[3] = itemPrice;
					row[4] = itemQuantity;
					row[5] = itemTotal;

					model.addRow(row);
					sPane.setViewportView(table);
					isFound = true;
					calculateGrandTotal();
					break;
				} else {
					// isFound = false;
				}
			}

			if (isFound != true) {// item not in list
				JOptionPane.showMessageDialog(null, "Invalid Item ID");
			}

			// input.close();
		}

		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e);
		}

	}

	public void calculateGrandTotal() {
		double total = 0;
		double totalItemQty = 0;
		for (int i = 0; i < table.getRowCount(); i++) {
			double amount = (double) table.getValueAt(i, 5);
			total += amount;
			double itemQty = (double) table.getValueAt(i, 4);
			totalItemQty += itemQty;
		}
		textFieldGrandTotal.setValue(total);
		textFieldTotalItems.setValue(totalItemQty);
		textFieldCashReturned.setValue(total);

		System.out.println(total);
	}
}
