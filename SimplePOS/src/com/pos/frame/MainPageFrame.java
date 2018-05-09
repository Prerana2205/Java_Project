package com.pos.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.Timer;

import com.pos.frame.report.CashierReport;
import com.pos.frame.report.DrawerManagementFrame;
import com.pos.frame.report.InventoryReport;
import com.pos.frame.report.RegisterReport;
import com.pos.input.SystemInput;
import com.pos.main.SwingDesign;

/**
 * @author Prerana
 *
 */
public class MainPageFrame extends JFrame {
	JLabel labelDateTime;
	private JTextField textFieldRegisterNumber;
	long cashierEndTime;
	long cashierStartTime;
	SystemInput systemInput;

	public MainPageFrame(String userName) {
		systemInput = new SystemInput();
		systemInput.setLogOnDate();
		systemInput.setLogOnTime();
		systemInput.setUserName(userName);
		systemInput.setRegisterNumber("1");

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Welcome " + userName);
		setSize(1000, 779);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		JDesktopPane desktop = new JDesktopPane();
		desktop.setBounds(12, 43, 958, 884);
		getContentPane().add(desktop);

		labelDateTime = new JLabel("");
		labelDateTime.setBounds(808, 13, 162, 23);
		getContentPane().add(labelDateTime);

		tickTock();
		getContentPane().add(labelDateTime);

		textFieldRegisterNumber = new JTextField();
		textFieldRegisterNumber.setEditable(false);
		textFieldRegisterNumber.setBounds(80, 8, 116, 22);
		getContentPane().add(textFieldRegisterNumber);
		textFieldRegisterNumber.setColumns(10);

		textFieldRegisterNumber.setText("1");

		JLabel lblNewLabel = new JLabel("Register#");
		lblNewLabel.setBounds(12, 13, 73, 16);
		getContentPane().add(lblNewLabel);
		Timer timer = new Timer(500, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tickTock();
			}
		});
		timer.setRepeats(true);
		timer.setCoalesce(true);
		timer.setInitialDelay(0);
		timer.start();

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		// CREATE SALE
		JMenuItem mntmCreateSale = new JMenuItem("Create Sale");
		mntmCreateSale.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				desktop.removeAll();
				desktop.updateUI();
				SaleFrame csf = new SaleFrame(systemInput, Integer.parseInt(textFieldRegisterNumber.getText()));
				desktop.add(csf);
				csf.setVisible(true);

			}
		});
		mnFile.add(mntmCreateSale);

		// RETURN SALE
		JMenuItem mntmReturn = new JMenuItem("Return");
		mntmReturn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				desktop.removeAll();
				desktop.updateUI();
				ReturnFrame crf = new ReturnFrame(systemInput, Integer.parseInt(textFieldRegisterNumber.getText()));
				desktop.add(crf);
				crf.setVisible(true);
			}
		});
		mnFile.add(mntmReturn);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mnFile.add(mntmExit);

		JMenu mnSettings = new JMenu("Management");
		menuBar.add(mnSettings);

		JMenuItem mntmInventorymanagement = new JMenuItem("Inventory Management");
		mntmInventorymanagement.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				desktop.removeAll();
				desktop.updateUI();
				InventoryManagementFrame imf = new InventoryManagementFrame();
				desktop.add(imf);
				imf.setVisible(true);
			}
		});
		mnSettings.add(mntmInventorymanagement);

		JMenuItem mntmDrawerManagement = new JMenuItem("Drawer Management");
		mntmDrawerManagement.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				desktop.removeAll();
				desktop.updateUI();
				DrawerManagementFrame drawer = new DrawerManagementFrame();
				desktop.add(drawer);
				drawer.setVisible(true);
			}
		});
		mnSettings.add(mntmDrawerManagement);

		/*JMenuItem mntmNewMenuItem = new JMenuItem("Cashier Management");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				desktop.removeAll();
				desktop.updateUI();
				CashierManagementFrame cashier = new CashierManagementFrame();
				desktop.add(cashier);
				cashier.setVisible(true);
				cashier.getSize();


				Dimension parentSize = desktop.getSize();
				Dimension childSize = cashier.getSize();

				//set center screen
				cashier.setLocation((parentSize.width - childSize.width)/2, (parentSize.height - childSize.height)/2);


			}
		});
		mnSettings.add(mntmNewMenuItem);*/



		JMenu mnReports = new JMenu("Reports");
		menuBar.add(mnReports);

		JMenuItem mntmRegister = new JMenuItem("Register");
		mntmRegister.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				desktop.removeAll();
				desktop.updateUI();
				RegisterReport registerReport = new RegisterReport();
				desktop.add(registerReport);
				registerReport.setVisible(true);
			}

		});
		mnReports.add(mntmRegister);

		JMenuItem mntmInventory = new JMenuItem("Inventory");
		mntmInventory.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				desktop.removeAll();
				desktop.updateUI();
				InventoryReport inventoryReport = new InventoryReport();
				desktop.add(inventoryReport);
				inventoryReport.setVisible(true);

			}
		});
		mnReports.add(mntmInventory);

		JMenuItem mntmCashier = new JMenuItem("Cashier");
		mntmCashier.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {


				desktop.updateUI();
				CashierReport cashierReport = new CashierReport(systemInput);
				desktop.add(cashierReport);
				cashierReport.setVisible(true);


			}
		});
		mnReports.add(mntmCashier);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);


		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				desktop.removeAll();
				desktop.updateUI();
				AboutFrame af = new AboutFrame();
				desktop.add(af);
				af.setVisible(true);
			}
		});
		mnHelp.add(mntmAbout);

		JMenuItem mntmLogout = new JMenuItem("Logout");
		mntmLogout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cashierEndTime = System.currentTimeMillis() / 1000 / 60;
				/*
				 * Cashier cashier = new
				 * Cashier(username,cashierStartTime,cashierEndTime,
				 * Integer.parseInt(textFieldRegisterNumber.getText()), );
				 */
				dispose();
				SwingDesign sd = new SwingDesign();
				sd.setSize(300, 400);
				sd.setVisible(true);
				sd.setLocationRelativeTo(null);
				addDataToRegister(systemInput);
			}
		});
		mnHelp.add(mntmLogout);


		prepareGUI();
	}

	public void addDataToRegister(SystemInput systemInput) {

		File file = new File("Register.txt");
		FileWriter fileWriter;
		try {
			if (file.exists()) {

				fileWriter = new FileWriter(file, true);

			} else {
				fileWriter = new FileWriter(file);
			}
			BufferedWriter output = new BufferedWriter(fileWriter);
			String registerOutput = systemInput.getRegisterNumber() + " " + systemInput.getUserName() + " "
					+ systemInput.getLogOnDate() + " " + systemInput.getLogOffDate() + " " + systemInput.getLogOnTime()
					+ " " + systemInput.getLogOffTime() + " " + systemInput.getTotalSalesAmount();
			output.write(registerOutput);
			output.newLine();
			output.close();
			systemInput = null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void prepareGUI() {

	}

	public void tickTock() {
		labelDateTime.setText(DateFormat.getDateTimeInstance().format(new Date()));
	}

	public void recordCashierLogTime() {

		// long loggedTime = endTimeInSeconds - beginTimeInSeconds;

	}
}
