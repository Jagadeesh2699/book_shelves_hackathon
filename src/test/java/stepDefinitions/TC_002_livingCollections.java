package stepDefinitions;

import testBase.BaseClass;
import java.io.IOException;
import java.util.List;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.HomePage;
import utilities.ExcelUtility;

public class TC_002_livingCollections extends BaseClass{
	HomePage hp ;
	ExcelUtility excel ;

	@Given("user should be on homepage")
	public void user_should_be_on_homepage() {
		logger.info("***TC_002_Collections has Started***"); 
		hp = new HomePage(BaseClass.driver);
	}
	
	@When("navigate to Living collections")
	public void navigate_to_living_collections() throws IOException {
		hp.go_to_Living_collections();
		logger.info("Navivating to living Collections..");
	    excel = new ExcelUtility(p.getProperty("setPath"));
	    String heading = hp.get_subMenu_Header();
	    System.out.println(heading);
	    excel.setCellData("Living_subMenu", 1, 1, heading);
			    
	}
	
	@Then("fetch and submenus under Seating & Chairs")
	public void fetch_and_submenus_under_seating_chairs() throws IOException {
		List<String> ll = hp.get_all_subMenus_of_Living_collections();
	    int j=3;
	    logger.info("Adding SubMenu List to Excel Started..");
	    for(String s : ll) {
	    	System.out.println(s);
	    	excel.setCellData("Living_subMenu", j, 1, s);
	    	j=j+1;
	    }
	    logger.info("Adding SubMenu to Excel Completed..");
	    logger.info("***TC_002_Collections has completed***");
	    System.out.println("=================================================");
	    
	}

}
