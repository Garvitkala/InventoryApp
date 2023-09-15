package Project;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class SalesOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @OneToMany(mappedBy = "SalesOrder")
    private List<OrderItem> orderItems;

    public SalesOrder() {
        this.orderItems = new ArrayList<>();
    }

    public Long getOrderId() {
        return orderId;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void addOrderItem(Product product, int quantity) {
        OrderItem orderItem = new OrderItem(this, product, quantity);
        orderItems.add(orderItem);
    }

    public double calculateTotal() {
        double total = 0;
        for (OrderItem orderItem : orderItems) {
            total += orderItem.calculateSubtotal();
        }
        return total;
    }

    public void clearOrder() {
        orderItems.clear();
    }
@Entity
    public static class OrderItem {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long orderItemId;

        @ManyToOne
        private SalesOrder SalesOrder;

        @ManyToOne
        private Product product;

        private int quantity;

        public OrderItem() {
            // Default constructor required by JPA
        }

        public OrderItem(SalesOrder SalesOrder, Product product, int quantity) {
            this.SalesOrder = SalesOrder;
            this.product = product;
            this.quantity = quantity;
        }

        public Product getProduct() {
            return product;
        }

        public int getQuantity() {
            return quantity;
        }

        public double calculateSubtotal() {
            return product.getPrice() * quantity;
        }
    }
}
