package elaborato_ing_sw.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import elaborato_ing_sw.utils.WriteableObjectProperty;

public class Credentials implements Serializable{
	private static final long serialVersionUID = 1L;
	
	protected WriteableObjectProperty<String> user;
	protected String md5Pwd;
	
	public Credentials(String user, String pwd) {
		this.user = new WriteableObjectProperty<String>(user);
		this.md5Pwd = Credentials.getMd5(pwd);
	}
	
    public static String getMd5(String input) 
    { 
        try { 
  
            // Static getInstance method is called with hashing MD5 
            MessageDigest md = MessageDigest.getInstance("MD5"); 
  
            // digest() method is called to calculate message digest 
            //  of an input digest() return array of byte 
            byte[] messageDigest = md.digest(input.getBytes()); 
  
            // Convert byte array into signum representation 
            BigInteger no = new BigInteger(1, messageDigest); 
  
            // Convert message digest into hex value 
            String hashtext = no.toString(16); 
            while (hashtext.length() < 32) { 
                hashtext = "0" + hashtext; 
            } 
            return hashtext; 
        }  
  
        // For specifying wrong message digest algorithms 
        catch (NoSuchAlgorithmException e) { 
            throw new RuntimeException(e); 
        } 
    }
    
	public String getUser() {
		return user.get();
	}

	public WriteableObjectProperty<String> getUsernameProperty() {
		return user;
	}
	
	public void setUser(String user) {
		this.user.set(user);
	}

	public String getMd5Pwd() {
		return md5Pwd;
	}

	public void setMd5Pwd(String md5Pwd) {
		this.md5Pwd = Credentials.getMd5(md5Pwd);
	}

	@Override
	public String toString() {
		return "Credentials [user=" + user + ", md5Pwd=" + md5Pwd + "]";
	}
	
}
