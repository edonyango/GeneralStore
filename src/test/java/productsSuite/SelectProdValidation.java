package productsSuite;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import pageObject.HomePage;
import pageObject.ProductsPage;
import setup.Base;

public class SelectProdValidation extends Base {
	
	@Test
	public void checkOutEmptyCart() throws IOException, InterruptedException {
		//Checkout an empty cart
		service = startServer();
		AndroidDriver<AndroidElement> driver = Capabilities();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		HomePage homePg = new HomePage(driver);
		ProductsPage productsPg = new ProductsPage(driver);
		homePg.nameField.sendKeys("username");
		homePg.letsShopBtn.click();
		productsPg.cartBtn.click();
		String noSelectionError = productsPg.toastMsg.getText();
		//System.out.println(noSelectionError);
		Assert.assertEquals(noSelectionError, "Please add some product at first");
		service.stop();
	}

}
