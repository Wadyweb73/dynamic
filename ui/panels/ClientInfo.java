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
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Dimension;


public class ClientInfo extends JFrame implements ActionListener {
	public JFrame frame;
	public JPanel mainPanel;
	public JPanel top_panel, bottom_panel;
	public JButton _submitButton;
	public JTextField field_searchClient;
	public JScrollPane jScrollPane;
	public JTable table;
	public DefaultTableModel model;
	public Border border;
	
	public ClientInfo() {
		border = BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0x123456));

		_submitButton = MainWindow.configureSubmitButton();
		_submitButton.setPreferredSize(new Dimension(150, 37));
		_submitButton.setVerticalAlignment(JButton.CENTER);
		field_searchClient = MainWindow.configureInputField();
		field_searchClient.setPreferredSize(new Dimension(400, 37));

		model       = ShowClients.configureModel();
		table       = ShowClients.configureJTable(model);
		jScrollPane = new JScrollPane(table);

		jScrollPane.setPreferredSize(new Dimension(1207, 673));
		jScrollPane.setOpaque(false);
		jScrollPane.getViewport().setOpaque(false);

		_submitButton.addActionListener(this);
		
		top_panel    = configureTopPanel();
		bottom_panel = configureBottomPanel();
		mainPanel    = configureMainPanel();
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
	
	public JPanel configureTopPanel() {
		JPanel panel = new JPanel();

		panel.setLayout(new FlowLayout(FlowLayout.TRAILING));
		panel.setPreferredSize(new Dimension(100, 50));
		panel.setBorder(border);
		panel.add(field_searchClient);
		panel.add(_submitButton);
		
		return panel;
	}
	
	public JPanel configureBottomPanel() {
		JPanel panel = new JPanel();
		
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel.setPreferredSize(new Dimension(100, 400));
		panel.setOpaque(false);
		panel.setBorder(border);
		panel.add(jScrollPane);
		
		return panel;
	}
	
	public JPanel configureMainPanel() {
		JPanel panel = new JPanel();

		panel.setLayout(new BorderLayout());
		panel.setPreferredSize(new Dimension(1204, 673));
		panel.add(top_panel, BorderLayout.NORTH);
		panel.add(bottom_panel, BorderLayout.SOUTH);
				
		return panel;
	}
	

	public static void main(String[] args) {
		new ClientInfo();
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String name = field_searchClient.getText();
		String req = "SELECT * FROM client WHERE name=?";

		if(event.getSource() == _submitButton) {
			if(name.isEmpty()) {
				JOptionPane.showMessageDialog(mainPanel, "Search field cannot be empty!", "Search error!", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				try {
					System.out.print("\n\nNo try");
					PreparedStatement ps = DBConnection.getConexao().prepareStatement(req);
					ResultSet res = ps.executeQuery();
					System.out.print("\n\n" + res.getString("name" + "\n\n"));
				}
				catch(SQLException e) {
					e.getStackTrace();
				}
			}
		}
	}

}