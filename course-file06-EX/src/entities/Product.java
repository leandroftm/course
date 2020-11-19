package entities;

public class Product {

	private String name;
	private double price;
	private int quantity;
	
	public Product() {
		
	}
	
	public Product(String name, double price, int quantity) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}
	
	public String getName() {
		return name;
	}
	
	public double price() {
		return price;
	}
	
	public int quantity() {
		return quantity;
	}
	
	public String totalPrice(){
		return name + "," + String.format("%.2f", price * quantity);		
	}
	
	@Override
	public String toString() {
		return name + "," + price + "," + quantity ;
	}
}
