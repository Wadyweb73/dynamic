package ui.styles;

import ui.Login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class LoginWindowComponentStyles extends Login {
    public static JLabel configureInputLabel(String type) {
		JLabel label = new JLabel(type);
		
		label.setForeground(new Color(0x123456));
		label.setFont(new Font("monsterrat", Font.PLAIN, 15));
		
		return label;
	}
	
	public static JTextField configureUsernameField() {
		JTextField field = new JTextField();
		Border border = BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0xEBF8FE));

		field.setForeground(new Color(0x123456));
		field.setFont(new Font("Consolas", Font.BOLD, 14));
		field.setBorder(null);
		field.setBorder(border);
		
		return field;
	}

	public static JPasswordField configurePasswordField() {
		JPasswordField field = new JPasswordField();
		Border border = BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0xEBF8FE));

		field.setForeground(new Color(0x123456));
		field.setFont(new Font("Consolas", Font.BOLD, 14));
		field.setBorder(null);
		
		field.setEchoChar('*');
		field.setBorder(border);
		
		return field;
	}

	public static JButton configureSubmitButton() {
		JButton button = new JButton();
		
		button.setText("Login");
		button.setFont(new Font("Consolas", Font.PLAIN, 20));
		button.setBackground(new Color(0x123456));
		button.setForeground(new Color(0xffffff));
		button.setFocusPainted(false);
		button.setBorder(BorderFactory.createEmptyBorder(1, 1, 1,1));

		return button;
	}

	public static JLabel configureErrorMsgLabel() {
		JLabel label  = new JLabel();
		Border border = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.RED);

		label.setFont(new Font("Consolas", Font.PLAIN, 20));
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setBorder(border);
		
		return label;
	}
	
	public static JLabel configureBackgroundImageLabel(int width, int height) {
		String imagePath  = "public/images/9024.jpg";
		ImageIcon icon    = new ImageIcon(imagePath);
		Image image       = icon.getImage().getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING); 
		icon = new ImageIcon(image);
		JLabel imageLabel = new JLabel(icon); 
		
		return imageLabel;
	}
	
	public static JLabel configureTitleLabel() {
		JLabel label = new JLabel();
		
		label.setText("Entrar");
		label.setFont(new Font("Consolas", Font.BOLD, 40));
		label.setForeground(new Color(0x123456));
		label.setBounds(60, 100, 100, 100);
		label.setVerticalAlignment(JLabel.CENTER);
		label.setSize(200, 30);
		
		return label;
	}
	
	public static JPanel setLoginPanel() {
		JPanel panel                    = new JPanel();
		JPanel centerPanel              = new JPanel();
		JPanel titleLabelContainerPanel = new JPanel();		
		
		titleLabelContainerPanel.setBackground(new Color(0xEBF8FE));
		titleLabelContainerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		titleLabelContainerPanel.add(titleLabel);

		usernameInput_Label.setBounds(55, 40, 290, 30);
		passwordInput_Label.setBounds(55, 110, 290, 30);
		field_username.setBounds(55, 70, 290, 30);
		field_password.setBounds(55, 140, 290, 30);
		submitButton.setBounds(245, 180, 100, 30);
		errorMsgLabel.setBounds(45, 240, 315, 30);
		
		centerPanel.setLayout(null);
		centerPanel.setBackground(new Color(0x89D5F5));
		centerPanel.add(usernameInput_Label);
		centerPanel.add(field_username);
		centerPanel.add(passwordInput_Label);
		centerPanel.add(field_password);
		centerPanel.add(submitButton);
		centerPanel.add(errorMsgLabel);
		
		panel.setLayout(new BorderLayout());
		panel.setBackground(new Color(0x89D5F5));
		panel.setBounds(480, 180, 400, 355);
		panel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0x12345)));

		panel.add(titleLabelContainerPanel, BorderLayout.NORTH);
		panel.add(centerPanel, BorderLayout.CENTER);
		
		return panel;
	}
	
	public static JFrame setFrameLayout() {
		JFrame frame = new JFrame();
		
		frame.setTitle("Dynamic App");
		frame.setResizable(true);
		frame.setSize(new Dimension(1366, 743));
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new FlowLayout());
		
		backgroundLabel = configureBackgroundImageLabel(frame.getWidth(), frame.getHeight());
		backgroundLabel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
		
		layeredPane.add(backgroundLabel, Integer.valueOf(0));
		layeredPane.add(loginPanel, Integer.valueOf(1));
		
		frame.setContentPane(layeredPane);
		frame.setVisible(true);
		
		return frame;
	}		
}
