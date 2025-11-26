package com.warehouse.views.products;

import com.warehouse.services.WarehouseService;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class ProductFormView extends JDialog {

    private final WarehouseService warehouseService;
    private boolean saved = false;

    private final JTextField nameField  = new JTextField();
    private final JTextField catField   = new JTextField();
    private final JTextField qtyField   = new JTextField();
    private final JTextField priceField = new JTextField();

    public ProductFormView(Window owner, WarehouseService service) {
        super(owner, "Add Product", ModalityType.APPLICATION_MODAL);
        this.warehouseService = service;
        initUI();
    }

    private void initUI() {
        setSize(400, 300);
        setLocationRelativeTo(getOwner());
        setLayout(new BorderLayout(10, 10));

        JPanel form = new JPanel(new GridLayout(4, 2, 5, 5));
        form.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        form.add(new JLabel("Name:"));
        form.add(nameField);
        form.add(new JLabel("Category:"));
        form.add(catField);
        form.add(new JLabel("Quantity:"));
        form.add(qtyField);
        form.add(new JLabel("Price:"));
        form.add(priceField);

        add(form, BorderLayout.CENTER);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton save = new JButton("Save");
        JButton cancel = new JButton("Cancel");
        buttons.add(cancel);
        buttons.add(save);

        add(buttons, BorderLayout.SOUTH);

        save.addActionListener(e -> onSave());
        cancel.addActionListener(e -> dispose());
    }

    private void onSave() {
        try {
            String name = nameField.getText().trim();
            String cat  = catField.getText().trim();
            int qty     = Integer.parseInt(qtyField.getText().trim());
            double price = Double.parseDouble(priceField.getText().trim());

            boolean ok = warehouseService.addProduct(name, cat, qty, price, new Date(), 1);
            if (ok) {
                saved = true;
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to save product.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid input: " + ex.getMessage());
        }
    }

    public boolean isSaved() {
        return saved;
    }
}