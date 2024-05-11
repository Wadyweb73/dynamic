package ui.listeners;

import java.awt.FlowLayout;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import database.DBConnection;
import models.Client;
import ui.MainWindow;
import ui.panels.ClientInfoAndPayments;
import ui.panels.CreateUser;
import ui.panels.ServedClients;
import ui.panels.ShowClients;
import ui.panels.ShowComments;

public class  MainWindowActionEventListeners {
    public static void add_client_button_event_handler() {
        MainWindow.titleLabel.setText("REGISTER MENU");
        MainWindow.rightSidePanel_main.removeAll();

        MainWindow.rightSidePanel_main.setLayout(null);
        MainWindow.rightSidePanel_main.add(MainWindow.person_DataInputPanel);
        MainWindow.rightSidePanel_main.add(MainWindow.problemFieldContainer); 
        MainWindow.rightSidePanel_main.add(MainWindow.submitButtonContainer);
        MainWindow.rightSidePanel_main.revalidate();
        MainWindow.rightSidePanel_main.repaint();
    }

    public static void list_clients_button_event_handler() {
        String req = "SELECT * FROM client";
		MainWindow.titleLabel.setText("CLIENT LIST");

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
			
			MainWindow.rightSidePanel_main.removeAll();
			MainWindow.rightSidePanel_main.setLayout(new FlowLayout(FlowLayout.CENTER));
			MainWindow.rightSidePanel_main.add(showCLI.mainPanel);
			MainWindow.rightSidePanel_main.revalidate();
			MainWindow.rightSidePanel_main.repaint();
		}
		catch(SQLException e) {
			e.getStackTrace();
		}
    }

	public static void served_clients_button_action_event_listener () {
		// String query = "SELECT * FROM COMME";
		ServedClients servedClients = new ServedClients();

		MainWindow.titleLabel.setText("TAREFAS ATENTIDAS");
		MainWindow.rightSidePanel_main.removeAll();
		MainWindow.rightSidePanel_main.setLayout(new FlowLayout(FlowLayout.CENTER));
		MainWindow.rightSidePanel_main.add(servedClients.mainPanel);
		MainWindow.rightSidePanel_main.revalidate();
		MainWindow.rightSidePanel_main.repaint();
	}

	public static void not_served_clients_button_action_event_listener() {
		String req = "SELECT * FROM comments";

		PreparedStatement ps = null;

		try {
			ps = DBConnection.getConexao().prepareStatement(req);

			ResultSet res = ps.executeQuery();
			ShowComments comments = new ShowComments();

			while(res.next()) {
				String descrip = new String(res.getString("discription"));
				descrip = descrip.replace("\n", System.lineSeparator());

				Object[] data = {
					res.getString("id"),
					"",
					descrip
				};

				comments.addContentFromMySQL(data);
			}

			MainWindow.titleLabel.setText("NAO ATENDIDOS");
			MainWindow.rightSidePanel_main.removeAll();
			MainWindow.rightSidePanel_main.setLayout(new FlowLayout(FlowLayout.CENTER));
			MainWindow.rightSidePanel_main.add(comments.mainPanel);
			MainWindow.rightSidePanel_main.revalidate();
			MainWindow.rightSidePanel_main.repaint();
		}
		catch(SQLException e) {
			e.getStackTrace();
		}
	}

	public static void add_user_button_action_event_listener() {
		CreateUser novoUser = new CreateUser();

		MainWindow.titleLabel.setText("NOVO USUARIO");
		MainWindow.rightSidePanel_main.removeAll();
		MainWindow.rightSidePanel_main.setLayout(new FlowLayout(FlowLayout.CENTER));
		MainWindow.rightSidePanel_main.add(novoUser.mainPanel);
		MainWindow.rightSidePanel_main.revalidate();
		MainWindow.rightSidePanel_main.repaint();
	}

	public static void client_information_button_action_event_listener() {
			ClientInfoAndPayments clientInfo = new ClientInfoAndPayments();

			MainWindow.titleLabel.setText("INFORMACÃO DO CLIENTE");
			MainWindow.rightSidePanel_main.removeAll();
			MainWindow.rightSidePanel_main.setLayout(new FlowLayout(FlowLayout.CENTER));
			MainWindow.rightSidePanel_main.add(clientInfo.mainPanel);
			MainWindow.rightSidePanel_main.revalidate();
			MainWindow.rightSidePanel_main.repaint();
	}

	public static void submit_button_action_event_listner() {
		Client client = new Client();

		client.setName(MainWindow.field_name.getText()); 
		client.setBI(MainWindow.field_BI.getText()); 
		client.setEmail(MainWindow.field_email.getText());
		client.setResidence(MainWindow.field_residence.getText()); 
		client.setTell(MainWindow.field_tell.getText());

		if(client.getName().isEmpty() || client.getTell().isEmpty()) {
			JOptionPane.showMessageDialog(
				new MainWindow().frame,
				"Os campos nome e telefone não podem ser vazios!",
				"Empty fields",
				JOptionPane.OK_OPTION
			);
		}
		else {

			String sql_cli     = "INSERT INTO client (NAME, BI, RESIDENCE,	EMAIL, CONTACT) VALUES (?, ?, ?, ?, ?);";
			String sql_comment = "INSERT INTO comments (DISCRIPTION) VALUES (?);";
			
			try {
				PreparedStatement client_ps  = DBConnection.getConexao().prepareStatement(sql_cli);
				PreparedStatement comment_ps = DBConnection.getConexao().prepareStatement(sql_comment);

				client_ps.setString(1, client.getName     ());
				client_ps.setString(2, client.getBI       ());
				client_ps.setString(3, client.getResidence());
				client_ps.setString(4, client.getEmail    ());
				client_ps.setString(5, client.getTell     ());

				comment_ps.setString(1, MainWindow.field_problemDescription.getText());
				
				client_ps .execute();				
				comment_ps.execute();

				System.out.print("\n\n User registered and saved on DB!");
				JOptionPane.showConfirmDialog(
					new MainWindow().frame, 
					"Cliente registado!",
					"CLIENT REGISTER",
					JOptionPane.OK_OPTION
				);

				client_ps.close();
				comment_ps.close();
				resetFields();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void resetFields() {
		MainWindow.field_name.setText("");
		MainWindow.field_BI.setText("");
		MainWindow.field_email.setText("");
		MainWindow.field_tell.setText("");
		MainWindow.field_residence.setText("");
		MainWindow.field_problemDescription.setText("");
	}
}
