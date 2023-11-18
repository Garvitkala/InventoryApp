# Inventory Management System

The Inventory Management System is a Java-based application designed to manage customer information and inventory for businesses. The system includes functionality for customer registration, product management, and sales order creation.


## Features

- **Customer Management:** Register and log in as a customer to perform operations like searching for products and creating sales orders.
- **Product Management:** As an administrator, add, delete, and list all products in the inventory.
- **Sales Order Creation:** Customers can create sales orders by searching for products, adding them to the order, and placing the order.
- **Persistence:** Utilizes Jakarta Persistence (JPA) for data storage and retrieval.

### ER-Diagram

 +---------------------+         +---------------------+
 |       Customer      |         |       Product       |
 +---------------------+         +---------------------+
 | customerId (PK)     |         | productId (PK)      |
 | name                |         | name                |
 | username            |         | category            |
 | password            |         | brand               |
 | email               |         | price               |
 | address             |         | stockQuantity       |
 | isDeleted           |         | lowStockAlertThreshold|
 +---------------------+         | isDeleted           |
                                +---------------------+
                                       |
                                       |
                                       |
                             +---------------------+
                             |     SalesOrder      |
                             +---------------------+
                             | orderId (PK)        |
                             +---------------------+
                             |                     |
                             |                     |
                             |                     |
                             |                     |
                             |                     |
                             v                     v
               +---------------------+ 1    N +---------------------+
               |      OrderItem      |<--------|       Product       |
               +---------------------+         +---------------------+
               | orderItemId (PK)    |         | productId (FK)      |
               +---------------------+         +---------------------+
               | quantity            |         |                     |
               +---------------------+         |                     |
                                               +---------------------+




## Getting Started

### Prerequisites

To run the Inventory Management System, you need to have the following installed:

- Java Development Kit (JDK) version 8 or higher
- Jakarta Persistence (JPA) implementation (e.g., Hibernate)
- Maven for building the project

### Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/your-username/inventory-management-system.git

### Tech-Stack
-JAVA

