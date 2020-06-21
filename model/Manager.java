package elaborato_ing_sw.model;

import java.time.LocalDate;

import elaborato_ing_sw.utils.WriteableObjectProperty;

public class Manager extends Person {
	private static final long serialVersionUID = 1L;

	private WriteableObjectProperty<Role> role;
	private WriteableObjectProperty<Integer> serialNumber;

	// NB: lo username di accesso al sistema per il manager e il serialNumber,
	// perche identifica gia in maniera univoca un manager
	public Manager(String name, String surname, LocalDate dateOfBirth, String pwd, int serialNumber, Role role) {
		super(name, surname, dateOfBirth, new Credentials(String.valueOf(serialNumber), pwd));
		this.serialNumber = new WriteableObjectProperty<Integer>(serialNumber);
		this.role = new WriteableObjectProperty<Role>(role);
	}

	public Manager() {
		super(null, null, null, null);
	}

	public int getSerialNumber() {
		return serialNumber.get();
	}

	public void setSerialNumber(int serialNumber) {
		this.serialNumber.set(serialNumber);
	}

	public Role getRole() {
		return role.get();
	}

	public WriteableObjectProperty<Role> getRoleProperty() {
		return role;
	}

	public void setRole(Role role) {
		this.role.set(role);
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Manager
				&& this.getCredentials().getUser().equals(((Manager) obj).getCredentials().getUser());
	}

	@Override
	public String toString() {
		return "Manager [serialNumber=" + serialNumber + ", role=" + role + ", name=" + name + ", surname=" + surname
				+ ", dateOfBirth=" + dateOfBirth + ", credentials=" + credentials + "]";
	}

}
