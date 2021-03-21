package PageClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Steps.Hook;

public class AccountPage  {
	
	WebDriverWait wait;
	public static WebDriver driver;
	
	public AccountPage() {
		AccountPage.driver = Hook.getDriver();
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Hook.ExplicitWaitTimeOutUnit);
	}
	
	@FindBy(id="username")
	WebElement username;
	
	@FindBy(id="pasword")
	WebElement password;
	
	@FindBy(name="Login")
	WebElement Login;
	
	public void enterUsername(String uname) {
		wait.until(ExpectedConditions.visibilityOf(username));
		username.sendKeys(uname);
	}
	
	public void enterPassword(String pwd) {
		wait.until(ExpectedConditions.visibilityOf(password));
		password.sendKeys(pwd);
	}
	
	public void clickLogin() {
		wait.until(ExpectedConditions.visibilityOf(Login));
		Login.click();
	}

}
