package pageObjects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class HomePage extends BasePage{
	

	public HomePage(WebDriver driver) {
		
		super(driver);
	}
	
	//ELEMENTS	
	@FindBy(xpath="//figure[@class='header__topBar_logo']//*[name()='svg']") 
	WebElement logoIcon;
	
	@FindBy(xpath="//section[@class='header__topBar_sectionLeft']/a/figure") 
	WebElement logo;
	
	@FindBy(xpath="//*[@id='wrapper']") 
	WebElement wrapper;
	
	@FindBy(xpath="//input[@id='search']") 
	WebElement search;
	
	@FindBy(xpath="//span[@class=\"search-icon icofont-search\"]") 
	WebElement searchIcon;

	@FindBy(xpath="//a[@class='featuredLinksBar__link'][normalize-space()='Gift Cards']")
	WebElement giftCards;
	
	@FindBy(xpath="//section[@class='header__topBar_sectionLeft']")
	WebElement logIn;
	
	@FindBy(xpath="//span[normalize-space()='Living']") 
	WebElement living;
	
	@FindBy(xpath="//li[@class='topnav_item livingunit']//div//div//a[1]") 
	WebElement submenu_head;
	
	@FindBy(xpath="//li[@class='topnav_item livingunit']//li[1]//ul//li") 
	List<WebElement> living_subMenu;
	
	//ACTION METHODS
	public void search_for_Book_Shelves(){
		search.sendKeys("Bookshelves");
		searchIcon.click();
	}
	

	public void go_to_Living_collections(){
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", living);	
		living.click();
	}
	public  String get_subMenu_Header(){
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5000));
		wait.until(ExpectedConditions.visibilityOfAllElements(submenu_head));
		return submenu_head.getText();
	}
	
	public  List<String> get_all_subMenus_of_Living_collections(){
		List<String> ll =new ArrayList<String>();
		for(WebElement w : living_subMenu) {
			
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5000));
		wait.until(ExpectedConditions.visibilityOfAllElements(w));
		ll.add(w.getText());
			
		}
		return ll;
	}
	
	public void clickOnGiftCard() throws InterruptedException {		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8000));
		wait.until(ExpectedConditions.visibilityOfAllElements(giftCards));
		Thread.sleep(2000);   
		giftCards.click();
		Thread.sleep(2000);
	       
	}
	
	public boolean navigate_to_home_page() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5000));
		wait.until(ExpectedConditions.visibilityOfAllElements(logo));
		JavascriptExecutor js = (JavascriptExecutor) driver;		
		js.executeScript("window.scrollTo(0, -document.body.scrollHeight)");
		logoIcon.click();
		wait.until(ExpectedConditions.visibilityOfAllElements(wrapper));
		return wrapper.isDisplayed();
	}
}
