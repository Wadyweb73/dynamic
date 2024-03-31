package ui.panels;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.table.*;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ShowClients {
	public JPanel mainPanel;
	public static DefaultTableModel model;
	public static JScrollPane jScrollPane;
	public static int targetColumnIndex;
	public static JTable table;
	
	public ShowClients() {
		super();
		targetColumnIndex = 6;
		model       = configureModel();
		table       = configureJTable(model);
		jScrollPane = new JScrollPane(table);

		jScrollPane.setPreferredSize(new Dimension(1207, 673));
		jScrollPane.setOpaque(false);
		jScrollPane.getViewport().setOpaque(false);
		
		mainPanel = configureMainPanel();
	}

	public static JTable configureJTable(DefaultTableModel model) {
		JTable table = new JTable(model);

		table.setBackground(Color.GRAY);
		table.getColumnModel().getColumn(6).setCellRenderer(new StatusColumnCellRenderer());
		table.getColumnModel().getColumn(0).setMaxWidth(30);
		table.getColumnModel().getColumn(3).setPreferredWidth(200);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.getColumnModel().getColumn(5).setPreferredWidth(150);
		table.getColumnModel().getColumn(6).setPreferredWidth(50);
		table.setForeground(new Color(0x123456));
		table.setFont(new Font("consolas", Font.PLAIN, 15));
		table.setRowHeight(30);

		return table;
	}

	public static DefaultTableModel configureModel() {
		DefaultTableModel model = new DefaultTableModel(5, 5);

		model.setColumnCount(0);
		model.setRowCount(0);
		model.addColumn("ID");
		model.addColumn("Name");
		model.addColumn("BI");
		model.addColumn("Email");
		model.addColumn("Tell");
		model.addColumn("Residence");
		model.addColumn("STATUS");

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

	static class StatusColumnCellRenderer extends DefaultTableCellRenderer {
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

			if(column == 6) {
				model.setValueAt("pending", row, column);
				cellComponent.setForeground(Color.RED);
			}
			else {
				cellComponent.setBackground(table.getBackground());
			}
			
			return cellComponent;
		}
	}
}

