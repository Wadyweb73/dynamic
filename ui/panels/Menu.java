package ui.panels;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Menu implements MouseListener {
    public static JLabel imageLabelContainer;
    public static JPanel dashboardPanel, bottomPanel, mainPanel;
    public static JButton servicesInProgessDashboardButton, activeClientsDashboardButton, scheduleDashboardButton, quotesDashboardButton, pendingPaymentsDashboardButton;
    public static JButton newServiceButton, activeClientsButton, scheduleButton, quotesButton, goToPaymentsButton;

    public Menu() {
        activeClientsDashboardButton     = configureDashboardButton("Clientes Activos", 129);
        servicesInProgessDashboardButton = configureDashboardButton("Serviços em Progresso", 15);
        pendingPaymentsDashboardButton   = configureDashboardButton("Pagamentos pendentes", 10);
        quotesDashboardButton            = configureDashboardButton("Cotações em aberto", 8);
        scheduleDashboardButton          = configureDashboardButton("Agenda", null);

        newServiceButton                 = configureActionButtons("Registar novo serviço"); 
        activeClientsButton              = configureActionButtons("Ver clientes");
        scheduleButton                   = configureActionButtons("Agenda de hoje");
        quotesButton                     = configureActionButtons("Ver cotaçẽs");
        goToPaymentsButton               = configureActionButtons("Ver pagamentos");
        
        imageLabelContainer              = configureDashboardPanelBackgroundImage();
        dashboardPanel                   = configureDashboardPanel();
        bottomPanel                      = configureBottomPanel();
        mainPanel                        = configureMainPanel();

        newServiceButton.addMouseListener(this);
        activeClientsButton.addMouseListener(this);
        goToPaymentsButton.addMouseListener(this);
        scheduleButton.addMouseListener(this);
        quotesButton.addMouseListener(this);
    }

    public static JButton configureDashboardButton(String text, Integer numero) {
        String html;
        if (numero != null) {
            html = 
                    "" +
                        "<html>" +
                            "<header>" +
                                "<style>" +
                                    ".number {  }" +
                                "</style>" +
                            "</header>" +
                            "<center style='font-family: Arial; font-size: 17pt;'>" + text + "<br><br><span style='background-color: cyan; color: #007BFF; width: 30px; padding: 2px, 6px; border: 1px solid black;'>" + numero + "</span></center>" +
                        "</html>" +
                    "";
        }
        else {
            html = "";
        }
        JButton button = new JButton(html);

        button.setPreferredSize(new Dimension(100, 100));
        button.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        button.setBackground(new Color(0xEBF8FE));
        button.setForeground(new Color(0x123456));
        button.setFocusPainted(false);

        SwingUtilities.invokeLater(()-> {
            ImageIcon icon = new ImageIcon("public/images/calendar.png");
            Image img = icon.getImage().getScaledInstance(
                150, 
                150, 
                Image.SCALE_SMOOTH
                );
            scheduleDashboardButton.setIcon(new ImageIcon(img));
            scheduleDashboardButton.setVerticalTextPosition(SwingConstants.BOTTOM);
            scheduleDashboardButton.setHorizontalTextPosition(SwingConstants.CENTER);
        });

        return button;
    }

    public static JButton configureActionButtons(String type) {
        JButton button = new JButton(type);

        button.setBackground(new Color(0x007BFF));
        button.setForeground(new Color(0xffffff));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        
        return button;
    } 
    
    public JLabel configureDashboardPanelBackgroundImage() {
        ImageIcon icon = new ImageIcon("public/images/dynamic-services-logo.jpg");
        Image image    = icon.getImage().getScaledInstance(200, 200, Image.SCALE_REPLICATE);

        icon = new ImageIcon(image);
        JLabel label = new JLabel(icon);
        label.setPreferredSize(new Dimension(1000, 200));

        return label;
    }

    JPanel configureBottomPanel() {
        JPanel panel = new JPanel();

        panel.setPreferredSize(new Dimension(1200, 35));
        panel.setOpaque(false);
        panel.setLayout(new GridLayout(1, 4, 50, 0));
        panel.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 25));
        
        panel.add(activeClientsButton);
        panel.add(newServiceButton);
        panel.add(quotesButton);
        panel.add(goToPaymentsButton);
        panel.add(scheduleButton);
        
        return panel;
    }
 
    JPanel configureDashboardPanel() {
        JPanel panel = new JPanel();

        panel.setPreferredSize(new Dimension(1200, 200));
        panel.setOpaque(false);
        panel.setLayout(new GridLayout(1, 4, 5, 2));

        panel.add(activeClientsDashboardButton);
        panel.add(servicesInProgessDashboardButton);
        panel.add(quotesDashboardButton);
        panel.add(pendingPaymentsDashboardButton);
        panel.add(scheduleDashboardButton);

        return panel;
    }

    public static JPanel configureMainPanel() {
        JPanel panel = new JPanel();
        
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setLayout(new BorderLayout(0, 5));
        panel.setBackground(new Color(0x808080));

        panel.add(dashboardPanel, BorderLayout.NORTH);
        panel.add(bottomPanel);
        
        return panel;
    }


    @Override
    public void mouseEntered(MouseEvent event) {
        if (event.getSource() == newServiceButton) {
            newServiceButton.setBackground(new Color(0xffffff));
            newServiceButton.setForeground(new Color(0x007BFF));
            newServiceButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
        else if (event.getSource() == activeClientsButton) {
            activeClientsButton.setBackground(new Color(0xffffff));
            activeClientsButton.setForeground(new Color(0x007BFF));
            activeClientsButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
        else if (event.getSource() == quotesButton) {
            quotesButton.setBackground(new Color(0xffffff));
            quotesButton.setForeground(new Color(0x007BFF));
            quotesButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
        else if (event.getSource() == scheduleButton) {
            scheduleButton.setBackground(new Color(0xffffff));
            scheduleButton.setForeground(new Color(0x007BFF));
            scheduleButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
        else if (event.getSource() == goToPaymentsButton) {
            goToPaymentsButton.setBackground(new Color(0xffffff));
            goToPaymentsButton.setForeground(new Color(0x007BFF));
            goToPaymentsButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    }

    public void mouseExited(MouseEvent event) {
        if (event.getSource() == newServiceButton) {
            newServiceButton.setBackground(new Color(0x007BFF));
            newServiceButton.setForeground(new Color(0xffffff));
        }
        else if (event.getSource() == activeClientsButton) {
            activeClientsButton.setBackground(new Color(0x007BFF));
            activeClientsButton.setForeground(new Color(0xffffff));
        }
        else if (event.getSource() == quotesButton) {
            quotesButton.setBackground(new Color(0x007BFF));
            quotesButton.setForeground(new Color(0xffffff));
        }
        else if (event.getSource() == scheduleButton) {
            scheduleButton.setBackground(new Color(0x007BFF));
            scheduleButton.setForeground(new Color(0xffffff));
        }
        else if (event.getSource() == goToPaymentsButton) {
            goToPaymentsButton.setBackground(new Color(0x007BFF));
            goToPaymentsButton.setForeground(new Color(0xffffff));
        }
    }
    public void mouseClicked(MouseEvent event) {}
    public void mousePressed(MouseEvent arg0) {}
    public void mouseReleased(MouseEvent arg0) {}   
}
