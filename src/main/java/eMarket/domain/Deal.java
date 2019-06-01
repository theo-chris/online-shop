package eMarket.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import eMarket.EMarketApp;

import javax.persistence.*;

@Entity(name = "deal")
public class Deal {

	public static int lastId = 1;
	@Id
	@GeneratedValue(strategy= GenerationType.TABLE)
	@Column(name="deal_id", nullable=false)
	private int id;
    @Column(name="deal_start_date", nullable = false)
    private LocalDate startDate;
    @Column(name="deal_end_date")
    private LocalDate endDate;
    @Column(name= "deal_discount", nullable = false)
    private double discount;
    @ManyToOne(optional = false)
	@JoinColumn(name="deal_product")
	private Product product;


	
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Deal(){}
    
    public Deal(int id, LocalDate startDate, double discount, Product product) {
		this.id = id;
		this.startDate = startDate;
		this.discount = discount;
		this.product = product;
	}

	public void setId() {
		this.id = lastId;
		lastId++;
	}
	
	public void close() {
		this.endDate = EMarketApp.getSystemDate();
	}

	public boolean isActive() {
		boolean active =  false;
		if (this.endDate == null)
			active = (EMarketApp.getSystemDate().compareTo(startDate) >=0);
		else {
			System.out.println("system date: " + EMarketApp.getSystemDate().toString());
			System.out.println("start date: " + startDate);
			System.out.println("end date: " + endDate);
			active = (EMarketApp.getSystemDate().compareTo(startDate) >=0) && (EMarketApp.getSystemDate().compareTo(endDate) <=0);
		}
		System.out.println(this.getId() + " (" + this.getProduct().getName() + ") is active?" + active);
		return active;
	}
	
	public String getStartDateAsString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return this.startDate.format(formatter);
	}

	public String getEndDateAsString() {
		if (endDate != null) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			return this.endDate.format(formatter);
		}
		return "";
	}
}
