package ui.panels;

import static ui.MainWindow.rightSidePanel_main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import database.DBConnection;
import ui.MainWindow;

public class ShowQuotes implements MouseListener {
    public static JPanel panel, additionalInfoPanel;
    public static DefaultTableModel model;
    public static JButton assossiateQuoteWithClientButton, createNewClientThroughQuote;
    public static JTable table;
    public static JScrollPane scrollPane;

    public ShowQuotes() {
        assossiateQuoteWithClientButton = configureButton("Assossiate quote");
        createNewClientThroughQuote     = configureButton("Registar como cliente");
        
        model               = configureTableModel();
        table               = configureTable(model);
        scrollPane          = configureScrollPane(table);
        additionalInfoPanel = configureAdditionalInformationPanel();

        table.addMouseListener(this);
    }

    public static JButton configureButton(String text) {
        JButton button = new JButton(text);

        button.setBackground(new Color(0123456));
        button.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        button.setForeground(new Color(0xffffff));
        button.setVerticalAlignment(JButton.CENTER);
        button.setHorizontalAlignment(JButton.CENTER);        
        button.setFocusPainted(false);
        
        return button;
    }

    public DefaultTableModel configureTableModel() {
        DefaultTableModel model = new DefaultTableModel();

        model.setRowCount(0);
        model.setColumnCount(0);
        model.addColumn("ID da cot.");
        model.addColumn("Nome");
        model.addColumn("Telefone");
        model.addColumn("Endereco");
        model.addColumn("Cuso estimado");
        model.addColumn("Descricão");
        model.addColumn("Data");

        return model;
    }

    public static JTable configureTable(DefaultTableModel model) {
        JTable table = new JTable(model) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table.setBackground(new Color(0xcdcdcd));
        table.setForeground(new Color(0x123456));
        table.setOpaque(false);
        table.setRowHeight(30);        
        table.setFocusable(false);
        table.setFont(new Font("Consolas", Font.PLAIN, 15));
        
        return table;
    }

    public static JScrollPane configureScrollPane(JTable table) {
        JScrollPane scrollpane = new JScrollPane(table);

        scrollpane.getViewport().setOpaque(false);
        scrollpane.setOpaque(false);
        scrollpane.setPreferredSize(new Dimension(1200, 670));

        return scrollpane;
    }


    public JPanel configureAdditionalInformationPanel() {
        JPanel panel  = new JPanel();

        panel.setPreferredSize(new Dimension(100, 70));
        panel.setLayout(new FlowLayout());
        panel.setBackground(new Color(0x123456));

        panel.add(createNewClientThroughQuote);
        panel.add(assossiateQuoteWithClientButton);

        return panel;
    }


    public static void addContentFromMySQL(Object[] data) {
        model.addRow(data);
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        if(event.getSource() == table){
            if(event.getClickCount() == 1) {
                rightSidePanel_main.add(additionalInfoPanel, BorderLayout.SOUTH);
                rightSidePanel_main.repaint();
                rightSidePanel_main.revalidate();
            }
        }
    }
    //     else if(event.getSource() == createNewClientThroughQuote) {
    //         String query = "SELECT * FROM Quotes";
    //         Integer rows = table.get

    //         try {
    //             PreparedStatement ps = DBConnection.getConexao().prepareStatement(query);
                

    //         }
    //         catch(SQLException e) {
    //             JOptionPane.showMessageDialog(MainWindow.frame, e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
    //         }
    //     }
    // }

    @Override
    public void mouseEntered(MouseEvent event) {}

    @Override
    public void mouseExited(MouseEvent arg0) {}

    @Override
    public void mousePressed(MouseEvent arg0) {}

    @Override
    public void mouseReleased(MouseEvent arg0) {}
}
