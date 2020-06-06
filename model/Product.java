package elaborato_ing_sw.model;

import java.io.Serializable;

import elaborato_ing_sw.utils.WriteableObjectProperty;

public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	private WriteableObjectProperty<String> name;
	private WriteableObjectProperty<String> brand;
	private WriteableObjectProperty<Section> section;
	private WriteableObjectProperty<Integer> pcsPerPack;
	private WriteableObjectProperty<Double> price;
	private String iconPath;
	private boolean isAvailable;
	private WriteableObjectProperty<Integer> quantity;

	public Product(String name, String brand, Section section, int pcsPerPack, double price, String iconPath,
			boolean isAvailable, int quantity) {
		this.name = new WriteableObjectProperty<String>(name);
		this.brand = new WriteableObjectProperty<String>(brand);
		this.section = new WriteableObjectProperty<Section>(section);
		this.pcsPerPack = new WriteableObjectProperty<Integer>(pcsPerPack);
		this.price = new WriteableObjectProperty<Double>(price);
		this.iconPath = iconPath;
		this.isAvailable = isAvailable;
		this.quantity = new WriteableObjectProperty<Integer>(quantity);
	}

	public String getName() {
		return name.get();
	}

	public WriteableObjectProperty<String> getNameProperty() {
		return name;
	}

	public void setName(String name) {
		this.name.set(name);
	}

	public String getBrand() {
		return brand.get();
	}

	public WriteableObjectProperty<String> getBrandProperty() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand.set(brand);
	}

	public WriteableObjectProperty<Section> getSectionProperty() {
		return section;
	}

	public Section getSection() {
		return section.get();
	}

	public void setSection(Section section) {
		this.section.set(section);
	}

	public int getPcsPerPack() {
		return pcsPerPack.get();
	}

	public WriteableObjectProperty<Integer> getPcsProperty() {
		return pcsPerPack;
	}

	public void setPcsPerPack(int pcsPerPack) {
		this.pcsPerPack.set(pcsPerPack);
	}

	public double getPrice() {
		return price.get();
	}

	public WriteableObjectProperty<Double> getPriceProperty() {
		return price;
	}

	public void setPrice(double price) {
		this.price.set(price);
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

	public int getQuantity() {
		return quantity.get();
	}

	public WriteableObjectProperty<Integer> getQuantityProperty() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity.set(quantity);
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Product && this.getName().equals(((Product) obj).getName())
				&& this.getBrand().equals(((Product) obj).getBrand());
	}

	public String toString() {
		return "Prodotto [name=" + name + ", brand=" + brand + ", section=" + section + ", pcsPerPack=" + pcsPerPack
				+ ", price=" + price + ", iconPath=" + iconPath + ", isAvailable=" + isAvailable + ", quantity="
				+ quantity + "]";
	}

}
