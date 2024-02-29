package pageObjects;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.ExcelUtility;

public class GiftCard extends BasePage {
	
	WebDriverWait wait;
	
	public GiftCard(WebDriver driver) 
	{
		super(driver);
	}
	
	@FindBy(xpath="//h2[@class='_FCNL']")
	WebElement occasion;
	
	@FindBy(xpath="//*[@id=\"app-container\"]/div/main/section/section[1]/ul/li[3]")
	WebElement birthday;
	
	@FindBy(xpath="//*[@id=\"app-container\"]/div/main/section/section[1]/ul/li[3]/div/div/button")
	WebElement choose;
	
	@FindBy(id="ip_2251506436")
	WebElement gifCardAmount;
	
	@FindBy(xpath="//div[@class='_3PNvG']//select[1]")
	WebElement monthdrpdwn;
	
	@FindBy(xpath="//div[@class='_3PNvG']//select[2]")
	WebElement datedrpdwn;
	
	@FindBy(xpath="//button[@class='_1IFIb _1fVSi action-button _1gIUf _1XfDi']")
	WebElement nextbutton;
	
	@FindBy(xpath="//input[@id='ip_4036288348']")
	public WebElement recipientName;
	
	@FindBy(xpath="//input[@id='ip_137656023']")
	public WebElement recipientEmail;
	
	@FindBy(xpath="//input[@id='ip_3177473671']")
	public WebElement recipientNum;
	
	@FindBy(xpath="//input[@id='ip_1082986083']")
	public WebElement yourName;
	
	@FindBy(xpath="//input[@id='ip_4081352456']")
	public WebElement youremail;
	
	@FindBy(xpath="//input[@id='ip_2121573464']")
	public WebElement yournumber;
	
	@FindBy(xpath="//input[@id='ip_2194351474']")
	public WebElement youraddress;
	
	@FindBy(xpath="//input[@id='ip_567727260']")
	public WebElement yourpin;
	
	@FindBy(xpath="//textarea[@id='ip_582840596']")
	public WebElement text;
	
	@FindBy(xpath="//button[@class='_3Hxyv _1fVSi action-button _1gIUf _1XfDi']")
	WebElement submit;

	@FindBy(xpath="//button[@class='_3Hxyv _1fVSi action-button _1gIUf _1XfDi']")
	WebElement confirm;
	
	@FindBy(xpath="//div[@class=\"_2wEGI\"]//section[3]//div[2]//div//div[2]//div[1]")
	public WebElement confirmFromname;
	
	@FindBy(xpath="//div[@class=\"_2wEGI\"]//section[3]//div[2]//div//div[2]//div[2]")
	public WebElement confirmFromemail;
	
	@FindBy(xpath="//div[@class=\"_2wEGI\"]//section[3]//div[2]//div//div[2]//div[3]")
	public WebElement confirmFromnum;
	
	@FindBy(xpath="//div[@class=\"_2wEGI\"]//section[1]/div[2]")
	public WebElement confirmToamount;
	
	@FindBy(xpath="//div[@class='_2wEGI']//section[3]/div[2]/div[2]/div[2]/div[1]")
	public WebElement confirmToname;
	
	@FindBy(xpath="//div[@class='_2wEGI']//section[3]/div[2]/div[2]/div[2]/div[2]")
	public WebElement confirmToemail;
	
	@FindBy(xpath="//div[@class='_2wEGI']//section[3]/div[2]/div[2]/div[2]/div[3]")
	public WebElement confirmTonumber;
	
	@FindBy(xpath="//*[@id=\"app-container\"]/div/main/section/section[4]/div[2]/section[3]/div[2]/div[2]/div[2]/div[5]")
	public WebElement confirmTopin;
	
	@FindBy(xpath="//div[@class='_2wEGI']//section[3]/div[2]/div[2]/div[2]/div[4]")
	public WebElement confirmToadr;
	
	@FindBy(xpath="//*[@id=\"app-container\"]/div/main/section/section[4]/div[2]/section[3]/div[3]/div[2]")
	public WebElement confirmmsg;
	
	//ACTIONS
	public void clickOnBirthday() throws InterruptedException {
		wait = new WebDriverWait(driver, Duration.ofSeconds(8000));
		wait.until(ExpectedConditions.visibilityOfAllElements(occasion));
		
		JavascriptExecutor js1 = (JavascriptExecutor)driver;
		js1.executeScript("arguments[0].scrollIntoView();",occasion);
		
		wait.until(ExpectedConditions.visibilityOfAllElements(birthday));
		Actions actions=new Actions(driver);
		actions.moveToElement(birthday).perform();
		
		js1.executeScript("arguments[0].click();",choose);
	}
	
	public void amountSelectMonth(String Tamount) throws InterruptedException{	
		gifCardAmount.sendKeys(Tamount);
		Select month=new Select(monthdrpdwn);
		month.selectByIndex(2);
		wait.until(ExpectedConditions.visibilityOfAllElements(datedrpdwn));
		Select date=new Select(datedrpdwn);
		date.selectByValue("22");
	}

	public void nextButton() throws InterruptedException {
		
		wait.until(ExpectedConditions.visibilityOfAllElements(nextbutton));
		nextbutton.click();
	}

	public String errorMessage() throws InterruptedException{
		
		confirm.click();
		String error=recipientEmail.getAttribute("validationMessage");
		return error;
	}

	public void validDetail(String validemail) throws InterruptedException {
		
		recipientEmail.clear();
		recipientEmail.sendKeys(validemail);
	}
	public String getConfimDetailsAmount() {
		String amount=confirmToamount.getText();
		String amt=amount.replaceAll("[,₹ ]","");
		return amt;	
	}
	public String getConfimDetailsPin() {
		String[] pin=confirmTopin.getText().split(",");
		String pins=pin[0];
		return pins;	
	}

	public void clickOnConfirm() {
		confirm.click();
	}
}
