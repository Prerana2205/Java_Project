package com.pos.frame.report;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class RegisterReport extends JInternalFrame {
	private JTable table;

	/**
	 * Create the frame.
	 */
	public RegisterReport() {
		setTitle("Register Report");
		setClosable(true);
		setBounds(100, 100, 623, 510);
		getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 75, 555, 345);
		getContentPane().add(scrollPane);

		table = new JTable();
		table.setFocusable(false);
		DefaultTableModel model = new DefaultTableModel(new Object[][] {},
				new String[] { "Register#", "UserName", "LogOnDate", "LogOffDate", "LogOnTime","LogOffTime","TotalSales" });
		table.setModel(model);
		scrollPane.setViewportView(table);

		JLabel lblRegisterReport = new JLabel("Register Report");
		lblRegisterReport.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegisterReport.setBounds(161, 13, 263, 36);
		getContentPane().add(lblRegisterReport);

		addReportToTable(model);

	}

	public void addReportToTable(DefaultTableModel model) {
		Object[] row = new Object[7];
		String line;
		FileReader fileReader;
		BufferedReader bufferedReader;
		try {
			fileReader = new FileReader("Register.txt");

			bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				String lines[] = line.split(" ");
				row[0] = lines[0];
				row[1] = lines[1];
				row[2] = lines[2];
				row[3] = lines[3];
				row[4] = lines[4];
				row[5] = lines[5];
				row[6] = lines[6];
				model.addRow(row);
			}
			bufferedReader.close();
			fileReader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
