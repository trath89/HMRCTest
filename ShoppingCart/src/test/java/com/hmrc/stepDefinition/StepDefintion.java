package com.hmrc.stepDefinition;

import static org.apache.commons.lang3.StringUtils.substring;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.hmrc.configs.Config;
import com.hmrc.configs.Driver;
import com.hmrc.util.UtilityClass;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDefintion {

	public static WebDriver driver;

	public String quantity;

	public String cost;

	public String userName;

	@Given("^user is on landing page$")
	public void user_is_on_landing_page() throws Throwable {

		driver= Driver.initialize("chrome");
		Config conf= new Config(System.getProperty("user.dir") + "//ConfigProperties//config.properties");
		driver.get(conf.getObject("URL"));
	}

	@When("^user chooses to Sign in$")
	public void user_chooses_to_Sign_in() throws Throwable {

		driver.findElement(By.className("login")).click();

	}

	@When("^user enters an email address to create an account$")
	public void user_enters_an_email_address_to_create_an_account() throws Throwable {
		Random random = new Random();
		userName = "abc" + random.nextInt() + "@gmail.com";
		UtilityClass.addText(driver, By.xpath("//input[@id=\"email_create\"]"), userName);
	}

	@When("^user clicks on Register$")
	public void user_clicks_on_Register() throws Throwable {

		driver.findElement(By.xpath("//span//i[@class=\"icon-user left\"]")).click();
	}

	@Then("^user lands on create an account page$")
	public void user_lands_on_create_an_account_page() throws Throwable {

		String url = driver.getCurrentUrl();
		Assert.assertTrue(url.contains("account-creation"));
		System.out.println("url------"+url);
	}

	@When("^user provides personal information$")
	public void user_provides_personal_information() throws Throwable {

		UtilityClass.wait(driver, By.xpath("//input[@id='id_gender1']"));

		UtilityClass.clickEvent(driver, By.xpath("//input[@id='id_gender2']"));
		UtilityClass.addText(driver, By.xpath("//input[@name=\"customer_firstname\"]"), "Tripti");
		UtilityClass.addText(driver, By.xpath("//input[@name=\"customer_lastname\"]"), "Rath");
		driver.findElement(By.xpath("//input[@name=\"passwd\"]")).sendKeys("password123");
		new Select(driver.findElement(By.xpath("//select[@name=\"days\"]"))).selectByValue("10");
		new Select(driver.findElement(By.xpath("//select[@name=\"months\"]"))).selectByValue("1");
		new Select(driver.findElement(By.xpath("//select[@name=\"years\"]"))).selectByValue("1987");
		UtilityClass.clickEvent(driver, By.xpath("//input[@id=\"newsletter\"]"));
		UtilityClass.clickEvent(driver, By.xpath("//input[@id=\"optin\"]"));
		UtilityClass.addText(driver, By.xpath("//input[@id=\"firstname\"]"), "Tripti");
		UtilityClass.addText(driver, By.xpath("//input[@id=\"lastname\"]"), "Rath");
		UtilityClass.addText(driver, By.xpath("//input[@id=\"company\"]"), "SP Info");
		UtilityClass.addText(driver, By.xpath("//input[@id=\"address1\"]"), "147");
		UtilityClass.addText(driver, By.xpath("//input[@id=\"address2\"]"), "Champion Way");
		UtilityClass.addText(driver, By.xpath("//input[@id=\"city\"]"), "Fleet");
		new Select(driver.findElement(By.xpath("//select[@id=\"id_state\"]"))).selectByValue("1");
		UtilityClass.addText(driver, By.xpath("//input[@id=\"postcode\"]"), "12345");
		new Select(driver.findElement(By.xpath("//select[@id=\"id_country\"]"))).selectByValue("21");
		UtilityClass.addText(driver, By.xpath("//textarea[@id=\"other\"]"), "Landmark");
		UtilityClass.addText(driver, By.xpath("//input[@id=\"phone\"]"), "012345678");
		UtilityClass.addText(driver, By.xpath("//input[@id=\"phone_mobile\"]"), "8383838383");
		UtilityClass.addText(driver, By.xpath("//input[@id=\"alias\"]"), "MyAddress");
	}

	@When("^click on Register$")
	public void click_on_Register() throws Throwable {
		UtilityClass.clickEvent(driver, By.xpath("//button[@id=\"submitAccount\"]"));
	}

	@Then("^the new user account is created$")
	public void the_new_user_account_is_created() throws Throwable {
		Assert.assertTrue(driver.getCurrentUrl().contains("my-account"));
	}

	@When("^Customer clicks on the Menu Dresses$")
	public void Customer_clicks_on_the_Menu_Dresses() throws Throwable {
		UtilityClass.clickEvent(driver, By.xpath("//*[@id=\"block_top_menu\"]/ul/li[2]/a"));
	}

	@Then("^user lands on dresses landing page$")
	public void user_lands_on_dresses_landing_page() throws Throwable {
		Assert.assertTrue(driver.getCurrentUrl().contains("id_category"));
	}

	@When("^customer sorts dresses on price highest to lowest$")
	public void customer_sorts_dresses_on_price_highest_to_lowest() throws Throwable {
		WebElement element = driver.findElement(By.xpath("//*[@id='selectProductSort']"));
		Select select = new Select(element);
		select.selectByVisibleText("Price: Highest first");
	}

	@Then("^The dresses are sorted in the order of highest to lowest$")
	public void the_dresses_are_sorted_in_the_order_of_highest_to_lowest() throws Throwable {
		checkingHightoLowSort();
	}

	public static void checkingHightoLowSort() {

		String newcurentPrice = "";
		String newpreviousPrice = "";

		List<WebElement> price = driver.findElements(By.xpath("//span[@class='price product-price']"));

		for (WebElement aPrice : price) {
			String curentPrice = aPrice.getText();
			if (curentPrice.length() > 0) {
				newcurentPrice = substring(curentPrice, 1, curentPrice.length() - 1);
			}
			if (newcurentPrice.length() > 0 && newpreviousPrice.length() > 0) {
				System.out.println(aPrice.getText());
				break;
			}
			if (newcurentPrice.length() > 0) {
				newpreviousPrice = newcurentPrice;
				newcurentPrice = "";
			}
		}
	}

	@When("^User clicks on Pink colour$")
	public void user_clicks_on_Pink_colour() throws InterruptedException {
		UtilityClass.clickEvent(driver, By.xpath("//*[@id='layered_id_attribute_group_24']"));
	}

	@Given("^User clicks on Add to Cart for the dress displayed$")
	public void clicks_on_Add_to_Cart_for_the_dress_displayed() {

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Actions actions = new Actions(driver);
		WebElement txt_printedDressBlock = driver.findElement(By.xpath("//*[@id='center_column']/ul/li/div/div[2]"));
		actions.moveToElement(txt_printedDressBlock);
		WebElement btn_addToCart = driver.findElement(By.xpath("//*[@id='center_column']/ul/li//div/a[1]/span"));
		actions.moveToElement(btn_addToCart);
		actions.click().build().perform();

	}

	@Then("^Message ‘Product successfully added to your shopping cart’ is seen$")
	public void message_Product_successfully_added_to_your_shopping_cart_is_seen() throws InterruptedException {

		Thread.sleep(5000);
		String txtQuantity = driver
				.findElement(By.cssSelector(
						"#layer_cart > div.clearfix > div.layer_cart_cart.col-xs-12.col-md-6 > h2 > span.ajax_cart_product_txt"))
				.getText();
		System.out.println("Text displayed in Cart is" + txtQuantity);
		Assert.assertEquals(txtQuantity, "There is 1 item in your cart.");
		driver.findElement(By.xpath("//*[@id='layer_cart']/div[1]/div[2]/div[4]/span/span")).click();

	}



	//	@Then("^User checks number of dresses displayed$")
	//	public void user_checks_number_of_dresses_displayed() throws InterruptedException {
	//
	//		List<WebElement> no_of_items = driver.findElements(By.xpath("//*[@id='center_column']/ul/li"));
	//		System.out.println("Number of items in the list are " + no_of_items.size());
	//
	//	}
	//
	//		@Then("^User confirms number of items in the cart$")
	//		public void user_confirms_number_of_items_in_the_cart() {
	//			driver.findElement(By.xpath("//*[@id='header']//div[3]/div/a")).click();
	//			String txt_noOfProducts = driver.findElement(By.xpath("//*[@id='cart_title']/span")).getText();
	//			Assert.assertEquals(txt_noOfProducts, "Your shopping cart contains: 1 Product[]");
	//		}

	//	@Then("^User confirms the total displayed for items added in cart$")
	//	public void user_confirms_the_total_displayed_for_items_added_in_cart() {
	//		String total = driver.findElement(By.xpath("//*[@id='total_product']")).getText();
	//		Assert.assertEquals(total, "$50.99");
	//
	//	}





	@When("^user clicks on Sign Out$")
	public void user_clicks_on_Sign_Out() throws Throwable {

		UtilityClass.clickEvent(driver, By.xpath("//*[@id='header']/div[2]/div/div/nav/div[2]/a"));
	}

	@Then("^user land on landing page$")
	public void user_land_on_landing_page() throws Throwable {
		Assert.assertTrue(driver.getCurrentUrl().contains("id_category"));
	}

	@When("^User enters valid username and password$")
	public void user_enters_valid_username_and_password() throws Throwable {
		UtilityClass.addText(driver, By.xpath("//input[@id=\"email\"]"), userName);
		UtilityClass.addText(driver, By.xpath("//input[@id=\"passwd\"]"), "password123");
	}

	@When("^clicks on Sign In button$")
	public void clicks_on_Sign_In_button() throws Throwable {
		UtilityClass.clickEvent(driver, By.xpath("//*[@id='SubmitLogin']"));
	}

	@Then("^Home page is displayed$")
	public void home_page_is_displayed() throws Throwable {
		WebElement webelement = driver.findElement(By.xpath("//*[@id='columns']/div[1]/span[2]"));
		Assert.assertTrue(webelement.isDisplayed());
	}

	@Then("^User confirms the total displayed for items added in cart$")
	public void user_confirms_the_total_displayed_for_items_added_in_cart() throws Throwable {
		driver.findElement(By.xpath("//*[@id='header']//div[3]/div/a")).click();
		String txt_noOfProducts = driver.findElement(By.xpath("//*[@id='center_column']/p")).getText();
		Assert.assertEquals(txt_noOfProducts, "Your shopping cart is empty.");
	}

}
