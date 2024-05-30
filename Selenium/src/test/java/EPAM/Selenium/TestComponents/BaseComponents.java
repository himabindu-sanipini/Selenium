package EPAM.Selenium.TestComponents;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import EPAM.PageObjectModel.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseComponents 
{
	public WebDriver driver;
	public LandingPage landingPage;
	public WebDriver InitializeDriver() throws IOException 
	{
	
		Properties prop = new Properties();
	
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\EPAM\\Resources\\GlobalData.properties");
		prop.load(fis);
		String browserName = prop.getProperty("browser");
		
		if(browserName.equalsIgnoreCase("chrome"))
		{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		
		}
		else if (browserName.equalsIgnoreCase("firefox"))
		{
			//firefoxcode
		}
		else if (browserName.equalsIgnoreCase("edge"))
		{
			System.setProperty("webdriver.edge.driver","edge.exe");
			driver = new EdgeDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}

@BeforeMethod
public LandingPage launchApplication() throws IOException
{
	 driver = InitializeDriver();
	 landingPage = new LandingPage(driver);
		landingPage.GoTO();
		return landingPage;
}

@AfterMethod
public void tearDown() {
	driver.close();
}
}
