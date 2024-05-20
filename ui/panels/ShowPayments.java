package ui.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class ShowPayments {
    public static JScrollPane scrollPane;
    public static DefaultTableModel tableModel;
    public static JTable table;
    public static JPanel mainPanel;
    public static JButton payment_button;

    public ShowPayments () {
        tableModel                 = configureTableModel();
        table                      = configureTable(tableModel);
        scrollPane                 = configureScrollPane(table);
        mainPanel                  = configureMainPanel();
    }
    
   public DefaultTableModel configureTableModel() {
        DefaultTableModel model = new DefaultTableModel();

        model.setColumnCount(0);
        model.setRowCount(0);
        model.addColumn("Nome");
        model.addColumn("Valor");
		model.addColumn("Data de pagamento");	

        return model;
    }

    public JTable configureTable(DefaultTableModel model) {
        JTable table = new JTable(model);

        table.setBorder(null);
        table.setForeground(new Color(0x123456));
		table.setBackground(Color.LIGHT_GRAY);
		table.setFont(new Font("consolas", Font.PLAIN, 15));
		table.setRowHeight(30);
        table.setFocusable(false);

        for (int i = 0; i < table.getRowCount(); i++) {
            for (int y = 0; y < table.getColumnCount(); y++) {
                table.isCellEditable(i, y);
            }
        }
        
        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(0x123456));
        header.setForeground(new Color(0xcdcdcd));                    
        
        return table;
    }

    boolean isCellEditable(int row, int column) {
        return false;
    }

    public JScrollPane configureScrollPane(JTable table) {
        JScrollPane scrollPane = new JScrollPane(table);

        scrollPane.setPreferredSize(new Dimension(700, 670));
        scrollPane.setBorder(null);
        scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
        
        return scrollPane;
    }

    public JPanel configureMainPanel() {
        JPanel panel = new JPanel();

        panel.setLayout(new BorderLayout(0, 0));
        panel.setPreferredSize(new Dimension(1207, 673));
        panel.setBackground(Color.GRAY);

        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }
    
	public static void addContentFromMySQL(Object[] obj) {
		tableModel.addRow(obj);
	}
}
