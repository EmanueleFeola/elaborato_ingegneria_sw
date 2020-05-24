package elaborato_ing_sw.model;

import java.io.Serializable;
import java.util.Date;

// NB: per l utente la mail è memorizzata nel campo user del campo Credentials

public class User extends Person implements Serializable{
	private String address;
	private String city;
	private int postalCode; // CAP
	private String telNum;
	
	public User(String name, String surname, Date dateOfBirth, Credentials credentials, String address, String city, int postalCode, String telNum) {
		super(name, surname, dateOfBirth, credentials);
		this.address = address;
		this.city = city;
		this.postalCode = postalCode;
		this.telNum = telNum;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}

	public String getTelNum() {
		return telNum;
	}

	public void setTelNum(String telNum) {
		this.telNum = telNum;
	}

	@Override
	public String toString() {
		return "User [address=" + address + ", city=" + city + ", postalCode=" + postalCode + ", telNum=" + telNum
				+ ", name=" + name + ", surname=" + surname + ", dateOfBirth=" + dateOfBirth + ", credentials="
				+ credentials + "]";
	}

	
}
