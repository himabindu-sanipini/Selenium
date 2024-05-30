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
import EPAM.PageObjectModel.OrderPage;
import EPAM.PageObjectModel.ProductCatalogue;
import EPAM.Selenium.TestComponents.BaseComponents;
import io.github.bonigarcia.wdm.WebDriverManager;



public class SubmitOrderTest extends BaseComponents{
	String productName = "ZARA COAT 3";
	@Test
	public void SubmitOrderTest() throws IOException{
	
	
//	LandingPage landingPage = launchApplication();
	ProductCatalogue productCatalogue = landingPage.loginApplication("himabindu.sanipini@gmail.com", "Tryselenium2$");
	
	
	List<WebElement> products = productCatalogue.GetProductList();
	productCatalogue.addProductToCart(productName);
	CartPage cartPage = productCatalogue.goToCartPage();
	
	Boolean match = cartPage.VerifyProductDisplay(productName);
	Assert.assertTrue(match);
	CheckOutPage checkOutPage = cartPage.GoToCheckOut();
	checkOutPage.selectCountry("india");
	ConfirmationPage confirmationPage = checkOutPage.SubmitOrder();


	String confirmMessage = confirmationPage.GetConfirmationMessage();
	Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	
	}
	
	@Test(dependsOnMethods = {"SubmitOrderTest"})
	
	public void OrderHistoryTest()
	{
		ProductCatalogue productCatalogue = landingPage.loginApplication("himabindu.sanipini@gmail.com", "Tryselenium2$");
		OrderPage orderPage = productCatalogue.GoToOrderPage();
		Assert.assertTrue(orderPage.VerifyOrderDisplay(productName));
	}
}
