package testCases;

import utilities.ExcelUtility;
import java.io.IOException;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObjects.HomePage;
import pageObjects.GiftCard;
import testBase.BaseClass;
import utilities.dataProviders;

public class TC_003_giftCardTest extends BaseClass {
	HomePage hp;
	GiftCard gf;
	ExcelUtility excel;
	
	
	//NAVIGATING TO GIFTGARDS
    @Test(priority=1,groups={"sanity","master"})
	public void click_on_gift_cards() throws Exception {
    	logger.info("***TC_003_giftCardTest has Started***");
    	hp = new HomePage(BaseClass.driver);
    	gf=new GiftCard(BaseClass.driver);
		
    	hp.clickOnGiftCard();
    	logger.info("Gift Card page opened..");
		gf.clickOnBirthday();
		logger.info("Clicked On birthday..");
	}
    
    //CUSTOMISING GIFT CARD
	@Test(priority=2,groups={"regression","master"},dependsOnMethods= {"click_on_gift_cards"},dataProvider="dp",dataProviderClass=dataProviders.class)
	public void  customise_your_gift_card(String getRecipientName,String getRecipientemail,String getRecipientnumber,String getYourName,String  getYouremail,String  getYourNumber,String  getYouraddress,String  getYourpin,String  getYourmessage,String getRecipientvalidemail,String getGiftCardAmount) throws InterruptedException {
		
		gf.amountSelectMonth(getGiftCardAmount);
		logger.info("Amount selected..");
		gf.nextButton();
		logger.info("customising gift card completed..");
	}
	
	//TESTING WITH INVALID DETAILS
	@Test(priority=3,groups={"regression","master"},dependsOnMethods= {"customise_your_gift_card"},dataProvider="dp",dataProviderClass=dataProviders.class)
	public void checking_with_invalidEmail(String getRecipientName,String getRecipientemail,String getRecipientnumber,String getYourName,String  getYouremail,String  getYourNumber,String  getYouraddress,String  getYourpin,String  getYourmessage,String getRecipientvalidemail,String getGiftCardAmount) throws InterruptedException, IOException {
		
		gf.recipientName.sendKeys(getRecipientName);
		gf.recipientEmail.sendKeys(getRecipientemail);
		gf.recipientNum.sendKeys(getRecipientnumber);
		gf.yourName.sendKeys(getYourName);
		gf.youremail.sendKeys(getYouremail);
		logger.info("Invalid email entered..");
		gf.yournumber.sendKeys(getYourNumber);
		gf.youraddress.sendKeys(getYouraddress);
		gf.yourpin.sendKeys(getYourpin);
		gf.text.sendKeys(getYourmessage);
		System.out.println(gf.errorMessage());
		excel = new ExcelUtility(p.getProperty("setPath"));
		logger.info("Writing into excel started..");
		excel.setCellData("error_message", 2, 2, gf.errorMessage());
		logger.info("writing into excel completed..");
	}
	
	//TESTING WITH VALID DETAILS
	@Test(priority=4,groups={"regression","master"},dependsOnMethods= {"checking_with_invalidEmail"},dataProvider="dp",dataProviderClass=dataProviders.class)
	public void checking_with_validEmail(String getRecipientName,String getRecipientemail,String getRecipientnumber,String getYourName,String  getYouremail,String  getYourNumber,String  getYouraddress,String  getYourpin,String  getYourmessage,String getRecipientvalidemail,String getGiftCardAmount) throws InterruptedException{
		
		gf.validDetail(getRecipientvalidemail);
		logger.info("Invalid email cleared and  valid email id is entered...");
		gf.clickOnConfirm();
	}
	
	//VALIDATING THE FINAL DETAILS	
	@Test(priority=5,groups={"regression","master"},dependsOnMethods= {"checking_with_validEmail"},dataProvider="dp",dataProviderClass=dataProviders.class)
	public void validDetails(String getRecipientName,String getRecipientemail,String getRecipientnumber,String getYourName,String  getYouremail,String  getYourNumber,String  getYouraddress,String  getYourpin,String  getYourmessage,String getRecipientvalidemail,String getGiftCardAmount) throws IOException, InterruptedException {
		logger.info("validate all the given details in the Confirm Details section Started........");
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(getRecipientName,gf.confirmFromname.getText(),"Recipient's names are not equal");
		sa.assertEquals(getRecipientvalidemail,gf.confirmFromemail.getText(),"Recipient's emails are not equal");
		sa.assertEquals(getRecipientnumber,gf.confirmFromnum.getText(),"Recipient's phone numbers are not equal");
		sa.assertEquals(getYourName,gf.confirmToname.getText(),"Recipient's names are not equal");
		sa.assertEquals(getYouremail,gf.confirmToemail.getText(),"To mails are not equal");
		sa.assertEquals(getYourNumber,gf.confirmTonumber.getText(),"To number not equal");
		sa.assertEquals(getYourpin,gf.getConfimDetailsPin(),"To pin are not equal");
		sa.assertEquals(getGiftCardAmount,gf.getConfimDetailsAmount(),"In confirm details amount is not equal");
		sa.assertEquals(getYourmessage,gf.confirmmsg.getText(),"gif message is not equal");
		sa.assertAll();
		logger.info("validate all the given details in the Confirm Details section completed........");
		logger.info("***TC_003_giftCardTest has completed***");
	}
	
}
