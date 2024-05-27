package ui.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import database.DBConnection;

public class ShowPayments implements MouseListener, ActionListener {
    public static JScrollPane scrollPane;
    public static DefaultTableModel tableModel;
    public static JPanel description_panel, date_panel, receiptCode_panel;
    public static JTable table;
    public static JPanel mainPanel, additionalInformationPanel;
    public static JButton goToReceiptButton;

    public ShowPayments () {
        tableModel                 = configureTableModel();
        table                      = configureTable(tableModel);
        scrollPane                 = configureScrollPane(table);
        goToReceiptButton          = configureActionButton();
        description_panel          = createInfoPanel("Problema:", null);
        date_panel                 = createInfoPanel("Data de requisição: ", null);
        receiptCode_panel          = createInfoPanel("Numero de Recibo", null);
        additionalInformationPanel = configureAdditionalInformationPanel();
        mainPanel                  = configureMainPanel();

        goToReceiptButton.addActionListener(this);
        description_panel.addMouseListener(this);
        date_panel.addMouseListener(this);
        receiptCode_panel.addMouseListener(this);
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
        JTable table = new JTable(model) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // All cells are not editable
                return false;
            }
        };

        table.setBorder(null);
        table.setForeground(new Color(0x123456));
		table.setBackground(Color.LIGHT_GRAY);
		table.setFont(new Font("consolas", Font.PLAIN, 15));
		table.setRowHeight(40);
        table.setFocusable(false);
        
        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(0x123456));
        header.setForeground(new Color(0xcdcdcd));     
        header.setFont(new Font("Consolas", Font.BOLD, 17));          

        table.addMouseListener(this);
        
        return table;
    }

    public static JButton configureActionButton() {
        JButton button = new JButton("Ir para pagamentos");
        
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setAlignmentY(Component.CENTER_ALIGNMENT);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(60, 179, 113));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));
        Insets insets = new Insets(0, 0, 20, 0);
        button.setMargin(insets);

        return button;
    }

    private JPanel createInfoPanel(String label, String value) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
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

    public void updateAdditionalInfo(String description, String request_date, String receipt_code) {
        ((JLabel)description_panel.getComponent(1)).setText(description);
        ((JLabel)receiptCode_panel.getComponent(1)).setText(receipt_code);
        ((JLabel)date_panel       .getComponent(1)).setText(request_date);
    }

    public JScrollPane configureScrollPane(JTable table) {
        JScrollPane scrollPane = new JScrollPane(table);

        scrollPane.setPreferredSize(new Dimension(700, 670));
        // scrollPane.setBorder(null);
        scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
        
        return scrollPane;
    }

    public JPanel configureAdditionalInformationPanel() {
        JPanel panel  = new JPanel();

        panel.setPreferredSize(new Dimension(100, 200));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(0x123456));
    
        panel.add(description_panel);
        panel.add(date_panel);
        panel.add(receiptCode_panel);
        panel.add(Box.createRigidArea(new Dimension(0, 7))); // Espaçamento entre os detalhes e o botão
        panel.add(goToReceiptButton);
        panel.add(Box.createRigidArea(new Dimension(0, 18))); // Espaçamento entre os detalhes e o botão

        return panel;
    }

    public JPanel configureMainPanel() {
        JPanel panel = new JPanel();

        panel.setLayout(new BorderLayout(0, 0));
        panel.setPreferredSize(new Dimension(1207, 677));
        panel.setBackground(new Color(0xEBF8FE));

        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

//===================================================================================

    @Override
    public void mouseEntered(MouseEvent event) {
        if (event.getSource() == goToReceiptButton) {
            goToReceiptButton.setBackground(new Color(60, 179, 113));
            goToReceiptButton.setForeground(new Color(0x123456));
        }
        else if (event.getSource() == description_panel) {
            description_panel.setBackground(new Color(60, 179, 113));
            description_panel.setForeground(new Color(0x123456));
        }
        else if(event.getSource() == date_panel) {
            date_panel.setBackground(new Color(60, 179, 113));
            date_panel.setForeground(new Color(0x123456));
        }
        else if(event.getSource() == receiptCode_panel) {
            receiptCode_panel.setBackground(new Color(60, 179, 113));
            receiptCode_panel.setForeground(new Color(0x123456));
        }
    }

    public void mouseExited(MouseEvent event) {
        if (event.getSource() == goToReceiptButton) {
            goToReceiptButton.setBackground(new Color(0xcdcdcd));
        }
        else if (event.getSource()== additionalInformationPanel) {
            additionalInformationPanel.setBackground(new Color(0x123456));
        }
        else if (event.getSource() == description_panel) {
            description_panel.setBackground(new Color(0x123456));
        }
        else if(event.getSource() == date_panel) {
            date_panel.setBackground(new Color(0x123456));
        }
        else if(event.getSource() ==  receiptCode_panel) {
            receiptCode_panel.setBackground(new Color(0x123456));
        }
    }

    public void mouseClicked(MouseEvent event) {
        int line     = table.getSelectedRow();
        int column   = 0;
        String query = "SELECT * FROM comments INNER JOIN client ON comments.client_id = client.id WHERE client.name = ?";

        try {
            PreparedStatement ps = DBConnection.getConexao().prepareStatement(query);
            ps.setString(1, (String) table.getValueAt(line, column));
            ResultSet res        = ps.executeQuery(); 

            if (res.next()) {
                updateAdditionalInfo(res.getString("discription"), res.getString("task_date"), "dsgjhhj");
            }
            
            mainPanel.remove(additionalInformationPanel);
            mainPanel.add(additionalInformationPanel, BorderLayout.SOUTH);
            mainPanel.repaint();
            mainPanel.revalidate();
        }
        catch(SQLException e) {
            e.getMessage();
        }
        
    }

    public void mousePressed(MouseEvent arg0) {}
    public void mouseReleased(MouseEvent arg0) {}   
    
	public static void addContentFromMySQL(Object[] obj) {
		tableModel.addRow(obj);
	}

    @Override
    public void actionPerformed(ActionEvent arg0) {}
}
