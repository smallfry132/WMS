package com.warehouse.controllers;

import com.warehouse.models.Customer;
import com.warehouse.models.Product;
import com.warehouse.services.OrderService;
import com.warehouse.services.WarehouseService;

import java.util.List;

public class OrderController {

    private final WarehouseService warehouseService;
    private final OrderService orderService;

    public OrderController() {
        this.warehouseService = new WarehouseService();
        this.orderService = new OrderService();
    }

    public List<Customer> getAllCustomers() {
        return warehouseService.getAllCustomers();
    }

    public List<Product> getAllProducts() {
        return warehouseService.getAllProducts();
    }
}