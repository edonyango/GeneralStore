package pageObject;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class HomePage {
	public HomePage(AndroidDriver<AndroidElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='General Store']")
	public AndroidElement homePageTitle;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/spinnerCountry")
	public AndroidElement countryDropDown;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Bahamas']")
	public AndroidElement country;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/nameField")
	public AndroidElement nameField;
	
	@AndroidFindBy(xpath="//android.widget.RadioButton[@text='Male']")
	public AndroidElement maleRadio;
	
	@AndroidFindBy(xpath="//android.widget.RadioButton[@text='Female']")
	public AndroidElement femaleRadio;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/btnLetsShop")
	public AndroidElement letsShopBtn;
	
	//Implementing Toast
	@AndroidFindBy(xpath="//android.widget.Toast[1]")
	public AndroidElement toastMsg;

}
