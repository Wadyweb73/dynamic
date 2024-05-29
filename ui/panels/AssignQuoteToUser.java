package ui.panels;

import static ui.listeners.mainwindowlisteners.MainWindowActionEventHandlers.*;
import static ui.panels.ShowQuotes.selectedQuoteId;
import static ui.MainWindow.*;
import database.DBConnection;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.JTableHeader;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AssignQuoteToUser implements ActionListener, MouseListener {
    public static JPanel mainPanel, additionalInformationPanel;
    public static JButton confirmButton, cancelButton;
    public static JScrollPane scrollPane;
    public static JTable table;
    public static DefaultTableModel model;
    
    public AssignQuoteToUser() {
        confirmButton = configureButton("Assossiar");
        cancelButton  = configureButton("Cancelar");
        cancelButton    .setBackground(Color.RED);
        model         = configureModel();
        table         = configureTable(model);
        scrollPane    = configureScrollPane(table);
        mainPanel     = configureMainPanel();
        additionalInformationPanel = configureAdditionalInformationPanel();

        confirmButton.addActionListener(this);
        cancelButton.addActionListener(this);
        table.addMouseListener(this);
    }

    public static JButton configureButton(String txt) {
        JButton button = new JButton(txt);

        button.setBackground(Color.GREEN);
        button.setForeground(Color.WHITE);
        button.setPreferredSize(new Dimension(200, 40));

        return button;
    }

    public static DefaultTableModel configureModel() {
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnCount(0);
        model.setRowCount(0);

        model.addColumn("Id");
        model.addColumn("Nome");
        model.addColumn("Email"); 

        return model;
    } 

    public static JTable configureTable(DefaultTableModel model) {
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
        table.setFocusable(false);

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Consolas", Font.BOLD, 13));
        header.setBackground(new Color(0x123456));
        header.setForeground(Color.LIGHT_GRAY);
        header.setReorderingAllowed(false);               

        return table;
    }

    public static JScrollPane configureScrollPane(JTable table) {
        JScrollPane scrollPane = new JScrollPane(table);

        scrollPane.setBorder(null);
        scrollPane.setPreferredSize(new Dimension(1200, 700));
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        
        return scrollPane;
    }

    public JPanel configureAdditionalInformationPanel() {
        JPanel panel  = new JPanel();

        panel.setPreferredSize(new Dimension(100, 55));
        panel.setLayout(new FlowLayout());
        panel.setBackground(new Color(0x123456));

        panel.add(confirmButton);
        panel.add(cancelButton);

        return panel;
    }

    public static JPanel configureMainPanel() {
        JPanel panel = new JPanel();

        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(100, 100));
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }

    
    @Override
    public void actionPerformed(ActionEvent event) {
        if(event.getSource() == confirmButton) {
            String quoteQuery = "SELECT * FROM Quotes WHERE quote_id = ?";
            String query = "INSERT INTO comments(discription, task_status, payd, client_id, task_date) VALUES(?, ?, ?, ?, ?)";

            try {
                PreparedStatement psQuote = DBConnection.getConexao().prepareStatement(quoteQuery);
                PreparedStatement ps      = DBConnection.getConexao().prepareStatement(query);

                psQuote.setString(1, Integer.toString(selectedQuoteId));
                ResultSet res = psQuote.executeQuery();

                while(res.next()) {
                    ps.setString(1, res.getString("service_description"));
                    ps.setString(2, "PENDING");
                    ps.setBoolean(3, false);
                    ps.setInt(4, Integer.parseInt((String) table.getValueAt(table.getSelectedRow(), 0)));
                    ps.setString(5, getDateFromStorage());

                    ps.execute();
                    JOptionPane.showConfirmDialog(frame, "Cotação adicionda para " + (String) table.getValueAt(table.getSelectedRow(), 1), "Informação", JOptionPane.INFORMATION_MESSAGE);
                    
                    ps.close();
                }

            }   
            catch(SQLException e) {
                JOptionPane.showMessageDialog(frame, e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
            }
        }
        else if(event.getSource() == cancelButton) {
            menu_button_action_performed_handler();
        }
    }

    
    public void mouseClicked(MouseEvent event) {
        if(event.getSource() == table){
            rightSidePanel_main.add(additionalInformationPanel, BorderLayout.SOUTH);
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

	public static void addContentFromMySQL(Object[] obj) {
		model.addRow(obj);
	}

    public static void main(String[] a) {
        new AssignQuoteToUser();
    }
}
