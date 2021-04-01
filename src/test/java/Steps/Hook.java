package Steps;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Hook {
	public static Duration ExplicitWaitTimeOutUnit = Duration.ofSeconds(10);
	public static WebDriver driver;

	@Before
	public void startSetUp() {

		//System.setProperty("webdriver.chrome.silentOutput", "true");
		File file = new File(System.getProperty("user.dir")+"/Drivers/chromedriver"); 
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath()); 
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		//options.setBinary(System.getProperty("user.dir")+"/Drivers/chromedriver");
		
		
		
		options.addArguments("enable-automation");
		options.addArguments("--headless");
		options.addArguments("--window-size=1920,1080");
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-extensions");
		options.addArguments("--dns-prefetch-disable");
		options.addArguments("--disable-gpu");
		options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
		options.addArguments("enable-features=NetworkServiceInProcess");
		options.addArguments("disable-features=NetworkService");
		driver = new ChromeDriver(options);
		driver.manage().window().setSize(new Dimension(1200, 800));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		File folder = new File(System.getProperty("user.dir") + "/FailedScreenshots/");
		for (File f : folder.listFiles()) {
			if (f.getName().endsWith(".png")) {
				f.delete();
			}
		 }
		/*
		String osName = System.getProperty("os.name");
		System.out.println("OS >>> " + osName);
		if (osName.equalsIgnoreCase("Mac OS X")) {
			System.out.println("drive : "+System.getProperty("user.dir"));
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/Drivers/chromedriver");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			driver = new ChromeDriver(options);

		} else if (osName.equals("Windows 10")) {
			System.out.println("drive : "+System.getProperty("user.dir"));
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/Drivers/chromedriver.exe");
			driver = new ChromeDriver();

		} else if (osName.equals("Linux")) {
			System.out.println("drive : "+System.getProperty("user.dir"));
//			DesiredCapabilities caps = new DesiredCapabilities();
//			caps.setJavascriptEnabled(true); // not really needed: JS enabled by default
//			caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
//					System.getProperty("user.dir") + "/Drivers/phantomjs");
//			driver = new PhantomJSDriver(caps);
			
			
			
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/Drivers/chromedriver");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
		
			options.addArguments("headless");
			driver = new ChromeDriver(options);
			
			} else {
			System.out.println("Driver is not configured for this Operating System.");
		}
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.manage().deleteAllCookies();
		*/
//		File folder = new File(System.getProperty("user.dir") + "/FailedScreenshots/");
//		for (File f : folder.listFiles()) {
//			if (f.getName().endsWith(".png")) {
//				f.delete();
//			}
//		}

	}

	@After
	public void tearDown(Scenario step) {
		
		if (step.isFailed() == true) {
			String screenshotName = step.getName().replaceAll(" ", "_");
			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

			try {
				FileUtils.copyFile(screenshot,
						new File(System.getProperty("user.dir") + "/FailedScreenshots/" + screenshotName + ".png"));

			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}

	}

	

	public static WebDriver getDriver() {
		return driver;
	}

}
