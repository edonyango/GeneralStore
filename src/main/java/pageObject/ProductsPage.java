package pageObject;

import java.util.List;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ProductsPage {
	public ProductsPage(AndroidDriver<AndroidElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Products']")
	public AndroidElement prdctsPageTitle;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/productName")
	public List<AndroidElement> productsName;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/productAddCart")
	public List<AndroidElement> productsToCartBtn;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/appbar_btn_cart")
	public AndroidElement cartBtn;
	
	@AndroidFindBy(className="android.widget.LinearLayout")
	public List<AndroidElement> productsLayout;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/counterText")
	public AndroidElement numOfCartPrdcts;
	
	@AndroidFindBy(xpath="//android.widget.Toast[1]")
	public AndroidElement toastMsg;

}
