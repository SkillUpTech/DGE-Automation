package com.skillup.dge.webPages;

import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ForgetPasswordPage
{
	WebDriver driver;
	RegisterPage registerPage;
	public ForgetPasswordPage(WebDriver driver) {
		this.driver = driver;
		this.registerPage = new RegisterPage(driver);
	}
	
	public ArrayList<String> forgetPasswordProcess(ArrayList<String> data)
	{
		ArrayList<String> status = new ArrayList<>();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String signInLocator = "//div[@class='btn-holder']//a[contains(text(),'Sign in')]";
		String forgetPasswordLocator = "//a[@id='forgot-password']";
		String emailLocator = "//input[@id='email']";
		String submitLocator = "//span[contains(text(),'Submit')]";
		try
		{
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
			status.add(registerPage.FocusWindow("login"));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			if(driver.findElements(By.xpath("//a[@id='forgot-password']")).size()>0)
			{
				WebElement forgetPasswordButton = driver.findElement(By.xpath(forgetPasswordLocator));
				js.executeScript("arguments[0].scrollIntoView(true);", forgetPasswordButton);
				if (forgetPasswordButton.isDisplayed())
				{
					js.executeScript("arguments[0].click();", forgetPasswordButton);
				}
			}
			status.add(registerPage.FocusWindow("reset"));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			if(driver.findElements(By.xpath("//input[@id='email']")).size()>0)
			{
				WebElement email = driver.findElement(By.xpath(emailLocator));
				js.executeScript("arguments[0].scrollIntoView(true);", email);
				if (email.isDisplayed())
				{
					email.sendKeys(data.get(3)); // email)
				}
			}
			WebElement submitButton = driver.findElement(By.xpath(submitLocator));
			js.executeScript("arguments[0].scrollIntoView(true);", submitButton);
			if (submitButton.isDisplayed()) 
			{
				js.executeScript("arguments[0].click();", submitButton);
			}
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			status.addAll(this.ValidationMessage());
			
			System.out.println("Forget Password process is completed successfully");
		} 
		catch (Exception e) {
			e.printStackTrace();
			status.add("Error");
		}
		return status;
	}
	public ArrayList<String> ValidationMessage()
	{
		ArrayList<String> status = new ArrayList<>();
		String validationMessageLocator = "//span[contains(text(),'Submit')]";
		String mailConfirmationLocator = "//div[normalize-space()='Check your email']";
		try
		{
			if (driver.findElements(By.xpath(validationMessageLocator)).size() > 0)
			{
				WebElement validationMessage = driver.findElement(By.xpath(validationMessageLocator));
				if (validationMessage.isDisplayed())
				{
					System.out.println("Validation message is displayed");
					status.add("email");
				}
			} 
			else
			{
				if(driver.findElements(By.xpath(mailConfirmationLocator)).size()>0)
				{
					System.out.println("Validation message is not displayed");
					System.out.println("confirmation message is displayed");
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			status.add("Error");
		}
		return status;
	}
	public ArrayList<String> checkTC_P_01(ArrayList<String> data)
	{
		ArrayList<String> status = new ArrayList<>();;
		try
		{
			status.addAll(this.forgetPasswordProcess(data));
			if(!status.contains("Error")||status.contains("email"))
			{
				status.add(registerPage.FocusWindow("https://stagecourses.skillup.online/"));
			}
			else
			{
				System.out.println("Error message is displayed for invalid credentials");
			}
			driver.navigate().back();
			driver.navigate().refresh();
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
			status.addAll(this.forgetPasswordProcess(data));
			if(!status.contains("Error")||status.contains("email"))
			{
				status.add(registerPage.FocusWindow("https://stagecourses.skillup.online/"));
			}
			else
			{
				System.out.println("Error message is displayed for invalid credentials");
			}
			driver.navigate().back();
			driver.navigate().refresh();
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
			status.addAll(this.forgetPasswordProcess(data));
			if(!status.contains("Error")||status.contains("email"))
			{
				status.add(registerPage.FocusWindow("https://stagecourses.skillup.online/"));
			}
			else
			{
				System.out.println("Error message is displayed for invalid credentials");
			}
			driver.navigate().back();
			driver.navigate().refresh();
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
			status.addAll(this.forgetPasswordProcess(data));
			if(!status.contains("Error")||status.contains("email"))
			{
				status.add(registerPage.FocusWindow("https://stagecourses.skillup.online/"));
			}
			else
			{
				System.out.println("Error message is displayed for invalid credentials");
			}
			driver.navigate().back();
			driver.navigate().refresh();
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
			status.addAll(this.forgetPasswordProcess(data));
			if(!status.contains("Error")||status.contains("email"))
			{
				status.add(registerPage.FocusWindow("https://stagecourses.skillup.online/"));
			}
			else
			{
				System.out.println("Error message is displayed for invalid credentials");
			}
			driver.navigate().back();
			driver.navigate().refresh();
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
			status.addAll(this.forgetPasswordProcess(data));
			if(!status.contains("Error")||status.contains("email"))
			{
				status.add(registerPage.FocusWindow("https://stagecourses.skillup.online/"));
			}
			else
			{
				System.out.println("Error message is displayed for invalid credentials");
			}
			driver.navigate().back();
			driver.navigate().refresh();
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
			status.addAll(this.forgetPasswordProcess(data));
			if(!status.contains("Error")||status.contains("email"))
			{
				status.add(registerPage.FocusWindow("https://stagecourses.skillup.online/"));
			}
			else
			{
				System.out.println("Error message is displayed for invalid credentials");
			}
			driver.navigate().back();
			driver.navigate().refresh();
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
			status.addAll(this.forgetPasswordProcess(data));
			if(!status.contains("Error")||status.contains("email"))
			{
				status.add(registerPage.FocusWindow("https://stagecourses.skillup.online/"));
			}
			else
			{
				System.out.println("Error message is displayed for invalid credentials");
			}
			driver.navigate().back();
			driver.navigate().refresh();
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
			status.addAll(this.forgetPasswordProcess(data));
			if(!status.contains("Error")||status.contains("email"))
			{
				status.add(registerPage.FocusWindow("https://stagecourses.skillup.online/"));
			}
			else
			{
				System.out.println("Error message is displayed for invalid credentials");
			}
			driver.navigate().back();
			driver.navigate().refresh();
		} catch (Exception e) {
			e.printStackTrace();
			status.add("Error");
		}
		return  status;
	}
}
