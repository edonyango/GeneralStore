package productsSuite;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import pageObject.CartPage;
import pageObject.HomePage;
import pageObject.ProductsPage;
import resources.TestData;
import resources.Utilities;
import setup.Base;

public class SelectProducts extends Base {
	
	@BeforeTest
	public void killAllNodes() throws IOException, InterruptedException {
		//taskkill /F /IM node.exe
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Thread.sleep(3000);
	}
	
	@Test
	public void selectTwoPrdcts() throws IOException, InterruptedException {
		//Select products on the first page (2)
		service = startServer();
		AndroidDriver<AndroidElement> driver = Capabilities();
		HomePage homePg = new HomePage(driver);
		ProductsPage productsPg = new ProductsPage(driver);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		homePg.nameField.sendKeys("Name");
		homePg.letsShopBtn.click();
		int numOfPrdcts = productsPg.productsToCartBtn.size();
		for (int a=0;a<numOfPrdcts;a++) {
			productsPg.productsToCartBtn.get(a).click();
		}
		
		String numCartPd = productsPg.numOfCartPrdcts.getText();
		Assert.assertEquals(numCartPd, "2");
		service.stop();
	}
	
	@Test(dataProvider="apkProducts", dataProviderClass=TestData.class)
	public void specificPrdcts (String product1, String product2) throws IOException, InterruptedException {
		//Select 2 specific products and confirm them in the checkout page
		service = startServer();
		AndroidDriver<AndroidElement> driver = Capabilities();
		HomePage homePg = new HomePage(driver);
		ProductsPage productsPg = new ProductsPage(driver);
		CartPage cartPg = new CartPage(driver);
		Utilities util = new Utilities(driver);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		homePg.nameField.sendKeys("Username");
		homePg.letsShopBtn.click();
		String [] products = {product1,product2};
		for (int b=0;b<products.length;b++) {
		util.scrollToText(products[b]);
		productsPg.productsToCartBtn.get(1).click();
		}
		productsPg.cartBtn.click();
		String prdct1 = cartPg.cartPrdctsName.get(0).getText();
		String prdct2 = cartPg.cartPrdctsName.get(1).getText();
		
		Assert.assertEquals(product1, prdct1);
		Assert.assertEquals(product2, prdct2);
		service.stop();
	}

}
