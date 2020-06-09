package elaborato_ing_sw.model;

import java.io.Serializable;
import java.time.LocalDate;

import elaborato_ing_sw.utils.WriteableObjectProperty;

public abstract class Person implements Serializable{
	private static final long serialVersionUID = 1L;
	
	// SimpleObjectProperty non e' serializzabile, quindi usiamo questo workaround finche' non implementiamo il DB
	protected WriteableObjectProperty<String> name;
	protected WriteableObjectProperty<String> surname;
	protected WriteableObjectProperty<LocalDate> dateOfBirth;
	protected WriteableObjectProperty<Credentials> credentials;
	
	public Person(String name, String surname, LocalDate dateOfBirth, Credentials credentials) {
		this.name = new WriteableObjectProperty<String>(name);
		this.surname = new WriteableObjectProperty<String>(surname);
		this.dateOfBirth = new WriteableObjectProperty<LocalDate>(dateOfBirth);
		this.credentials = new WriteableObjectProperty<Credentials>(credentials);
	}

	public Person() {
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

	public String getSurname() {
		return surname.get();
	}
	
	public WriteableObjectProperty<String> getSurnameProperty() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname.set(surname);
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth.get();
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth.set(dateOfBirth);
	}

	public Credentials getCredentials() {
		return credentials.get();
	}

	public void setCredentials(Credentials credentials) {
		this.credentials.set(credentials);
	}

}
