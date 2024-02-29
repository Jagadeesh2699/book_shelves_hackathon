package testCases;

import testBase.BaseClass;
import utilities.ExcelUtility;
import pageObjects.HomePage;
import java.io.IOException;
import java.util.List;
import org.testng.annotations.Test;


public class TC_002_Collections extends BaseClass{
	HomePage hp ;
	ExcelUtility excel ;
		
	@Test(priority=1,groups={"sanity","regression","master"})
	public void go_to_Living_collections() throws InterruptedException, IOException {
		logger.info("***TC_002_Collections has Started***");
		
		
		//NAVIGATING TO LIVING COLLECTIONS
		hp = new HomePage(BaseClass.driver);
	    hp.go_to_Living_collections();
	    logger.info("Navivating to living Collections..");
	    excel = new ExcelUtility(p.getProperty("setPath"));
	    String heading = hp.get_subMenu_Header();
	    System.out.println(heading);
	    excel.setCellData("Living_subMenu", 1, 1, heading);
	}
	
	    @Test(priority=2,dependsOnMethods= {"go_to_Living_collections"},groups={"regression","master"})
	    public void get_all_sub_menus() throws IOException {
	    
	    //PRINTING ALL SUBMENUS
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
	    }

	    
}
