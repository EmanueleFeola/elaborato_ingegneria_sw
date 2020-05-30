package elaborato_ing_sw.dataManager;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import elaborato_ing_sw.model.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public abstract class PersonDaoImpl implements PersonDao{
	private ObservableList<Person> people;
	private String filepath; // filepath per storage 
	
	protected PersonDaoImpl(String filepath) {
		this.filepath = filepath;
		
		people = FXCollections.observableArrayList();
	
		Person p;
		boolean eof = false;
		
		try{  
		  ObjectInputStream in = new ObjectInputStream(new FileInputStream(filepath));  
		  
		  while(!eof) {
			  p = (Person)in.readObject();  

			  if(p != null)
				  people.add(p);			  
			  else
				  eof = true;
		  }
		  
		  System.out.println(people);  
		  
		  in.close();  
		  
		}
		catch(EOFException e){
			// non è un errore, è giusto che arrivi alla fine del file
			System.out.println("Read successfully");  
		}
		catch(FileNotFoundException e) {
			// se il file ancora non esiste, lo creo
		    try {
		        File fd = new File(filepath);
		        if (fd.createNewFile())
		          System.out.println("File created: " + fd.getName());
		    }
		    catch (IOException ioe) {
		        System.out.println("An error occurred.");
		        ioe.printStackTrace();
		    }
		}
		catch(Exception e) {
			System.out.println("Read error");
			System.out.println(e);			
		}
	}
	
	@Override
	public ObservableList<Person> getAllUsers() {
		return people;
	}

	@Override
	public Person getUser(String user) {
    	for(Person p : people)
    		if(p.getCredentials().getUser().equals(user))
    			return p;
    	
    	return null;
	}

	@Override
	public boolean updateUser(Person user) {
		if(!people.contains(user))
			return false;
		
		for(int i = 0; i < people.size(); i++)
			if(people.get(i).equals(user))
				people.set(i, user);
		
		updateSource();
		
		return true;
	}

	@Override
	public boolean deleteUser(Person user) {
		if(!people.contains(user))
			return false;
		
		people.remove(user);

		updateSource();

		return true;
	}

	@Override
	public boolean addUser(Person user) {
		if(people.contains(user))
			return false;
		
		people.add(user);
		
		updateSource();

		return true;
	}

	@Override
	public boolean updateSource() {
		// NB: da richiamare quando l utente esce dall app, in modo da salvare i dati correnti su file
		try{  
			FileOutputStream fout = new FileOutputStream(filepath);  
			ObjectOutputStream out = new ObjectOutputStream(fout);  
	  
			for(Person p : people)
				out.writeObject(p);  				  
	  
		    out.flush();  
		    out.close();  
	  
		    System.out.println("Serialized successfully");  
		}catch(Exception e){
			System.out.println("Write error");
			System.out.println(e);
			
			return false;
		}  
	
		return true;
	}

}
