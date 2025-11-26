package com.warehouse.views;

import com.warehouse.services.InventoryService;
import com.warehouse.services.OrderService;
import com.warehouse.services.WarehouseService;
import com.warehouse.views.components.HeaderBar;
import com.warehouse.views.components.NavigationPanel;
import com.warehouse.views.dashboard.MainDashboardView;
import com.warehouse.views.orders.OrderFormView;
import com.warehouse.views.products.ProductListView;

import javax.swing.*;
import java.awt.*;

public class MainApplicationView extends JFrame {

    private final WarehouseService warehouseService;
    private final OrderService orderService;
    private final InventoryService inventoryService;

    // Center content switched by navigation
    private final CardLayout cardLayout = new CardLayout();
    private final JPanel contentPanel = new JPanel(cardLayout);

    public static final String CARD_DASHBOARD = "dashboard";
    public static final String CARD_PRODUCTS  = "products";
    public static final String CARD_ORDERS    = "orders";

    public MainApplicationView() {
        // backend services (shared by all views)
        warehouseService = new WarehouseService();
        orderService = new OrderService();
        inventoryService = new InventoryService();

        initUI();
    }

    private void initUI() {
        // Look & feel
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ignore) {}

        setTitle("Warehouse Management System");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Top header
        add(new HeaderBar("Warehouse Management System"), BorderLayout.NORTH);

        // Left navigation
        NavigationPanel nav = new NavigationPanel();
        nav.setNavigationListener(this::showCard);
        add(nav, BorderLayout.WEST);

        // Center content (cards)
        contentPanel.add(new MainDashboardView(warehouseService, inventoryService, orderService), CARD_DASHBOARD);
        contentPanel.add(new ProductListView(warehouseService), CARD_PRODUCTS);
        contentPanel.add(new OrderFormView(warehouseService, orderService), CARD_ORDERS);

        add(contentPanel, BorderLayout.CENTER);

        // default view
        showCard(CARD_DASHBOARD);
    }

    private void showCard(String name) {
        cardLayout.show(contentPanel, name);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainApplicationView().setVisible(true));
    }
}