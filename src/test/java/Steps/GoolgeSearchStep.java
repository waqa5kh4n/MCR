package Steps;

import PageClass.GoogleSearchPage;
import io.cucumber.java.en.Given;

public class GoolgeSearchStep extends GoogleSearchPage{
	

	@Given("I Launch Google")
	public void i_Launch_Google() {
		LaunchGoogle();
	}

	@Given("I Search SomethingFeature: Google Search")
	public void i_Search_SomethingFeature_Google_Search() throws InterruptedException {
		GoogleSearch();
	}
}
