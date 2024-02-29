package stepDefinitions;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.asserts.SoftAssert;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.GiftCard;
import pageObjects.HomePage;
import testBase.BaseClass;
import utilities.ExcelUtility;

public class TC_003_giftCardCreation extends BaseClass{
	
	HomePage hp;
	GiftCard gf;
	ExcelUtility excel;
	Properties p;
	Logger logger;
	Map<String, String> dataMap;
	
	@Given("navigate to giftCards page")
	public void navigate_to_gift_cards_page() throws InterruptedException, IOException {
		logger=LogManager.getLogger();
		logger.info("***TC_003_giftCardTest has Started***");
		p = BaseClass.p;
		
    	hp = new HomePage(BaseClass.driver);
    	gf=new GiftCard(BaseClass.driver);
		
    	hp.clickOnGiftCard();
    	logger.info("Gift Card page opened..");
	}
	@When("choose occasion as -birthday\\/anniversary-")
	public void choose_occasion_as_birthday_anniversary() throws InterruptedException {
		gf.clickOnBirthday();
		logger.info("Clicked On birthday..");
	}
	
	@When("fill -customize your gift card- with valid price & date and click on -Next- button")
	public void fill_customize_your_gift_card_with_valid_price_date_and_click_on_next_button() throws Exception {
		try {
		gf.amountSelectMonth(p.getProperty("amount"));
		logger.info("Amount selected..");
		gf.nextButton();
		logger.info("customising gift card completed..");
		}catch(Exception e){
			System.out.println(e.toString());
		}
	}
	
	@When("fill -To & From- details under -who is the lucky person- with invalid email Id")
	public void fill_to_from_details_under_who_is_the_lucky_person_with_invalid_email_id(DataTable dataTable) {
		dataMap = dataTable.asMap(String.class,String.class);
	    
	    gf.recipientName.sendKeys(dataMap.get("recipientsName"));
		gf.recipientEmail.sendKeys(dataMap.get("recipientsEmail"));
		gf.recipientNum.sendKeys(dataMap.get("recipientsNumber"));
		gf.yourName.sendKeys(dataMap.get("yourName"));
		gf.youremail.sendKeys(dataMap.get("yourEmail"));
		logger.info("Invalid email entered..");
		gf.yournumber.sendKeys(dataMap.get("yourNumber"));
		gf.youraddress.sendKeys(dataMap.get("address"));
		gf.yourpin.sendKeys(dataMap.get("pin"));
		gf.text.sendKeys(dataMap.get("message"));
		
	}
	
	@When("capture and display the error message in console output")
	public void capture_and_display_the_error_message_in_console_output() throws InterruptedException, IOException {
		System.out.println(gf.errorMessage());
		excel = new ExcelUtility(p.getProperty("setPath"));
		logger.info("Writing into excel started..");
		excel.setCellData("error_message", 2, 2, gf.errorMessage());
		logger.info("writing into excel completed..");
		
	}
	@Then("fill valid email Id and confirm")
	public void fill_valid_email_id_and_confirm(DataTable dataTable) throws InterruptedException {
		dataMap = dataTable.asMap(String.class,String.class);
		gf.validDetail(dataMap.get("validEmail"));
		logger.info("Invalid email cleared and  valid email id is entered...");
		gf.clickOnConfirm();
	}
	@Then("validate all the given details in -confirm Details- section")
	public void validate_all_the_given_details_in_confirm_details_section() throws IOException {
		logger.info("validate all the given details in the Confirm Details section Started........");
		SoftAssert sa = new SoftAssert();	
		excel = new ExcelUtility(p.getProperty("getPath"));
		sa.assertEquals(excel.getCellData("Sheet1", 1, 0),gf.confirmFromname.getText(),"Recipient's names are not equal");
		sa.assertEquals(excel.getCellData("Sheet1", 1, 9),gf.confirmFromemail.getText(),"Recipient's emails are not equal");
		sa.assertEquals(excel.getCellData("Sheet1", 1, 2),gf.confirmFromnum.getText(),"Recipient's phone numbers are not equal");
		sa.assertEquals(excel.getCellData("Sheet1", 1, 3),gf.confirmToname.getText(),"Recipient's names are not equal");
		sa.assertEquals(excel.getCellData("Sheet1", 1, 4),gf.confirmToemail.getText(),"To mails are not equal");
		sa.assertEquals(excel.getCellData("Sheet1", 1, 5),gf.confirmTonumber.getText(),"To number not equal");
		sa.assertEquals(excel.getCellData("Sheet1", 1, 7),gf.getConfimDetailsPin(),"To pin are not equal");
		sa.assertEquals(excel.getCellData("Sheet1", 1, 10),gf.getConfimDetailsAmount(),"In confirm details amount is not equal");
		sa.assertEquals(excel.getCellData("Sheet1", 1, 8),gf.confirmmsg.getText(),"gif message is not equal");
		sa.assertAll();
		
		logger.info("validate all the given details in the Confirm Details section completed........");
		logger.info("***TC_003_giftCardTest has completed***");
//		BaseClass.tearDown();
		System.out.println("============================================================================");
	}
	

}
