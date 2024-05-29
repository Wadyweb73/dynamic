package ui.listeners.loginwindowlisteners;

import database.DBConnection;
import ui.MainWindow;
import static ui.Login.*;
import static ui.listeners.mainwindowlisteners.MainWindowActionEventHandlers.client_information_button_action_performed_handler;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.Timer;

public class LoginWindowActionEventHandlers implements EventListener{
    public static void  submit_button_action_performed_handler() {
		String username = new String(field_username.getText());
		String password = new String(field_password.getPassword());
		String sql      = "SELECT * FROM users";
		Boolean confirm = false;
		
		if(username.equals("admin") && password.equals("admin")) {
			errorMsgLabel.setText("Welcome");
			errorMsgLabel.setForeground(Color.GREEN);
			errorMsgLabel.revalidate();
			errorMsgLabel.repaint();
			
			confirm = !confirm;
			
			frame.dispose();
			new MainWindow();
		}
		
		if(!confirm) {
			try {

				System.out.print(sql);
				PreparedStatement ps = DBConnection.getConexao().prepareStatement(sql);
				ResultSet res = ps.executeQuery();

				while(res.next()) {
					if(res.getString("usuario").equals(username) && res.getString("senha").equals(password)) {
						confirm = !confirm;

						frame.dispose();
						new MainWindow();
						MainWindow.frame.remove(MainWindow.barButton_addUser);
						MainWindow.frame.remove(MainWindow.barButton_Menu);
						client_information_button_action_performed_handler();
					}
				}
				ps.close();
				

			} 
			catch(SQLException e) {  
				e.getMessage();
			}
		}
		
		if(!confirm) {
			errorMsgLabel.setText("Authentication Error!!");
			System.out.println("Antes do erro!");
			errorMsgLabel.setForeground(Color.RED);
			errorMsgLabel.revalidate();
			errorMsgLabel.repaint();

			Timer timer = new Timer(3000, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					errorMsgLabel.setText("");
					errorMsgLabel.setForeground(Color.RED);
					errorMsgLabel.revalidate();
					errorMsgLabel.repaint();
				}

			});

			timer.setRepeats(false);
			timer.start();
		}
	}
}