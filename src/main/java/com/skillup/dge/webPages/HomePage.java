package com.skillup.dge.webPages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;

public class HomePage 
{
	WebDriver driver;
	String BaseWindow = "";
	String RegisterButtonlocator = "";
	String SignInButtonLocator = "";
	String googleButtonLocator ="";
	String dropDownLocator = "";
	String signOutLocator = "";
	String usernameFromDashboardLocator = "";
	String homePageLink = "";
	String exploreButtonLocator = "";
	RegisterPage registerPage;
	SignInPage signInPage;
	
	public HomePage(WebDriver driver)
	{
		this.driver = driver;
		this.registerPage = new RegisterPage(driver);
		this.signInPage = new SignInPage(driver);
	}
	
	public String clickWebElement(String locator)
	{
		 String status = "";
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		    try
		    {
		    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		    	driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		        List<WebElement> elements = driver.findElements(By.xpath(locator));
		        if (!elements.isEmpty())
		        {
		            WebElement element = elements.get(0);
		            js.executeScript("arguments[0].scrollIntoView(true);", element);
		            js.executeScript("arguments[0].click();", element);
		            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		           
		        } 
		        else
		        {
		            System.out.println("Element not found: " + locator);
		            status = "Fail";
		        }
		    }
		    catch (Exception e)
		    {
		        e.printStackTrace();
		        status = "Fail";
		    }
		return status;
	}
	public String openLinkInNewTab(String locator)
	{
		 String status = "";
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		    try {
		        List<WebElement> elements = driver.findElements(By.xpath(locator));
		        if (!elements.isEmpty())
		        {
		            WebElement element = elements.get(0);
		            js.executeScript("arguments[0].scrollIntoView(true);", element);
		            String getURL = element.getAttribute("href");
		            driver.switchTo().newWindow(WindowType.TAB); // Open a new tab
		            driver.get(getURL); // Navigate to the URL
		        } else {
		            System.out.println("Element not found: " + locator);
		            status = "Fail";
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		        status = "Fail";
		    }
		return status;
	}
	public ArrayList<String> checkHomePageURL(String url)
	{
		ArrayList<String> status = new ArrayList<String>();
		BaseWindow = driver.getWindowHandle();
		try
		{
			driver.switchTo().newWindow(WindowType.TAB); // Open a new tab)
			driver.get(url); // Navigate to the URL
			System.out.println("home page url lanuched");
		}
		catch (Exception e)
        {
            e.printStackTrace();
            status.add("Fail");
        }
		return status;
	}
	
	public ArrayList<String> CheckSkillupLogoFromHomePageTop()
	{
		ArrayList<String> status = new ArrayList<String>();
		String locator = "//img[@alt='SkillUp Home Page']";
		try
		{
			status.add(this.clickWebElement(locator));
			 driver.close();
	         driver.switchTo().window(BaseWindow);
	         System.out.println("Skillup Logo verified");
		}
		catch (Exception e)
        {
            e.printStackTrace();
            status.add("Fail");
        }
		return status;
	}
	
	public ArrayList<String> CheckDiscoverTab()
	{
		ArrayList<String> status = new ArrayList<String>();
		String locator = "//div[@class='mobile-nav-item hidden-mobile nav-item nav-tab']//a[normalize-space()='Discover courses']";
		try
		{
			status.add(this.clickWebElement(locator));
			if (!status.contains("Fail"))
			{
				driver.navigate().back();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
				 System.out.println("Discover verified");
			} 
		}
		catch (Exception e)
        {
            e.printStackTrace();
            status.add("Fail");
        }
		return status;
	}
	
	public ArrayList<String> CheckRegisterButton()
	{
		ArrayList<String> status = new ArrayList<String>();
		RegisterButtonlocator = "//div[@class='mobile-nav-item hidden-mobile nav-item']//a[@class='register-btn btn'][normalize-space()='Register for free']";
		try
		{
			status.add(this.clickWebElement(RegisterButtonlocator));
			if (!status.contains("Fail"))
			{
				driver.navigate().back();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
				System.out.println("Register verified");
			} 
		}
		catch (Exception e)
        {
            e.printStackTrace();
            status.add("Fail");
        }
		return status;
	}
	
	public ArrayList<String> CheckSignInButton()
	{
		ArrayList<String> status = new ArrayList<String>();
		SignInButtonLocator = "//div[@class='mobile-nav-item hidden-mobile nav-item']//a[@class='sign-in-btn btn'][normalize-space()='Sign in']";
		try
		{
			status.add(this.clickWebElement(SignInButtonLocator));
			if (!status.contains("Fail"))
			{
				driver.navigate().back();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
				System.out.println("SignIn verified");
			} 
		}
		catch (Exception e)
        {
            e.printStackTrace();
            status.add("Fail");
        }
		return status;
	}
	
	public ArrayList<String> CheckSeacrhFieldTextBox()
	{
		ArrayList<String> status = new ArrayList<String>();
		String searchTextlocator = "//input[@placeholder='Search for a course']";
		String searchButtonlocator = "//button[@type='submit'][@class='search-button']";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try
		{
			if(driver.findElements(By.xpath(searchTextlocator)).size()>0)
			{
				WebElement searchText = driver.findElement(By.xpath(searchTextlocator));
				js.executeScript("arguments[0].scrollIntoView(true);", searchText);
				if (searchText.isDisplayed()) 
				{
					searchText.sendKeys("test course");
					WebElement searchButton = driver.findElement(By.xpath(searchButtonlocator));
					js.executeScript("arguments[0].scrollIntoView(true);", searchButton);
					js.executeScript("arguments[0].click();", searchButton);
					if(driver.findElements(By.xpath("//div[@id='discovery-message']")).size()>0)
					{
						WebElement discoveryMessage = driver.findElement(By.xpath("//div[@id='discovery-message']"));
						js.executeScript("arguments[0].scrollIntoView(true);", discoveryMessage);
						String getText = discoveryMessage.getText();
						System.out.println("Discovery course searched Message: " + getText);
						if(driver.findElements(By.xpath("//span[@class='fa fa-times']")).size()>0)
						{
							WebElement closeButton = driver.findElement(By.xpath("//span[@class='fa fa-times']"));
							js.executeScript("arguments[0].scrollIntoView(true);", closeButton);
							if (closeButton.isDisplayed()) 
							{
								js.executeScript("arguments[0].click();", closeButton);
								driver.navigate().back();
								driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
							}
						}
					}
				}
				Thread.sleep(2000);
			} 
			else
			{
				status.add("Fail");
			}
			System.out.println("search filed Text verified");
		}
		catch (Exception e)
        {
            e.printStackTrace();
            status.add("Fail");
        }
		return status;
	}
	
	public ArrayList<String> CheckLogoFromHomepageFooter()
	{
		ArrayList<String> status = new ArrayList<String>();
		String locator = "//a[normalize-space()='Skillup Online']";
		try
		{
			WebElement footerLogo = driver.findElement(By.xpath(locator));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView(true);", footerLogo);
			if (footerLogo.isDisplayed())
			{
				String getURL = footerLogo.getAttribute("href");
				driver.switchTo().newWindow(WindowType.TAB); // Open a new tab
				driver.get(getURL); // Navigate to the URL
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
				driver.close();
				driver.switchTo().window(BaseWindow);
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
			} 
			else
			{
				status.add("Fail");
			}
			System.out.println("logo on footer verified");
		}
		catch (Exception e)
        {
            e.printStackTrace();
            status.add("Fail");
        }
		return status;
	}
	
	public ArrayList<String> CheckAboutUsLinkFromHome()
	{
		ArrayList<String> status = new ArrayList<String>();
		String locator = "//a[normalize-space()='About Us']";
		try
		{
			status.add(this.clickWebElement(locator));
			if (!status.contains("Fail"))
			{
				driver.navigate().back();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
				System.out.println("About link verified");
			} 
		}
		catch (Exception e)
        {
            e.printStackTrace();
            status.add("Fail");
        }
		return status;
	}
	
	public ArrayList<String> CheckBlogLinkFromHome()
	{
		ArrayList<String> status = new ArrayList<String>();
		String locator = "//a[normalize-space()='Blog']";
		try
		{
			status.add(this.clickWebElement(locator));
			if (!status.contains("Fail"))
			{
				driver.navigate().back();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
				System.out.println("blog link verified");
			} 
		}
		catch (Exception e)
        {
            e.printStackTrace();
            status.add("Fail");
        }
		return status;
	}
	
	public ArrayList<String> CheckDonateLinkFromHome()
	{
		ArrayList<String> status = new ArrayList<String>();
		String locator = "//a[normalize-space()='Donate']";
		try
		{
			status.add(this.clickWebElement(locator));
			if (!status.contains("Fail"))
			{
				driver.navigate().back();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
				System.out.println("Donate link verified");
			} 
		}
		catch (Exception e)
        {
            e.printStackTrace();
            status.add("Fail");
        }
		return status;
	}
	
	public ArrayList<String> CheckTOSLinkFromHome()
	{
		ArrayList<String> status = new ArrayList<String>();
		String locator = "//a[normalize-space()='Terms of Service']";
		try
		{
			status.add(this.clickWebElement(locator));
			if (!status.contains("Fail"))
			{
				driver.navigate().back();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
				System.out.println("TOS Link verified");
			} 
		}
		catch (Exception e)
        {
            e.printStackTrace();
            status.add("Fail");
        }
		return status;
	}
	
	public ArrayList<String> CheckPrivacyPolicyLinkFromHome()
	{
		ArrayList<String> status = new ArrayList<String>();
		String locator = "//a[normalize-space()='Privacy Policy']";
		try
		{
			status.add(this.clickWebElement(locator));
			if (!status.contains("Fail"))
			{
				driver.navigate().back();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
				System.out.println("Privacy Policy verified");
			} 
		}
		catch (Exception e)
        {
            e.printStackTrace();
            status.add("Fail");
        }
		return status;
	}
	
	public ArrayList<String> CheckHelpLinkFromHome()
	{
		ArrayList<String> status = new ArrayList<String>();
		String locator = "//a[normalize-space()='Help']";
		try
		{
			status.add(this.clickWebElement(locator));
			if (!status.contains("Fail"))
			{
				driver.navigate().back();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
				System.out.println("Help Link verified");
			} 
		}
		catch (Exception e)
        {
            e.printStackTrace();
            status.add("Fail");
        }
		return status;
	}
	
	public ArrayList<String> CheckContactUsLinkFromHome()
	{
		ArrayList<String> status = new ArrayList<String>();
		String locator = "//a[normalize-space()='Contact Us']";
		try
		{
			status.add(this.clickWebElement(locator));
			if (!status.contains("Fail"))
			{
				driver.navigate().back();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
				System.out.println("Contact Link verified");
			} 
		}
		catch (Exception e)
        {
            e.printStackTrace();
            status.add("Fail");
        }
		return status;
	}
	
	public ArrayList<String> checkRegisterTab() 
	{
		ArrayList<String> status = new ArrayList<String>();
		try 
		{
			status.add(this.clickWebElement(RegisterButtonlocator));
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	
	public ArrayList<String> checkGoogleButtonFromRegisterPage() {
		ArrayList<String> status = new ArrayList<String>();
		googleButtonLocator = "//button[@id='oa2-google-oauth2']";
		try {
			status.add(this.clickWebElement(googleButtonLocator));
			status.add(registerPage.FocusWindow("signin"));
			driver.get("https://stagecourses.skillup.online/");
			BaseWindow = driver.getWindowHandle();
		} catch (Exception e) {
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkSignTab() {
		ArrayList<String> status = new ArrayList<String>();
		try {
			status.add(this.clickWebElement(SignInButtonLocator));
			status.add(registerPage.FocusWindow("login"));
		} catch (Exception e) {
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkForgotPasswordLink() {
		ArrayList<String> status = new ArrayList<String>();
		String forgotPasswordLocator = "//a[@id='forgot-password']";
		try 
		{
			status.add(this.clickWebElement(forgotPasswordLocator));
			driver.navigate().back();
		} catch (Exception e) {
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkGoogleButtonFromSignInPage() {
		ArrayList<String> status = new ArrayList<String>();
		dropDownLocator = "//button[@aria-label='Account menu for']";
		signOutLocator = "//a[normalize-space()='Sign out']";
		homePageLink = "//a[normalize-space()='click here to go to the home page']";
		try
		{
			status.add(this.clickWebElement(googleButtonLocator));
			status.add(registerPage.FocusWindow("signin"));
			driver.get("https://stagecourses.skillup.online/");
			BaseWindow = driver.getWindowHandle();
		} 
		catch (Exception e) {
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkSignInProcess(ArrayList<String> data) {
		ArrayList<String> status = new ArrayList<String>();
		try 
		{
			status.addAll(signInPage.signInProcess(data));
			Thread.sleep(200);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(70));
			System.out.println("sign in done");
		} catch (Exception e) {
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkDashboardURL()
	{
		ArrayList<String> status = new ArrayList<String>();
		try 
		{
			Thread.sleep(200);
			status.add(registerPage.FocusWindow("learner-dashboard"));
			Thread.sleep(200);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(70));
			System.out.println("Dashboard URL verified");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkUsernameFromDashboard()
	{
		ArrayList<String> status = new ArrayList<String>();
		usernameFromDashboardLocator = "//button[contains(@aria-label,'Account menu for')]";
		try 
		{
			if(driver.findElements(By.xpath(usernameFromDashboardLocator)).size()>0)
			{
				System.out.println("Username is displayed on dashboard");
			}
			else
			{
				status.add("Fail");
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> CheckFindCourseLink() {
		ArrayList<String> status = new ArrayList<String>();
		String FindCourseLocator = "//a[contains(text(),'Find a course ')]";
		String logoFromExploreCourses = "//img[@alt='SkillUp Home Page']";
		try {
			Thread.sleep(200);
			System.out.println("Find course link validation started");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(70));
			status.add(this.clickWebElement(FindCourseLocator));
			Thread.sleep(1000);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(70));
			status.add(registerPage.FocusWindow("https://stagecourses.skillup.online/courses"));
			status.add(this.clickWebElement(logoFromExploreCourses));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(70));
		} catch (Exception e) {
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> CheckColorModeFromDashboard() {
		ArrayList<String> status = new ArrayList<String>();
		String colorSlideFromDashboardLocator = "//span[@class='slider round']";
		try {
			status.add(this.clickWebElement(colorSlideFromDashboardLocator));
			WebElement body = driver.findElement(By.tagName("body"));
			String backgroundColor = body.getCssValue("background-color");
			System.out.println("Background color is: " + backgroundColor);
			driver.navigate().back();
		} catch (Exception e) {
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	
	public ArrayList<String> CheckValidCourseToSearch(String data) {
		ArrayList<String> status = new ArrayList<String>();
		String searchTextField = "//input[@id='discovery-input']";
		String searchButtonlocator = "//button[@title='Search']";
		String courseTextlocator = "//span[@class='query']";
		String closeAlert = "//span[@class='fa fa-times']";
		try {
			//status.add(this.clickWebElement(exploreButtonLocator));
			Thread.sleep(200);
			status.add(this.clickWebElement(searchTextField));
			WebElement searchText = driver.findElement(By.xpath(searchTextField));
			searchText.sendKeys(data);
			status.add(this.clickWebElement(searchButtonlocator));
			if(driver.findElements(By.xpath(courseTextlocator)).size()>0)
			{
				System.out.println("Course searched text is displayed");
			}
			else
			{
				status.add("Fail");
			}
			if(driver.findElements(By.xpath(closeAlert)).size()>0)
			{
				driver.findElement(By.xpath(closeAlert)).click();
			}
			else
			{
				status.add("Fail");
			}
			driver.navigate().refresh();
		} catch (Exception e) {
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	
	public ArrayList<String> CheckInValidCourseToSearch(String data) {
		ArrayList<String> status = new ArrayList<String>();
		String searchTextField = "//input[@id='discovery-input']";
		String searchButtonlocator = "//button[@title='Search']";
		
		String invalidTextAlert = "//div[@class='search-status-label']";
		try {
			
			status.add(this.clickWebElement(searchTextField));
			WebElement searchText = driver.findElement(By.xpath(searchTextField));
			searchText.sendKeys(data);
			status.add(this.clickWebElement(searchButtonlocator));
			if(driver.findElements(By.xpath(invalidTextAlert)).size()>0)
			{
				System.out.println("Invalid text alert is displayed");
			}
			else
			{
				status.add("Fail");
			}
		} catch (Exception e) {
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	
	public ArrayList<String> CheckEmptyFieldToSearch() {
		ArrayList<String> status = new ArrayList<String>();
		String searchTextField = "//input[@id='discovery-input']";
		String searchButtonlocator = "//button[@title='Search']";
		
		try {
			driver.navigate().refresh();
			status.add(this.clickWebElement(searchTextField));
			WebElement searchText = driver.findElement(By.xpath(searchTextField));
			searchText.sendKeys(" ");
			status.add(this.clickWebElement(searchButtonlocator));
			
		} catch (Exception e) {
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	
	public ArrayList<String> CheckSkillupLink() {
		ArrayList<String> status = new ArrayList<String>();
		String skillupLinkOnFooter = "//a[normalize-space()='Skillup Online']";
		try {
			status.add(this.clickWebElement(skillupLinkOnFooter));
			status.add(registerPage.FocusWindow("https://in.skillup.online/"));
			driver.close();
			driver.switchTo().window(BaseWindow);
		} catch (Exception e) {
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	
	public ArrayList<String> CheckAboutUsLink() {
		ArrayList<String> status = new ArrayList<String>();
		try {
			status.addAll(this.CheckAboutUsLinkFromHome());
		} catch (Exception e) {
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	
	public ArrayList<String> CheckBlogLink() {
		ArrayList<String> status = new ArrayList<String>();
		try {
			status.addAll(this.CheckBlogLinkFromHome());
		} catch (Exception e) {
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	
	public ArrayList<String> CheckDonateLink() {
		ArrayList<String> status = new ArrayList<String>();
		try {
			status.addAll(this.CheckDonateLinkFromHome());
		} catch (Exception e) {
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	
	public ArrayList<String> CheckTOSLink() {
		ArrayList<String> status = new ArrayList<String>();
		try {
			status.addAll(this.CheckTOSLinkFromHome());
		} catch (Exception e) {
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	
	public ArrayList<String> CheckPrivacyPolicyLink() {
		ArrayList<String> status = new ArrayList<String>();
		try {
			status.addAll(this.CheckPrivacyPolicyLinkFromHome());
		} catch (Exception e) {
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	
	public ArrayList<String> CheckHelpLink() {
		ArrayList<String> status = new ArrayList<String>();
		try {
			status.addAll(this.CheckHelpLinkFromHome());
		} catch (Exception e) {
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	
	public ArrayList<String> CheckContactUsLink() {
		ArrayList<String> status = new ArrayList<String>();
		try {
			status.addAll(this.CheckContactUsLinkFromHome());
		} catch (Exception e) {
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	
	
}
