package com.pos.frame;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JTextArea;

/**
 * @author Prerana
 *
 */
public class AboutFrame extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the frame.
	 */
	public AboutFrame() {
		setClosable(true);
		setBounds(12, 20, 920, 840);
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("About Software");
		setSize(1000, 1000);
		getContentPane().setLayout(null);

		JTextArea txtrVersion = new JTextArea();
		txtrVersion.setText("Simple POS");
		txtrVersion.setBounds(52, 37, 330, 31);
		getContentPane().add(txtrVersion);

		JTextArea textArea = new JTextArea();
		textArea.setText("Version: 1.0");
		textArea.setBounds(52, 81, 330, 31);
		getContentPane().add(textArea);

	}
}
