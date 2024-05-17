package ui;

import ui.listeners.loginwindowlisteners.LoginWindowActionEventListners;
import ui.styles.LoginWindowComponentStyles;

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

		titleLabel          = LoginWindowComponentStyles.configureTitleLabel();
		usernameInput_Label = LoginWindowComponentStyles.configureInputLabel("Usuario");
		passwordInput_Label = LoginWindowComponentStyles.configureInputLabel("Senha");
		field_username      = LoginWindowComponentStyles.configureUsernameField();
		field_password      = LoginWindowComponentStyles.configurePasswordField();
		
		submitButton        = LoginWindowComponentStyles.configureSubmitButton();
		errorMsgLabel       = LoginWindowComponentStyles.configureErrorMsgLabel(); 

		loginPanel          = LoginWindowComponentStyles.setLoginPanel();
		frame               = LoginWindowComponentStyles.setFrameLayout();

		submitButton.addActionListener(this);
	}

	/*=================== EVENTS ===================*/
	public void actionPerformed(ActionEvent event) {
        if(event.getSource() == submitButton) {
			LoginWindowActionEventListners.submit_button_action_performed_handler();
		}
	}
}
