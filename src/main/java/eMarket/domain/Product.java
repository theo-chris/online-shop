package eMarket.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="product")
public class Product {

	public static int lastId = 0;
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name="product_id", nullable=false)
    private int id = -1 ;
	@Column(name="product_name", nullable=false)
    private String name;
	@Column(name="product_description")
    private String description;
	@Column(name="product_price", nullable=false)
    private Double price;
    
    
    
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Product(){}
    
    public Product(int id, String name, String description, Double price) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
	}

//	public void setId() {
//		this.id = lastId;
//		lastId++;
//	}

}
