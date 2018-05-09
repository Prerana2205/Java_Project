package com.pos.main;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import com.pos.frame.MainPageFrame;

/**
 * @author Prerana
 *
 */
public class SwingDesign extends javax.swing.JFrame {

	public SwingDesign() {

		prepareGUI();


	}

	private void prepareGUI() {

		getContentPane().setBackground(Color.WHITE);
		setTitle("Simple POS");
		getContentPane().setLayout(null);

		JButton btnLogIn = new JButton("Log In ");
		btnLogIn.setBackground(UIManager.getColor("Menu.selectionBackground"));
		btnLogIn.setBounds(108, 184, 97, 25);
		getContentPane().add(btnLogIn);

		JLabel lblUserName = new JLabel("Username");
		lblUserName.setBounds(35, 29, 170, 16);
		getContentPane().add(lblUserName);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(40, 89, 154, 16);
		getContentPane().add(lblPassword);

		JCheckBox chckbxNewCheckBox = new JCheckBox("Remember Me");
		chckbxNewCheckBox.setBackground(Color.WHITE);
		chckbxNewCheckBox.setBounds(35, 142, 113, 25);
		getContentPane().add(chckbxNewCheckBox);

		JTextArea messageTextArea = new JTextArea();
		messageTextArea.setEditable(false);
		messageTextArea.setBackground(new Color(255, 255, 255));
		messageTextArea.setBounds(12, 234, 237, 48);
		getContentPane().add(messageTextArea);

		JFormattedTextField usernameTextField = new JFormattedTextField();
		usernameTextField.setBounds(35, 58, 177, 25);
		getContentPane().add(usernameTextField);

		JPasswordField passwordTextField = new JPasswordField();
		passwordTextField.setBounds(35, 108, 177, 25);
		getContentPane().add(passwordTextField);

		// Action performed on clicking LOGIN
		btnLogIn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String inputUsername = usernameTextField.getText();
				char[] inputPasswordChar = passwordTextField.getPassword();
				String inputPassword = String.valueOf(inputPasswordChar);

				File loginDetails = new File("LoginDetails.txt");
				String detail, username, password;
				messageTextArea.setText("");
				try {
					Scanner input = new Scanner(loginDetails);
					while (input.hasNextLine()) {
						detail = input.nextLine();
						String[] string = detail.split(" ");
						username = string[0];
						password = string[1];
						if (inputUsername.equals(username) && inputPassword.equals(password)) {
							dispose();
							MainPageFrame page = new MainPageFrame(username);
							page.setVisible(true);

						} else {
							messageTextArea.setText("Enter the valid username and password");
						}

					}
					input.close();
					//
				} catch (Exception e) {
					messageTextArea.setText(e.getMessage());
				}

			}

		});

	}

	public static void main(String[] arg) {

		SwingDesign frame = new SwingDesign();
		frame.setSize(300, 400);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}
}