package elaborato_ing_sw.model;

import java.time.LocalDate;

public class Manager extends Person{
	private static final long serialVersionUID = 1L;
	
	private int serialNumber;
	private Role role;
	
	public Manager(String name, String surname, LocalDate dateOfBirth, Credentials credentials, int serialNumber, Role role) {
		super(name, surname, dateOfBirth, credentials);
		this.serialNumber = serialNumber;
		this.role = role;
	}

	public int getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Manager [serialNumber=" + serialNumber + ", role=" + role + ", name=" + name + ", surname=" + surname
				+ ", dateOfBirth=" + dateOfBirth + ", credentials=" + credentials + "]";
	}

}
