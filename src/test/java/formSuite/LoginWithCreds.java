package formSuite;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import pageObject.HomePage;
import pageObject.ProductsPage;
import resources.TestData;
import resources.Utilities;
import setup.Base;

public class LoginWithCreds extends Base {
	
	@Test(dataProvider="apkUsername", dataProviderClass=TestData.class)
	public void loginCreds(String name) throws IOException, InterruptedException {
		//start server
		service = startServer();
		AndroidDriver<AndroidElement> driver = Capabilities();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		HomePage homePg = new HomePage(driver);
		ProductsPage productsPg = new ProductsPage(driver);
		Utilities util = new Utilities(driver);
		homePg.countryDropDown.click();
		util.scrollToText("Bahamas");
		homePg.country.click();
		homePg.nameField.sendKeys(name);
		//hide keyboard action
		driver.hideKeyboard();
		homePg.femaleRadio.click();
		homePg.letsShopBtn.click();
		String prdctsPgTitle = productsPg.prdctsPageTitle.getText();
		//Confirm if clicking the let's shop button redirects the user to products page
		Assert.assertEquals("Products", prdctsPgTitle);
		//stop server
		service.stop();
		
	}
}
