package ui.panels;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

public class Menu {
    public static JLabel imageLabelContainer;
    public static JPanel mainpanel;
    public static JButton servicesInProgessDashboardButton, activeClientsDashboardButton, scheduleDashboardButton, quotesDashboardButton, pendingPaymentsDashboardButton;
    public static JLabel servicesInProgressDashboardLabel, activeClientsDashboardLabel, scheduleDashboardLabel, quotesDashboardLabel, pendingPaymentsDashboardLabel;

    public Menu() {
        activeClientsDashboardButton     = configureDashboardButton("Clientes Activos");
        servicesInProgessDashboardButton = configureDashboardButton("Serviços em Progresso");
        pendingPaymentsDashboardButton   = configureDashboardButton("Pagamentos pendentes");
        quotesDashboardButton            = configureDashboardButton("Cotações em aberto");
        scheduleDashboardButton          = configureDashboardButton("Agenda");
        
        imageLabelContainer = configureMainPanelBackgroundImage();
        mainpanel           = configureMainPanel();
    }

    public static JButton configureDashboardButton(String text) {
        JButton button = new JButton(text);

        button.setPreferredSize(new Dimension(100, 100));
        button.setBackground(new Color(0xcdcdcd));
        button.setForeground(new Color(0x123456));
        button.setFont(new Font("consolas", Font.BOLD, 15));
        button.setFocusable(false);

        return button;
    }
    
    public JLabel configureMainPanelBackgroundImage() {
        ImageIcon icon = new ImageIcon("public/images/dynamic-services-logo.jpg");
        Image image    = icon.getImage().getScaledInstance(200, 200, Image.SCALE_REPLICATE);

        icon = new ImageIcon(image);
        JLabel label = new JLabel(icon);
        label.setPreferredSize(new Dimension(1000, 200));

        return label;
    }

    JPanel configureMainPanel() {
        JPanel panel = new JPanel();

        panel.setPreferredSize(new Dimension(1200, 200));
        panel.setLayout(new GridLayout(1, 4, 2, 2));
        panel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));

        panel.add(activeClientsDashboardButton);
        panel.add(servicesInProgessDashboardButton);
        panel.add(quotesDashboardButton);
        panel.add(scheduleDashboardButton);
        panel.add(pendingPaymentsDashboardButton);

        return panel;
    }
}
