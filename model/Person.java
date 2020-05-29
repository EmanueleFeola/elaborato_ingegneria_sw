package elaborato_ing_sw.model;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class Person implements Serializable{
	protected String name;
	protected String surname;
	protected LocalDate dateOfBirth;
	protected Credentials credentials;
	
	public Person(String name, String surname, LocalDate dateOfBirth, Credentials credentials) {
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

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Credentials getCredentials() {
		return credentials;
	}

	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}

}
