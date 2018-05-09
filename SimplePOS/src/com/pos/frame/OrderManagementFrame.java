package com.pos.frame;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class OrderManagementFrame extends JInternalFrame {
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					OrderManagementFrame frame = new OrderManagementFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public OrderManagementFrame() {
		setClosable(true);
		setBounds(100, 100, 512, 511);
		getContentPane().setLayout(null);

		JButton btnGenerateReport = new JButton("Generate Report");
		btnGenerateReport.setBounds(159, 42, 163, 33);
		getContentPane().add(btnGenerateReport);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(33, 193, 422, 224);
		getContentPane().add(scrollPane);

		table = new JTable();
		table.setFocusable(false);
		DefaultTableModel model = new DefaultTableModel(new Object[][] {},
				new String[] { "Supplier", "Order Placed" });
		table.setModel(model);
		scrollPane.setViewportView(table);

	}

}
