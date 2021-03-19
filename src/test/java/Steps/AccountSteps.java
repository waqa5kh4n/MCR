package Steps;

import PageClass.AccountPage;
import io.cucumber.java.en.Given;
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

}
