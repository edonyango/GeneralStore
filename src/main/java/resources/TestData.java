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

}
