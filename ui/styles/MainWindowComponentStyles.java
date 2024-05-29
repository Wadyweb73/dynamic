package ui.styles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import static ui.MainWindow.*;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import ui.panels.Menu;

public class MainWindowComponentStyles {

	public static JPanel configureLeftPanel() {
		JPanel panel = new JPanel();

		panel.setPreferredSize(new Dimension(155, 100));
		panel.setBackground(new Color(0x5D6D7E));
        panel.setBorder(BorderFactory.createMatteBorder(0, 0, 0,1, Color.WHITE));
		panel.setLayout(new FlowLayout());

		logoutButton.setBackground(new Color(0xffffff));
        logoutButton.setText(null);
        ImageIcon icon = new ImageIcon("public/images/logout-red-simple-icon.png");
        Image img = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        logoutButton.setIcon(new ImageIcon(img));
		logoutButton.setForeground(new Color(0xffffff));
		logoutButton.setFont(new Font("Consolas", Font.BOLD, 15));

		panel.add(barButton_Menu);
		panel.add(barButton_addClient);
		panel.add(barButton_showClients);
		panel.add(barButton_showDoneTasks);
		panel.add(barButton_showUndoneTasks);
        panel.add(barButton_quoteRegistration);
		panel.add(barButton_showQuotes);
		panel.add(barButton_payments);
		panel.add(barButton_clientInfo);
		panel.add(barButton_addUser);
		panel.add(logoutButton);

		return panel;
	}

	public static JButton configureBarButtons(String textButton) {
		JButton button = new JButton(textButton);

		button.setBackground(new Color(0xEBF8FE));
		button.setForeground(new Color(0x100000));
		button.setFocusPainted(false);
		button.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));
		button.setFont(new Font("Consolas", Font.PLAIN, 15));
		button.setPreferredSize(new Dimension(147, 40));
		
		return button;
	}

	public static JTextField configureInputField()  {
		JTextField field = new JTextField();
		Border border = BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0x123456));
		
		field.setPreferredSize(new Dimension(300, 40));
		field.setBackground(Color.WHITE);
		field.setForeground(new Color(0x123456));
		field.setBorder(border);
		field.setHorizontalAlignment(JTextField.LEADING);
		
		return field;
	}
	
	public static JLabel configureLabelForInput(String type) {
		JLabel label = new JLabel(type);
		
		label.setForeground(new Color(0x123456));
		label.setPreferredSize(new Dimension(100, 30));
		label.setFont(new Font("Consolas", Font.PLAIN, 15));
		
		return label;
	}
	
	public static JTextArea configureInputForDiscription()  {
		JTextArea field = new JTextArea("");
		Border border = BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0x123456));
		
		field.setForeground(new Color(0x123456));
		field.setBackground(new Color(0xcdcdcd));
		field.setFont(new Font("Consolas", Font.ITALIC, 15));
		field.setPreferredSize(new Dimension(100, 168));
		field.setBorder(border);
		
		return field;
	}
	
	public static JPanel configurePanelForDiscription() {
		JPanel panel = new JPanel();
		
		panel.setBounds(295, 420, 550, 200);
		panel.setLayout(new BorderLayout(2, 1));
		panel.setOpaque(false);
		
		problemDiscriptionLabel.setForeground(new Color(0x12345));
		problemDiscriptionLabel.repaint();
		
		panel.add(problemDiscriptionLabel, BorderLayout.NORTH);
		panel.add(field_problemDescription, BorderLayout.SOUTH);
		
		return panel;
	}
	
	public static JButton configureSubmitButton() {
		JButton button = new JButton();
		Border border = BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0x123456));
		
		button.setText("Guardar");
		button.setBackground(new Color(214, 183, 148));
		button.setBorder(border);
		button.setFocusPainted(false);
		button.setForeground(new Color(0x12356));
		
		return button;
	}
	
	public static JPanel configureButtonContainer() {
		JPanel panel = new JPanel();

		panel.setBounds(745, 630, 100, 30);
		panel.setLayout(new GridLayout());

		panel.add(submitButton);

		return panel;
	}
	
	public static JLabel configureTitleLabel(String title) {
		JLabel label = new JLabel();
		
		label.setText(title);
		label.setForeground(new Color(0xffffff));
		label.setFont(new Font("Consolas", Font.BOLD, 40));
		label.setVerticalAlignment(JLabel.CENTER);
		label.setHorizontalAlignment(JLabel.CENTER);
		
		return label;
	}
	
	public static JPanel configure_rightSidePanel_Top() {
		JPanel panel = new JPanel();
		
		panel.setPreferredSize(new Dimension(100, 55));
		panel.setBackground(new Color(0x5D6D7E));
		panel.setLayout(new BorderLayout());

		panel.setBorder(BorderFactory.createMatteBorder(0, 0, 1,0, Color.WHITE));

		panel.add(titleLabel);

		return panel;
	}
	
	public static JPanel configure_rightSidePanel_Main() {
		JPanel panel = new JPanel();
		
		panel.setPreferredSize(new Dimension(100, 100));
		panel.setBackground(new Color(0xF0F0F0));
		panel.setLayout(new FlowLayout());

		new Menu();
		panel.add(Menu.mainPanel);

		return panel;
	}

	public static JPanel configureRightPanel() {
		JPanel panel = new JPanel();

		panel.setPreferredSize(new Dimension(100, 100));
		panel.setLayout(new BorderLayout(1, 1));
		panel.setBackground(new Color(0xE3F2FD));
		
		panel.add(rightSidePanel_main, BorderLayout.CENTER);
		
		return panel;
	}	
	
	public static JPanel setClientRegisterPanel() {
		JPanel panel = new JPanel();

		panel.setOpaque(false);
		panel.setBounds(295, 10, 550, 400);
		panel.setLayout(new GridLayout(11, 1));
		
		panel.add(label_name);
		panel.add(field_name);

		panel.add(label_BI);
		panel.add(field_BI);
		
		panel.add(label_tell);
		panel.add(field_tell);
		
		panel.add(label_email);
		panel.add(field_email);
		
		panel.add(label_residence);
		panel.add(field_residence);
		
		return panel;
	}

	public static JFrame configureMainWindow() {
		JFrame frame = new JFrame();

		frame.setTitle("Dynamic Services for Africa");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(900, 745));
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setLayout(new BorderLayout(0, 0));
		frame.setVisible(true);

        frame.add(rightSidePanel_top, BorderLayout.NORTH);
		frame.add(leftSidebar_Panel,  BorderLayout.WEST);
		frame.add(rightSidebar_Panel, BorderLayout.CENTER);
		
		return frame;
	}
}
