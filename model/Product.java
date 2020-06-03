package elaborato_ing_sw.model;

import java.io.Serializable;

public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name;
	private String brand;
	private Section section;
	private int pcsPerPack;
	private double price;
	private String iconPath;
	private boolean isAvailable;

	public Product(String name, String brand, Section section, int pcsPerPack, double price, String iconPath, boolean isAvailable) {
		this.name = name;
		this.brand = brand;
		this.section = section;
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
	
	public Section getSection() {
		return section;
	}
	
	public void setSection(Section section) {
		this.section = section;
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

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Product && this.getName().equals(((Product) obj).getName())
				&& this.getBrand().equals(((Product) obj).getBrand());
	}

	public String toString() {
		return "Prodotto [name=" + name + ", brand=" + brand + ", section=" + section + ", pcsPerPack=" + pcsPerPack + ", price=" + price
				+ ", iconPath=" + iconPath + ", isAvailable=" + isAvailable + "]";
	}

}
