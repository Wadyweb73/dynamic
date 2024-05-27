package ui;

import models.Client;

import static ui.listeners.mainwindowlisteners.MainWindowActionEventListeners.*;
import static ui.styles.MainWindowComponentStyles.*;

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
	
	public static JTextArea field_problemDescription;
	public static JButton barButton_addClient;
	public static JButton barButton_showClients;
	public static JButton barButton_clientInfo;
	public static JButton barButton_showDoneTasks;
	public static JButton barButton_purchaseBill;
	public static JButton barButton_showUndoneTasks;
	public static JButton barButton_addUser;
	public static JButton barButton_Menu;
	public static JButton barButton_payments;
	public static JButton submitButton;
	public static JButton logoutButton;
	
	public MainWindow() {
		barButton_showClients     = configureBarButtons("Listar clientes");
		barButton_addClient       = configureBarButtons("Ad. cliente");
		barButton_showDoneTasks   = configureBarButtons("Atendidadas");
		barButton_showUndoneTasks = configureBarButtons("Pendentes");
		barButton_clientInfo      = configureBarButtons("Info do cliente");
		barButton_addUser         = configureBarButtons("Novo usuario");
		barButton_Menu     	      = configureBarButtons("Home");
		barButton_payments        = configureBarButtons("Pagamentos");
		logoutButton              = configureBarButtons("Sair");
		label_name                = configureLabelForInput("Nome");
		label_email               = configureLabelForInput("Email");
		label_tell                = configureLabelForInput("Telefone");
		label_residence           = configureLabelForInput("Endereço");
		label_BI                  = configureLabelForInput("BI ou NUIT");
		problemDiscriptionLabel   = configureLabelForInput("Descricao do problema");
		titleLabel                = configureTitleLabel("DASHBOARD");

		field_problemDescription  = configureInputForDiscription ();
		problemFieldContainer     = configurePanelForDiscription ();
		field_name                = configureInputField          ();
		field_email               = configureInputField          ();
		field_tell                = configureInputField          ();
		field_residence           = configureInputField          ();
		field_BI                  = configureInputField          ();
		submitButton              = configureSubmitButton        ();
		person_DataInputPanel     = setClientRegisterPanel       (); 
		submitButtonContainer     = configureButtonContainer     ();
		rightSidePanel_top        = configure_rightSidePanel_Top ();
		rightSidePanel_main       = configure_rightSidePanel_Main();
		leftSidebar_Panel         = configureLeftPanel           ();
		rightSidebar_Panel        = configureRightPanel          ();
		frame                     = configureMainWindow          ();
		
		submitButton.addActionListener(this);
		barButton_Menu.addActionListener(this);
		barButton_payments.addActionListener(this);
		barButton_addClient.addActionListener(this);
		barButton_showClients.addActionListener(this);
		barButton_showUndoneTasks.addActionListener(this);
		barButton_showDoneTasks.addActionListener(this);
		barButton_clientInfo.addActionListener(this);
		barButton_addUser.addActionListener(this);
		logoutButton.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == barButton_addClient) {
			add_client_button_action_performed_handler();		
		}
		else if(event.getSource() == barButton_showClients) {
			list_clients_button_action_performed_handler();
		}
		else if(event.getSource() == barButton_showUndoneTasks) {
			not_served_clients_button_action_performed_handler();
		}
		else if(event.getSource() == barButton_clientInfo) {
			client_information_button_action_performed_handler();
		}
		else if(event.getSource() == barButton_showDoneTasks) {
			served_clients_button_action_performed_handler();
		}
		else if(event.getSource() == barButton_addUser) {
			add_user_button_action_performed_handler();			
		}
		else if(event.getSource() == submitButton) {
			submit_button_action_performed_handler();
		}
		else if(event.getSource() == barButton_payments) {
			payement_list_button_action_performed_handler();
		}
		else if(event.getSource() == barButton_Menu) {
			menu_button_action_performed_handler();
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


