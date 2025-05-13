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
	
	public HomePage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public String clickWebElement(String locator)
	{
		 String status = "";
		    try {
		        List<WebElement> elements = driver.findElements(By.xpath(locator));
		        if (!elements.isEmpty())
		        {
		            WebElement element = elements.get(0);
		            JavascriptExecutor js = (JavascriptExecutor) driver;
		            js.executeScript("arguments[0].scrollIntoView(true);", element);
		            js.executeScript("arguments[0].click();", element);
		           
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
		String locator = "//div[@class='mobile-nav-item hidden-mobile nav-item']//a[@class='register-btn btn'][normalize-space()='Register for free']";
		try
		{
			status.add(this.clickWebElement(locator));
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
		String locator = "//div[@class='mobile-nav-item hidden-mobile nav-item']//a[@class='sign-in-btn btn'][normalize-space()='Sign in']";
		try
		{
			status.add(this.clickWebElement(locator));
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
	
}
