package org.step;

import java.awt.AWTException;
import java.io.IOException;

import org.junit.Assert;
import org.pojo.classes.AddToCartPagePOJO;
import org.pojo.classes.HomePagePOJO;
import org.pojo.classes.ProductPOJO;
import org.reusable.UtilityClass;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ProductAddAndRemove extends UtilityClass{
	
	HomePagePOJO home;
	ProductPOJO product;
	AddToCartPagePOJO cart;
	
	@Given("User launched the browser and flipkart")
	public void user_launched_the_browser_and_flipkart() {

		launchBrowser("Chrome");
		launchUrl("https://www.flipkart.com/");
		implitWait();
	}

	@When("User searched the product through search box")
	public void user_searched_the_product_through_search_box() throws AWTException, IOException {

		home = new HomePagePOJO();
		String fromExcel = readFromExcel("Sheet1", 0, 0);
		passTextToTextBox(home.getSearchBox(),fromExcel );
		pressEnter(); 
	}

	@When("User adding product into cart")
	public void user_adding_product_into_cart() {
	    
		product = new ProductPOJO();
		driver.navigate().refresh();
		clickWebElement(product.getProduct());
		switchToAnotherWindow(1);
		driver.navigate().refresh();
		clickWebElement(product.getAddToCart());	
	}

	@When("User place order and login to the application")
	public void user_place_order_and_login_to_the_application() throws AWTException, InterruptedException, IOException {
	    
		pageDown();
		cart = new AddToCartPagePOJO();
		Assert.assertTrue("Wrong Product Added Into Card", cart.getProductCheck().getText().contains("iPhone"));
		clickWebElement(cart.getPlaceOrder());
		passTextToTextBox(cart.getPhNo(), readFromExcel("Sheet1", 0, 1));
		clickWebElement(cart.getContinueBtn());
	}

	@When("User has to select address and payment option")
	public void user_has_to_select_address_and_payment_option() {
	   
		passTextUsingJs(cart.getName(), "Pradeep");
		passTextUsingJs(cart.getPhNo(), "9080219612");
		passTextUsingJs(cart.getPinCode(), "600126");
		passTextUsingJs(cart.getLocality(), "Indian");
		passTextUsingJs(cart.getAddress(), "Medavakkam, Near Pallikaranai");
		passTextUsingJs(cart.getCity(), "Chennai");
		selectState(cart.getStateDd(), "Tamil Nadu");
		clickWebElement(cart.getSelectHome());
		clickWebElement(cart.getSaveAddress());
	}

	@When("User need remove the product from the cart")
	public void user_need_remove_the_product_from_the_cart() {
	    
	}

	@Then("User closes the browser")
	public void user_closes_the_browser() throws InterruptedException {
	    
		Thread.sleep(5000);
		closeBrowser();
	}

}
