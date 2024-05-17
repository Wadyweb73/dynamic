package ui.panels;

import database.DBConnection;
import ui.*;
import ui.listeners.mainwindowlisteners.MainWindowActionEventListeners;

import static ui.styles.MainWindowComponentStyles.*;
import static ui.MainWindow.*;
import static ui.panels.ShowClients.*;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Dimension;

public class ClientInfoAndPayments implements ActionListener {
	public JFrame frame;
	public JPanel mainPanel;
	public JPanel top_panel, center_panel, bottom_panel;
	public JPanel bottom_left_panel, bottom_right_panel;
	public static JButton submitButton, cancelTask_button, endTaskButton;
	public JTextField field_searchClient;
	public JScrollPane jScrollPane;
	public JTable table;
	public DefaultTableModel model;
	public Border border;

	public JLabel amountToPay_label;
	public JTextField amountToPay_field;
	public JTextArea commentField;
	
	public ClientInfoAndPayments() {
		border = BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0x123456));
		
		cancelTask_button  = configureCancelTaskButton();
		endTaskButton      = configureEndTaskButton();
		submitButton       = configureSubmitSearchButton();
		amountToPay_field  = configureAmountToPayField(); 
		field_searchClient = configureSearchInputField();
		commentField       = configureCommentArea();
		model              = configureModel();
		table              = configureJTable(model);
		jScrollPane        = configureScrollPane(table);
		top_panel          = configureTopPanel();
		center_panel       = configureCenterPanel();
		bottom_left_panel  = configureBottomLeftPanel();
		bottom_right_panel = configureBottomRightPanel();
		bottom_panel       = configureBottomPanel();
		mainPanel          = configureMainPanel();

		submitButton.addActionListener(this);
		endTaskButton.addActionListener(this);
	}

	public JButton configureEndTaskButton() {
		JButton button = configureBarButtons("Concluir");

		button.setPreferredSize(new Dimension(127, 37));
		button.setBackground(Color.GREEN);
		
		return button;
	}
	
	public JButton configureSubmitSearchButton() {
		JButton button = configureBarButtons("Procurar");
		
		button.setPreferredSize(new Dimension(150, 37)); 
		button.setVerticalAlignment(JButton.CENTER);

		return button;
	}
	
	public JButton configureCancelTaskButton() {
		JButton button = configureBarButtons("Cancelar");

		button.setBackground(Color.RED);
		button.setPreferredSize(new Dimension(127, 37));
		
		return button;
	}

	public JTextField configureAmountToPayField() {
		JTextField field = configureInputField();

		field.setPreferredSize(new Dimension(260, 40));
		field.setText("  $ Valor do servico");
		
		return field;
	}

	public JTextArea configureCommentArea() {
		JTextArea area = configureInputForDiscription();

		area.setBounds(10, 10, 880, 200);
		area.setText("This is the field from which you can see the description!!");

		return area;
	}
	
	public JTextField configureSearchInputField()  {
		JTextField field = new JTextField();
		
		field.setPreferredSize(new Dimension(400, 37));
		field.setBackground(new Color(0xcdcdcd));
		field.setForeground(new Color(0x123456));
		field.setBorder(border);
		field.setHorizontalAlignment(JTextField.LEADING);
		field.setFont(new Font("consolas", Font.PLAIN, 15));
		
		return field;
	}

	public JScrollPane configureScrollPane(JTable table) {
		JScrollPane scrollpane = new JScrollPane(table);

		scrollpane.setPreferredSize(new Dimension(1207, 121));
		scrollpane.setOpaque(false);
		scrollpane.getViewport().setOpaque(false);

		return scrollpane;
	}

	public void addContentFromMySQL(Object[] obj) {
		model.addRow(obj);
	}
	
	public JPanel configureTopPanel() {
		JPanel panel = new JPanel();

		panel.setLayout(new FlowLayout(FlowLayout.TRAILING));
		panel.setPreferredSize(new Dimension(100, 50));
		panel.setBackground(new Color(0x123456));
		panel.setBorder(border);
		panel.add(field_searchClient);
		panel.add(submitButton);
		
		return panel;
	}
	
	public JPanel configureCenterPanel() {
		JPanel panel = new JPanel();
		
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel.setPreferredSize(new Dimension(100, 200));
		panel.setBackground(Color.LIGHT_GRAY);
		panel.add(jScrollPane);
		
		return panel;
	}
	
	public JPanel configureBottomLeftPanel() {
		JPanel panel = new JPanel();
		
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(900, 100));
		panel.setBackground(Color.LIGHT_GRAY);

		panel.add(commentField);
		
		return panel;
	} 
	
	public JPanel configureBottomRightPanel() {
		JPanel panel = new JPanel();
		
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel.setPreferredSize(new Dimension(300, 100));
		panel.setBackground(Color.LIGHT_GRAY);

		panel.add(amountToPay_field);
		panel.add(cancelTask_button);
		panel.add(endTaskButton);
		
		return panel;
	} 
	
	public JPanel configureBottomPanel() {
		JPanel panel = new JPanel();

		panel.setPreferredSize(new Dimension(100, 488));
		panel.setLayout(new BorderLayout(5, 0));
		panel.setBackground(Color.GRAY);

		panel.add(bottom_left_panel, BorderLayout.WEST);
		panel.add(bottom_right_panel, BorderLayout.EAST);
		
		return panel;
	}
	
	public JPanel configureMainPanel() {
		JPanel panel = new JPanel();
		
		panel.setLayout(new BorderLayout(0, 5));
		panel.setPreferredSize(new Dimension(1204, 672));
		panel.setBackground(Color.GRAY);
		panel.add(top_panel, BorderLayout.NORTH);
		panel.add(center_panel, BorderLayout.CENTER);
		panel.add(bottom_panel, BorderLayout.SOUTH);

		return panel;
	}

	public static void main(String[] args) {
		new ClientInfoAndPayments();
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		
		if(event.getSource() == submitButton) {
			String name        = field_searchClient.getText();
			String description = new String();
			String req         = "SELECT * FROM client WHERE name='" + name + "';";

			if(name.isEmpty()) {
				JOptionPane.showMessageDialog(
					MainWindow.rightSidePanel_main,
					"O campo de pesquisa nao pode ser vazio!",
					"Search error!", 
					JOptionPane.INFORMATION_MESSAGE
				);
			}
			else {
				try {
					PreparedStatement ps = DBConnection.getConexao().prepareStatement(req);
					ResultSet res = ps.executeQuery();
					
					if(res.next()) {
						Object[] data = {
							res.getInt("id"),
							res.getString("name"),
							res.getString("bi"),
							res.getString("Email"),
							res.getString("contact"),
							res.getString("residence"),
						};

						addContentFromMySQL(data);

						try {

							String findUserProblem = "SELECT * FROM comments com INNER JOIN client cli on com.id = cli.id WHERE cli.name = '" + name + "';";
							PreparedStatement ps_userProblem = DBConnection.getConexao().prepareStatement(findUserProblem);
							ResultSet _res = ps_userProblem.executeQuery();

							if(_res.next()) {
								description = _res.getString("discription");
								description = description.replace("\n", System.lineSeparator());	
							}

							ps.close();
							ps_userProblem.close();
							
						}
						catch(SQLException e) {
							e.getMessage();
						}

						commentField.setText("");
						commentField.append(description);
						commentField.revalidate();
						commentField.repaint();

						rightSidePanel_main.removeAll();
						rightSidePanel_main.setLayout(new FlowLayout(FlowLayout.CENTER));
						rightSidePanel_main.add(mainPanel);
						rightSidePanel_main.revalidate();
						rightSidePanel_main.repaint();
					}
					else {
						JOptionPane.showMessageDialog(
							rightSidePanel_main,
							"Cliente nao encontrado!",
							"Search ERROR", 
							JOptionPane.ERROR_MESSAGE
							);
						}
						ps.close();
					}
				catch(SQLException e) {
					e.getStackTrace();
				}
			}
		}
		else if(event.getSource() == endTaskButton) {
			if(amountToPay_field.getText().isEmpty()) {
				JOptionPane.showMessageDialog(
					rightSidePanel_main,
					"Insira o valor do servico!",
					"Erro de pagamento", 
					JOptionPane.INFORMATION_MESSAGE
				);
			}
			else {
				try {
					Double valor = Double.parseDouble(amountToPay_field.getText());
					String name  = field_searchClient.getText();
				
					try {
						String findUserProblem = "SELECT com.id, cli.id AS client_id FROM comments com INNER JOIN client cli ON com.client_id = cli.id WHERE cli.name = ?";
						String alterTaskStatus = "UPDATE comments SET task_status = 'SERVED' WHERE id = ?";
						String payment_query   = "INSERT INTO pagamento (id, valor, paydate) VALUES (?, ?, ?)";

						PreparedStatement ps_commentId = DBConnection.getConexao().prepareStatement(findUserProblem);
						PreparedStatement ps_updateCom = DBConnection.getConexao().prepareStatement(alterTaskStatus);
						PreparedStatement ps_payment   = DBConnection.getConexao().prepareStatement(payment_query  );
						
						ps_commentId.setString(1, name);
						
						ResultSet res = ps_commentId.executeQuery();
						System.err.println("Depois da execução da query");
						
						while (res.next()) {
							Integer commentId = res.getInt("id");
							System.err.println("ID do problema: " + commentId);
							ps_updateCom.setInt(1, res.getInt("id"));
							
							ps_payment.setInt(1, res.getInt("client_id"));
							ps_payment.setDouble(2, valor);
							ps_payment.setString(3, MainWindowActionEventListeners.getDateFromStorage());
							
							ps_payment.execute();
							ps_updateCom.executeUpdate();
						}
						
						res.close();
						ps_commentId.close();
						ps_payment.close();
						ps_updateCom.close();
				
						JOptionPane.showMessageDialog(mainPanel, "Pagamento efectuado com sucesso!", "Info: Pagamento", JOptionPane.INFORMATION_MESSAGE);
					} catch (SQLException e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(mainPanel, "Erro ao buscar o ID: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
					}
				} 
				catch(NumberFormatException e) {
					JOptionPane.showMessageDialog(
						rightSidePanel_main,
						"Insira um valor valido",
						"Payment Error", 
						JOptionPane.ERROR_MESSAGE
					);
				}
			}
		} 
	}
}