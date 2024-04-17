package formSuite;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.PressesKey;
import pageObject.HomePage;
import resources.Utilities;
import setup.Base;

public class LoginPgValidations extends Base {
	
	@BeforeTest
	public void killAllNodes() throws IOException, InterruptedException {
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Thread.sleep(3000);
	}
	
	@Test
	public void validateHomePg() throws IOException, InterruptedException {
		//Check if launching application leads to "General Store" page
		service = startServer();
		AndroidDriver<AndroidElement> driver = Capabilities();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		HomePage homePg = new HomePage(driver);
		String homePgTitle = homePg.homePageTitle.getText();
		Assert.assertEquals("General Store", homePgTitle);
		service.stop();
	}
	
	@Test
	public void loginWoName() throws IOException, InterruptedException {
		//Check if a toast message is displayed when login without name
		service = startServer();
		AndroidDriver<AndroidElement> driver = Capabilities();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		HomePage homePg = new HomePage(driver);
		Utilities util = new Utilities(driver);
		homePg.countryDropDown.click();
		util.scrollToText("Bahamas");
		homePg.country.click();
		homePg.letsShopBtn.click();
		//Capturing toast message
		String toastMessage = homePg.toastMsg.getAttribute("name");
		//System.out.println(toastMessage);
		Assert.assertEquals("Please enter your name",toastMessage);
		service.stop();
	}
	
	@Test
	public void exitAppMessage () throws IOException, InterruptedException {
		//check if exit application toast appears when BACK button is clicked
		service = startServer();
		AndroidDriver<AndroidElement> driver = Capabilities();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		HomePage homePg = new HomePage (driver);
		homePg.femaleRadio.click();
		//Press back key
		((PressesKey) driver).pressKey(new KeyEvent(AndroidKey.BACK));
		String toastMessage = homePg.toastMsg.getText();
		//System.out.println(toastMessage);
		Assert.assertEquals("Press BACK again to exit",toastMessage);
		service.stop();
		
	}

}
