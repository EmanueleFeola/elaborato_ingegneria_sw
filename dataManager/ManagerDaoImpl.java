package elaborato_ing_sw.dataManager;

public class ManagerDaoImpl extends PersonDaoAbstract{
	private static ManagerDaoImpl instance;
	private static String managerFilename = "managers";
	
	private ManagerDaoImpl(String filepath) {
		super(filepath);
	}
	
	public static ManagerDaoImpl getManagerDaoImpl()
	{
		if(instance == null)
			instance = new ManagerDaoImpl(managerFilename);
		
		return (ManagerDaoImpl)instance;
	}
}
