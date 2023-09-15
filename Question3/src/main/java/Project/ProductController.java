package Project;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import java.util.List;

public class ProductController {
    private EntityManagerFactory emFactory;

    public ProductController() {
        emFactory = Persistence.createEntityManagerFactory("ans2");
    }

    public void close() {
        if (emFactory != null && emFactory.isOpen()) {
            emFactory.close();
        }
    }

    public void addInitialProducts() {
        EntityManager em = emFactory.createEntityManager();
        try {
            em.getTransaction().begin();

            Product product4 = new Product();
            product4.setName("Laptop");
            product4.setCategory("Electronics");
            product4.setBrand("Dell");
            product4.setPrice(799.99);
            product4.setStockQuantity(30);
            product4.setLowStockAlertThreshold(5);
            product4.setDeleted(false);

            Product product5 = new Product();
            product5.setName("Coffee Maker");
            product5.setCategory("Home & Kitchen");
            product5.setBrand("Keurig");
            product5.setPrice(89.99);
            product5.setStockQuantity(50);
            product5.setLowStockAlertThreshold(10);
            product5.setDeleted(false);

            Product product6 = new Product();
            product6.setName("Running Shoes");
            product6.setCategory("Footwear");
            product6.setBrand("Nike");
            product6.setPrice(119.99);
            product6.setStockQuantity(40);
            product6.setLowStockAlertThreshold(5);
            product6.setDeleted(false);

            Product product7 = new Product();
            product7.setName("Headphones");
            product7.setCategory("Electronics");
            product7.setBrand("Bose");
            product7.setPrice(249.99);
            product7.setStockQuantity(60);
            product7.setLowStockAlertThreshold(7);
            product7.setDeleted(false);

            Product product8 = new Product();
            product8.setName("Backpack");
            product8.setCategory("Travel");
            product8.setBrand("North Face");
            product8.setPrice(99.99);
            product8.setStockQuantity(35);
            product8.setLowStockAlertThreshold(5);
            product8.setDeleted(false);

            Product product9 = new Product();
            product9.setName("Gaming Console");
            product9.setCategory("Electronics");
            product9.setBrand("Sony");
            product9.setPrice(299.99);
            product9.setStockQuantity(25);
            product9.setLowStockAlertThreshold(5);
            product9.setDeleted(false);

            Product product10 = new Product();
            product10.setName("Smartphone");
            product10.setCategory("Electronics");
            product10.setBrand("Samsung");
            product10.setPrice(699.99);
            product10.setStockQuantity(45);
            product10.setLowStockAlertThreshold(5);
            product10.setDeleted(false);

            em.persist(product4);
            em.persist(product5);
            em.persist(product6);
            em.persist(product7);
            em.persist(product8);
            em.persist(product9);
            em.persist(product10);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void addProduct(Product product) {
        EntityManager em = emFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(product);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<Product> getAllProducts() {
        EntityManager em = emFactory.createEntityManager();
        try {
            Query query = em.createQuery("SELECT p FROM Product p");
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public void deleteProduct(int productId) {
        EntityManager em = emFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            Product product = em.find(Product.class, productId);
            if (product != null) {
                em.remove(product);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}

