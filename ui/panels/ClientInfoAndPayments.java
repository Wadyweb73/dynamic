package ui.panels;

import database.DBConnection;
import ui.*;

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
	public static JButton _submitButton, cancelTask_button, done_button;
	public JTextField field_searchClient;
	public JScrollPane jScrollPane;
	public JTable table;
	public DefaultTableModel model;
	public Border border;

	public JLabel amountToPay_label;
	public JTextField amountToPay_field;
	public JLabel commentID;
	public JTextArea comment;
	
	public ClientInfoAndPayments() {
		border = BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0x123456));
		
		cancelTask_button = MainWindow.configureBarButtons("Cancelar");
		done_button       = MainWindow.configureBarButtons("Concluir");
		_submitButton     = MainWindow.configureBarButtons("Procurar");

		cancelTask_button.setPreferredSize(new Dimension(127, 37));
		done_button.setPreferredSize(new Dimension(127, 37));

		_submitButton.setPreferredSize(new Dimension(150, 37)); 
		_submitButton.setVerticalAlignment(JButton.CENTER);
		cancelTask_button.setBackground(Color.RED);
		done_button.setBackground(Color.GREEN);
		
		amountToPay_field  = MainWindow.configureInputField();
		amountToPay_field.setPreferredSize(new Dimension(260, 40));
		amountToPay_field.setText("  $ Valor do servico");
		field_searchClient = MainWindow.configureInputField();
		field_searchClient.setPreferredSize(new Dimension(400, 37));
		field_searchClient.setBackground(Color.LIGHT_GRAY);
		field_searchClient.setForeground(Color.WHITE);
		field_searchClient.setFont(new Font("consolas", Font.PLAIN, 15));

		comment = MainWindow.configureInputForDiscription();
		comment.setBounds(10, 10, 880, 200);
		comment.setText("This is the field from which you can see the description!!");

		model       = ShowClients.configureModel();
		table       = ShowClients.configureJTable(model);
		jScrollPane = new JScrollPane(table);
		
		jScrollPane.setPreferredSize(new Dimension(1207, 121));
		jScrollPane.setOpaque(false);
		jScrollPane.getViewport().setOpaque(false);

		_submitButton.addActionListener(this);
		done_button.addActionListener(this);
		
		top_panel          = configureTopPanel();
		center_panel       = configureCenterPanel();
		bottom_left_panel  = configureBottomLeftPanel();
		bottom_right_panel = configureBottomRightPanel();
		bottom_panel       = configureBottomPanel();
		mainPanel          = configureMainPanel();
	}
	
	public JTextField configureInputField()  {
		JTextField field = new JTextField();
		
		field.setPreferredSize(new Dimension(300, 40));
		field.setBackground(new Color(0xcdcdcd));
		field.setForeground(new Color(0x123456));
		field.setBorder(border);
		field.setHorizontalAlignment(JTextField.LEADING);
		
		return field;
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
		panel.add(_submitButton);
		
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

		panel.add(comment);
		
		return panel;
	} 
	
	
	public JPanel configureBottomRightPanel() {
		JPanel panel = new JPanel();
		
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel.setPreferredSize(new Dimension(300, 100));
		panel.setBackground(Color.LIGHT_GRAY);

		panel.add(amountToPay_field);
		panel.add(cancelTask_button);
		panel.add(done_button);
		
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
		
		if(event.getSource() == _submitButton) {
			String name = field_searchClient.getText();
			String defaultString = "\n\n\tNome do Cliente: " + name + "\n\tData: 12 de Abril de 2024\n\t";
			String req = "SELECT * FROM client WHERE name='" + name + "';";

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
								String description = _res.getString("discription");
								defaultString = defaultString + "Problema: " + description + "\n\t"; 
								System.err.println(defaultString);
							}

							ps.close();
							ps_userProblem.close();
							
						}
						catch(SQLException e) {
							e.getMessage();
						}

						comment.setText(defaultString);
						comment.revalidate();
						comment.repaint();

						MainWindow.rightSidePanel_main.removeAll();
						MainWindow.rightSidePanel_main.setLayout(new FlowLayout(FlowLayout.CENTER));
						MainWindow.rightSidePanel_main.add(mainPanel);
						MainWindow.rightSidePanel_main.revalidate();
						MainWindow.rightSidePanel_main.repaint();
					}
					else {
						JOptionPane.showMessageDialog(
							MainWindow.rightSidePanel_main,
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
		else if(event.getSource() == done_button) {
			if(amountToPay_field.getText().isEmpty()) {
				JOptionPane.showMessageDialog(
					MainWindow.rightSidePanel_main,
					"Insira o valor do servico!",
					"submit ERROR", 
					JOptionPane.INFORMATION_MESSAGE
				);
			}
			else {
				try {
					Double valor = Double.parseDouble(amountToPay_field.getText());
					// String name = field_searchClient.getText();
					
					try {

						String sql = "INSERT INTO pagamento (id, valor) VALUES (?, ?)";
						// String findUserProblem = "SELECT id FROM comments com INNER JOIN client cli on com.id = cli.id WHERE cli.name = '" + name + "';";
						// PreparedStatement ps_id = DBConnection.getConexao().prepareStatement(findUserProblem);
						
						PreparedStatement ps = DBConnection.getConexao().prepareStatement(sql);

						// ResultSet res = ps_id.executeQuery();
						// res.next();
						// System.err.println(res.getString("id"));

						ps.setString(1, 1 + "");
						ps.setString(2, "" + valor);

						ps.execute();
						JOptionPane.showMessageDialog(mainPanel, "Pagamento efectuado com sucesso!");
						ps.close();
					}
					catch(SQLException e) {
						e.getMessage();
					}
				}
				catch(NumberFormatException e) {
					JOptionPane.showMessageDialog(
						MainWindow.rightSidePanel_main,
						"Insira um valor valido",
						"Payment Error", 
						JOptionPane.ERROR_MESSAGE
					);
				}
			}
		} 
		
	}

}