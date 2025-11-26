package com.warehouse.views.products;

import com.warehouse.models.Product;
import com.warehouse.services.WarehouseService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ProductListView extends JPanel {

    private final WarehouseService warehouseService;
    private final DefaultTableModel model;
    private final JTable table;

    public ProductListView(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;

        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel top = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton addBtn = new JButton("Add Product");
        top.add(addBtn);
        add(top, BorderLayout.NORTH);

        String[] cols = {"ID", "Name", "Category", "Stock", "Price"};
        model = new DefaultTableModel(cols, 0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        addBtn.addActionListener(e -> openForm());

        refreshTable();
    }

    private void openForm() {
        ProductFormView dialog = new ProductFormView(SwingUtilities.getWindowAncestor(this), warehouseService);
        dialog.setVisible(true);
        if (dialog.isSaved()) {
            refreshTable();
        }
    }

    private void refreshTable() {
        model.setRowCount(0);
        try {
            List<Product> list = warehouseService.getAllProducts();
            for (Product p : list) {
                model.addRow(new Object[]{
                    p.getProductId(),
                    p.getName(),
                    p.getCategory(),
                    p.getQuantity(),
                    p.getPrice()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading products: " + e.getMessage());
        }
    }
}