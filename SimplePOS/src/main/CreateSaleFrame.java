package main;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JDesktopPane;

public class CreateSaleFrame extends JInternalFrame {

	double itemQuantity = 1;
	static int receiptNumber;
	JScrollPane sPane;
	DefaultTableModel model;
	JLabel lblInvoice;
	JFormattedTextField textFieldInvoiceNo;
	JLabel lblEmployee;
	JFormattedTextField textFieldEmployeeName;
	private JTable table;
	Object[] row = new Object[6];
	static int itemCount = 0;
	JButton btnRemove;
	JFormattedTextField textFieldGrandTotal;
	JFormattedTextField textFieldCashReturned;
	JFormattedTextField textFieldCashReceived;
	JFormattedTextField textFieldTotalItems;
	double cashReceived, grandTotal, cashReturned;
	String username;

	/**
	 * Create the frame.
	 * 
	 * @param username
	 */
	public CreateSaleFrame(String username) {
		this.username = username;
		setTitle("Create Sale");
		setClosable(true);
		setBounds(100, 100, 746, 468);
		getContentPane().setLayout(null);

		JLabel lblItemId = new JLabel("Item ID");
		lblItemId.setBounds(12, 45, 56, 16);
		getContentPane().add(lblItemId);

		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setBounds(12, 100, 56, 16);
		getContentPane().add(lblQuantity);

		JFormattedTextField textFieldItemId = new JFormattedTextField();
		textFieldItemId.setBounds(12, 61, 69, 31);
		getContentPane().add(textFieldItemId);

		JFormattedTextField textFieldQuantity = new JFormattedTextField();
		textFieldQuantity.setBounds(12, 118, 69, 31);
		getContentPane().add(textFieldQuantity);
		textFieldQuantity.setValue(new Double(itemQuantity));

		JButton btnAddToTable = new JButton("Add");

		btnAddToTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// CreateSalePanel csp = new CreateSalePanel();
				// csp.setVisible(true);
				if (textFieldItemId.getText().length() == 0)
					JOptionPane.showMessageDialog(null, "Enter a Valid ItemId");
				else
					addItemsToTable(textFieldItemId.getText(), ((Number) textFieldQuantity.getValue()).doubleValue());

			}
		});
		btnAddToTable.setBounds(12, 169, 97, 25);
		getContentPane().add(btnAddToTable);

		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteAllRows(model);

			}
		});
		btnReset.setBounds(12, 245, 97, 25);
		getContentPane().add(btnReset);

		JButton btnPrintReceipt = new JButton("Print Receipt");
		btnPrintReceipt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (((Number) textFieldCashReceived.getValue())
						.doubleValue() >= ((Number) textFieldGrandTotal.getValue()).doubleValue()) {
					textFieldCashReturned.setValue(((Number) textFieldCashReceived.getValue()).doubleValue()
							- ((Number) textFieldGrandTotal.getValue()).doubleValue());
					generateReceipt();
				} else
					JOptionPane.showMessageDialog(null, "Please enter Cash Received greater than Grand Total");
			}

		});
		btnPrintReceipt.setBounds(12, 394, 148, 25);
		getContentPane().add(btnPrintReceipt);

		JLabel lblCashReceived = new JLabel("Cash Received");
		lblCashReceived.setBounds(12, 334, 97, 16);
		getContentPane().add(lblCashReceived);

		JLabel lblReturn = new JLabel("Return");
		lblReturn.setBounds(12, 363, 97, 16);
		getContentPane().add(lblReturn);

		textFieldCashReceived = new JFormattedTextField();
		textFieldCashReceived.setBounds(104, 331, 56, 19);
		getContentPane().add(textFieldCashReceived);

		textFieldCashReturned = new JFormattedTextField();
		textFieldCashReturned.setEditable(false);
		textFieldCashReturned.setBounds(104, 363, 56, 19);
		getContentPane().add(textFieldCashReturned);

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

		textFieldCashReceived.setValue(new Double(cashReceived));

		// textFieldCashReceived.setValue("0");
		textFieldGrandTotal.setValue(new Double(grandTotal));
		textFieldCashReturned.setValue(new Double(cashReturned));

		JLabel lblGrandTotal = new JLabel("GRAND TOTAL");
		lblGrandTotal.setBounds(316, 341, 94, 19);
		panel.add(lblGrandTotal);

		JLabel lblTotalItems = new JLabel("Total Items");
		lblTotalItems.setBounds(148, 342, 94, 19);
		panel.add(lblTotalItems);

		textFieldTotalItems = new JFormattedTextField();
		textFieldTotalItems.setEditable(false);
		textFieldTotalItems.setBounds(219, 339, 94, 22);
		panel.add(textFieldTotalItems);

		lblInvoice = new JLabel("Invoice#");
		lblInvoice.setBounds(12, 23, 56, 16);
		getContentPane().add(lblInvoice);

		textFieldInvoiceNo = new JFormattedTextField();
		textFieldInvoiceNo.setEditable(false);
		textFieldInvoiceNo.setBounds(80, 21, 106, 19);
		getContentPane().add(textFieldInvoiceNo);

		lblEmployee = new JLabel("Employee");
		lblEmployee.setBounds(262, 23, 56, 16);
		getContentPane().add(lblEmployee);

		textFieldEmployeeName = new JFormattedTextField();
		textFieldEmployeeName.setEditable(false);
		textFieldEmployeeName.setBounds(332, 20, 106, 19);
		getContentPane().add(textFieldEmployeeName);
		textFieldEmployeeName.setValue(username.toUpperCase());

		btnRemove = new JButton("Remove");

		btnRemove.setBounds(12, 207, 97, 25);
		getContentPane().add(btnRemove);

		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int selectedRowIndex = table.getSelectedRow();
					model.removeRow(selectedRowIndex);
				} catch (Exception ex) {
					// System.out.println(ex.getMessage());
					JOptionPane.showMessageDialog(null, "Select a row to remove");
				}
			}
		});

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					boolean rowsAreSelected = table.getSelectedRowCount() > 0;
					if (rowsAreSelected == true)
						btnRemove.setEnabled(true);
					else
						btnRemove.setEnabled(false);
				}
			}
		});

	}

	String itemDesc;// =item Name/desc
	double itemPrice = 0;
	double itemTotal = 0;

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
					itemCount++;
					itemDesc = item[1];
					itemPrice = Double.parseDouble(item[2]);
					itemTotal = itemPrice * itemQuantity;
					row[0] = itemCount;
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

		System.out.println(total);
	}

	public static void deleteAllRows(final DefaultTableModel model) {
		for (int i = model.getRowCount() - 1; i >= 0; i--) {
			model.removeRow(i);
		}
	}

	//Work under progress
	private void generateReceipt() {
		// receiptNumber = 1 ;

		/*File newReceipt = new File(receiptNumber + ".txt");
		try {
			// Scanner input = new Scanner(menuItems);
			PrintWriter output = new PrintWriter(newReceipt);
			// Scanner in = new Scanner(System.in);
			DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
			Calendar calobj = Calendar.getInstance();
			// System.out.println(df.format(calobj.getTime()));

			output.print("Invoice number: " + receiptNumber + "\t\t" + "Employee: " + username + "Date/Time: "
					+ "df.format(calobj.getTime())");
			output.println();
			output.println("Item # \t Item ID \t Item Description \t Price \t Quantity \t Total");
			output.print("");
			
			output.close();
		}

		catch (Exception e) {
			System.out.println("There was an error" + e.toString());
		}

*/	}
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { CreateSaleFrame frame = new
	 * CreateSaleFrame(); frame.setVisible(true); } catch (Exception e) {
	 * e.printStackTrace(); } } }); }
	 */
}
