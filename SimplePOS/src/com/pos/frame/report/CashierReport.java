package com.pos.frame.report;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.pos.input.SystemInput;

public class CashierReport extends JInternalFrame {
	private JTable cashierReportTable;
	private JTable discrepancyTable;

	/**
	 * Create the frame.
	 */
	public CashierReport(SystemInput systemInput) {
		setTitle("Cashier Report");
		setClosable(true);
		setBounds(100, 100, 623, 510);
		getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 75, 554, 141);
		getContentPane().add(scrollPane);

		cashierReportTable = new JTable();
		cashierReportTable.setFocusable(false);
		DefaultTableModel model = new DefaultTableModel(new Object[][] {},
				new String[] { "Register#", "LogOnDate", "LogOffDate", "LogOnTime", "LogOffTime", "TotalSales" });
		cashierReportTable.setModel(model);
		scrollPane.setViewportView(cashierReportTable);

		JLabel lblCashierReport = new JLabel("Cashier Report for " + systemInput.getUserName().toUpperCase());
		lblCashierReport.setHorizontalAlignment(SwingConstants.CENTER);
		lblCashierReport.setBounds(161, 13, 263, 36);
		getContentPane().add(lblCashierReport);

		JLabel lblDiscrepencyRecord = new JLabel("Discrepency Record");
		lblDiscrepencyRecord.setHorizontalAlignment(SwingConstants.CENTER);
		lblDiscrepencyRecord.setBounds(209, 245, 180, 27);
		getContentPane().add(lblDiscrepencyRecord);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(28, 302, 554, 141);
		getContentPane().add(scrollPane_1);

		discrepancyTable = new JTable();
		scrollPane_1.setColumnHeaderView(discrepancyTable);

		discrepancyTable.setFocusable(false);
		DefaultTableModel discrepancyModel = new DefaultTableModel(new Object[][] {},
				new String[] { "Register#", "Receipt#", "Date", "Total Amount", "Cash Received", "Cash Returned", });
		discrepancyTable.setModel(discrepancyModel);
		scrollPane_1.setViewportView(discrepancyTable);
		addDiscrepancyTable(discrepancyModel, systemInput);

		addReportToTable(model, systemInput);

	}

	public void addDiscrepancyTable(DefaultTableModel discrepancyModel, SystemInput systemInput) {
		Object[] row = new Object[6];
		String userName = systemInput.getUserName();
		File folder = new File("Drawer/");
		File[] listOfFiles = folder.listFiles();
		for (File file : listOfFiles) {
			String fileName[] = file.getName().split("_");

			if (fileName[0].equalsIgnoreCase(userName)) {
				try {
					String line;
					FileReader fileReader = new FileReader(file);
					BufferedReader bufferedReader = new BufferedReader(fileReader);

					while ((line = bufferedReader.readLine()) != null) {
						String lines[] = line.split(" ");
						double totalSalesAmount = Double.valueOf(lines[1]);
						double amountReceived = Double.valueOf(lines[4]);
						double amountReturned = Double.valueOf(lines[2]);

						double amountToReturn = amountReceived - totalSalesAmount;
						if ((amountToReturn > amountReturned) || (amountToReturn < amountReturned)
								|| amountReceived < totalSalesAmount) { // map register#
							row[0] = lines[3];
							row[1] = lines[0];
							row[2] = fileName[1].replace(".txt", "");
							row[3] = lines[1];
							row[4] = lines[4];
							row[5] = lines[2];

							discrepancyModel.addRow(row);
						}
					}

					bufferedReader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}

	public void addReportToTable(DefaultTableModel model, SystemInput systemInput) {
		Object[] row = new Object[6];
		String line;
		FileReader fileReader;
		BufferedReader bufferedReader;
		try {
			fileReader = new FileReader("Register.txt");

			bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				String lines[] = line.split(" ");
				if (systemInput.getUserName().equalsIgnoreCase(lines[1])) {
					row[0] = lines[0];
					row[1] = lines[2];
					row[2] = lines[3];
					row[3] = lines[4];
					row[4] = lines[5];
					row[5] = lines[6];

					model.addRow(row);
				}
			}
			bufferedReader.close();
			fileReader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
