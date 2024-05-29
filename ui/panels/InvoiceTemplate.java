package ui.panels;

import static ui.MainWindow.titleLabel;
import static ui.listeners.mainwindowlisteners.MainWindowActionEventHandlers.payement_list_button_action_performed_handler;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ui.MainWindow;

public class InvoiceTemplate implements ActionListener {
    public static JPanel mainPanel;
    public static JLabel invoiceNumberLabel, clientNameLabel, dateTimeLabel, orderLabel;
    public static JButton cancelButton;

    public InvoiceTemplate() {
        cancelButton = configureCancelButton();
        
        invoiceNumberLabel = configureInvoiceLabel("Factura n: 123");
        clientNameLabel = configureInvoiceLabel("Nome do cliente: Jorge Marrengula");
        dateTimeLabel = configureInvoiceLabel("12.12.2019");
        orderLabel = configureInvoiceLabel("1. Montagem de um AC");
        mainPanel = configureMainPanel();

        cancelButton.addActionListener(this);
    }

    public static JButton configureCancelButton() {
        JButton button = new JButton("Voltar");

        button.setPreferredSize(new Dimension(200, 30));
        button.setBackground(Color.RED);
        button.setForeground(Color.WHITE);
        
        return button;
    }

    public static JLabel configureInvoiceLabel(String txt) {
        JLabel label = new JLabel(txt);

        label.setPreferredSize(new Dimension(300, 30));
        label.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0x123456)));
        label.setFont(new Font("Helvetica", Font.ITALIC, 20));

        return label;
    }

    public static JPanel configureMainPanel() {
        JPanel panel = new JPanel();
        
        panel.setPreferredSize(new Dimension(900, 300));
        panel.setLayout(new GridLayout(4, 1));
        titleLabel = new JLabel("Factura");
        panel.add(titleLabel);
        panel.add(invoiceNumberLabel);
        panel.add(orderLabel);
        panel.add(clientNameLabel);
        panel.add(dateTimeLabel);

        panel.add(cancelButton);

        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == cancelButton) {
            new MainWindow();
            payement_list_button_action_performed_handler();
        }
    }

    public static void main(String[] args) {
        new InvoiceTemplate();
        JFrame frame = new JFrame();
        frame.setSize(new Dimension(1000, 700));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER));
        frame.add(mainPanel);

        frame.setVisible(true);
    }
}