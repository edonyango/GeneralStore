package cartSuite;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static java.time.Duration.ofSeconds;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import pageObject.CartPage;
import pageObject.HomePage;
import pageObject.ProductsPage;
import setup.Base;

public class CheckOutCart extends Base{
	
	@Test
	public void completePurchase() throws IOException, InterruptedException {
		//visit website to complete purchase 
		service = startServer();
		AndroidDriver<AndroidElement> driver = Capabilities();
		HomePage homePg = new HomePage(driver);
		ProductsPage productsPg = new ProductsPage(driver);
		CartPage cartPg = new CartPage(driver);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		homePg.nameField.sendKeys("Username");
		homePg.letsShopBtn.click();
		productsPg.productsToCartBtn.get(1).click();
		productsPg.cartBtn.click();
		cartPg.emailCheckBox.click();
		@SuppressWarnings("rawtypes")
		TouchAction touch = new TouchAction(driver);
		touch.longPress(longPressOptions().withElement(element(cartPg.tOCButton)).withDuration(ofSeconds(2))).release().perform();
		cartPg.tOCClose.click();
		cartPg.visitWebsiteBtn.click();
		service.stop();
		
	}

}
