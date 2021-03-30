package Steps;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import PageClass.AccountPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AccountSteps extends AccountPage {

	@Given("I open chrome browser")
	public void i_open_chrome_browser() {
		driver.get("https://login.salesforce.com/");
	}

	@Given("I enter valid username {string}")
	public void i_enter_valid_username(String unamee) {
		enterUsername(unamee);
	}

	@Given("I enter valid password {string}")
	public void i_enter_valid_password(String pwd) {
		enterPassword(pwd);
	}

	@When("I click the Login button")
	public void i_click_the_Login_button() {
		clickLogin();
	}

	@Given("I open {string} browser")
	public void i_open_browser(String string) {

	}

	@Given("I go to the {string} environment")
	public void i_go_to_the_environment(String environment) {
		if (environment.equalsIgnoreCase("qa")) {
			driver.get("https://login.salesforce.com/");
		}
	}

	@When("I click the {string} button")
	public void i_click_the_button(String name) {
		driver.findElement(By.name(name)).click();
	}

	@Then("I should see the {string} tab")
	public void i_should_see_the_tab(String tab) {
		driver.findElement(By.linkText(tab)).click();
	}

	@Then("I should see the {string} home page")
	public void i_should_see_the_home_page(String expectedVal) {
		String homeText = driver.findElement(By.className("pageType")).getText();
		System.out.println(homeText);
		Assert.assertEquals(expectedVal, homeText);
	}

	@When("I should see the following values for {string} field")
	public void i_should_see_the_following_values_for_field(String fieldName, DataTable values) {
		List<List<String>> rows = values.asLists(String.class);

		WebElement val = driver.findElement(By.name(fieldName));
		Select sel = new Select(val);
		List<WebElement> opt = sel.getOptions();

		for (int i = 0; i < rows.size(); i++) {
			List<String> value = rows.get(i);
			for (String va1l : value) {
				Assert.assertEquals(va1l, opt.get(i).getText());
			}

		}
	}

	@Then("I create {string} with the following values")
	public void i_create_with_the_following_values(String object,DataTable data) {
		
		
		List<Map<String, String>> values = data.asMaps();
		for (Map<String, String> singleRecord : values) {
			driver.findElement(By.linkText(object)).click();
			driver.findElement(By.xpath("//input[@name='new']")).click();
			

			driver.findElement(By.id("acc2")).sendKeys(singleRecord.get("Account Name"));
			driver.findElement(By.id("acc5")).sendKeys(singleRecord.get("Account Number"));

			WebElement activeField = driver.findElement(By.id("00N2E00000D7LOy"));
			Select activeDropDown = new Select(activeField);
			activeDropDown.selectByVisibleText(singleRecord.get("Active"));

			WebElement accountSourceField = driver.findElement(By.id("AccountSource"));
			Select accountSourceDropDown = new Select(accountSourceField);
			accountSourceDropDown.selectByVisibleText(singleRecord.get("Account Source"));

			driver.findElement(By.id("acc8")).sendKeys(singleRecord.get("Annual Revenue"));

			WebElement annualRevenueField = driver.findElement(By.id("acc9"));
			Select annualRevenueDropDown = new Select(annualRevenueField);
			annualRevenueDropDown.selectByVisibleText(singleRecord.get("Rating"));

			WebElement typeField = driver.findElement(By.id("acc6"));
			Select typeDropDown = new Select(typeField);
			typeDropDown.selectByVisibleText(singleRecord.get("Type"));
			
			driver.findElement(By.name("save")).click();

		}
	}

	@Then("I see the record got created")
	public void i_see_the_record_got_created() {
		System.out.println("Record created");

	}

	@Then("I quit")
	public void i_quit() {
driver.quit();
	}

}