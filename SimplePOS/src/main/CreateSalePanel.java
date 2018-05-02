package main;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JFormattedTextField;

public class CreateSalePanel extends JPanel {
	private JTable table;
	Object[] row = new Object[6];
	static int itemCount = 0;
	JScrollPane sPane;
	DefaultTableModel model;
	JLabel lblInvoice;
	JFormattedTextField textFieldInvoiceNo;
	JLabel lblEmployee;
	JFormattedTextField textFieldEmployeeName;
	/**
	 * Create the panel.
	 */
	public CreateSalePanel() {
		setBackground(new Color(204, 255, 255));
		setBorder(new LineBorder(new Color(0, 0, 0), 2));
		setLayout(null);

		sPane = new JScrollPane();
		sPane.setBounds(22, 56, 533, 345);
		add(sPane);
		table = new JTable();
		//sPane.setViewportView(table);
		table.setFocusable(false);

		table.setFont(new Font("Georgia", Font.PLAIN, 13));
		table.getTableHeader().setFont(new Font("Georgia", Font.BOLD, 10));
		model = new DefaultTableModel(new Object[][] {},
				new String[] { "Item #", "Item ID", "Item Description", "Price", "Quantity", "Total" });
		// sPane.setViewportView(table);
		table.setModel(model);
		//model.addRow(row);
		
		lblInvoice = new JLabel("Invoice#");
		lblInvoice.setBounds(12, 23, 56, 16);
		add(lblInvoice);

		textFieldInvoiceNo = new JFormattedTextField();
		textFieldInvoiceNo.setEditable(false);
		textFieldInvoiceNo.setBounds(80, 21, 106, 19);
		add(textFieldInvoiceNo);

		lblEmployee = new JLabel("Employee");
		lblEmployee.setBounds(262, 23, 56, 16);
		add(lblEmployee);

		textFieldEmployeeName = new JFormattedTextField();
		textFieldEmployeeName.setEditable(false);
		textFieldEmployeeName.setBounds(332, 20, 106, 19);
		add(textFieldEmployeeName);

	}

	// String itemName;
	String itemDesc;// =item Name/desc
	double itemPrice = 0;
	double itemTotal = 0;

	public void addItemsToTable(String itemId, double itemQuantity) {
		// TODO Auto-generated method stub
		File itemsList = new File("Items.txt");
		String item;
		Scanner input;
		try {
			input = new Scanner(itemsList);

			while (input.hasNextLine()) {
				item = input.next();

				if (item.equals(itemId)) {
					itemCount++;
					itemDesc = input.next();
					itemPrice = Double.parseDouble(input.next());
					break;
				}
			}
			itemTotal = itemPrice * itemQuantity;
			row[0] = itemCount;
			row[1] = itemId;
			row[2] = itemDesc;
			row[3] = itemPrice;
			row[4] = itemQuantity;
			row[5] = itemTotal;

			model.addRow(row);sPane.setViewportView(table);
			input.close();
		} 
		
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
