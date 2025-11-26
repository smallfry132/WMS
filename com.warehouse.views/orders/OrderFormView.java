package com.warehouse.views.orders;

import com.warehouse.models.Customer;
import com.warehouse.models.Product;
import com.warehouse.services.OrderService;
import com.warehouse.services.WarehouseService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class OrderFormView extends JPanel {

    private final WarehouseService warehouseService;
    private final OrderService orderService;

    private final JComboBox<Customer> customerBox = new JComboBox<>();
    private final JComboBox<Product> productBox   = new JComboBox<>();
    private final JTextField qtyField            = new JTextField(5);
    private final DefaultTableModel cartModel;
    private final JLabel totalLabel = new JLabel("Total: $0.00");
    private double currentTotal = 0.0;

    public OrderFormView(WarehouseService warehouseService, OrderService orderService) {
        this.warehouseService = warehouseService;
        this.orderService = orderService;

        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        top.add(new JLabel("Customer:"));
        top.add(customerBox);
        top.add(new JLabel("Product:"));
        top.add(productBox);
        top.add(new JLabel("Qty:"));
        top.add(qtyField);

        JButton addBtn = new JButton("Add to Cart");
        top.add(addBtn);
        add(top, BorderLayout.NORTH);

        cartModel = new DefaultTableModel(new String[]{"ID", "Name", "Unit Price", "Qty", "Subtotal"}, 0);
        JTable cartTable = new JTable(cartModel);
        add(new JScrollPane(cartTable), BorderLayout.CENTER);

        JPanel bottom = new JPanel(new BorderLayout());
        totalLabel.setFont(new Font("Arial", Font.BOLD, 18));
        bottom.add(totalLabel, BorderLayout.WEST);

        JButton checkoutBtn = new JButton("Complete Order");
        bottom.add(checkoutBtn, BorderLayout.EAST);
        add(bottom, BorderLayout.SOUTH);

        loadCombos();

        addBtn.addActionListener(e -> addToCart());
        checkoutBtn.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Order processed (demo)."));
    }

    private void loadCombos() {
        try {
            List<Customer> customers = warehouseService.getAllCustomers();
            for (Customer c : customers) customerBox.addItem(c);
        } catch (Exception ignored) {}

        try {
            List<Product> products = warehouseService.getAllProducts();
            for (Product p : products) productBox.addItem(p);
        } catch (Exception ignored) {}
    }

    private void addToCart() {
        try {
            Product p = (Product) productBox.getSelectedItem();
            int qty = Integer.parseInt(qtyField.getText().trim());
            if (p == null || qty <= 0) {
                JOptionPane.showMessageDialog(this, "Select product and quantity > 0");
                return;
            }
            double sub = p.getPrice() * qty;
            cartModel.addRow(new Object[]{
                p.getProductId(), p.getName(), p.getPrice(), qty, sub
            });
            currentTotal += sub;
            totalLabel.setText("Total: $" + currentTotal);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid qty");
        }
    }
}