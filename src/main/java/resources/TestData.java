package resources;

import org.testng.annotations.DataProvider;

public class TestData {
	
	@DataProvider(name="apkUsername")
	public Object[][] getNameData(){
		
		Object name [][] = new Object[][]{
			{"Eddie"},{"34413"},{"@#$%&"}
		};
		return name;
	}
	
	@DataProvider(name="apkProducts")
	public Object[][] getProductData(){
		Object product [][] = new Object[][] {
			{"Converse All Star","Jordan 6 Rings"}
		};
		return product;
	}

}
