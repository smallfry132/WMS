package com.warehouse.views.components;

import com.warehouse.views.MainApplicationView;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class NavigationPanel extends JPanel {

    private Consumer<String> navigationListener;

    public NavigationPanel() {
        setPreferredSize(new Dimension(220, 0));
        setLayout(new GridLayout(0, 1, 0, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        addButton("Dashboard", MainApplicationView.CARD_DASHBOARD);
        addButton("Products",  MainApplicationView.CARD_PRODUCTS);
        addButton("Orders (POS)", MainApplicationView.CARD_ORDERS);
        add(Box.createVerticalGlue());
        add(exitButton());
    }

    private JButton exitButton() {
        JButton btn = new JButton("Exit");
        btn.setForeground(Color.RED.darker());
        btn.addActionListener(e -> System.exit(0));
        return btn;
    }

    private void addButton(String text, String cardName) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("SansSerif", Font.BOLD, 14));
        btn.setFocusPainted(false);
        btn.addActionListener(e -> {
            if (navigationListener != null) {
                navigationListener.accept(cardName);
            }
        });
        add(btn);
    }

    public void setNavigationListener(Consumer<String> listener) {
        this.navigationListener = listener;
    }
}