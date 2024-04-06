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
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Dimension;


public class ClientInfo implements ActionListener {
	public JFrame frame;
	public JPanel mainPanel;
	public JPanel top_panel, center_panel, bottom_panel;
	public JPanel bottom_left_panel, bottom_right_panel;
	public static JButton _submitButton;
	public JTextField field_searchClient;
	public JScrollPane jScrollPane;
	public JTable table;
	public DefaultTableModel model;
	public Border border;
	
	public ClientInfo() {
		border = BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0x123456));

		_submitButton = MainWindow.configureBarButtons("Procurar");
		_submitButton.setPreferredSize(new Dimension(150, 37)); 
		_submitButton.setVerticalAlignment(JButton.CENTER);
		
		field_searchClient = MainWindow.configureInputField();
		field_searchClient.setPreferredSize(new Dimension(400, 37));
		field_searchClient.setBackground(Color.LIGHT_GRAY);
		field_searchClient.setForeground(Color.WHITE);
		field_searchClient.setFont(new Font("consolas", Font.PLAIN, 15));

		model       = ShowClients.configureModel();
		table       = ShowClients.configureJTable(model);
		jScrollPane = new JScrollPane(table);
		
		jScrollPane.setPreferredSize(new Dimension(1207, 121));
		jScrollPane.setOpaque(false);
		jScrollPane.getViewport().setOpaque(false);

		_submitButton.addActionListener(this);
		
		top_panel    = configureTopPanel();
		center_panel = configureCenterPanel();
		bottom_left_panel  = configureBottomLeftPanel();
		bottom_right_panel = configureBottomRightPanel();
		bottom_panel = configureBottomPanel();
		mainPanel    = configureMainPanel();
		// mainPanel.setBackground(new Color(0xaf7ca79));
	}

	public JButton configure_SubmitButton() {
		JButton button = new JButton();
		
		button.setText("Confirmar");
		button.setBackground(new Color(214, 183, 148));
		button.setBorder(border);
		button.setForeground(new Color(0x12356));
		
		return button;
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
		
		panel.setPreferredSize(new Dimension(100, 100));
		panel.setBackground(Color.LIGHT_GRAY);
		
		return panel;
	} 

	public JPanel configureBottomRightPanel() {
		JPanel panel = new JPanel();
		
		panel.setPreferredSize(new Dimension(100, 100));
		panel.setBackground(Color.LIGHT_GRAY);
		
		return panel;
	} 
	
	public JPanel configureBottomPanel() {
		JPanel panel = new JPanel();

		panel.setPreferredSize(new Dimension(100, 488));
		panel.setLayout(new GridLayout(1, 2, 5, 0));
		panel.setBackground(Color.GRAY);

		panel.add(bottom_left_panel);
		panel.add(bottom_right_panel);
		
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
		new ClientInfo();
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		
		if(event.getSource() == _submitButton) {
			String name = field_searchClient.getText();
			String req = "SELECT * FROM client WHERE name='" + name + "';";

			if(name.isEmpty()) {
				JOptionPane.showMessageDialog(
					MainWindow.rightSidePanel_main,
					"Search field cannot be empty!",
					"Search error!", 
					JOptionPane.INFORMATION_MESSAGE
				);
			}
			else {
				try {
					System.out.print("\n\nNo try");
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

						MainWindow.rightSidePanel_main.removeAll();
						MainWindow.rightSidePanel_main.setLayout(new FlowLayout(FlowLayout.CENTER));
						MainWindow.rightSidePanel_main.add(mainPanel);
						MainWindow.rightSidePanel_main.revalidate();
						MainWindow.rightSidePanel_main.repaint();
					}
					else {
						JOptionPane.showMessageDialog(
							MainWindow.rightSidePanel_main,
							"O cliente nao existe!",
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
	}

}