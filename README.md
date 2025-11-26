# Warehouse Management System – GUI Layer

This folder contains the complete **Graphical User Interface** for the Warehouse Management System.  
The GUI is fully separated from business logic and database access (DAO + service layer).

The goal of this structure is to demonstrate **clean architecture**, modular design, and readable separation of concerns.

## 📁 Package Structure – `com.warehouse.views`

com.warehouse.views
│
├── MainApplicationView.java      # Main window, navigation + content area
│
├── components/                   # Reusable UI building blocks
│   ├── HeaderBar.java
│   └── NavigationPanel.java
│
├── dashboard/                    # Overview / home screen
│   └── MainDashboardView.java
│
├── products/                     # Product management UI
│   ├── ProductListView.java
│   └── ProductFormView.java
│
└── orders/                       # Order / POS UI
└── OrderFormView.java

Each view is a **self-contained Swing panel or dialog**, making it easy to maintain, test, and extend.

---

## 🧩 How the GUI Works

### `MainApplicationView`  
Acts as the *controller* for all screens.  
Uses a `CardLayout` to switch between:

- Dashboard  
- Product management  
- Orders (POS)

### Components  
Reusable UI elements:

- `HeaderBar` – Title bar at the top  
- `NavigationPanel` – Sidebar navigation menu  

### Feature Modules  
Each subfolder contains all UI classes related to a specific domain:

- `products/` handles product listing and form dialogs  
- `orders/` contains the POS system  
- `dashboard/` shows system statistics  

---

## ▶️ Running the GUI

Run:

```java
public static void main(String[] args) {
    new MainApplicationView().setVisible(true);
}