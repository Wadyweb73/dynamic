package ui.listeners.mainwindowlisteners;

import static ui.MainWindow.*;

import database.DBConnection;
import models.Client;

import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.*;
import java.time.format.DateTimeFormatter;

import ui.panels.ClientInfoAndPayments;
import ui.panels.CreateUser;
import ui.panels.Menu;
import ui.panels.ServedClients;
import ui.panels.ShowClients;
import ui.panels.UnservedClients;
import ui.styles.MainWindowComponentStyles;


public class  MainWindowActionEventListeners {
    public static void add_client_button_action_performed_handler() {
        titleLabel.setText("REGISTER MENU");
        rightSidePanel_main.removeAll();

        rightSidePanel_main.setLayout(null);
        rightSidePanel_main.add(person_DataInputPanel);
        rightSidePanel_main.add(problemFieldContainer); 
        rightSidePanel_main.add(submitButtonContainer);
        rightSidePanel_main.revalidate();
        rightSidePanel_main.repaint();
    }

    public static void list_clients_button_action_performed_handler() {
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

	public static void served_clients_button_action_performed_handler() {
		try {
			String req           = "SELECT com.id AS comment_id, com.task_date, com.discription, com.payd,cli.id AS cli_id FROM comments AS com INNER JOIN client AS cli ON com.client_id = cli.id WHERE com.task_status <> 'PENDING';";
			PreparedStatement ps = DBConnection.getConexao().prepareStatement(req);
			ResultSet res        = ps.executeQuery();
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

			titleLabel.setText("ATENDIDOS");
			rightSidePanel_main.removeAll();
			rightSidePanel_main.setLayout(new FlowLayout(FlowLayout.CENTER));
			rightSidePanel_main.add(ServedClients.mainPanel);
			rightSidePanel_main.revalidate();
			rightSidePanel_main.repaint();
		}
		catch(SQLException e) {
			e.getStackTrace();
		}
	}

	public static void not_served_clients_button_action_performed_handler() {
		String req = "SELECT com.id AS comment_id, com.task_date, com.discription, com.payd,cli.id AS cli_id FROM comments AS com INNER JOIN client AS cli ON com.client_id = cli.id WHERE com.task_status = 'PENDING'";

		try {
			PreparedStatement ps     = DBConnection.getConexao().prepareStatement(req);
			ResultSet res            = ps.executeQuery();
			UnservedClients unserved = new UnservedClients();
			
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
				
				unserved.addContentFromMySQL(data);
			}

			titleLabel.setText("NAO ATENDIDOS");
			rightSidePanel_main.removeAll();
			rightSidePanel_main.setLayout(new FlowLayout(FlowLayout.CENTER));
			rightSidePanel_main.add(UnservedClients.mainPanel);
			rightSidePanel_main.revalidate();
			rightSidePanel_main.repaint();
		}
		catch(SQLException e) {
			e.getStackTrace();
		}
	}

	public static void add_user_button_action_performed_handler() {
		CreateUser novoUser = new CreateUser();

		titleLabel.setText("NOVO USUARIO");
		rightSidePanel_main.removeAll();
		rightSidePanel_main.setLayout(new FlowLayout(FlowLayout.CENTER));
		rightSidePanel_main.add(novoUser.mainPanel);
		rightSidePanel_main.revalidate();
		rightSidePanel_main.repaint();
	}

	public static void client_information_button_action_performed_handler() {
			ClientInfoAndPayments clientInfo = new ClientInfoAndPayments();

			titleLabel.setText("INFORMACÃO DO CLIENTE");
			rightSidePanel_main.removeAll();
			rightSidePanel_main.setLayout(new FlowLayout(FlowLayout.CENTER));
			rightSidePanel_main.add(clientInfo.mainPanel);
			rightSidePanel_main.revalidate();
			rightSidePanel_main.repaint();
	}

	public static void menu_button_action_performed_handler() {
		new Menu();
		rightSidePanel_main.removeAll();
		rightSidePanel_main.setLayout(new BorderLayout(1, 1));
		rightSidePanel_main.add(Menu.mainpanel, BorderLayout.CENTER);
		rightSidePanel_main.repaint();
		rightSidePanel_main.revalidate();
	}

	public static void submit_button_action_performed_handler() {
		Client client = new Client();

		client.setName(field_name.getText()); 
		client.setBI(field_BI.getText()); 
		client.setEmail(field_email.getText());
		client.setResidence(field_residence.getText()); 
		client.setTell(field_tell.getText());

		if(client.getName().isEmpty() || client.getTell().isEmpty()) {
			frame = MainWindowComponentStyles.configureMainWindow();
			JOptionPane.showMessageDialog(
			frame,
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
				
				comment_ps.setString (1, field_problemDescription.getText());
				comment_ps.setString (2, "PENDING");
				comment_ps.setBoolean(3, false);
				comment_ps.setInt    (4, res.getInt("id"));
				comment_ps.setString (5, getDateFromStorage());

				comment_ps.execute();

				System.out.print("\n\n User registered and saved on DB!");
				JOptionPane.showMessageDialog(
					frame, 
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
		field_name.setText("");
		field_BI.setText("");
		field_email.setText("");
		field_tell.setText("");
		field_residence.setText("");
		field_problemDescription.setText("");
	}

	public static String getDateFromStorage() {
		LocalDate date              = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formattedDate        = date.format(formatter);

		return formattedDate;
	}
}
