package Project;

import java.util.List;
import java.util.Scanner;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class CustomerService {
    private EntityManagerFactory emFactory;
    private SalesOrder currentOrder;
    private Scanner scanner;

    public CustomerService() {
        emFactory = Persistence.createEntityManagerFactory("ans2");
        currentOrder = new SalesOrder();
        scanner = new Scanner(System.in);
    }

    public void close() {
        if (emFactory != null && emFactory.isOpen()) {
            emFactory.close();
        }
    }

    public void signUp() {
        EntityManager em = emFactory.createEntityManager();

        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter your username: ");
        String username = scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        try {
            em.getTransaction().begin();

            Customer customer = new Customer();
            customer.setName(name);
            customer.setUsername(username);
            customer.setPassword(password);

            em.persist(customer);

            em.getTransaction().commit();

            System.out.println("Sign-up successful. You can now log in.");
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Error: Sign-up failed. Please try again.");
        } finally {
            em.close();
        }
    }

    public boolean logIn() {
        EntityManager em = emFactory.createEntityManager();

        System.out.print("Enter your username: ");
        String username = scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        try {
            Customer customer = em.createQuery(
                    "SELECT c FROM Customer c WHERE c.username = :username AND c.password = :password",
                    Customer.class)
                    .setParameter("username", username)
                    .setParameter("password", password)
                    .getSingleResult();

            if (customer != null) {
                System.out.println("Log-in successful. Welcome, " + customer.getName() + "!");
                return true;
            } else {
                System.out.println("Log-in failed. Invalid credentials.");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Log-in failed. Please try again.");
            return false;
        } finally {
            em.close();
        }
    }

    public void searchProducts() {
        EntityManager em = emFactory.createEntityManager();

        System.out.print("Enter your search criteria: ");
        String searchCriteria = scanner.nextLine();

        try {
            em.getTransaction().begin();

            // Define a JPQL query to search for products based on the provided criteria
            String jpql = "SELECT p FROM Product p " +
                    "WHERE p.name LIKE :criteria " +
                    "OR p.category LIKE :criteria " +
                    "OR p.brand LIKE :criteria";

            TypedQuery<Product> query = em.createQuery(jpql, Product.class);
            query.setParameter("criteria", "%" + searchCriteria + "%");

            // Execute the query and retrieve the matching products
            List<Product> products = query.getResultList();

            if (!products.isEmpty()) {
                System.out.println("Search results:");
                for (Product product : products) {
                    System.out.println("Product ID: " + product.getProductId() + ", Name: " + product.getName() +
                            ", Category: " + product.getCategory() + ", Brand: " + product.getBrand() +
                            ", Price: " + product.getPrice() + ", Stock Quantity: " + product.getStockQuantity());
                }
            } else {
                System.out.println("No products found matching the criteria.");
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Error: Product search failed. Please try again.");
        } finally {
            em.close();
        }
    }

    public void createSalesOrder() {
    	
        try {
            int choice;
            do {
                System.out.println("Create Sales Order");
                System.out.println("1. Add Product to Order");
                System.out.println("2. View Order");
                System.out.println("3. Place Order");
                System.out.println("4. Cancel Order");
                System.out.println("0. Go Back");
                System.out.print("Enter your choice: ");
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        searchAndAddProductToOrder();
                        break;
                    case 2:
                        viewOrder();
                        break;
                    case 3:
                        placeOrder();
                        break;
                    case 4:
                        cancelOrder();
                        break;
                    case 0:
                        System.out.println("Going back to the Customer Menu...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } while (choice != 0);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
        }
    }
    private void searchAndAddProductToOrder() {
        EntityManager em = emFactory.createEntityManager();

        System.out.print("Enter the product name or keyword: ");
        String searchKeyword = scanner.nextLine();

        try {
            em.getTransaction().begin();

            // Define a JPQL query to search for products based on the provided keyword
            String jpql = "SELECT p FROM Product p " +
                    "WHERE p.name LIKE :keyword " +
                    "OR p.category LIKE :keyword " +
                    "OR p.brand LIKE :keyword";

            TypedQuery<Product> query = em.createQuery(jpql, Product.class);
            query.setParameter("keyword", "%" + searchKeyword + "%");

            List<Product> products = query.getResultList();

            if (!products.isEmpty()) {
                System.out.println("Search results:");
                for (Product product : products) {
                    System.out.println("Product ID: " + product.getProductId() + ", Name: " + product.getName() +
                            ", Category: " + product.getCategory() + ", Brand: " + product.getBrand() +
                            ", Price: " + product.getPrice() + ", Stock Quantity: " + product.getStockQuantity());
                }

                System.out.print("Enter the Product ID to add to the order (0 to cancel): ");
                int productIdToAdd = Integer.parseInt(scanner.nextLine());

                if (productIdToAdd != 0) {
                    System.out.print("Enter the quantity: ");
                    int quantity = Integer.parseInt(scanner.nextLine());

                    if (quantity > 0) {
                        Product selectedProduct = em.find(Product.class, productIdToAdd);
                        if (selectedProduct != null) {
                            currentOrder.addOrderItem(selectedProduct, quantity);
                            System.out.println("Product added to the order.");
                        } else {
                            System.out.println("Product not found.");
                        }
                    } else {
                        System.out.println("Invalid quantity. Please try again.");
                    }
                } else {
                    System.out.println("Product not added to the order.");
                }
            } else {
                System.out.println("No products found matching the keyword.");
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Error: Product search and addition to the order failed. Please try again.");
        } finally {
            em.close();
        }
    }

    private void viewOrder() {
        System.out.println("Current Order:");
        List<SalesOrder.OrderItem> orderItems = currentOrder.getOrderItems();

        if (orderItems.isEmpty()) {
            System.out.println("No items in the order.");
        } else {
            for (SalesOrder.OrderItem orderItem : orderItems) {
                Product product = orderItem.getProduct();
                System.out.println("Product: " + product.getName() +
                        ", Quantity: " + orderItem.getQuantity() +
                        ", Subtotal: " + orderItem.calculateSubtotal());
            }
            System.out.println("Total: " + currentOrder.calculateTotal());
        }
    }

    private void placeOrder() {
        EntityManager em = emFactory.createEntityManager();

        try {
            em.getTransaction().begin();

            
            em.persist(currentOrder);

            em.getTransaction().commit();

            currentOrder.clearOrder();

            System.out.println("Order placed successfully.");
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Error: Order placement failed. Please try again.");
            e.printStackTrace(); 
        } finally {
            em.close();
        }
    }


    private void cancelOrder() {
        currentOrder.clearOrder();
        System.out.println("Order canceled.");
    }
}
