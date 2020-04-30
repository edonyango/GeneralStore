package pageObject;

import java.util.List;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CartPage {
	public CartPage(AndroidDriver<AndroidElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Cart']")
	public AndroidElement cartPageTitle;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/productName")
	public List<AndroidElement> cartPrdctsName;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/productPrice")
	public List<AndroidElement> cartPrdctsPrice;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/totalAmountLbl")
	public AndroidElement totalAmount;
	
	@AndroidFindBy(className="android.widget.CheckBox")
	public AndroidElement emailCheckBox;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/btnProceed")
	public AndroidElement visitWebsiteBtn;
	

}
