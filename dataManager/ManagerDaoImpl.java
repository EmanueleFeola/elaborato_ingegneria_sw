package elaborato_ing_sw.dataManager;

public class ManagerDaoImpl extends PersonDaoImpl{
	private static String managerFilename = "managers";
	
	private ManagerDaoImpl(String filepath) {
		super(filepath);
	}
	
	public static ManagerDaoImpl getUserDaoImpl()
	{
	
		if(instance == null)
			instance = new ManagerDaoImpl(managerFilename);
		
		return (ManagerDaoImpl)instance;
	}
}
