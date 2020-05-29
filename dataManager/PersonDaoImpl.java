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
	private ObservableList<Person> users;
	private String filepath; // filepath per storage 
	protected static PersonDaoImpl instance;
	
	protected PersonDaoImpl(String filepath) {
		this.filepath = filepath;
		
		users = FXCollections.observableArrayList();
	
		Person u;
		boolean eof = false;
		
		try{  
		  ObjectInputStream in = new ObjectInputStream(new FileInputStream(filepath));  
		  
		  while(!eof) {
			  u = (Person)in.readObject();  

			  if(u != null)
				  users.add(u);			  
			  else
				  eof = true;
		  }
		  
		  System.out.println(users);  
		  
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
		return users;
	}

	@Override
	public Person getUser(String user) {
    	for(Person u : users)
    		if(u.getCredentials().getUser().equals(user))
    			return u;
    	
    	return null;
	}

	@Override
	public boolean updateUser(Person user) {
		if(!users.contains(user))
			return false;
		
		for(int i = 0; i < users.size(); i++)
			if(users.get(i).equals(user))
				users.set(i, user);
		
		return true;
	}

	@Override
	public boolean deleteUser(Person user) {
		if(!users.contains(user))
			return false;
		
		for(int i = 0; i < users.size(); i++)
			if(users.get(i).equals(user))
				users.remove(i);
		
		return true;
		
	}

	@Override
	public boolean addUser(Person user) {
		if(users.contains(user))
			return false;
		
		users.add(user);
		
		return true;
	}

	@Override
	public boolean updateSource() {
		// NB: da richiamare quando l utente esce dall app, in modo da salvare i dati correnti su file
		try{  
			FileOutputStream fout = new FileOutputStream(filepath);  
			ObjectOutputStream out = new ObjectOutputStream(fout);  
	  
			for(Person u : users)
				out.writeObject(u);  				  
	  
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
