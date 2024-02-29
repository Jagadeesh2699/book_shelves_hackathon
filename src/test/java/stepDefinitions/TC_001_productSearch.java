package stepDefinitions;

import pageObjects.HomePage;

import testBase.BaseClass;
import utilities.ExcelUtility;
import pageObjects.BookShelves;
import java.io.IOException;
import java.util.Map;
import org.apache.logging.log4j.Logger;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TC_001_productSearch extends BaseClass {
	BaseClass bc ;
	HomePage hp;
	BookShelves bs;
	ExcelUtility excel;
	Hooks hk;
	Logger logger;
	

	@Given("search for the Book Shelves from Search bar")
	public void search_for_the_book_shelves_from_search_bar() throws IOException {
//		Hooks.initilizeBrowser();
		//SEACH FOR BOOKSHELVES
		logger = BaseClass.logger;
		logger.info("***TC_001_productsSearch has Started in cucumber***");
		hp = new HomePage(BaseClass.driver);
		hp.search_for_Book_Shelves();
		logger.info("searched for Book Shelves..");
		//CLOSE POPUP
		bs = new BookShelves(BaseClass.driver);
		bs.closePopup();
		logger.info("Popup Closed..");
	}
	
	@When("select category as Kids Book Shelves")
	public void select_category_as_kids_book_shelves() throws InterruptedException, IOException {
		bs.select_category(p.getProperty("category"));
		logger.info("Selected Category as Kids Book Shelves..");
	}
	
	@When("select price range below Rs.{int},{int}")
	public void select_price_range_below_rs(Integer int1, Integer int2) throws InterruptedException {
		
		bs.select_price_range();
		logger.info("Selected Price Range..");
		
	}
	
	@When("select exclude out of stock option")
	public void select_exclude_out_of_stock_option() throws InterruptedException {
		
		bs.select_exclude_outofstock();
		logger.info("selected Exclude Out of Stock..");
		
	}
	
	@When("sort bookshelves from high price to low price")
	public void sort_bookshelves_from_high_price_to_low_price() throws InterruptedException {
		bs.select_sortby_as_high_to_low();
		logger.info("Selected Sort By..");
	}
	
	@Then("fetch top {int} products displayed and print in the console")
	public void fetch_top_products_displayed_and_print_in_the_console(Integer int1) throws InterruptedException, IOException {
		//PRINT FIRST 3 PRODUCTS
		excel = new ExcelUtility(p.getProperty("setPath"));
		int j=1;
		Map<String, Integer> map =bs.get_first_three_products();
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
		      System.out.println(entry.getKey() + "----" + entry.getValue());
		      excel.setCellData("BookShelves", j, 3, entry.getKey());
		      excel.setCellData("BookShelves", j, 4, entry.getValue());
		      logger.info("Adding products to Excel Sheet..");
		      j = j+1;
		      
		    }
		logger.info("Adding products to Excel Sheet completed..");
		hp.navigate_to_home_page();
		logger.info("navigated to home page..");
		logger.info("***TC_001_productsSearch has completed***");
		System.out.println("========================================================================");
	}


}
