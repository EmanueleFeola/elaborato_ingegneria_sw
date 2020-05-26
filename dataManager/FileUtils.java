package elaborato_ing_sw.dataManager;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import elaborato_ing_sw.model.User;
import javafx.collections.ObservableList;

public class FileUtils {
	private String filepath = "users";
	
	public void serializeArrayList(ObservableList<User> users) {
		  try{  
			  FileOutputStream fout=new FileOutputStream(filepath);  
			  ObjectOutputStream out=new ObjectOutputStream(fout);  
			  
			  for(User u : users) {
				  out.writeObject(u);  				  
			  }
			  
			  out.flush();  
			  out.close();  
			  
			  System.out.println("Serialized successfully");  
		  }catch(Exception e){
			  System.out.println("Write error");
			  System.out.println(e);
		  }  
	}
	
	public ArrayList<User> getUsers(){
		ArrayList<User> users = new ArrayList<User>();
		User u;
		boolean eof = false;
		
		try{  
		  ObjectInputStream in = new ObjectInputStream(new FileInputStream(filepath));  
		  
		  while(!eof) {
			  u = (User)in.readObject();  

			  if(u != null)
				  users.add(u);			  
			  else
				  eof = true;
		  }
		  
		  System.out.println("Read successfully");  
		  System.out.println(users);  
		  
		  in.close();  
		  
		}catch(Exception e){
			System.out.println("Read error");
			System.out.println(e);
		}
		
		return users;  
	}
	
}
