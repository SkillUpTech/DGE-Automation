package com.skillup.dge.webPages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegisterPage 
{
	WebDriver driver;
	String BaseWindow = "";
	public RegisterPage(WebDriver driver) 
	{
		this.driver = driver;
	}
	public String FocusWindow(String partialUrl)
	{
	    Set<String> windowHandles = driver.getWindowHandles();
	    for (String window : windowHandles) {
	        driver.switchTo().window(window);
	        if (driver.getCurrentUrl().contains(partialUrl)) {
	            System.out.println("Target window with URL containing '" + partialUrl + "' is displayed successfully");
	            return "";
	        }
	    }
	    return "Fail";
	}
	public ArrayList<String> checkErrorMessage()
	{
		ArrayList<String> status = new ArrayList<String>();
		try
		{
			String validationMsg = "//p[contains(text(),'Please check your responses and try again.')]";
			String fullnameErrorMsg = "//div[contains(text(),'Enter your full name')]";
			String emailErrorMsg = "//div[contains(text(),'Enter your email')]";
			String usernameErrorMsg = "//div[contains(text(),'Enter your email')]";
			String passwordErrorMsg = "//div[@id='password-9']//div[1]";
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			if(driver.findElements(By.xpath(validationMsg)).size()>0)
			{
				WebElement errorMsg = driver.findElement(By.xpath(validationMsg));
				if (errorMsg.isDisplayed())
				{
					System.out.println("Error message is displayed");
					if(driver.findElements(By.xpath(fullnameErrorMsg)).size()>0)
					{
						status.add("fullname");
					}
					if (driver.findElements(By.xpath(emailErrorMsg)).size() > 0) {
						status.add("email");
					}
					if (driver.findElements(By.xpath(usernameErrorMsg)).size() > 0) {
						status.add("username");
					}
					if (driver.findElements(By.xpath(passwordErrorMsg)).size() > 0) {
						status.add("password");
					}
				}
			} 
		}
		catch (Exception e) {
			System.out.println("Exception in checkErrorMessage: " + e.getMessage());
			e.printStackTrace();
			status.add("Error");
		}
		
		return  status;
	}
	public ArrayList<String> registerProcess(ArrayList<String> data)
	{
		ArrayList<String> status = new ArrayList<String>();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String registerButtonLocator = "//div[@class='mobile-nav-item hidden-mobile nav-item']//a[@class='register-btn btn'][normalize-space()='Register for free']";
		String fullnameLocator = "//input[@id='name']";
		String emailLocator = "//input[@id='email']";
		String username = "//input[@id='username']";
		String password = "//input[@id='password']";
		String createButtonLocator = "//span[contains(text(),'Create an account for free')]";
		try
		{
			BaseWindow = driver.getWindowHandle();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			if(driver.getCurrentUrl().contains("stagecourses.skillup.online"))
			{
				WebElement registerButton = driver.findElement(By.xpath(registerButtonLocator));
				js.executeScript("arguments[0].scrollIntoView(true);", registerButton);
				if (registerButton.isDisplayed()) 
				{
					js.executeScript("arguments[0].click();", registerButton);
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
					status.add(this.FocusWindow("register"));
					System.out.println("Register page is displayed successfully");
					
					WebElement fullname = driver.findElement(By.xpath(fullnameLocator));
					fullname.clear();
					if(data.get(4).contains("empty"))
					{
						fullname.sendKeys("");
					}
					else
					{
						fullname.sendKeys(data.get(4));
					}
					WebElement email = driver.findElement(By.xpath(emailLocator));
					email.clear();
					if(data.get(5).contains("empty"))
					{
						email.sendKeys("");
					}
					else
					{
						email.sendKeys(data.get(5));
					}
					WebElement uname = driver.findElement(By.xpath(username));
					uname.clear();
					if(data.get(6).contains("empty"))
					{
						uname.sendKeys("");
					}
					else
					{
						uname.sendKeys(data.get(6));
					}
					WebElement pwd = driver.findElement(By.xpath(password));
					pwd.clear();
					if(data.get(7).contains("empty"))
					{
						pwd.sendKeys("");
					}
					else
					{
						pwd.sendKeys(data.get(7));
					}
					WebElement createButton = driver.findElement(By.xpath(createButtonLocator));
					js.executeScript("arguments[0].scrollIntoView(true);", createButton);
					if (createButton.isDisplayed())
					{
						js.executeScript("arguments[0].click();", createButton);
						status.addAll(this.checkErrorMessage());
						if (status.size()>0) 
						{
							System.out.println("Error message is displayed");
						} 
						else
						{
							System.out.println("Error message is not displayed");
							status.add(this.FocusWindow("learner-dashboard"));
							System.out.println("Successfully registered and logged in to the dashboard page");
						}
					} 
					else 
					{
						System.out.println("Create button is not displayed");
					}
				} 
			}
		}
		catch (Exception e) 
		{
            System.out.println("Exception in registerProcess: " + e.getMessage());
            e.printStackTrace();
            status.add("Error");
        }
		return  status;
	}
	
	public ArrayList<String> logOut()
	{
		ArrayList<String> status = new ArrayList<String>();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String clicDropDown = "//div[@class='nav-container position-relative d-flex align-items-center']//descendant::button";
		String logOutLocator = "//a[contains(text(),'Sign Out')]";
		try
		{
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			if(driver.getCurrentUrl().contains("learner-dashboard"))
			{
				WebElement dropDown = driver.findElement(By.xpath(clicDropDown));
				js.executeScript("arguments[0].scrollIntoView(true);", dropDown);
				if(dropDown.isDisplayed())
				{
					js.executeScript("arguments[0].click();", dropDown);
					WebElement logOut = driver.findElement(By.xpath(logOutLocator));
					js.executeScript("arguments[0].scrollIntoView(true);", logOut);
					if(logOut.isDisplayed())
					{
						System.out.println("Log out button is displayed");
						js.executeScript("arguments[0].click();", logOut);
						status.add(this.FocusWindow("logout"));
						System.out.println("Successfully logged out");
					}
					else
					{
						System.out.println("Log out button is not displayed");
					}
				}
			}
		}
		catch (Exception e) {
			System.out.println("Exception in logOut: " + e.getMessage());
			e.printStackTrace();
			status.add("Error");
		}
		return status;
	}
	
	public ArrayList<String> NavigateToHomePage()
	{
		ArrayList<String> status = new ArrayList<String>();
		String clickHomeLinkAfterLogout = "//a[contains(text(),'click here to go to the home page')]";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try
		{
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			if(driver.findElements(By.xpath(clickHomeLinkAfterLogout)).size()>0)
			{
				WebElement homeLink = driver.findElement(By.xpath(clickHomeLinkAfterLogout));
				js.executeScript("arguments[0].scrollIntoView(true);", homeLink);
				if(homeLink.isDisplayed())
				{
					js.executeScript("arguments[0].click();", homeLink);
					status.add(this.FocusWindow("stagecourses.skillup.online"));
					System.out.println("Home page is displayed successfully");
					
				}
			}
			else 
			{
				System.out.println("Home page displayed");
			}
		}
		catch (Exception e) {
			System.out.println("Exception in RegisterForm: " + e.getMessage());
			e.printStackTrace();
			status.add("Error");
		}
		return status;
	}
	public ArrayList<String> checkTC_P_01(ArrayList<String> data)
	{
		ArrayList<String> result = new ArrayList<String>();
		List<String> status = new ArrayList<String>();
		try
		{
			result.addAll(this.registerProcess(data));
			if (result.contains("errorMessage")) 
			{
				System.out.println("Error message is displayed - TC_P_01");
				status.add("Fail");
			}
			else
			{
				result.addAll(this.logOut());
				result.addAll(this.NavigateToHomePage());
			}
		}
		catch (Exception e) {
			System.out.println("Exception in checkTC_P_01: " + e.getMessage());
			e.printStackTrace();
			status.add("Error");
		}
		return (ArrayList<String>) status;
		
	}
	public ArrayList<String> checkTC_P_02(ArrayList<String> data)
	{
		ArrayList<String> result = new ArrayList<String>();
		List<String> status = new ArrayList<String>();
		try
		{
			result.addAll(this.registerProcess(data));
			if (result.contains("errorMessage")) 
			{
				status.add("Fail");
				System.out.println("Error message is displayed - TC_P_02");
			}
			else
			{
				result.addAll(this.logOut());
				result.addAll(this.NavigateToHomePage());
			}
		}
		catch (Exception e) {
			System.out.println("Exception in checkTC_P_02: " + e.getMessage());
			e.printStackTrace();
			status.add("Error");
		}
		return (ArrayList<String>) status;
		
	}
	public ArrayList<String> checkTC_P_03(ArrayList<String> data)
	{
		ArrayList<String> result = new ArrayList<String>();
		ArrayList<String> status = new ArrayList<String>();
		try
		{
			result.addAll(this.registerProcess(data));
			if (result.contains("errorMessage")) 
			{
				System.out.println("Error message is displayed - TC_P_03");
				status.add("Error");
			}
			else
			{
				result.addAll(this.logOut());
				result.addAll(this.NavigateToHomePage());
			}
		}
		catch (Exception e) {
			System.out.println("Exception in checkTC_P_03: " + e.getMessage());
			e.printStackTrace();
			status.add("Error");
		}
		return  status;
		
	}
	public ArrayList<String> checkTC_P_04(ArrayList<String> data)
	{
		ArrayList<String> result = new ArrayList<String>();
		ArrayList<String> status = new ArrayList<String>();
		try
		{
			result.addAll(this.registerProcess(data));
			if (result.contains("errorMessage")) 
			{
				System.out.println("Error message is displayed - TC_P_04");
				status.add("Error");
			}
			else
			{
				result.addAll(this.logOut());
				result.addAll(this.NavigateToHomePage());
			}
		}
		catch (Exception e) {
			System.out.println("Exception in checkTC_P_04: " + e.getMessage());
			e.printStackTrace();
			status.add("Error");
		}
		return  status;
		
	}
	public ArrayList<String> checkTC_P_05(ArrayList<String> data)
	{
		ArrayList<String> result = new ArrayList<String>();
		ArrayList<String> status = new ArrayList<String>();
		try
		{
			result.addAll(this.registerProcess(data));
			if (result.contains("errorMessage")) 
			{
				System.out.println("Error message is displayed - TC_P_05");
				status.add("Error");
			}
			else
			{
				result.addAll(this.logOut());
				result.addAll(this.NavigateToHomePage());
			}
		}
		catch (Exception e) {
			System.out.println("Exception in checkTC_P_05: " + e.getMessage());
			e.printStackTrace();
			status.add("Error");
		}
		return  status;
		
	}
	public ArrayList<String> checkTC_P_06(ArrayList<String> data)
	{
		ArrayList<String> result = new ArrayList<String>();
		ArrayList<String> status = new ArrayList<String>();
		try
		{
			result.addAll(this.registerProcess(data));
			if (result.contains("errorMessage")) 
			{
				System.out.println("Error message is displayed - TC_P_06");
				status.add("Error");
			}
			else
			{
				result.addAll(this.logOut());
				result.addAll(this.NavigateToHomePage());
			}
		}
		catch (Exception e) {
			System.out.println("Exception in checkTC_P_06: " + e.getMessage());
			e.printStackTrace();
			status.add("Error");
		}
		return status;
		
	}
	public ArrayList<String> checkTC_N_01(ArrayList<String> data)
	{
		ArrayList<String> result = new ArrayList<String>();
		ArrayList<String> status = new ArrayList<String>();
		try
		{
			result.addAll(this.registerProcess(data));
			if (result.size()>0) 
			{
				System.out.println("negative testcase accepted - TC_N_01");
				result.addAll(this.logOut());
				result.addAll(this.NavigateToHomePage());
			}
			else 
			{
				driver.navigate().back();
				System.out.println("Issue in sign up page");
			}
		}
		catch (Exception e) {
			System.out.println("Exception in TC_N_01: " + e.getMessage());
			status.add("Error");
			e.printStackTrace();
		}
		
		return  status;
		
	}
	public ArrayList<String> checkTC_N_02(ArrayList<String> data)
	{
		ArrayList<String> result = new ArrayList<String>();
		ArrayList<String> status = new ArrayList<String>();
		try
		{
			result.addAll(this.registerProcess(data));
			if (result.size()>0) 
			{
				System.out.println("negative testcase accepted - TC_N_02");
				result.addAll(this.logOut());
				result.addAll(this.NavigateToHomePage());
			}
			else 
			{
				driver.navigate().back();
				System.out.println("Issue in sign up page");
			}
		}
		catch (Exception e) {
			System.out.println("Exception in TC_N_02: " + e.getMessage());
			e.printStackTrace();
			status.add("Error");
		}
		return status;
		
	}
	public ArrayList<String> checkTC_N_03(ArrayList<String> data)
	{
		ArrayList<String> result = new ArrayList<String>();
		ArrayList<String> status = new ArrayList<String>();
		try
		{
			result.addAll(this.registerProcess(data));
			if (result.size()>0) 
			{
				System.out.println("negative testcase accepted - TC_N_03");
				result.addAll(this.logOut());
				result.addAll(this.NavigateToHomePage());
			}
			else 
			{
				driver.navigate().back();
				System.out.println("Issue in sign up page");
			}
		}
		catch (Exception e) {
			System.out.println("Exception in TC_N_03: " + e.getMessage());
			e.printStackTrace();
			status.add("Error");
		}
		return status;
		
	}
	public ArrayList<String> checkTC_N_04(ArrayList<String> data)
	{
		ArrayList<String> result = new ArrayList<String>();
		ArrayList<String> status = new ArrayList<String>();
		try
		{
			result.addAll(this.registerProcess(data));
			if (result.size()>0) 
			{
				System.out.println("negative testcase accepted - TC_N_04");
				result.addAll(this.logOut());
				result.addAll(this.NavigateToHomePage());
			}
			else 
			{
				driver.navigate().back();
				System.out.println("Issue in sign up page");
			}
		}
		catch (Exception e) {
			System.out.println("Exception in TC_N_04: " + e.getMessage());
			e.printStackTrace();
			status.add("Error");
		}
		return  status;
		
	}
	public ArrayList<String> checkTC_N_05(ArrayList<String> data)
	{
		ArrayList<String> result = new ArrayList<String>();
		ArrayList<String> status = new ArrayList<String>();
		try
		{
			result.addAll(this.registerProcess(data));
			if (result.size()>0) 
			{
				System.out.println("negative testcase accepted - TC_N_05");
				result.addAll(this.logOut());
				result.addAll(this.NavigateToHomePage());
			}
			else 
			{
				driver.navigate().back();
				System.out.println("Issue in sign up page");
			}
		}
		catch (Exception e) {
			System.out.println("Exception in TC_N_05: " + e.getMessage());
			status.add("Error");
			e.printStackTrace();
		}
		return  status;
		
	}
	public ArrayList<String> checkTC_N_06(ArrayList<String> data)
	{
		ArrayList<String> result = new ArrayList<String>();
		ArrayList<String> status = new ArrayList<String>();
		try
		{
			result.addAll(this.registerProcess(data));
			if (result.size()>0) 
			{
				System.out.println("negative testcase accepted - TC_N_06");
				result.addAll(this.logOut());
				result.addAll(this.NavigateToHomePage());
			}
			else 
			{
				driver.navigate().back();
				System.out.println("Issue in sign up page");
			}
		}
		catch (Exception e) {
			System.out.println("Exception in TC_N_06: " + e.getMessage());
			status.add("Error");
			e.printStackTrace();
		}
		return  status;
		
	}
	public ArrayList<String> checkTC_N_07(ArrayList<String> data)
	{
		ArrayList<String> result = new ArrayList<String>();
		ArrayList<String> status = new ArrayList<String>();
		try
		{
			result.addAll(this.registerProcess(data));
			if (result.size()>0) 
			{
				System.out.println("negative testcase accepted - TC_N_07");
				result.addAll(this.logOut());
				result.addAll(this.NavigateToHomePage());
			}
			else 
			{
				driver.navigate().back();
				System.out.println("Issue in sign up page");
			}
		}
		catch (Exception e) {
			status.add("Error");
			System.out.println("Exception in TC_N_07: " + e.getMessage());
			e.printStackTrace();
		}
		return  status;
		
	}
	public ArrayList<String> checkTC_N_08(ArrayList<String> data)
	{
		ArrayList<String> result = new ArrayList<String>();
		ArrayList<String> status = new ArrayList<String>();
		try
		{
			result.addAll(this.registerProcess(data));
			if (result.size()>0) 
			{
				System.out.println("negative testcase accepted - TC_N_08");
				result.addAll(this.logOut());
				result.addAll(this.NavigateToHomePage());
			}
			else 
			{
				driver.navigate().back();
				System.out.println("Issue in sign up page");
			}
		}
		catch (Exception e) {
			status.add("Error");
			System.out.println("Exception in TC_N_08: " + e.getMessage());
			e.printStackTrace();
		}
		return  status;
		
	}
	public ArrayList<String> checkTC_N_09(ArrayList<String> data)
	{
		ArrayList<String> result = new ArrayList<String>();
		ArrayList<String> status = new ArrayList<String>();
		try
		{
			result.addAll(this.registerProcess(data));
			if (result.size()>0) 
			{
				System.out.println("negative testcase accepted - TC_N_09");
				result.addAll(this.logOut());
				result.addAll(this.NavigateToHomePage());
			}
			else 
			{
				driver.navigate().back();
				System.out.println("Issue in sign up page");
			}
		}
		catch (Exception e) {
			status.add("Error");
			System.out.println("Exception in TC_N_09: " + e.getMessage());
			e.printStackTrace();
		}
		return  status;
		
	}
	public ArrayList<String> checkTC_N_10(ArrayList<String> data)
	{
		ArrayList<String> result = new ArrayList<String>();
		ArrayList<String> status = new ArrayList<String>();
		try
		{
			result.addAll(this.registerProcess(data));
			if (result.size()>0) 
			{
				System.out.println("negative testcase accepted - TC_N_10");
				result.addAll(this.logOut());
				result.addAll(this.NavigateToHomePage());
			}
			else 
			{
				driver.navigate().back();
				System.out.println("Issue in sign up page");
			}
		}
		catch (Exception e) {
			status.add("Error");
			System.out.println("Exception in TC_N_10: " + e.getMessage());
			e.printStackTrace();
		}
		return  status;
		
	}
	public ArrayList<String> checkTC_N_11(ArrayList<String> data)
	{
		ArrayList<String> result = new ArrayList<String>();
		ArrayList<String> status = new ArrayList<String>();
		try
		{
			result.addAll(this.registerProcess(data));
			if (result.size()>0) 
			{
				System.out.println("negative testcase accepted - TC_N_11");
				result.addAll(this.logOut());
				result.addAll(this.NavigateToHomePage());
			}
			else
			{
				driver.navigate().back();
				System.out.println("Issue in sign up page");
			}
		}
		catch (Exception e) {
			status.add("Error");
			System.out.println("Exception in TC_N_11: " + e.getMessage());
			e.printStackTrace();
		}
		return  status;
		
	}
	public ArrayList<String> checkTC_N_12(ArrayList<String> data)
	{
		ArrayList<String> result = new ArrayList<String>();
		ArrayList<String> status = new ArrayList<String>();
		try
		{
			result.addAll(this.registerProcess(data));
			if (result.size()>0) 
			{
				System.out.println("negative testcase accepted - TC_N_12");
				result.addAll(this.logOut());
				result.addAll(this.NavigateToHomePage());
			}
			else 
			{
				driver.navigate().back();
				System.out.println("Issue in sign up page");
			}
		}
		catch (Exception e) {
			status.add("Error");
			System.out.println("Exception in TC_N_12: " + e.getMessage());
			e.printStackTrace();
		}
		return  status;
		
	}
	public ArrayList<String> checkTC_N_13(ArrayList<String> data)
	{
		ArrayList<String> result = new ArrayList<String>();
		ArrayList<String> status = new ArrayList<String>();
		try
		{
			result.addAll(this.registerProcess(data));
			if (result.size()>0) 
			{
				System.out.println("negative testcase accepted - TC_N_13");
				result.addAll(this.logOut());
				result.addAll(this.NavigateToHomePage());
			}
			else 
			{
				driver.navigate().back();
				System.out.println("Issue in sign up page");
			}
		}
		catch (Exception e) {
			status.add("Error");
			System.out.println("Exception in TC_N_13: " + e.getMessage());
			e.printStackTrace();
		}
		return status;
		
	}
	public ArrayList<String> checkTC_N_14(ArrayList<String> data)
	{
		ArrayList<String> result = new ArrayList<String>();
		ArrayList<String> status = new ArrayList<String>();
		try
		{
			result.addAll(this.registerProcess(data));
			if (result.size()>0) 
			{
				System.out.println("negative testcase accepted - TC_N_14");
				result.addAll(this.logOut());
				result.addAll(this.NavigateToHomePage());
			}
			else
			{
				driver.navigate().back();
				System.out.println("Issue in sign up page");
			}
		}
		catch (Exception e) {
			status.add("Error");
			System.out.println("Exception in TC_N_14: " + e.getMessage());
			e.printStackTrace();
		}
		return  status;
		
	}
}
