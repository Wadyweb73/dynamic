package ui.panels;

import ui.styles.MainWindowComponentStyles;

import javax.swing.JPanel;
import javax.swing.JTextField;

import database.DBConnection;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
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
		nameLabel = MainWindowComponentStyles.configureLabelForInput("Usuario");
		passLabel = MainWindowComponentStyles.configureLabelForInput("Senha");
		nameField = MainWindowComponentStyles.configureInputField();
		passField = MainWindowComponentStyles.configureInputField();

		nameLabel.setFont(new Font("consolas", Font.PLAIN, 12));
		passLabel.setFont(new Font("consolas", Font.PLAIN, 12));
		nameLabel.setBackground(Color.RED);
		passLabel.setBackground(Color.RED);
		
		submitButton  = MainWindowComponentStyles.configureSubmitButton();
		submitButton.setBounds(435, 370, 100, 35);
		nameContainer = configureNameContainer();
		passContainer = configurePasswordContainer();
		mainPanel     = configureMainPanel();

		submitButton.addActionListener(this);
	}

	public JPanel configureNameContainer() {
		JPanel panel = new JPanel();

		panel.setLayout(new FlowLayout(FlowLayout.LEADING));
		panel.setBounds(430, 200, 400, 80);

		panel.add(nameLabel);
		panel.add(nameField);
		panel.setOpaque(false);
		
		return panel;
	}
	
	public JPanel configurePasswordContainer() {
		JPanel panel = new JPanel();
		
		panel.setLayout(new FlowLayout(FlowLayout.LEADING));
		panel.setBounds(430, 280, 400, 80);

		panel.add(passLabel);
		panel.add(passField);
		panel.setOpaque(false);
		
		return panel;
	}

	public JPanel configureMainPanel() {
		JPanel panel = new JPanel();
		
		panel.setLayout(null);
		panel.setOpaque(false);
		panel.setPreferredSize(new Dimension(1207, 620));
		panel.add(nameContainer);
		panel.add(passContainer);
		panel.add(submitButton);

		return panel;
	}

	public void clearFields() {
		nameLabel.setText("");
		passLabel.setText("");
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == submitButton) {
			String username = new String(nameField.getText());
			String password = new String(passField.getText());
			Boolean user_exists = false;
			
			String sql = "INSERT INTO users(usuario, senha) VALUES (?, ?)";
			String pesquisa = "SELECT usuario FROM users";

			try {
				PreparedStatement ps          = DBConnection.getConexao().prepareStatement(sql);
				PreparedStatement ps_pesquisa = DBConnection.getConexao().prepareStatement(pesquisa);

				ResultSet res = ps_pesquisa.executeQuery();

				while(res.next()) {
					if(res.getString("usuario").equals(username)) {
						user_exists = !user_exists;
					}
				}

				if(user_exists) {
					JOptionPane.showMessageDialog(mainPanel, "Já existe um usuario com este nome!");
				}
				else {
					if(username.isEmpty() || password.isEmpty()) {
						JOptionPane.showMessageDialog(mainPanel, "Nenhum campo pode ser vazio!!");
					}
					else {
						ps.setString(1, username);
						ps.setString(2, password);	
						
						ps.execute();
						System.out.print("\n\nUser created!");
						ps.close();
					}
				}
				
				clearFields();
				nameField.setRequestFocusEnabled(true);
			}
			catch(SQLException e) {
				e.getMessage();
			}
		}
	}
}