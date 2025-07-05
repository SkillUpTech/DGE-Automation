package com.skillup.dge.webPages;

import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SignInPage 
{
	WebDriver driver;
	RegisterPage registerPage;
	String baseWindow = "";
	public SignInPage(WebDriver driver)
	{
		this.driver = driver;
		this.registerPage = new RegisterPage(driver);
	}
	public ArrayList<String> ErrorValidation()
	{
		ArrayList<String> status = new ArrayList<>();
		String errorLocator = "//div[@class='alert-heading h4']";
		try
		{
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			if(driver.findElements(By.xpath(errorLocator)).size()>0)
			{
				System.out.println("Validation message is displayed for SignIn page");
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
				if(driver.findElements(By.xpath("//div[contains(text(),'Enter your username or email')]")).size()>0)
				{
					status.add("username");
				}
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
				if (driver.findElements(By.xpath("//div[contains(text(),'Enter your password')]")).size() > 0)
				{
					status.add("password");
				}
				if(driver.findElements(By.xpath("//div[@class='alert-heading h4']/following-sibling::p")).size()>0)
				{
					status.add("username");
					status.add("password");
				}
			} 
			else
			{
				System.out.println("Validation message is not displayed for SignIn page");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			status.add("Error");
		}
		return status;
	}
	public ArrayList<String> signInProcess(ArrayList<String> data)
	{
		ArrayList<String> result = new ArrayList<>();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String signInLocator = "//div[@class='btn-holder']//a[contains(text(),'Sign in')]";
		String unameLocator = "//input[@id='emailOrUsername']";
		String pwdLocator = "//input[@id='password']";
		String signInButtonLocator = "//form[@id='sign-in-form']//button[@id='sign-in']";
		try 
		{
			String userName = data.get(1);
			String password = data.get(2);
			baseWindow = driver.getWindowHandle();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			if(driver.findElements(By.xpath(signInLocator)).size()>0)
			{
				WebElement signInButton = driver.findElement(By.xpath(signInLocator));
				js.executeScript("arguments[0].scrollIntoView(true);", signInButton);
				if (signInButton.isDisplayed()) 
				{
					js.executeScript("arguments[0].click();", signInButton);
				}
			}
			result.add(registerPage.FocusWindow("login"));
			WebElement userNameField = driver.findElement(By.xpath(unameLocator));
			js.executeScript("arguments[0].scrollIntoView(true);", userNameField);
			if (userNameField.isDisplayed()) 
			{
				if(userName.contains("empty"))
				{
					userNameField.clear();
					userNameField.sendKeys("");
				}
				else
				{
					userNameField.clear();
					userNameField.sendKeys(userName);
				}
			}
			WebElement passwordField = driver.findElement(By.xpath(pwdLocator));
			if (passwordField.isDisplayed()) 
			{
				if(password.contains("empty"))
				{
					passwordField.clear();
					passwordField.sendKeys("");
				}
				else
				{
					passwordField.clear();
					passwordField.sendKeys(password);
				}
			}
			WebElement signInButtonField = driver.findElement(By.xpath(signInButtonLocator));
			js.executeScript("arguments[0].scrollIntoView(true);", signInButtonField);
			if (signInButtonField.isDisplayed())
			{
				js.executeScript("arguments[0].click();", signInButtonField);
				result.addAll(this.ErrorValidation());
				if (result.contains("username")||result.contains("password")) 
				{
					System.out.println("Error message is displayed");
				} 
				else
				{
					System.out.println("Error message is not displayed");
					result.add(registerPage.FocusWindow("learner-dashboard"));
					System.out.println("Successfully registered and logged in to the dashboard page");
				}
			}
			
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			result.add("Error");
		}
		return  result;
	}
	public ArrayList<String> checkTC_P_01(ArrayList<String> data)
	{
		ArrayList<String> status = new ArrayList<>();;
		try
		{
			status.addAll(this.signInProcess(data));
			if(!status.contains("Error"))
			{
				status.addAll(registerPage.logOut());
				status.addAll(registerPage.NavigateToHomePage());
				registerPage.FocusWindow("https://stagecourses.skillup.online/");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			status.add("Error");
		}
		return  status;
	}
	
	public ArrayList<String> checkTC_N_01(ArrayList<String> data)
	{
		ArrayList<String> status = new ArrayList<>();;
		try 
		{
			status.addAll(this.signInProcess(data));
			if(status.contains("Error")||status.contains("username")||status.contains("password"))
			{
				System.out.println("Error message is displayed for invalid credentials");
				driver.navigate().back();
				driver.navigate().refresh();
			}
			else
			{
				System.out.println("Error message is not displayed for invalid credentials");
				status.addAll(registerPage.logOut());
				status.addAll(registerPage.NavigateToHomePage());
				registerPage.FocusWindow("https://stagecourses.skillup.online/");
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			status.add("Error");
		}
		return  status;
	}
	public ArrayList<String> checkTC_N_02(ArrayList<String> data)
	{

		ArrayList<String> status = new ArrayList<>();;
		try 
		{
			status.addAll(this.signInProcess(data));
			if(status.contains("Error")||status.contains("username")||status.contains("password"))
			{
				System.out.println("Error message is displayed for invalid credentials");
				driver.navigate().back();
				driver.navigate().refresh();
			}
			else
			{
				System.out.println("Error message is not displayed for invalid credentials");
				status.addAll(registerPage.logOut());
				status.addAll(registerPage.NavigateToHomePage());
				registerPage.FocusWindow("https://stagecourses.skillup.online/");
			}
		} catch (Exception e) {
			e.printStackTrace();
			status.add("Error");
		}
		return  status;
	}
	public ArrayList<String> checkTC_N_03(ArrayList<String> data)
	{
		ArrayList<String> status = new ArrayList<>();
		try 
		{
			status.addAll(this.signInProcess(data));
			if(status.contains("Error")||status.contains("username")||status.contains("password"))
			{
				System.out.println("Error message is displayed for invalid credentials");
				driver.navigate().back();
				driver.navigate().refresh();
			}
			else
			{
				System.out.println("Error message is not displayed for invalid credentials");
				status.addAll(registerPage.logOut());
				status.addAll(registerPage.NavigateToHomePage());
				registerPage.FocusWindow("https://stagecourses.skillup.online/");
			}
		} catch (Exception e) {
			e.printStackTrace();
				status.add("Error");
		}
		return  status;
	}
	public ArrayList<String> checkTC_N_04(ArrayList<String> data)
	{
		ArrayList<String> status = new ArrayList<>();
		try 
		{
			status.addAll(this.signInProcess(data));
			if(status.contains("Error")||status.contains("username")||status.contains("password"))
			{
				driver.navigate().back();
				driver.navigate().refresh();
				System.out.println("Error message is displayed for invalid credentials");
			}
			else
			{
				System.out.println("Error message is not displayed for invalid credentials");
				status.addAll(registerPage.logOut());
				status.addAll(registerPage.NavigateToHomePage());
				registerPage.FocusWindow("https://stagecourses.skillup.online/");
			}
		} catch (Exception e) {
			e.printStackTrace();
			status.add("Error");
		}
		return  status;
	}
	public ArrayList<String> checkTC_N_05(ArrayList<String> data)
	{
		ArrayList<String> status = new ArrayList<>();
		try 
		{
			status.addAll(this.signInProcess(data));
			if(status.contains("Error")||status.contains("username")||status.contains("password"))
			{
				System.out.println("Error message is displayed for invalid credentials");
				driver.navigate().back();
				driver.navigate().refresh();
			}
			else
			{
				System.out.println("Error message is not displayed for invalid credentials");
				status.addAll(registerPage.logOut());
				status.addAll(registerPage.NavigateToHomePage());
				status.add(registerPage.FocusWindow("https://stagecourses.skillup.online/"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			status.add("Error");
		}
		return  status;
	}
	public ArrayList<String> checkTC_N_06(ArrayList<String> data)
	{
		ArrayList<String> status = new ArrayList<>();
		try 
		{
			status.addAll(this.signInProcess(data));
			if(status.contains("Error")||status.contains("username")||status.contains("password"))
			{
				System.out.println("Error message is displayed for invalid credentials");
				driver.navigate().back();
				driver.navigate().refresh();
			}
			else
			{
				System.out.println("Error message is not displayed for invalid credentials");
				status.addAll(registerPage.logOut());
				status.addAll(registerPage.NavigateToHomePage());
				status.add(registerPage.FocusWindow("https://stagecourses.skillup.online/"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			status.add("Error");
		}
		return  status;
	}
	public ArrayList<String> checkTC_N_07(ArrayList<String> data)
	{
		ArrayList<String> status = new ArrayList<>();
		try 
		{
			status.addAll(this.signInProcess(data));
			if(status.contains("Error")||status.contains("username")||status.contains("password"))
			{
				System.out.println("Error message is displayed for invalid credentials");
				driver.navigate().back();
				driver.navigate().refresh();
			}
			else
			{
				System.out.println("Error message is not displayed for invalid credentials");
				status.addAll(registerPage.logOut());
				status.addAll(registerPage.NavigateToHomePage());
				status.add(registerPage.FocusWindow("https://stagecourses.skillup.online/"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			status.add("Error");
		}
		return  status;
	}
	public ArrayList<String> checkTC_N_08(ArrayList<String> data)
	{
		ArrayList<String> status = new ArrayList<>();
		try 
		{
			status.addAll(this.signInProcess(data));
			if(status.contains("Error")||status.contains("username")||status.contains("password"))
			{
				System.out.println("Error message is displayed for invalid credentials");
				driver.navigate().back();
				driver.navigate().refresh();
			}
			else
			{
				System.out.println("Error message is not displayed for invalid credentials");
				status.addAll(registerPage.logOut());
				status.addAll(registerPage.NavigateToHomePage());
				status.add(registerPage.FocusWindow("https://stagecourses.skillup.online/"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			status.add("Error");
		}
		return  status;
	}
	public ArrayList<String> checkTC_N_09(ArrayList<String> data)
	{
		ArrayList<String> status = new ArrayList<>();
		try 
		{
			status.addAll(this.signInProcess(data));
			if(status.contains("Error")||status.contains("username")||status.contains("password"))
			{
				System.out.println("Error message is displayed for invalid credentials");
				driver.navigate().back();
				driver.navigate().refresh();
			}
			else
			{
				System.out.println("Error message is not displayed for invalid credentials");
				status.addAll(registerPage.logOut());
				status.addAll(registerPage.NavigateToHomePage());
				status.add(registerPage.FocusWindow("https://stagecourses.skillup.online/"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			status.add("Error");
		}
		return  status;
	}
}
