package stepDefinitions;

import testBase.BaseClass;
import java.io.IOException;
import java.util.Properties;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

		 WebDriver driver;
	     Properties p;
	     Logger logger;
	  	     
	     @Before
	     public void setup() throws IOException{    	
	     	p=BaseClass.getProperties();
	     	driver=BaseClass.setup(p.getProperty("os"),p.getProperty("browser"));
	     	driver.get(p.getProperty("appURL"));
	     	driver.manage().window().maximize();	
	 	}
 
	     @After
	     public void tearDown(Scenario scenario) {  		
	        driver.quit();  
	     }
	

}


