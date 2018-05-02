package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;

class NextPage extends JFrame {
	JLabel labelDateTime ;
	NextPage(String username) {
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Welcome "+username);
		setSize(1000, 1000);
		getContentPane().setLayout(null);
		
		JDesktopPane desktop = new JDesktopPane();
		desktop.setBounds(12, 43, 958, 884);
		getContentPane().add(desktop);
		
		labelDateTime = new JLabel("");
		labelDateTime.setBounds(808, 13, 162, 23);
		getContentPane().add(labelDateTime);
		
		tickTock();
		getContentPane().add(labelDateTime);
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
		
		JMenuItem mntmCreateSale = new JMenuItem("Create Sale");
		mntmCreateSale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CreateSaleFrame csf = new CreateSaleFrame(username);
				desktop.add(csf);
				csf.setVisible(true);
				
			}
		});
		mnFile.add(mntmCreateSale);
		
		JMenuItem mntmReturn = new JMenuItem("Return");
		mnFile.add(mntmReturn);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mnFile.add(mntmExit);
		
		JMenu mnSettings = new JMenu("Settings");
		menuBar.add(mnSettings);
		
		JMenuItem mntmEmployee = new JMenuItem("Employee");
		mnSettings.add(mntmEmployee);
		
		JMenuItem mntmAdministrator = new JMenuItem("Administrator");
		mnSettings.add(mntmAdministrator);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmFindItems = new JMenuItem("Find Items");
		mnHelp.add(mntmFindItems);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mnHelp.add(mntmAbout);

		prepareGUI();
	}

	private void prepareGUI() {
		
		
	}
	
	public void tickTock() {
		labelDateTime.setText(DateFormat.getDateTimeInstance().format(new Date()));
    }
}
