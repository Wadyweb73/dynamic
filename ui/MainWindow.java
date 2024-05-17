package ui;

import models.Client;
import ui.listeners.mainwindowlisteners.MainWindowActionEventListeners;
import ui.styles.MainWindowComponentStyles;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTextField;

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
	
	public static JLabel label_name;
	public static JLabel label_BI;
	public static JLabel label_tell;
	public static JLabel label_email;
	public static JLabel label_residence;
	public static JLabel problemDiscriptionLabel;
	
	public    static JTextArea field_problemDescription;
	public static JButton barButton_addClient;
	public static JButton barButton_showClients;
	public static JButton barButton_clientInfo;
	public static JButton barButton_showDoneTasks;
	public static JButton barButton_showUndoneTasks;
	public static JButton barButton_addUser;
	public static JButton submitButton;
	public static JButton logoutButton;
	
	public MainWindow() {
		barButton_showClients     = MainWindowComponentStyles.configureBarButtons("Listar clientes");
		barButton_addClient       = MainWindowComponentStyles.configureBarButtons("Ad. cliente");
		barButton_showDoneTasks   = MainWindowComponentStyles.configureBarButtons("Atendidadas");
		barButton_showUndoneTasks = MainWindowComponentStyles.configureBarButtons("Pendentes");
		barButton_clientInfo      = MainWindowComponentStyles.configureBarButtons("Info do cliente");
		barButton_addUser         = MainWindowComponentStyles.configureBarButtons("Novo usuario");
		logoutButton              = MainWindowComponentStyles.configureBarButtons("Sair");
		label_name                = MainWindowComponentStyles.configureLabelForInput("Nome");
		label_email               = MainWindowComponentStyles.configureLabelForInput("Email");
		label_tell                = MainWindowComponentStyles.configureLabelForInput("Telefone");
		label_residence           = MainWindowComponentStyles.configureLabelForInput("Residencia");
		label_BI                  = MainWindowComponentStyles.configureLabelForInput("BI ou NUIT");
		problemDiscriptionLabel   = MainWindowComponentStyles.configureLabelForInput("Descricao do problema");
		titleLabel                = MainWindowComponentStyles.configureTitleLabel("MAIN");
		field_problemDescription  = MainWindowComponentStyles.configureInputForDiscription();
		problemFieldContainer     = MainWindowComponentStyles.configurePanelForDiscription();
		field_name                = MainWindowComponentStyles.configureInputField();
		field_email               = MainWindowComponentStyles.configureInputField();
		field_tell                = MainWindowComponentStyles.configureInputField();
		field_residence           = MainWindowComponentStyles.configureInputField();
		field_BI                  = MainWindowComponentStyles.configureInputField();
		person_DataInputPanel     = MainWindowComponentStyles.setClientDataPanel(); 
		submitButton              = MainWindowComponentStyles.configureSubmitButton();
		submitButtonContainer     = MainWindowComponentStyles.configureButtonContainer();
		
		rightSidePanel_top        = MainWindowComponentStyles.configure_rightSidePanel_Top();
		rightSidePanel_main       = MainWindowComponentStyles.configure_rightSidePanel_Main();
		leftSidebar_Panel         = MainWindowComponentStyles.configureLeftPanel ();
		rightSidebar_Panel        = MainWindowComponentStyles.configureRightPanel();

		frame                     = MainWindowComponentStyles.configureMainWindow();
		
		submitButton.addActionListener(this);
		barButton_addClient.addActionListener(this);
		barButton_showClients.addActionListener(this);
		barButton_showDoneTasks.addActionListener(this);
		barButton_showUndoneTasks.addActionListener(this);
		barButton_clientInfo.addActionListener(this);
		barButton_addUser.addActionListener(this);
		logoutButton.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == barButton_addClient) {
			MainWindowActionEventListeners.add_client_button_action_performed_handler();		
		}
		else if(event.getSource() == barButton_showClients) {
			MainWindowActionEventListeners.list_clients_button_action_performed_handler();
		}
		else if(event.getSource() == barButton_showUndoneTasks) {
			MainWindowActionEventListeners.not_served_clients_button_action_performed_handler();
		}
		else if(event.getSource() == barButton_clientInfo) {
			MainWindowActionEventListeners.client_information_button_action_performed_handler();
		}
		else if(event.getSource() == barButton_showDoneTasks) {
			MainWindowActionEventListeners.served_clients_button_action_performed_handler();
		}
		else if(event.getSource() == barButton_addUser) {
			MainWindowActionEventListeners.add_user_button_action_performed_handler();			
		}
		else if(event.getSource() == submitButton) {
			MainWindowActionEventListeners.submit_button_action_performed_handler();
		}
		else if(event.getSource() == logoutButton) {
			frame.dispose();
			new Login();
		}
	}

	public static void main(String[] a) {
		new MainWindow();
	}
}


