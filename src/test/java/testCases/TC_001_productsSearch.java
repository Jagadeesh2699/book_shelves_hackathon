package testCases;

import utilities.ExcelUtility;
import testBase.BaseClass;
import pageObjects.HomePage;
import pageObjects.BookShelves;
import java.io.IOException;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_001_productsSearch extends BaseClass{
	BaseClass bc ;
	HomePage hp;
	BookShelves bs;
	ExcelUtility excel;
	
	@Test(priority=1,groups={"sanity","regression","master"})
	public void search_for_bookshelves() throws InterruptedException, IOException{
		
		//SEACH FOR BOOKSHELVES
		logger.info("***TC_001_productsSearch has Started***");
		hp = new HomePage(BaseClass.driver);
		hp.search_for_Book_Shelves();
		logger.info("searched for Book Shelves..");
	}
	
	@Test(priority=2,dependsOnMethods= {"search_for_bookshelves"},groups={"sanity","regression","master"})
	public void close_popup() throws InterruptedException {
		//CLOSE POPUP
		bs = new BookShelves(BaseClass.driver);
		bs.closePopup();
		logger.info("Popup Closed..");
	}	
	
	@Test(priority=3,dependsOnMethods= {"search_for_bookshelves","close_popup"},groups={"regression","master"})
	public void apply_filters() throws InterruptedException {
		//SELECT FILTERS
		bs.select_category(p.getProperty("category"));
		logger.info("Selected Category as Kids Book Shelves..");
		bs.select_price_range();
		logger.info("Selected Price Range..");
		bs.select_sortby_as_high_to_low();
		logger.info("Selected Sort By..");
		bs.select_exclude_outofstock();
		logger.info("selected Exclude Out of Stock..");
	}	
	
	@Test(priority=4,dependsOnMethods= {"apply_filters"},groups={"regression","master"})
	public void get_top_3_products() throws IOException, InterruptedException {
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
		Assert.assertTrue(hp.navigate_to_home_page());
		
		logger.info("navigated to home page..");
		logger.info("***TC_001_productsSearch has completed***");
	}
}
