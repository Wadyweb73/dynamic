package ui.panels;

import static ui.panels.ClientInfoAndPayments.*;
import static ui.listeners.mainwindowlisteners.MainWindowActionEventListeners.*;
import database.DBConnection;
import ui.MainWindow;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.JTableHeader;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ServedClients implements MouseListener, ActionListener {
    public static JScrollPane scrollPane;
    public static DefaultTableModel tableModel;
    public static JTable table;
    public static JPanel namePanel, emailPanel, addressPanel, nuitPanel, phonePanel, problemDescriptionPanel;
    public static JPanel mainPanel, additionalInformationPanel;
    public static JButton payment_button;

    public ServedClients () {
        tableModel                 = configureTableModel();
        table                      = configureTable(tableModel);
        scrollPane                 = configureScrollPane(table);
        payment_button             = configureActionButton();
        
        namePanel                  = createInfoPanel("Nome: ", null);
        emailPanel                 = createInfoPanel("Email: ", null);
        addressPanel               = createInfoPanel("Endereco: ", null);
        nuitPanel                  = createInfoPanel("NUIT: ", null);
        phonePanel                 = createInfoPanel("Telefone: ", null);
        problemDescriptionPanel    = createInfoPanel("Descricao: ", null);
        additionalInformationPanel = configureAdditionalInformationPanel();
        mainPanel                  = configureMainPanel();

        payment_button.addMouseListener(this);
        payment_button.addActionListener(this);
    }
    
   public DefaultTableModel configureTableModel() {
        DefaultTableModel model = new DefaultTableModel();

        model.setColumnCount(0);
        model.setRowCount(0);
        model.addColumn("Numero de requisicao");
        model.addColumn("Id do cliente");
        model.addColumn("Descricao do problema");
		model.addColumn("Pago");
		model.addColumn("Data de requisicao");	

        return model;
    }

    public JTable configureTable(DefaultTableModel model) {
        JTable table = new JTable(model) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table.setBorder(null);
        table.setForeground(new Color(0x123456));
        table.setBackground(Color.LIGHT_GRAY);
		table.setFont(new Font("consolas", Font.PLAIN, 15));
        table.setOpaque(false);
		table.setRowHeight(30);
        table.getColumnModel().getColumn(2).setMinWidth(450);
        table.setFocusable(false);
        
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Consolas", Font.BOLD, 13));
        header.setReorderingAllowed(false);               
        
        table.addMouseListener(this);
        
        return table;
    }

    public JScrollPane configureScrollPane(JTable table) {
        JScrollPane scrollPane = new JScrollPane(table);

        scrollPane.setPreferredSize(new Dimension(1200, 670));
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setOpaque(false);
        
        return scrollPane;
    }

    public JPanel configureAdditionalInformationPanel() {
        JPanel panel  = new JPanel();

        panel.setPreferredSize(new Dimension(100, 300));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(0x123456));
    
        panel.add(namePanel);
        panel.add(emailPanel);
        panel.add(nuitPanel);
        panel.add(phonePanel);
        panel.add(addressPanel);
        panel.add(problemDescriptionPanel);
        panel.add(Box.createRigidArea(new Dimension(0, 18))); // Espaçamento entre os detalhes e o botão
        panel.add(payment_button);
        panel.add(Box.createRigidArea(new Dimension(0, 18))); // Espaçamento entre os detalhes e o botão

        return panel;
    }

    private JPanel createInfoPanel(String label, String value) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(0, 10, 1, 10));
        panel.setBackground(new Color(0x123456));
        panel.setPreferredSize(new Dimension(800, 50));

        JLabel labelComponent = new JLabel(label);
        labelComponent.setFont(new Font("Consolas", Font.ROMAN_BASELINE, 14));
        labelComponent.setForeground(new Color(0xffffff));

        JLabel valueComponent = new JLabel(value);
        valueComponent.setFont(new Font("Consolas", Font.PLAIN, 14));
        valueComponent.setForeground(new Color(0xffffff));

        panel.add(labelComponent, BorderLayout.WEST);
        panel.add(valueComponent, BorderLayout.EAST);

        return panel;
    } 

    public static JButton configureActionButton() {
        JButton button = new JButton("Ir para pagamantos");
        
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setAlignmentY(Component.CENTER_ALIGNMENT);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(60, 179, 113));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));
        button.setMargin(new Insets(0, 0, 20, 0));

        return button;
    }

    public void updateUserInfo(String name, String address, String email, String nuit, String phone, String problemDescription) {
        ((JLabel)namePanel.getComponent(1)).setText(name);
        ((JLabel)addressPanel.getComponent(1)).setText(address);
        ((JLabel)emailPanel.getComponent(1)).setText(email);
        ((JLabel)phonePanel.getComponent(1)).setText(phone);
        ((JLabel)nuitPanel.getComponent(1)).setText(nuit);
        ((JLabel)problemDescriptionPanel.getComponent(1)).setText(problemDescription);
    }

    public JPanel configureMainPanel() {
        JPanel panel = new JPanel();

        panel.setLayout(new BorderLayout(0, 0));
        panel.setPreferredSize(new Dimension(1207, 678));
        panel.setBackground(new Color(0xEBF8FE));

        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }
    
	public void addContentFromMySQL(Object[] obj) {
		tableModel.addRow(obj);
	}

