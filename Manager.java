package elaborato_ing_sw;

import java.util.Date;

public class Manager extends Person{
	private int serialNumber;
	private Role role;
	
	public Manager(String name, String surname, Date dateOfBirth, Credentials credentials, int serialNumber, Role role) {
		super(name, surname, dateOfBirth, credentials);
		this.serialNumber = serialNumber;
		this.role = role;
	}

}
