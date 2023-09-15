package Project;

import java.util.List;
import java.util.Scanner;

public class InventoryManagementApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Inventory Management System");
        System.out.println("1. Administrator");
        System.out.println("2. Customer");
        System.out.println("0. Exit");
        System.out.print("Enter your role (1 for Administrator, 2 for Customer, 0 to Exit): ");
        int roleChoice = scanner.nextInt();

        switch (roleChoice) {
            case 1:
                // Administrator role
                adminMenu();
                break;
            case 2:
                // Customer role
                customerMenu();
                break;
            case 0:
                System.out.println("Exiting...");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }

        scanner.close();
    }

    // Administrator menu
    private static void adminMenu() {
        ProductController controller = new ProductController();
        controller.addInitialProducts();
        Scanner scanner = new Scanner(System.in);

        try {
            int choice;
            do {
                System.out.println("Administrator Menu");
                System.out.println("1. Add Product");
                System.out.println("2. Delete Product");
                System.out.println("3. List All Products");
                System.out.println("4. Generate Invoice");
                System.out.println("5. Log Out");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        Product product = new Product();
                        
                        




                     System.out.println("Enter product name:");
                     scanner.next();
                     String productName = scanner.nextLine();

                     product.setName(productName);

                     

                     System.out.println("Enter product price:");
                     double productPrice = scanner.nextDouble();
                     product.setPrice(productPrice);

                     System.out.println("Enter product quantity:");
                     int productQuantity = scanner.nextInt();
                     product.setStockQuantity(productQuantity);

            
              
                        
                        
                        controller.addProduct(product);
                        System.out.println("Product added successfully.");
                        break;
                    case 2:
                        System.out.print("Enter the Product ID to delete: ");
                        int productId = scanner.nextInt();
                        controller.deleteProduct(productId);
                        System.out.println("Product deleted successfully.");
                        break;
                    case 3:
                        // List all products
                        List<Product> products = controller.getAllProducts();
                        if (products.isEmpty()) {
                            System.out.println("No products added.");
                        } else {
                            for (Product p : products) {
                                System.out.println("Product ID: " + p.getProductId() + ", Name: " + p.getName());
                            }
                        }
                        break;
                    case 4:
                        System.out.println("Generating invoice...");
                        break;
                    case 5:
                        System.out.println("Logging out...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } while (choice != 5);
        } finally {
            controller.close();
            scanner.close();
        }
    }

 // Customer menu
    private static void customerMenu() {
        Scanner scanner = new Scanner(System.in);
        CustomerService customerService = new CustomerService();
        boolean loggedIn = false;

        try {
            int choice;
            do {
                System.out.println("Customer Menu");
                if (!loggedIn) {
                    System.out.println("1. Sign Up");
                    System.out.println("2. Log In");
                    System.out.println("0. Exit");
                    System.out.print("Enter your choice: ");
                    choice = scanner.nextInt();

                    switch (choice) {
                        case 1:
                            System.out.println("Signing up...");
                            customerService.signUp();
                            break;
                        case 2:
                            System.out.println("Logging in...");
                            loggedIn = customerService.logIn();
                            break;
                        case 0:
                            System.out.println("Exiting...");
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                } else {
                    System.out.println("1. Search for Products");
                    System.out.println("2. Create Sales Order");
                    System.out.println("3. Log Out");
                    System.out.print("Enter your choice: ");
                    choice = scanner.nextInt();

                    switch (choice) {
                        case 1:
                            System.out.println("Searching for products...");
                            // Implement product search functionality here
                            customerService.searchProducts();
                            break;
                        case 2:
                            System.out.println("Creating sales order...");
                            customerService.createSalesOrder();
                            break;
                     
                      
                        case 3:
                            loggedIn = false;
                            System.out.println("Logging out...");
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                }
            } while (choice != 0);
        } finally {
            scanner.close();
        }
    }
   
    
}
