package ui.listeners;

import java.awt.FlowLayout;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.*;
import java.time.format.DateTimeFormatter;

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
		try {
			String req    = "SELECT com.id AS comment_id, com.task_date, com.discription, com.payd,cli.id AS cli_id FROM comments AS com INNER JOIN client AS cli ON com.client_id = cli.id WHERE com.task_status <> 'PENDING';";

			PreparedStatement ps = DBConnection.getConexao().prepareStatement(req);
			
			ResultSet res = ps.executeQuery();
			ServedClients served = new ServedClients();
			
			while(res.next()) {
				String payment = new String("Não");
				if (res.getBoolean("payd")) {
					payment = new String("Sim");
				}

				Object[] data = {
					res.getString("comment_id"),
					res.getString("cli_id"),
					res.getString("discription"),
					payment,
					res.getString("task_date")
				};
				
				served.addContentFromMySQL(data);
			}

			MainWindow.titleLabel.setText("ATENDIDOS");
			MainWindow.rightSidePanel_main.removeAll();
			MainWindow.rightSidePanel_main.setLayout(new FlowLayout(FlowLayout.CENTER));
			MainWindow.rightSidePanel_main.add(served.mainPanel);
			MainWindow.rightSidePanel_main.revalidate();
			MainWindow.rightSidePanel_main.repaint();
		}
		catch(SQLException e) {
			e.getStackTrace();
		}
	}

	public static void not_served_clients_button_action_event_listener() {
		String req = "SELECT com.id AS comment_id, com.task_date, com.discription, com.payd,cli.id AS cli_id FROM comments AS com INNER JOIN client AS cli ON com.client_id = cli.id WHERE com.task_status = 'PENDING'";

		try {
			PreparedStatement ps = DBConnection.getConexao().prepareStatement(req);
			
			ResultSet res = ps.executeQuery();
			res.next();
			ShowComments comments = new ShowComments();
			
			while(res.next()) {
				String payment = new String("Não");
				if (res.getBoolean("payd")) {
					payment = new String("Sim");
				}

				Object[] data = {
					res.getString("comment_id"),
					res.getString("cli_id"),
					res.getString("discription"),
					payment,
					res.getString("task_date")
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

	public static void submit_button_action_event_listener() {
		Client client = new Client();

		client.setName(MainWindow.field_name.getText()); 
		client.setBI(MainWindow.field_BI.getText()); 
		client.setEmail(MainWindow.field_email.getText());
		client.setResidence(MainWindow.field_residence.getText()); 
		client.setTell(MainWindow.field_tell.getText());

		if(client.getName().isEmpty() || client.getTell().isEmpty()) {
			MainWindow mw = new MainWindow();
			MainWindow.frame = mw.configureMainWindow();
			JOptionPane.showMessageDialog(
			MainWindow.frame,
				"Os campos nome e telefone não podem ser vazios!",
				"Empty fields",
				JOptionPane.OK_OPTION
			);
		}
		else {
			String sql_cli         = "INSERT INTO client (NAME, BI, RESIDENCE,	EMAIL, CONTACT) VALUES (?, ?, ?, ?, ?);";
			String sql_comment     = "INSERT INTO comments (DISCRIPTION, TASK_STATUS, PAYD, client_id, TASK_DATE) VALUES (?, ?, ?, ?, ?);";
			String sql_getClientId = "SELECT id FROM client WHERE id = (SELECT MAX(id) FROM client)";
			
			try {
				PreparedStatement client_ps    = DBConnection.getConexao().prepareStatement(sql_cli);
				PreparedStatement comment_ps   = DBConnection.getConexao().prepareStatement(sql_comment);
				PreparedStatement client_id_ps = DBConnection.getConexao().prepareStatement(sql_getClientId);

				client_ps.setString(1, client.getName     ());
				client_ps.setString(2, client.getBI       ());
				client_ps.setString(3, client.getResidence());
				client_ps.setString(4, client.getEmail    ());
				client_ps.setString(5, client.getTell     ());
				client_ps.execute();				
				
				ResultSet res = client_id_ps.executeQuery();
				res.next();
				
				comment_ps.setString (1, MainWindow.field_problemDescription.getText());
				comment_ps.setString (2, "PENDING");
				comment_ps.setBoolean(3, false);
				comment_ps.setInt    (4, res.getInt("id"));
				comment_ps.setString (5, getDateFromStorage());

				comment_ps.execute();

				System.out.print("\n\n User registered and saved on DB!");
				JOptionPane.showMessageDialog(
					MainWindow.frame, 
					"Cliente registado!",
					"CLIENT REGISTER",
					JOptionPane.INFORMATION_MESSAGE
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

	public static String getDateFromStorage() {
		LocalDate date              = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formattedDate        = date.format(formatter);

		return formattedDate;
	}
}
