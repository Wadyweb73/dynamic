package ui.panels;

import ui.MainWindow;

import javax.swing.JPanel;
import javax.swing.JTextField;

import database.DBConnection;

import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.*;

public class CreateUser implements ActionListener{
	public JPanel mainPanel;
	public JPanel nameContainer;
	public JPanel passContainer;

	JLabel nameLabel;
	JTextField nameField;
	JLabel passLabel;
	JTextField passField;
	JButton submitButton;

	public CreateUser() {
		nameLabel = new JLabel();
		passLabel = new JLabel();
		nameField = new JTextField(30);
		passField = new JTextField(30);

		nameLabel.setText("Nome");
		passLabel.setText("Palavra-passe");

		submitButton  = MainWindow.configureSubmitButton();
		nameContainer = configureNameContainer();
		passContainer = configurePasswordContainer();
		mainPanel     = configureMainPanel();

		submitButton.addActionListener(this);
	}

	public JPanel configureNameContainer() {
		JPanel panel = new JPanel();

		panel.setLayout(new FlowLayout());
		panel.add(nameLabel);
		panel.add(nameField);
		panel.setOpaque(false);

		return panel;
	}

	public JPanel configurePasswordContainer() {
		JPanel panel = new JPanel();
	
		panel.setLayout(new FlowLayout());
		panel.add(passLabel);
		panel.add(passField);
		panel.setOpaque(false);
	
		return panel;
	}

	public JPanel configureMainPanel() {
		JPanel panel = new JPanel();

		panel.setLayout(new GridLayout(2, 2));
		panel.setBackground(new Color(241, 120, 104));
		panel.setPreferredSize(new Dimension(1207, 620));
		panel.add(nameContainer);
		panel.add(passContainer);
		panel.add(submitButton);

		return panel;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == submitButton) {
			String username = new String(nameField.getText());
			String password = new String(passField.getText());
			
			String sql = "INSERT INTO users(usuario, senha) VALUES (?, ?)";

			try {
			PreparedStatement ps = DBConnection.getConexao().prepareStatement(sql);
				ps.setString(1, username);
				ps.setString(2, password);	

				System.out.print("\n\nUser created!");

				ps.execute();
				ps.close();
			}
			catch(SQLException e) {
				e.getMessage();
			}
		}
	}
}