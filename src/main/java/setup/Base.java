package setup;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class Base {
	
	public static AndroidDriver<AndroidElement> driver;
	public static AppiumDriverLocalService service;
	
	//start appium server
	public AppiumDriverLocalService startServer() {
		boolean flag = checkIfServerIsRunnning(4723);
		if (!flag) {
			service=AppiumDriverLocalService.buildDefaultService();
			service.start();
		}
		return service;
	}
	
	//check if the appium server is running
	public static boolean checkIfServerIsRunnning(int port) {

		boolean isServerRunning = false;
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);

			serverSocket.close();
		} catch (IOException e) {
			// If control comes here, then it means that the port is in use
			isServerRunning = true;
		} finally {
			serverSocket = null;
		}
		return isServerRunning;
	}
	
	//start emulator 
	public static void startEmulator() throws IOException, InterruptedException {
		Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\startEmulator.bat");
		Thread.sleep(3000);
	}

	public static AndroidDriver<AndroidElement> Capabilities () throws IOException, InterruptedException {
		//Initialize and read the property file
		FileInputStream loadProperties = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\setup\\global.properties");
		Properties projectProp = new Properties();
		projectProp.load(loadProperties);
		//Load application directory
		File appDirectory = new File("src");
		File apk = new File(appDirectory, (String) projectProp.get("appName"));
		//Get device name from properties
		String device = (String) projectProp.getProperty("deviceName");
		
		  if (device.contains("emulator")) { startEmulator(); }
		 
		//Declare desired capabilities
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
		cap.setCapability(MobileCapabilityType.APP, apk.getAbsolutePath());
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, device);
		cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 10);
		cap.setCapability("chromedriverExecutable", "C:\\Program Files\\Webdrivers\\chromedriver74.exe");
		driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
		return driver;
	}

}