// =========================== DATABASE OPERATIONS ============================
    public ResultSet getSelectedUserData() {
        int line = table.getSelectedRow();
        int column       = 1;
        String user_data_query   = "SELECT * FROM client WHERE id = ?";

        try {
            PreparedStatement user_data_statement   = DBConnection.getConexao().prepareStatement(user_data_query);            
            user_data_statement.setInt(1, Integer.parseInt((String)table.getValueAt(line, column)));          
            ResultSet res = user_data_statement.executeQuery();

            return res;
        }
        catch(SQLException e) {
            JOptionPane.showMessageDialog(MainWindow.frame, "\nErro: " + e.getMessage(), "Query Error!", JOptionPane.INFORMATION_MESSAGE);
        }

        return null;
    }

    @Override
    public void mouseEntered(MouseEvent event) {
        if (event.getSource() == payment_button) {
            payment_button.setBackground(new Color(60, 179, 113));
            payment_button.setForeground(new Color(0x123456));
        }
    }
    public void mouseExited(MouseEvent event) {
        if (event.getSource() == payment_button) {
            payment_button.setBackground(new Color(0xcdcdcd));
        }
    }
    public void mouseClicked(MouseEvent event) {
        if (event.getSource() == table) {
            if(event.getClickCount() == 1) {
                String description_query = "SELECT discription FROM comments WHERE client_id = ?"; 

                try {
                    PreparedStatement description_statement = DBConnection.getConexao().prepareStatement(description_query); 
                    ResultSet userdata_res = getSelectedUserData();
    
                    if (userdata_res.next()) {
                        description_statement.setInt(1, userdata_res.getInt("id"));
                        ResultSet description_res = description_statement.executeQuery();
    
                        if (description_res.next()) {
                            updateUserInfo(userdata_res.getString("name"), userdata_res.getString("residence"), userdata_res.getString("email"), userdata_res.getString("bi"), userdata_res.getString("contact"), description_res.getString("discription"));
                        }
                        
                        mainPanel.add(additionalInformationPanel, BorderLayout.SOUTH);
                        mainPanel.repaint();
                        mainPanel.revalidate();
                    }
                }
                catch(SQLException e) {
                    e.getMessage();
                }
            }
        }
    }
    public void mousePressed(MouseEvent arg0) {}
    public void mouseReleased(MouseEvent arg0) {}   


    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == payment_button) {
            new ClientInfoAndPayments();

            try {
                ResultSet res = getSelectedUserData();
                if (res.next()) {
                    client_information_button_action_performed_handler();
                    searchInputClient.setText(res.getString("name"));
                    search_client_info_action_performed_handler();
                }
            }
            catch(SQLException e) {
                e.getMessage();
            }
            
        }
    }
}
