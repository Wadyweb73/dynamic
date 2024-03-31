package ui.panels;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

public class ShowComments extends JFrame {
	public JPanel mainPanel;
	public DefaultTableModel model;
	public JScrollPane jScrollPane;
	public JTable table;

	public ShowComments() {
		model = configureModel();
		table   = new JTable(model);
		jScrollPane = new JScrollPane(table);

		table.setBackground(Color.GRAY);
		table.setForeground(new Color(0x123456));
		table.setFont(new Font("consolas", Font.PLAIN, 15));
		table.getColumnModel().getColumn(0).setMaxWidth(40);
		table.getColumnModel().getColumn(1).setPreferredWidth(700);
		table.setOpaque(false);
		jScrollPane.setPreferredSize(new Dimension(1204, 673));
		jScrollPane.setOpaque(false);
		jScrollPane.getViewport().setOpaque(false);
		
		table.setRowHeight(30);
		
		mainPanel = configureMainPanel();
	}


	public DefaultTableModel configureModel() {
		DefaultTableModel model = new DefaultTableModel(5, 5);

		model.setColumnCount(0);
		model.setRowCount(0);
		model.addColumn("ID");
		model.addColumn("DISCRIPTION");
		model.addColumn("DATE");

		return model;
	}

	public void addContentFromMySQL(Object[] obj) {
		model.addRow(obj);
	}

	public JPanel configureMainPanel() {
		JPanel panel = new JPanel();

		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel.setPreferredSize(new Dimension(1204, 673));
		panel.setOpaque(false);
		
		panel.add(jScrollPane);

		return panel;
	}

}
