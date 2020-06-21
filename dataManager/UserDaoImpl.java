package elaborato_ing_sw.dataManager;

public class UserDaoImpl extends PersonDaoAbstract{
	private static UserDaoImpl instance;
	private static String userFilename = "users";
	
	private UserDaoImpl(String filepath) {
		super(filepath);
	}
	
	public static UserDaoImpl getUserDaoImpl()
	{
		if(instance == null)
			instance = new UserDaoImpl(userFilename);
		
		return (UserDaoImpl)instance;
	}

}
