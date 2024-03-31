package ui;

import database.DBConnection;
import models.Client;
import ui.panels.*;

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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MainWindow extends Client implements ActionListener{
	public JFrame frame;
	public JLabel titleLabel;
	public ArrayList <Client> ClientList; 

	public JPanel leftSidebar_Panel;
	public JPanel rightSidebar_Panel;  
	public JPanel rightSidePanel_top;
	public JPanel rightSidePanel_main; 

	public JPanel person_DataInputPanel;
	public JPanel car_DataInputPanel;
	public JPanel problemFieldContainer;
	public JPanel submitButtonContainer;
	
	public JTextField field_name;
	public JTextField field_BI;
	public JTextField field_tell;
	public JTextField field_email;
	public JTextField field_residence;
	
	public JLabel label_name;
	public JLabel label_BI;
	public JLabel label_tell;
	public JLabel label_email;
	public JLabel label_residence;
	
	public JTextArea field_problemDescription;
	public JLabel problemDiscriptionLabel;
	
	public JButton barButton_addClient;
	public JButton barButton_showClients;
	public JButton barButton_clientInfo;
	public JButton barButton_showDoneProblems;
	public JButton barButton_showUndoneProblems;
	public JButton barButton_addUser;
	public JButton submitButton;
	
	public MainWindow() {
		ClientList = new ArrayList<Client>();

		barButton_showClients        = configureBarButtons("List Clients");
		barButton_addClient          = configureBarButtons("Add Client");
		barButton_showDoneProblems   = configureBarButtons("Done Tasks");
		barButton_showUndoneProblems = configureBarButtons("Undone Tasks");
		barButton_clientInfo         = configureBarButtons("Client Info");
		barButton_addUser            = configureBarButtons("Create User");

		label_name                   = configureLabelForInput("Name");
		label_email                  = configureLabelForInput("Email");
		label_tell                   = configureLabelForInput("Phone number");
		label_residence              = configureLabelForInput("Residence");
		label_BI                     = configureLabelForInput("Identity Ticked Number");
		problemDiscriptionLabel     = configureLabelForInput("Describe car problem");
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
		barButton_showUndoneProblems.addActionListener(this);
		barButton_clientInfo.addActionListener(this);
	}

	/* ======================= SETTING UP LEFT PANEL ======================= */ 

	protected JPanel configureLeftPanel() {
		JPanel panel = new JPanel();

		panel.setPreferredSize(new Dimension(150, 100));
		panel.setBackground(new Color(0x123456));
		panel.setLayout(new FlowLayout());

		panel.add(barButton_addClient);
		panel.add(barButton_showClients);
		panel.add(barButton_showDoneProblems);
		panel.add(barButton_showUndoneProblems);
		panel.add(barButton_clientInfo);
		panel.add(barButton_addUser);

		return panel;
	}

	public static JButton configureBarButtons(String textButton) {
		JButton button = new JButton(textButton);

		button.setBackground(new Color(214, 183, 148));
		button.setForeground(new Color(0x12356));
		button.setPreferredSize(new Dimension(120, 30));
		
		return button;
	}


	/* ====================== SETTING UP RIGHT PANEL ======================= */
	
	public static JTextField configureInputField()  {
		JTextField field = new JTextField();
		Border border = BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0x123456));
		
		field.setPreferredSize(new Dimension(300, 40));
		field.setBackground(new Color(0xcdcdcd));
		field.setForeground(new Color(0x123456));
		field.setBorder(border);
		field.setHorizontalAlignment(JTextField.LEADING);
		
		return field;
	}
	
	public JLabel configureLabelForInput(String type) {
		JLabel label = new JLabel(type);
		
		label.setForeground(new Color(0x123456));
		label.setPreferredSize(new Dimension(100, 30));
		label.setFont(new Font("Consolas", Font.BOLD, 15));
		
		return label;
	}
	
	public JTextArea configureInputForDiscription()  {
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
		
		button.setText("Confirmar");
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
		
		panel.setPreferredSize(new Dimension(50, 60));
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
		panel.setLayout(new BorderLayout(5, 5));
		
		panel.add(rightSidePanel_top, BorderLayout.NORTH);
		panel.add(rightSidePanel_main, BorderLayout.SOUTH);
		
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
	protected JFrame configureMainWindow() {
		JFrame frame = new JFrame();

		frame.setTitle("Auto Maputo Service");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(900, 745));
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);

		frame.add(leftSidebar_Panel, BorderLayout.WEST);
		frame.add(rightSidebar_Panel, BorderLayout.EAST);
		
		return frame;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == barButton_addClient) {
			
			titleLabel.setText("REGISTER MENU");
			rightSidePanel_main.removeAll();

			rightSidePanel_main.setLayout(null);
			rightSidePanel_main.add(person_DataInputPanel);
			rightSidePanel_main.add(problemFieldContainer); 
			rightSidePanel_main.add(submitButtonContainer);
			rightSidePanel_main.revalidate();
			rightSidePanel_main.repaint();

		}
		else if(event.getSource() == barButton_showClients) {
			String req = "SELECT * FROM client";
			titleLabel.setText("CLIENT LIST");

			try {
				PreparedStatement ps = DBConnection.getConexao().prepareStatement(req);
				ResultSet res = ps.executeQuery();
				ShowClients showCLI = new ShowClients();

				while(res.next()) {
						Object[] data = {
							res.getInt("id"),
							res.getString("name"),
							res.getString("bi"),
							res.getString("Email"),
							res.getString("contact"),
							res.getString("residence")
						};

						showCLI.addContentFromMySQL(data);
				}
				
				rightSidePanel_main.removeAll();
				rightSidePanel_main.setLayout(new FlowLayout(FlowLayout.CENTER));
				rightSidePanel_main.add(showCLI.mainPanel);
				rightSidePanel_main.revalidate();
				rightSidePanel_main.repaint();
			}
			catch(SQLException e) {
				e.getStackTrace();
			}
		}
		else if(event.getSource() == barButton_showUndoneProblems) {
			String req = "SELECT * FROM comments";

			PreparedStatement ps = null;

			try {
				ps = DBConnection.getConexao().prepareStatement(req);

				ResultSet res = ps.executeQuery();
				ShowComments comments = new ShowComments();

				while(res.next()) {
					Object[] data = {
						res.getString("id"),
						res.getString("discription")
					};

					comments.addContentFromMySQL(data);
				}

				titleLabel.setText("TODO LIST");
				rightSidePanel_main.removeAll();
				rightSidePanel_main.setLayout(new FlowLayout(FlowLayout.CENTER));
				rightSidePanel_main.add(comments.mainPanel);
				rightSidePanel_main.revalidate();
				rightSidePanel_main.repaint();
			}
			catch(SQLException e) {
				e.getStackTrace();
			}
		}
		else if(event.getSource() == barButton_clientInfo) {
			ClientInfo clientInfo = new ClientInfo();

			titleLabel.setText("INFORMACÃO DO CLIENTE");
			rightSidePanel_main.removeAll();
			rightSidePanel_main.setLayout(new FlowLayout(FlowLayout.CENTER));
			rightSidePanel_main.add(clientInfo.mainPanel);
			rightSidePanel_main.revalidate();
			rightSidePanel_main.repaint();
		}
		else if(event.getSource() == submitButton) {
			Client client = new Client();

			client.setName(field_name.getText()); 
			client.setBI(field_BI.getText()); 
			client.setEmail(field_email.getText());
			client.setResidence(field_residence.getText()); 
			client.setTell(field_tell.getText());

			String sql_cli = "INSERT INTO client (NAME, BI, RESIDENCE,	EMAIL, CONTACT) VALUES (?, ?, ?, ?, ?);";
			String sql_comment = "INSERT INTO comments (DISCRIPTION) VALUES (?);";
			
			PreparedStatement client_ps   = null;
			PreparedStatement comment_ps  = null;
			
			try {
				client_ps   = DBConnection.getConexao().prepareStatement(sql_cli);
				comment_ps  = DBConnection.getConexao().prepareStatement(sql_comment);

				client_ps.setString(1, client.getName     ());
				client_ps.setString(2, client.getBI       ());
				client_ps.setString(3, client.getResidence());
				client_ps.setString(4, client.getEmail    ());
				client_ps.setString(5, client.getTell     ());

				comment_ps.setString(1, field_problemDescription.getText());
				
				client_ps .execute();				
				comment_ps.execute();

				System.out.print("\n\n User registered and saved on DB!");

				resetFields();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		else if(event.getSource() == barButton_addUser) {

		}

	}


	public void resetFields() {
		field_name.setText("");
		field_BI.setText("");
		field_email.setText("");
		field_tell.setText("");
		field_residence.setText("");
		field_problemDescription.setText("");
	}


	public static void main(String[] a) {
		MainWindow mw = new MainWindow();
		mw.frame = mw.configureMainWindow();
	}
}


