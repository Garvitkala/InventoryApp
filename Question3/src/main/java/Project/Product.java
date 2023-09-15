package Project;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int productId;

    @Column(name = "name")
    private String name;

    @Column(name = "category")
    private String category;

    @Column(name = "brand")
    private String brand;

    @Column(name = "price")
    private double price;

    @Column(name = "stock_quantity")
    private int stockQuantity;

    @Column(name = "low_stock_alert_threshold")
    private int lowStockAlertThreshold;

    @Column(name = "is_deleted")
    private boolean isDeleted;

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public int getLowStockAlertThreshold() {
		return lowStockAlertThreshold;
	}

	public void setLowStockAlertThreshold(int lowStockAlertThreshold) {
		this.lowStockAlertThreshold = lowStockAlertThreshold;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Product(int productId, String name, String category, String brand, double price, int stockQuantity,
			int lowStockAlertThreshold, boolean isDeleted) {
		super();
		this.productId = productId;
		this.name = name;
		this.category = category;
		this.brand = brand;
		this.price = price;
		this.stockQuantity = stockQuantity;
		this.lowStockAlertThreshold = lowStockAlertThreshold;
		this.isDeleted = isDeleted;
	}

	public Product() {
	}
    
    
    
    
}



