package ui.panels;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

public class UnservedClients {
	public JPanel mainPanel;
	public DefaultTableModel model;
	public JScrollPane jScrollPane;
	public JTable table;

	public UnservedClients() {
		model       = configureModel();
		table       = configureJTable(model);
		jScrollPane = configureScrollPane(table);		
		mainPanel   = configureMainPanel();
	}

	public DefaultTableModel configureModel() {
		DefaultTableModel model = new DefaultTableModel(5, 5);

		model.setColumnCount(0);
		model.setRowCount(0);
        model.addColumn("Numero de requisicao");
        model.addColumn("Id do cliente");
        model.addColumn("Descricao do problema");
		model.addColumn("Pago");
		model.addColumn("Data de requisicao");	
		return model;
	}

	public static JTable configureJTable(DefaultTableModel model) {
		JTable table = new JTable(model);

		table.setBackground(Color.LIGHT_GRAY);
		table.setForeground(new Color(0x123456));
		table.setFont(new Font("consolas", Font.PLAIN, 15));
		table.getColumnModel().getColumn(0).setMaxWidth(50);
		table.getColumnModel().getColumn(2).setPreferredWidth(300);
		table.setRowHeight(30);

		JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(0x123456));
        header.setForeground(new Color(0xcdcdcd));

		return table;
	}

	public JScrollPane configureScrollPane(JTable table) {
		JScrollPane scrollPane = new JScrollPane(table);

		scrollPane.setPreferredSize(new Dimension(1207, 670));
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);

		return scrollPane;
	}
	
	public JPanel configureMainPanel() {
		JPanel panel = new JPanel();
		
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel.setPreferredSize(new Dimension(1204, 673));
		panel.setOpaque(false);
		
		panel.add(jScrollPane);
		
		return panel;
	}

	public void addContentFromMySQL(Object[] obj) {
		model.addRow(obj);
	}

}
