package com.pos.frame.report;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * @author Prerana
 *
 */
public class DrawerManagementFrame extends JInternalFrame {
	private JTable table;
	DefaultTableModel model;
	Object[] row = new Object[2];
	String username;
	double dInitialBalance = 500;

	JLabel initialBalance;
	JLabel totalInvoices;
	JLabel totalAmount;
	JLabel endDrawer;

	/**
	 * Create the frame.
	 */
	public DrawerManagementFrame() {
		setTitle("Drawer Management");
		setClosable(true);
		setBounds(12, 20, 922, 494);
		getContentPane().setLayout(null);

		JLabel lblEnterEmployeeName = new JLabel("Enter Employee Name");
		lblEnterEmployeeName.setBounds(12, 24, 137, 30);
		getContentPane().add(lblEnterEmployeeName);

		JLabel lblDate = new JLabel("Date (mm-dd-yyyy)");
		lblDate.setBounds(12, 78, 137, 30);
		getContentPane().add(lblDate);

		JFormattedTextField formattedEmployeeName = new JFormattedTextField();
		formattedEmployeeName.setBounds(201, 28, 171, 36);
		getContentPane().add(formattedEmployeeName);

		JFormattedTextField formattedDateField = new JFormattedTextField();
		formattedDateField.setBounds(201, 82, 171, 36);
		getContentPane().add(formattedDateField);

		JButton btnGetDrawerRecord = new JButton("Get Drawer Record");
		btnGetDrawerRecord.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				username = formattedEmployeeName.getText();
				String date = formattedDateField.getText();
				boolean success = addDrawerTable(username.toLowerCase(), date);
				if (!success) {
					JOptionPane.showMessageDialog(null, "File not found");
				}

			}

		});
		btnGetDrawerRecord.setBounds(12, 159, 163, 30);
		getContentPane().add(btnGetDrawerRecord);

		JLabel lblStart = new JLabel("Start Drawer");
		lblStart.setBounds(25, 227, 118, 30);
		getContentPane().add(lblStart);

		JLabel lblEndDrawer = new JLabel("End Drawer");
		lblEndDrawer.setBounds(263, 227, 118, 30);
		getContentPane().add(lblEndDrawer);

		JLabel lblNumberOfInvoices = new JLabel("Number of Invoices");
		lblNumberOfInvoices.setBounds(25, 340, 118, 30);
		getContentPane().add(lblNumberOfInvoices);

		JLabel lblTotalAmount = new JLabel("Total Amount");
		lblTotalAmount.setBounds(263, 340, 118, 30);
		getContentPane().add(lblTotalAmount);

		initialBalance = new JLabel("0.0");
		initialBalance.setBounds(25, 270, 118, 30);
		getContentPane().add(initialBalance);

		totalInvoices = new JLabel("0");
		totalInvoices.setBounds(25, 378, 118, 30);
		getContentPane().add(totalInvoices);

		endDrawer = new JLabel("0.0");
		endDrawer.setBounds(263, 270, 118, 30);
		getContentPane().add(endDrawer);

		totalAmount = new JLabel("0.0");
		totalAmount.setBounds(263, 378, 118, 30);
		getContentPane().add(totalAmount);

		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				initialBalance.setText("0.0");
				totalInvoices.setText("0");
				endDrawer.setText("0.0");
				totalAmount.setText("0.0");
				formattedEmployeeName.setText("");
				formattedDateField.setText("");

			}
		});
		btnReset.setBounds(209, 159, 163, 30);
		getContentPane().add(btnReset);
	}

	public boolean addDrawerTable(String username, String date) {

		boolean fileFound = false;
		Double sum = 0.0;
		Double sumReturnValue = 0.0;
		int invoice = 0;

		String fileName = username + "_" + date + ".txt";
		File folder = new File("Drawer/");
		File[] listOfFiles = folder.listFiles();
		File fileToRead = null;
		for (File file : listOfFiles) {
			if (file.getName().equalsIgnoreCase(fileName)) {
				fileToRead = file;
				fileFound = true;
				break;
			}
		}
		if (fileFound) {
			try {
				String line;
				FileReader fileReader = new FileReader(fileToRead);
				BufferedReader bufferedReader = new BufferedReader(fileReader);

				while ((line = bufferedReader.readLine()) != null) {
					String fields[] = line.split(" ");
					String value = fields[1];
					String returnValue = fields[2];
					sum = sum + Double.valueOf(value);
					sumReturnValue = sumReturnValue + Double.valueOf(returnValue);
					invoice = invoice + 1;
				}
				initialBalance.setText(String.valueOf(dInitialBalance));
				totalInvoices.setText(String.valueOf(invoice));
				totalAmount.setText(String.valueOf(sum));
				endDrawer.setText(String.valueOf(sum + dInitialBalance));


				bufferedReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return fileFound;
	}

}
