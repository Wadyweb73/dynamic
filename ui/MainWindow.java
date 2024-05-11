package ui;

import ui.listeners.MainWindowActionEventListeners;
import models.Client;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.border.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class MainWindow extends Client implements ActionListener{
	public static JFrame frame;
	public static JLabel titleLabel;
	public ArrayList <Client> ClientList; 

	public static JPanel leftSidebar_Panel;
	public static JPanel rightSidebar_Panel;  
	public static JPanel rightSidePanel_top;
	public static JPanel rightSidePanel_main; 

	public static JPanel person_DataInputPanel;
	public static JPanel car_DataInputPanel;
	public static JPanel problemFieldContainer;
	public static JPanel submitButtonContainer;
	
	public static JTextField field_name;
	public static JTextField field_BI;
	public static JTextField field_tell;
	public static JTextField field_email;
	public static JTextField field_residence;
	
	public JLabel label_name;
	public JLabel label_BI;
	public JLabel label_tell;
	public JLabel label_email;
	public JLabel label_residence;
	public JLabel problemDiscriptionLabel;
	
	public static JTextArea field_problemDescription;
	
	protected static JButton barButton_addClient;
	protected static JButton barButton_showClients;
	protected static JButton barButton_clientInfo;
	protected static JButton barButton_showDoneTasks;
	protected static JButton barButton_showUndoneTasks;
	protected static JButton barButton_addUser;
	protected static JButton submitButton;
	protected static JButton logoutButton;
	
	public MainWindow() {
		barButton_showClients        = configureBarButtons("Listar clientes");
		barButton_addClient          = configureBarButtons("Ad. cliente");
		barButton_showDoneTasks   = configureBarButtons("Atendidadas");
		barButton_showUndoneTasks = configureBarButtons("Pendentes");
		barButton_clientInfo         = configureBarButtons("Info do cliente");
		barButton_addUser            = configureBarButtons("Novo usuario");
		logoutButton                 = configureBarButtons("Sair");
		label_name                   = configureLabelForInput("Nome");
		label_email                  = configureLabelForInput("Email");
		label_tell                   = configureLabelForInput("Telefone");
		label_residence              = configureLabelForInput("Residencia");
		label_BI                     = configureLabelForInput("BI ou NUIT");
		problemDiscriptionLabel      = configureLabelForInput("Descricao do problema");
		titleLabel                   = configureTitleLabel("MAIN");
		field_problemDescription     = configureInputForDiscription();
		problemFieldContainer        = configurePanelForDiscription();
		field_name                   = configureInputField();
		field_email                  = configureInputField();
		field_tell                   = configureInputField();
		field_residence              = configureInputField();
		field_BI                     = configureInputField();
		person_DataInputPanel        = setClientDataPanel(); 
		submitButton                 = configureSubmitButton();
		submitButtonContainer        = configureButtonContainer();
		
		rightSidePanel_top           = configure_rightSidePanel_Top();
		rightSidePanel_main          = configure_rightSidePanel_Main();
		leftSidebar_Panel            = configureLeftPanel ();
		rightSidebar_Panel           = configureRightPanel();
		
		submitButton.addActionListener(this);
		barButton_addClient.addActionListener(this);
		barButton_showClients.addActionListener(this);
		barButton_showDoneTasks.addActionListener(this);
		barButton_showUndoneTasks.addActionListener(this);
		barButton_clientInfo.addActionListener(this);
		barButton_addUser.addActionListener(this);
		logoutButton.addActionListener(this);
	}

	/* ======================= SETTING UP LEFT PANEL ======================= */ 

	protected JPanel configureLeftPanel() {
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
	
	public JPanel configurePanelForDiscription() {
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
	
	public JPanel configureButtonContainer() {
		JPanel panel = new JPanel();

		panel.setBounds(450, 630, 100, 30);
		panel.setLayout(new GridLayout());

		panel.add(submitButton);

		return panel;
	}
	
	public JLabel configureTitleLabel(String title) {
		JLabel label = new JLabel();
		
		label.setText(title);
		label.setForeground(new Color(0x123456));
		label.setFont(new Font("Consolas", Font.BOLD, 40));
		label.setVerticalAlignment(JLabel.CENTER);
		label.setHorizontalAlignment(JLabel.CENTER);
		
		return label;
	}
	
	protected JPanel configure_rightSidePanel_Top() {
		JPanel panel = new JPanel();
		
		panel.setPreferredSize(new Dimension(50, 65));
		panel.setBackground(new Color(214, 183, 148));
		panel.setLayout(new BorderLayout());

		panel.add(titleLabel);

		return panel;
	}
	
	protected JPanel configure_rightSidePanel_Main() {
		JPanel panel = new JPanel();
		
		panel.setPreferredSize(new Dimension(100, 684));
		panel.setBackground(Color.GRAY);
		panel.setLayout(null);

		return panel;
	}

	protected JPanel configureRightPanel() {
		JPanel panel = new JPanel();

		panel.setPreferredSize(new Dimension(1215, 100));
		panel.setLayout(new BorderLayout(1, 1));
		
		panel.add(rightSidePanel_top, BorderLayout.NORTH);
		panel.add(rightSidePanel_main, BorderLayout.CENTER);
		
		return panel;
	}	
	
	/*  ================== SETTING UP EVENTS FOR BAR BUTTONS ==================== */
	public JPanel setClientDataPanel() {
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
	public JFrame configureMainWindow() {
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
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == barButton_addClient) {
			MainWindowActionEventListeners.add_client_button_event_handler();		
		}
		else if(event.getSource() == barButton_showClients) {
			MainWindowActionEventListeners.list_clients_button_event_handler();
		}
		else if(event.getSource() == barButton_showUndoneTasks) {
			MainWindowActionEventListeners.not_served_clients_button_action_event_listener();
		}
		else if(event.getSource() == barButton_clientInfo) {
			MainWindowActionEventListeners.client_information_button_action_event_listener();
		}
		else if(event.getSource() == barButton_showDoneTasks) {
			MainWindowActionEventListeners.served_clients_button_action_event_listener();
		}
		else if(event.getSource() == barButton_addUser) {
			MainWindowActionEventListeners.add_user_button_action_event_listener();			
		}
		else if(event.getSource() == submitButton) {
			MainWindowActionEventListeners.submit_button_action_event_listener();
		}
		else if(event.getSource() == logoutButton) {
			frame.dispose();
			new Login();
		}
	}

	public static void main(String[] a) {
		MainWindow mw = new MainWindow();
		MainWindow.frame = mw.configureMainWindow();
	}
}


