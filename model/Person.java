package elaborato_ing_sw.model;

import java.io.Serializable;
import java.util.Date;

public abstract class Person implements Serializable{
	protected String name;
	protected String surname;
	protected Date dateOfBirth;
	protected Credentials credentials;
	
	public Person(String name, String surname, Date dateOfBirth, Credentials credentials) {
		this.name = name;
		this.surname = surname;
		this.dateOfBirth = dateOfBirth;
		this.credentials = credentials;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Credentials getCredentials() {
		return credentials;
	}

	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}

}
