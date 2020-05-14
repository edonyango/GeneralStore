package cartSuite;

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
import setup.Base;

public class ConfirmTotal extends Base{
	
	@BeforeTest
	public void killAllNodes () throws IOException, InterruptedException {
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Thread.sleep(3000);
	}
	
	@Test
	public void totalPurchase () throws IOException, InterruptedException {
		//confirm the sum of all products ins the same as the indicated total purchase amount
		service = startServer();
		AndroidDriver<AndroidElement> driver = Capabilities();
		HomePage homePg = new HomePage(driver);
		ProductsPage productsPg = new ProductsPage(driver);
		CartPage cartPg = new CartPage(driver);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		homePg.nameField.sendKeys("Username");
		homePg.letsShopBtn.click();
		productsPg.productsToCartBtn.get(0).click();
		productsPg.productsToCartBtn.get(1).click();
		productsPg.cartBtn.click();
		int prodNum = cartPg.cartPrdctsPrice.size();
		
		double sum = 0;
		for (int c=0;c<prodNum;c++) {
			String rawPrice1 = cartPg.cartPrdctsPrice.get(c).getText();
			String rawPrice2 = removeDollarSign(rawPrice1);
			double price = priceToDouble(rawPrice2);
			sum +=price;
		}
		String total1 = cartPg.totalAmount.getText();
		String total2 = removeDollarSign(total1);
		String total3 = total2.trim();
		double realTotal = priceToDouble(total3);
		
		Assert.assertEquals(sum, realTotal);
		service.stop();
	}
	
	public String removeDollarSign (String item) {
		String price = item.substring(1);
		return price;
	}
	
	public double priceToDouble (String price) {
		double amount = Double.parseDouble(price);
		return amount;
	}

}
