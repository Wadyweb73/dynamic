package ui;

import ui.listeners.loginwindowlisteners.LoginWindowActionEventListners;
import static ui.styles.LoginWindowComponentStyles.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login implements ActionListener{
	public static JFrame frame;
	public static JLayeredPane layeredPane; 
	public static JPanel loginPanel;
	public static JButton submitButton;
	public static JLabel usernameInput_Label;
	public static JLabel passwordInput_Label;
	public static JLabel titleLabel, errorMsgLabel, backgroundLabel;

	public static JTextField field_username;
	public static JPasswordField field_password;	
	
	public Login() {
		layeredPane = new JLayeredPane();

		titleLabel          = configureTitleLabel();
		usernameInput_Label = configureInputLabel("Usuario");
		passwordInput_Label = configureInputLabel("Senha");
		field_username      = configureUsernameField();
		field_password      = configurePasswordField();
		
		submitButton        = configureSubmitButton();
		errorMsgLabel       = configureErrorMsgLabel(); 

		loginPanel          = setLoginPanel();
		frame               = setFrameLayout();

		submitButton.addActionListener(this);
	}

	/*=================== EVENTS ===================*/
	public void actionPerformed(ActionEvent event) {
        if(event.getSource() == submitButton) {
			LoginWindowActionEventListners.submit_button_action_performed_handler();
		}
	}
}
