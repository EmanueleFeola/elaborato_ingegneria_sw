package elaborato_ing_sw.model;

public class Product {
	// TODO: add section (fruit, meat...)
	
	private String name;
	private String brand;
	private int pcsPerPack;
	private double price;
	private String iconPath;
	private boolean isAvailable;
	
	public Product(String name, String brand, int pcsPerPack, double price, String iconPath, boolean isAvailable) {
		this.name = name;
		this.brand = brand;
		this.pcsPerPack = pcsPerPack;
		this.price = price;
		this.iconPath = iconPath;
		this.isAvailable = isAvailable;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public int getPcsPerPack() {
		return pcsPerPack;
	}
	public void setPcsPerPack(int pcsPerPack) {
		this.pcsPerPack = pcsPerPack;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getIconPath() {
		return iconPath;
	}
	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}
	public boolean isAvailable() {
		return isAvailable;
	}
	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	
	public String toString() {
		return "Prodotto [name=" + name + ", brand=" + brand + ", pcsPerPack=" + pcsPerPack + ", price=" + price
				+ ", iconPath=" + iconPath + ", isAvailable=" + isAvailable + "]";
	}
	
	
}
