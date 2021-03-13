package formSuite;

import java.io.IOException;
import org.testng.annotations.BeforeMethod;
import setup.Base;

public class TestMethods extends Base{
	
	@BeforeMethod
	public void killAllNodes() throws IOException, InterruptedException {
		//taskkill /F /IM node.exe
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Thread.sleep(3000);
	}
		
}
