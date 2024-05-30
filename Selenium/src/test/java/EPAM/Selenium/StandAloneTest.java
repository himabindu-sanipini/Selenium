package EPAM.Selenium;
import java.time.Duration;
import java.util.List;
import java.util.function.Predicate;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import EPAM.PageObjectModel.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;



public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
	WebDriverManager.chromedriver().setup();
	WebDriver driver = new ChromeDriver();
	String productName = "ZARA COAT 3";
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.manage().window().maximize();
	driver.get("https://rahulshettyacademy.com/client");
	driver.findElement(By.id("userEmail")).sendKeys("himabindu.sanipini@gmail.com");
//	LandingPage landingPage = new LandingPage(driver);
	driver.findElement(By.id("userPassword")).sendKeys("Tryselenium2$");
	driver.findElement(By.id("login")).click();
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
	List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	WebElement prod = products.stream().filter(product ->
	product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
	prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
	wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
	driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
	List <WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
	Boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
	
	Assert.assertTrue(match);
	driver.findElement(By.cssSelector(".totalRow button")).click();
	Actions a = new Actions(driver);
	a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")),"india").build().perform();
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
	driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
//	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".action__submit")));
	WebElement element = driver.findElement(By.cssSelector(".action__submit"));

	Actions actions = new Actions(driver);

	actions.moveToElement(element).click().perform();
//	Thread.sleep(5000);
//	driver.findElement(By.cssSelector(".action__submit")).click();

	String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
	Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}
}
