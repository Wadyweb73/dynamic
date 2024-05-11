package ui.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

public class ServedClients {
    JScrollPane scrollPane;
    DefaultTableModel tableModel;
    JFrame frame;
    JTable table;
    public JPanel mainPanel;

    public ServedClients() {
        tableModel = configureTableModel();
        table      = configureTable(tableModel);
        scrollPane = configureScrollPane(table);
        mainPanel  = configureMainPanel();
    }
    
    DefaultTableModel configureTableModel() {
        DefaultTableModel model = new DefaultTableModel(5, 5);

        model.setColumnCount(0);
        model.setRowCount(2);
        model.addColumn("Id");
        model.addColumn("Id do cliente");
        model.addColumn("Descricao do problema");
        model.addColumn("Valor do Serviço");
        
        return model;
    }

    public JTable configureTable(DefaultTableModel model) {
        JTable table = new JTable(model);

        table.setForeground(new Color(0x123456));
		table.setBackground(Color.LIGHT_GRAY);
		table.setFont(new Font("consolas", Font.PLAIN, 15));
		table.setRowHeight(30);
        
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
        panel.setPreferredSize(new Dimension(1207, 673));
        panel.setOpaque(false);

        panel.add(scrollPane);
        
        return panel;
    }
    
    public static void main(String[] a) {
        new ServedClients();
    }
    
}
