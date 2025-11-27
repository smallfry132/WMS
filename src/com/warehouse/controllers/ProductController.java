package com.warehouse.controllers;

import com.warehouse.models.Product;
import com.warehouse.services.WarehouseService;

import java.util.Date;
import java.util.List;

public class ProductController {

    private final WarehouseService warehouseService;

    public ProductController() {
        this.warehouseService = new WarehouseService();
    }

    public List<Product> getAllProducts() {
        return warehouseService.getAllProducts();
    }

    public boolean addProduct(String name, String category, int quantity, double price) {
        return warehouseService.addProduct(name, category, quantity, price, new Date(), 1);
    }
}