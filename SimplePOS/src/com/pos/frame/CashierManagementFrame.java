package com.pos.frame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class CashierManagementFrame extends JInternalFrame {
	private JTable table;

	public CashierManagementFrame() {
		setBounds(100, 100, 450, 300);
		setTitle("Cashier Report");
		setClosable(true);

		setBounds(100, 100, 623, 510);
		getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 75, 555, 345);
		getContentPane().add(scrollPane);

		table = new JTable();
		table.setFocusable(false);
		DefaultTableModel model = new DefaultTableModel(new Object[][] {},
				new String[] { "User", "Assignment from", "Assignment to", "Register", "Discrepancy" });
		table.setModel(model);
		scrollPane.setViewportView(table);

		JLabel lblCashierReport = new JLabel("Cashier Report");
		lblCashierReport.setHorizontalAlignment(SwingConstants.CENTER);
		lblCashierReport.setBounds(161, 13, 263, 36);
		getContentPane().add(lblCashierReport);

		addReportToTable(model);

	}

	public void addReportToTable(DefaultTableModel model) {
		Object[] row = new Object[5];
		String line;
		FileReader fileReader;
		BufferedReader bufferedReader;
		try {
			fileReader = new FileReader("Cashier.txt");

			bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				String lines[] = line.split(" ");
				row[0] = lines[0];
				row[1] = lines[1];
				row[2] = lines[2];
				row[3] = lines[3];
				row[4] = lines[4];
				model.addRow(row);
			}
			bufferedReader.close();
			fileReader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

