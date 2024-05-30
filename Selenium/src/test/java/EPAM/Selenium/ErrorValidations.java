package EPAM.Selenium;
import java.io.IOException;
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
import org.testng.annotations.Test;

import EPAM.PageObjectModel.CartPage;
import EPAM.PageObjectModel.CheckOutPage;
import EPAM.PageObjectModel.ConfirmationPage;
import EPAM.PageObjectModel.LandingPage;
import EPAM.PageObjectModel.ProductCatalogue;
import EPAM.Selenium.TestComponents.BaseComponents;
import io.github.bonigarcia.wdm.WebDriverManager;



public class ErrorValidations extends BaseComponents{

	@Test
	public void SubmitOrderTest() throws IOException{
	
	String productName = "ZARA COAT 3";
//	LandingPage landingPage = launchApplication();
	landingPage.loginApplication("himabindu.sanipini@gmail.com", "tryselenium2$");
//	
	Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMsg());
	}
}

