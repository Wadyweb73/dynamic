package ui.styles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import ui.MainWindow;

public class MainWindowComponentStyles extends MainWindow {
    	/* ======================= SETTING UP LEFT PANEL ======================= */ 

	public static JPanel configureLeftPanel() {
		JPanel panel = new JPanel();

		panel.setPreferredSize(new Dimension(160, 100));
		panel.setBackground(new Color(0x123456));
		panel.setLayout(new FlowLayout());

		panel.add(barButton_addClient);
		panel.add(barButton_showClients);
		panel.add(barButton_showDoneTasks);
		panel.add(barButton_showUndoneTasks);
		panel.add(barButton_clientInfo);
		panel.add(barButton_addUser);
		panel.add(logoutButton);

		return panel;
	}

	public static JButton configureBarButtons(String textButton) {
		JButton button = new JButton(textButton);

		button.setBackground(new Color(0xaf7ca79));
		button.setForeground(new Color(0x12356));
		button.setFont(new Font("Consolas", Font.PLAIN, 15));
		button.setPreferredSize(new Dimension(150, 40));
		
		return button;
	}


	/* ====================== SETTING UP RIGHT PANEL ======================= */
	
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
		label.setFont(new Font("Consolas", Font.BOLD, 15));
		
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
		
		panel.setBounds(50, 420, 500, 200);
		panel.setLayout(new BorderLayout(2, 1));
		panel.setOpaque(false);
		
		problemDiscriptionLabel.setForeground(Color.WHITE);
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
		button.setForeground(new Color(0x12356));
		
		return button;
	}
	
	public static JPanel configureButtonContainer() {
		JPanel panel = new JPanel();

		panel.setBounds(450, 630, 100, 30);
		panel.setLayout(new GridLayout());

		panel.add(submitButton);

		return panel;
	}
	
	public static JLabel configureTitleLabel(String title) {
		JLabel label = new JLabel();
		
		label.setText(title);
		label.setForeground(new Color(0x123456));
		label.setFont(new Font("Consolas", Font.BOLD, 40));
		label.setVerticalAlignment(JLabel.CENTER);
		label.setHorizontalAlignment(JLabel.CENTER);
		
		return label;
	}
	
	public static JPanel configure_rightSidePanel_Top() {
		JPanel panel = new JPanel();
		
		panel.setPreferredSize(new Dimension(50, 65));
		panel.setBackground(new Color(214, 183, 148));
		panel.setLayout(new BorderLayout());

		panel.add(titleLabel);

		return panel;
	}
	
	public static JPanel configure_rightSidePanel_Main() {
		JPanel panel = new JPanel();
		
		panel.setPreferredSize(new Dimension(100, 684));
		panel.setBackground(Color.GRAY);
		panel.setLayout(null);

		return panel;
	}

	public static JPanel configureRightPanel() {
		JPanel panel = new JPanel();

		panel.setPreferredSize(new Dimension(1215, 100));
		panel.setLayout(new BorderLayout(1, 1));
		
		panel.add(rightSidePanel_top, BorderLayout.NORTH);
		panel.add(rightSidePanel_main, BorderLayout.CENTER);
		
		return panel;
	}	
	
	/*  ================== SETTING UP EVENTS FOR BAR BUTTONS ==================== */
	public static JPanel setClientDataPanel() {
		JPanel panel = new JPanel();

		panel.setOpaque(false);
		panel.setBounds(50, 10, 500, 400);
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

	/* ====================== SETTING UP MAIN WINDOW ====================== */ 
	public static JFrame configureMainWindow() {
		JFrame frame = new JFrame();

		frame.setTitle("Dynamic Services for Africa");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(900, 745));
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setLayout(new BorderLayout(1, 0));
		frame.setVisible(true);

		frame.add(leftSidebar_Panel,  BorderLayout.WEST);
		frame.add(rightSidebar_Panel, BorderLayout.CENTER);
		
		return frame;
	}
}
