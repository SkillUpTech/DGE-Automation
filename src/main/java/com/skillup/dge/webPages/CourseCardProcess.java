package com.skillup.dge.webPages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CourseCardProcess
{
	WebDriver driver;
	HomePage homePage;
	RegisterPage registerPage;
	String courseCardTitleText = "";
	String courseCardOrganizationText = "";
	String courseCardDateTextValue = "";
	String summaryWindow = "";
	public CourseCardProcess(WebDriver driver)
	{
		this.driver = driver;
		this.homePage = new HomePage(driver);
		this.registerPage = new RegisterPage(driver);
	}
	
	public ArrayList<String> checkCourseCardSelection_PastDate(ArrayList<String> data)
	{
		ArrayList<String> status = new ArrayList<String>();
		String clickFindCourse = "//a[contains(text(),'Find a course ')]";
		String courseCardList = "//ul[@class='courses-listing courses-list']/li";
		String courseCardTitle = ".//span[@class='course-title']";
		String courseCardOrg = ".//span[@class='course-organization']";
		String courseCardButton = ".//a[@class='learn-more']";
		String courseCardDate = ".//div[@class='course-date']";
		try 
		{
			status.add(homePage.clickWebElement(clickFindCourse));
			status.add(registerPage.FocusWindow("https://stagecourses.skillup.online/courses"));
			if(driver.findElements(By.xpath(courseCardList)).size()>0)
			{
				List<WebElement> courseCards = driver.findElements(By.xpath(courseCardList));
				for(WebElement card : courseCards)
				{
					WebElement courseCard = card.findElement(By.xpath(courseCardTitle));
					courseCardTitleText = courseCard.getText();
					WebElement courseCardOrganization = card.findElement(By.xpath(courseCardOrg));
					courseCardOrganizationText = courseCardOrganization.getText();
					WebElement courseCardDateText = card.findElement(By.xpath(courseCardDate));
					courseCardDateTextValue = courseCardDateText.getText();
					if(courseCardTitleText.equals(data.get(1)))
                    {
						status.add(homePage.clickWebElement(courseCardButton));
						status.add(registerPage.FocusWindow("about"));
						summaryWindow = driver.getWindowHandle();
                        break;
                    }
                    else
                    {
                        status.add("Fail");
                    }
				}
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public String checkWebElementComparision(String locator, String data)
	{
		 String status = "";
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		    try {
		        List<WebElement> elements = driver.findElements(By.xpath(locator));
		        if (!elements.isEmpty())
		        {
		            WebElement element = elements.get(0);
		            js.executeScript("arguments[0].scrollIntoView(true);", element);
		            String elementText = element.getText();
		            if(elementText.equals(data))
		            {
		            	System.out.println("Element text matches: " + elementText);
		            }
		            else
		            {
						System.out.println("Element text does not match: " + elementText);
						status = "Fail";
		            }
		        } 
		        else {
		            System.out.println("Element not found: " + locator);
		            status = "Fail";
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		        status = "Fail";
		    }
		return status;
	}
	public ArrayList<String> checkTitleFromCourseAboutPage()
	{
		ArrayList<String> status = new ArrayList<String>();
		String titleOfCourse = "//div[@class='info-profile']/h1";
		try 
		{
			status.add(checkWebElementComparision(titleOfCourse, courseCardTitleText));
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkOrgFromCourseAboutPage()
	{
		ArrayList<String> status = new ArrayList<String>();
		String orgOfCourse = "//div[@class='info-profile']//span";
		try 
		{
			status.add(checkWebElementComparision(orgOfCourse, courseCardOrganizationText));
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkEnrollButton()
	{
		ArrayList<String> status = new ArrayList<String>();
		String enrollButton = "//span[@class='register disabled']";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try 
		{
			if (driver.findElements(By.xpath(enrollButton)).size() > 0)
			{
				WebElement checkEnrollButtonText = driver.findElement(By.xpath(enrollButton));
				js.executeScript("arguments[0].scrollIntoView(true);", checkEnrollButtonText);
				String enrollButtonText = checkEnrollButtonText.getText();
				if (enrollButtonText.equals("Enrollment in this course is by invitation only"))
				{
					System.out.println("unable to enroll");
				} 
				else
				{
					System.out.println("able to enroll");
					status.add(homePage.clickWebElement(enrollButton));
					status.add(registerPage.FocusWindow("learner-dashboard/"));
				}
			} 
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkDateFromCourseSummary()
	{
		ArrayList<String> status = new ArrayList<String>();
		String CourseCardDate = "//ol[@class='important-dates']/li[2]//span";
		try 
		{
			status.add(checkWebElementComparision(CourseCardDate, courseCardDateTextValue));
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	
	public ArrayList<String> checkTwitterSharingFromCourseSummary()
	{
		ArrayList<String> status = new ArrayList<String>();
		String enrollButton = "//div[@class='social-sharing']/a[1]";
		try 
		{
			status.add(homePage.clickWebElement(enrollButton));
			status.add(registerPage.FocusWindow("x"));
			driver.close();
			driver.switchTo().window(summaryWindow);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkFacebookSharingFromCourseSummary()
	{
		ArrayList<String> status = new ArrayList<String>();
		String enrollButton = "//div[@class='social-sharing']/a[2]";
		try 
		{
			status.add(homePage.clickWebElement(enrollButton));
			
			status.add(registerPage.FocusWindow("facebook"));
			driver.close();
			driver.switchTo().window(summaryWindow);
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkMailSharingFromCourseSummary()
	{
		ArrayList<String> status = new ArrayList<String>();
		String enrollButton = "//div[@class='social-sharing']/a[3]";
		try 
		{
			status.add(homePage.clickWebElement(enrollButton));
			status.add(registerPage.FocusWindow("mail"));
			driver.close();
			driver.switchTo().window(summaryWindow);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	
}
