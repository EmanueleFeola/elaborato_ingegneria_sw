package elaborato_ing_sw.dataManager;

import elaborato_ing_sw.model.Product;

public class ProductDaoImpl extends DaoImpl<Product>{
	private static ProductDaoImpl instance;
	private static String productFilename = "products";
	
	protected ProductDaoImpl(String filepath) {
		super(filepath);
	}
	
	public static ProductDaoImpl getProductDaoImpl()
	{
		if(instance == null)
			instance = new ProductDaoImpl(productFilename);
		
		return (ProductDaoImpl)instance;
	}

	@Override
	public Product getItem(String product) {
		for (Product p : getAllItems())
			if (p.getName().equals(product))
				return p;

		return null;
	}
	
	@Override
	public boolean addItem (Product product) {
		if (objs.contains(product))
			return false;

		objs.add(product);

		updateSource();

		return true;
	}
}
