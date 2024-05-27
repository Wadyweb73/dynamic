package ui.panels;

import database.DBConnection;
import ui.MainWindow;

import javax.swing.*;

import static ui.listeners.mainwindowlisteners.MainWindowActionEventListeners.menu_button_action_performed_handler;
import static ui.styles.MainWindowComponentStyles.configureInputField;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class QuoteRegistrationPanel {
    public static JPanel mainPanel;
    public static JPanel formPanel;
    public static JPanel buttonPanel;
    public static JTextField clientNameField, clientEmailField, clientPhoneField, estimatedCostField, nuitField, clientAddressField;
    public static JLabel clientNameLabel, clientEmailLabel, clientPhoneLabel, clientNuitLabel, estimatedCostLabel, serviceDescriptionLabel,clientAddressLabel, quoteDateLabel;
    public static JTextArea serviceDescriptionField;
    public static JFormattedTextField quoteDateField;
    public static JButton saveButton, resetButton, cancelButton;

    public QuoteRegistrationPanel() {
        clientEmailLabel        = configureLabels("Email do cliente");
        clientNameLabel         = configureLabels("Nome do cliente");
        serviceDescriptionLabel = configureLabels("Descrição do serviço");
        clientPhoneLabel        = configureLabels("Telefone do cliente");
        clientNuitLabel         = configureLabels("NUIT do cliente");
        clientAddressLabel      = configureLabels("Endereço do cliente");
        estimatedCostLabel      = configureLabels("Custo estimado do serviço"); 
        quoteDateLabel          = configureLabels("Quote creation date");
        clientNameField         = configureInputField();
        clientEmailField        = configureInputField();
        clientPhoneField        = configureInputField();
        estimatedCostField      = configureInputField();
        clientAddressField      = configureInputField();
        serviceDescriptionField = new JTextArea(10, 20);
        quoteDateField          = new JFormattedTextField(java.time.LocalDate.now().toString());
        saveButton              = createButton("Salvar", new Color(0x58D68D));
        resetButton             = createButton("Redefinir", new Color(0xF5B041));
        cancelButton            = createButton("Cancelar", new Color(0x5D6D7E));

        buttonPanel             = configureButtonPanel(); 
        mainPanel               = configureMainPanel();
        formPanel               = configureFormPanel();

        mainPanel.add(formPanel, BorderLayout.CENTER);
    }

    public static JPanel configureButtonPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel.setBackground(Color.WHITE);

        panel.add(saveButton);
        panel.add(resetButton);
        panel.add(cancelButton);

        return panel;
    }
    
    public JPanel configureMainPanel() {
        JPanel panel = new JPanel();

        panel.setLayout(new BorderLayout(0, 0));
        panel.setPreferredSize(new Dimension(700, 630));
        panel.setBackground(new Color(0xEBF8FE));

        return panel;
    }

    public JLabel configureLabels(String text) {
        JLabel label = new JLabel(text);

        label.setForeground(new Color(0x123456));
        label.setFont(new Font("Consolas", Font.PLAIN, 15));
        
        return label;
    }

    public JPanel configureFormPanel() {
        JPanel panel = new JPanel();

        // panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setLayout(new GridLayout(15, 1));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 30));

        panel.add(clientNameLabel);
        panel.add(clientNameField);

        panel.add(clientEmailLabel);
        panel.add(clientEmailField);

        panel.add(clientPhoneLabel);
        panel.add(clientPhoneField);

        panel.add(clientAddressLabel);
        panel.add(clientAddressField);

        panel.add(estimatedCostLabel);
        panel.add(estimatedCostField);  

        panel.add(quoteDateLabel);
        panel.add(quoteDateField);

        panel.add(serviceDescriptionLabel);
        panel.add(serviceDescriptionField);

        serviceDescriptionField.setLineWrap(true);
        serviceDescriptionField.setWrapStyleWord(true);
        serviceDescriptionField.setBackground(new Color(0x808080));

        panel.add(buttonPanel);
     
        addEventHandlers();

        return panel;
    }

    private JButton createButton(String text, Color backgroundColor) {
        JButton button = new JButton(text);

        button.setFont(new Font("Arial", Font.BOLD, 15));
        button.setBackground(backgroundColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        return button;
    }

    private void addEventHandlers() {
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Salvar a cotação no banco de dados
                try {
                    String query = "INSERT INTO quotes (client_name, client_email, client_phone, service_description, estimated_cost, quote_date) VALUES (?, ?, ?, ?, ?, ?)";
                    PreparedStatement statement = DBConnection.getConexao().prepareStatement(query);

                    statement.setString(1, clientNameField.getText());
                    statement.setString(2, clientEmailField.getText());
                    statement.setString(3, clientPhoneField.getText());
                    statement.setString(4, serviceDescriptionField.getText());
                    statement.setString(5, estimatedCostField.getText());
                    statement.setString(6, quoteDateField.getText());

                    statement.executeUpdate();
                    JOptionPane.showMessageDialog(MainWindow.frame, "Cotação salva com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(MainWindow.frame, "Erro ao salvar a cotação: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientNameField.setText("");
                clientEmailField.setText("");
                clientPhoneField.setText("");
                serviceDescriptionField.setText("");
                estimatedCostField.setText("");
                quoteDateField.setText(java.time.LocalDate.now().toString());
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu_button_action_performed_handler();
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
