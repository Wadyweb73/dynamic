package ui.panels;

import static ui.MainWindow.*;
import static ui.listeners.mainwindowlisteners.MainWindowActionEventHandlers.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

public class ShowQuotes implements MouseListener, ActionListener {
    public static JPanel panel, additionalInfoPanel;
    public static DefaultTableModel model;
    public static JButton assossiateQuoteWithClientButton, createNewClientThroughQuoteButton, removeQuoteButton;
    public static JTable table;
    public static JScrollPane scrollPane;
    public static Integer selectedQuoteId;

    public ShowQuotes() {
        assossiateQuoteWithClientButton       = configureButton("Assossiar Cotacao");
        createNewClientThroughQuoteButton     = configureButton("Registar aos clientes");
        removeQuoteButton                     = configureButton("Remover");
        removeQuoteButton.setBackground(Color.RED);
        removeQuoteButton.setForeground(Color.WHITE);
        
        model               = configureTableModel();
        table               = configureTable(model);
        scrollPane          = configureScrollPane(table);
        additionalInfoPanel = configureAdditionalInformationPanel();

        createNewClientThroughQuoteButton.addActionListener(this);
        assossiateQuoteWithClientButton.addActionListener(this);
        removeQuoteButton.addActionListener(this);
        
        table.addMouseListener(this);
        createNewClientThroughQuoteButton.addMouseListener(this);
        assossiateQuoteWithClientButton.addMouseListener(this);
        removeQuoteButton.addMouseListener(this);
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
        model.addColumn("Cuso estimado");
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

        panel.setPreferredSize(new Dimension(100, 45));
        panel.setLayout(new FlowLayout());
        panel.setBackground(new Color(0x123456));

        panel.add(createNewClientThroughQuoteButton);
        panel.add(assossiateQuoteWithClientButton);
        panel.add(removeQuoteButton);

        return panel;
    }

    public static void addContentFromMySQL(Object[] data) {
        model.addRow(data);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if(event.getSource() == createNewClientThroughQuoteButton) {
            String query = "SELECT * FROM Quotes WHERE quote_id = ?";

            try {
                PreparedStatement ps = DBConnection.getConexao().prepareStatement(query);
                ps.setString(1, (String) table.getValueAt(table.getSelectedRow(), 0));
                
                ResultSet res = ps.executeQuery();

                if(res.next()) {
                    field_name.setText(res.getString("client_name"));
                    field_email.setText(res.getString("client_email"));
                    field_tell.setText(res.getString("client_phone"));
                    field_residence.setText(res.getString("client_address"));
                    field_BI.setText(res.getString("client_nuit"));
                    field_problemDescription.setText(res.getString("service_description"));
                }
    
                add_client_button_action_performed_handler();
            }
            catch(SQLException e) {
                JOptionPane.showMessageDialog(MainWindow.frame, e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
            }
        }
        else if(event.getSource() == assossiateQuoteWithClientButton) {
            int row = table.getSelectedRow();
            selectedQuoteId = Integer.parseInt((String) table.getValueAt(row, 0));
            new AssignQuoteToUser();

            String query = "SELECT * FROM client";

            try {
                PreparedStatement ps = DBConnection.getConexao().prepareStatement(query);

                ResultSet res = ps.executeQuery();
                while(res.next()) {
                    Object[] data = {
                        res.getString("id"),
                        res.getString("name"),
                        res.getString("email")
                    };

                    AssignQuoteToUser.addContentFromMySQL(data);
                    }
                ps.close();
            }
            catch(SQLException e) {
                e.getMessage();
            }
            
            rightSidePanel_main.removeAll();
            titleLabel.setText("ASSOSSIAR COTAÇÃO A UM CLIENTE");
            rightSidePanel_main.add(AssignQuoteToUser.mainPanel, BorderLayout.CENTER);
            rightSidePanel_main.repaint();
            rightSidePanel_main.revalidate();
        }
        else if(event.getSource() == removeQuoteButton) {
            String query = "DELETE FROM Quotes WHERE quote_id = ?";

            try {
                PreparedStatement ps = DBConnection.getConexao().prepareStatement(query);

                ps.setString(1, (String) table.getValueAt(table.getSelectedRow(), 0));

                ps.execute();
                JOptionPane.showMessageDialog(MainWindow.frame, "Cotação excluída com sucesso!", "Informação", JOptionPane.INFORMATION_MESSAGE);
                ps.close();

                rightSidePanel_main.remove(scrollPane);
                list_quotations_button_action_performed_handler();
            }
            catch(SQLException e) {
                JOptionPane.showMessageDialog(MainWindow.frame, "Erro ao eliminar cotação!", "Erro!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        if(event.getSource() == table){
            rightSidePanel_main.add(additionalInfoPanel, BorderLayout.SOUTH);
            rightSidePanel_main.repaint();
            rightSidePanel_main.revalidate();
        }
    }

    @Override
    public void mouseEntered(MouseEvent event) {}

    @Override
    public void mouseExited(MouseEvent arg0) {}

    @Override
    public void mousePressed(MouseEvent arg0) {}

    @Override
    public void mouseReleased(MouseEvent arg0) {}
}
