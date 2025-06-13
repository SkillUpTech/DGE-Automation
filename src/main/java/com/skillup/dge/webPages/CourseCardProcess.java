package com.skillup.dge.webPages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CourseCardProcess
{
	WebDriver driver;
	HomePage homePage;
	RegisterPage registerPage;
	String courseCardTitleText = "";
	String courseCardOrganizationText = "";
	String courseCardDateTextValue = "";
	String courseCardStartDateTextValue;
	String courseCardEndDateTextValue;
	String summaryWindow = "";
	String checkAlertText = "";
	public CourseCardProcess(WebDriver driver)
	{
		this.driver = driver;
		this.homePage = new HomePage(driver);
		this.registerPage = new RegisterPage(driver);
	}
	
	public ArrayList<String> checkFindCourseLink()
	{
		ArrayList<String> status = new ArrayList<String>();
		String clickFindCourse = "//a[contains(text(),'Find a course ')]";
		try
		{
			if (driver.findElements(By.xpath(clickFindCourse)).size() > 0) 
			{
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
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		    try {
		        List<WebElement> elements = driver.findElements(By.xpath(locator));
		        if (!elements.isEmpty())
		        {
		            WebElement element = elements.get(0);
		            js.executeScript("arguments[0].scrollIntoView(true);", element);
		            wait.until(ExpectedConditions.visibilityOf(element));
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

	public String textComparision(String data1, String data2) 
	{
		String status = "";
		try {
			if (data1.trim().equals(data2.trim()))
			{
				System.out.println("Element text matches: " );
			} 
			else
			{
				System.out.println("Element text does not match: ");
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
	public void waitForPageToLoad(WebDriver driver, int timeoutInSeconds) {
	    new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds)).until(
	        webDriver -> ((JavascriptExecutor) webDriver)
	            .executeScript("return document.readyState").equals("complete")
	    );
	}
	
	public ArrayList<String> checkDateFromCourseSummary()
	{
		ArrayList<String> status = new ArrayList<String>();
		String CourseContentDate = "//ol[@class='important-dates']/li[2]//span";
		try 
		{
			WebElement courseContentDateLocate = driver.findElement(By.xpath(CourseContentDate));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView(true);", courseContentDateLocate);
			String courseContentDateTextt = courseContentDateLocate.getText();
			status.add(this.textComparision(courseCardDateTextValue, courseContentDateTextt));
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
		String TwitterButton = "//div[@class='social-sharing']/a[1]";
		try 
		{
			status.add(homePage.openLinkInNewTab(TwitterButton));
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
		String facebookButton = "//div[@class='social-sharing']/a[2]";
		try 
		{
			status.add(homePage.openLinkInNewTab(facebookButton));
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
		String mailButton = "//div[@class='social-sharing']/a[3]";
		try 
		{
			status.add(homePage.clickWebElement(mailButton));
			driver.switchTo().window(summaryWindow);
			Thread.sleep(2000); // wait for the mail popup to appear
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
		String enrollButton = "//a[contains(text(),'Enroll Now')]";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		try 
		{
			
			if (driver.findElements(By.xpath(enrollButton)).size() > 0)
			{
				WebElement checkEnrollButtonText = driver.findElement(By.xpath(enrollButton));
				js.executeScript("arguments[0].scrollIntoView(true);", checkEnrollButtonText);
		        wait.until(ExpectedConditions.visibilityOf(checkEnrollButtonText));
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
					System.out.println(status.get(1));
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
	
	public ArrayList<String> CardSettingsOptions()
	{
		ArrayList<String> status = new ArrayList<String>();
		String settingsLink = "//div[@data-testid='CourseCard'][1]//span[@class='pgn__icon btn-icon__icon']";
		try 
		{
			status.add(homePage.clickWebElement(settingsLink));
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	
	
	public ArrayList<String> checkEmailSettingsOption()
	{
		ArrayList<String> status = new ArrayList<String>();
		String emailSettings = "//a[contains(text(),'Email settings')]";
		String EmailAlertText = "Receive course emails?";
		String locateAlertText = "//div[@class='bg-white p-3 rounded shadow']/h4";
		try 
		{
			status.add(homePage.clickWebElement(emailSettings));
			status.add(this.checkWebElementComparision(locateAlertText, EmailAlertText));
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	
	public ArrayList<String> checkEmailNevermindOption()
	{
		ArrayList<String> status = new ArrayList<String>();
		String neverMindButton = "//button[contains(text(),'Never mind')]";
		try 
		{
			status.add(homePage.clickWebElement(neverMindButton));
			status.add(registerPage.FocusWindow("learner-dashboard/"));
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkMailsOnOffSubmission()
	{
		ArrayList<String> status = new ArrayList<String>();
		String settingsLink = "//div[@data-testid='CourseCard'][1]//span[@class='pgn__icon btn-icon__icon']";
		String emailSettings = "//a[contains(text(),'Email settings')]";
		String locateAlertText = "//div[@class='bg-white p-3 rounded shadow']/h4";
		String EmailAlertText = "Receive course emails?";
		String emailButtonOnOff = "//input[contains(@id,'form-field')]";
		String saveSettings = "//button[contains(text(),'Save settings')]";
		try 
		{
			status.add(homePage.clickWebElement(settingsLink));
			status.add(homePage.clickWebElement(emailSettings));
			status.add(this.checkWebElementComparision(locateAlertText, EmailAlertText));
			status.add(homePage.clickWebElement(emailButtonOnOff));
			status.add(homePage.clickWebElement(saveSettings));
			status.add(registerPage.FocusWindow("learner-dashboard/"));
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkSettingsLinkToUnEnrollCard()
	{
		ArrayList<String> status = new ArrayList<String>();
		String settingsLink = "//div[@data-testid='CourseCard'][1]//span[@class='pgn__icon btn-icon__icon']";
		try
		{
			status.add(homePage.clickWebElement(settingsLink));
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkUnenrollOption()
	{
		ArrayList<String> status = new ArrayList<String>();
		String unenrollButton = "//a[normalize-space()='Unenroll']";
		checkAlertText = "Unenroll from course?";
		String locateAlertText = "//div[@class='bg-white p-3 rounded shadow']/h4";
		try
		{
			status.add(homePage.clickWebElement(unenrollButton));
			Thread.sleep(200); // wait for the alert to appear
			status.add(this.checkWebElementComparision(locateAlertText, checkAlertText));
			Thread.sleep(200); 
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkUnEnrollNeverMindButton()
	{
		ArrayList<String> status = new ArrayList<String>();
		
		String neverButton = "//button[contains(text(),'Never mind')]";
		String locateAlertText = "//div[@class='bg-white p-3 rounded shadow']/h4";
		try
		{
			Thread.sleep(200); 
			status.add(homePage.clickWebElement(neverButton));
			Thread.sleep(200);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			if (driver.findElements(By.xpath(locateAlertText)).size() > 0)
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
	public ArrayList<String> checkSkipSurveyFromUnenrollOption()
	{
		ArrayList<String> status = new ArrayList<String>();
		String settingsLink = "//div[@data-testid='CourseCard'][1]//span[@class='pgn__icon btn-icon__icon']";
		String unenrollButton = "//a[normalize-space()='Unenroll']";
		String locateUnenrollButtonFromAlert = "//button[contains(text(),'Unenroll')]";
		String skipsurveyButton = "//button[contains(text(),'Skip survey')]";
		try
		{
			status.add(homePage.clickWebElement(settingsLink));
			Thread.sleep(200);
			status.add(homePage.clickWebElement(unenrollButton));
			Thread.sleep(200);
			status.add(homePage.clickWebElement(locateUnenrollButtonFromAlert));
			Thread.sleep(200);
			status.add(homePage.clickWebElement(skipsurveyButton));
			Thread.sleep(200);
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkReturnToDasboardButton()
	{
		ArrayList<String> status = new ArrayList<String>();
		String clickReturnToDashboard = "//button[contains(text(),'Return to dashboard')]";
		try
		{
			status.add(homePage.clickWebElement(clickReturnToDashboard));
			Thread.sleep(200);
			status.add(registerPage.FocusWindow("learner-dashboard/"));
			Thread.sleep(200);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	
	public ArrayList<String> checkSelectCourseToUnEnroll(ArrayList<String> data)
	{
		ArrayList<String> status = new ArrayList<String>();
		String enrollButton = "//a[contains(text(),'Enroll Now')]";
		try
		{
			status.addAll(this.checkEnrollCardToCompleteCourse(data));
			Thread.sleep(200);
			status.add(homePage.clickWebElement(enrollButton));
			Thread.sleep(200);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	
	
	public ArrayList<String> checkReasonToUnenroll()
	{
		ArrayList<String> status = new ArrayList<String>();
		String settingsLink = "//div[@data-testid='dropdown']/button";
		String unenrollButtonFromSettingsOption = "//a[contains(text(),'Unenroll')]";
		String locateAlertTextFromUnEnroll = "//div[@class='bg-white p-3 rounded shadow']/h4";
		String selectReasonToUnEnroll = "//div[@role='radiogroup']/div//input[1]";
		String submitSurveyButton = "//button[contains(text(),'Submit reason')]";
		String AlertTextFromUnEnroll = "Unenroll from course?";
		String unenrollButton = "//button[contains(text(),'Unenroll')]";
		String clickReturnToDashboard = "//button[contains(text(),'Return to dashboard')]";
		try
		{
			status.add(homePage.clickWebElement(settingsLink));
			System.out.println(status);
			Thread.sleep(200); // wait for the settings to open
			status.add(homePage.clickWebElement(unenrollButtonFromSettingsOption));
			System.out.println(status);
			Thread.sleep(200);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			if(driver.findElements(By.xpath(locateAlertTextFromUnEnroll)).size() > 0)
			{
				status.add(this.checkWebElementComparision(locateAlertTextFromUnEnroll, AlertTextFromUnEnroll));
				System.out.println(status);
			}
			else
			{
				status.add("Fail");
			}
			Thread.sleep(200);
			status.add(homePage.clickWebElement(unenrollButton));
			System.out.println(status);
			Thread.sleep(200);
			status.add(homePage.clickWebElement(selectReasonToUnEnroll));
			System.out.println(status);
			Thread.sleep(200);
			status.add(homePage.clickWebElement(submitSurveyButton));
			System.out.println(status);
			Thread.sleep(200);
			status.add(homePage.clickWebElement(clickReturnToDashboard));
			System.out.println(status);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	
	public ArrayList<String> selectCourseCardToComplete(ArrayList<String> data)
	{
		ArrayList<String> status = new ArrayList<String>();
		try
		{
			status.addAll(this.checkEnrollCardToCompleteCourse(data));
			Thread.sleep(200);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	
	
	public ArrayList<String> checkEnrollCardToCompleteCourse(ArrayList<String> data)
	{

		ArrayList<String> status = new ArrayList<String>();
		String courseCode = ".//h2/span[@class='course-code']";
		String clickFindCourse = "//a[contains(text(),'Find a course ')]";
		String courseCardList = "//ul[@class='courses-listing courses-list']/li";
		String courseCardTitle = ".//span[@class='course-title']";
		String courseCardOrg = ".//span[@class='course-organization']";
		String courseCardButton = ".//a[@class='learn-more']";
		String courseCardDate = ".//div[@class='course-date']"; // course card starts date
		JavascriptExecutor js = (JavascriptExecutor) driver;
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));

		try 
		{
			
			status.add(homePage.clickWebElement(clickFindCourse));
			Thread.sleep(200); // wait for the page to load
			status.add(registerPage.FocusWindow("/courses"));
			
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
			Thread.sleep(200);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			if(driver.findElements(By.xpath(courseCardList)).size()>0)
			{
				List<WebElement> courseCards = driver.findElements(By.xpath(courseCardList));
				for(WebElement card : courseCards)
				{
					
					js.executeScript("arguments[0].scrollIntoView(true);", card);
					wait.until(ExpectedConditions.visibilityOf(card));
					Thread.sleep(200);
					WebElement courseCodeLocator = card.findElement(By.xpath(courseCode));
					js.executeScript("arguments[0].scrollIntoView(true);", courseCodeLocator);
					String courseCodeText = courseCodeLocator.getAttribute("textContent");
					System.out.println(courseCodeText);
					WebElement courseCard = card.findElement(By.xpath(courseCardTitle));
					 js.executeScript("arguments[0].scrollIntoView(true);", courseCard);
			            wait.until(ExpectedConditions.visibilityOf(courseCard));
					courseCardTitleText = courseCard.getText();
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
					if(courseCardTitleText.equals(data.get(1))&&courseCodeText.equals(data.get(2)))
                    {
						WebElement courseCardOrganization = card.findElement(By.xpath(courseCardOrg));
						 js.executeScript("arguments[0].scrollIntoView(true);", courseCardOrganization);
				            wait.until(ExpectedConditions.visibilityOf(courseCardOrganization));
						courseCardOrganizationText = courseCardOrganization.getText();
						Thread.sleep(200);
						WebElement courseCardDateLocate = card.findElement(By.xpath(courseCardDate));
						String courseCardDateText[] = courseCardDateLocate.getText().split("Starts:");
						courseCardDateTextValue = courseCardDateText[1];
						
						WebElement  locateCard = card.findElement(By.xpath(courseCardButton));
						
						js.executeScript("arguments[0].scrollIntoView(true);", locateCard);
						wait.until(ExpectedConditions.visibilityOf(locateCard));
		                wait.until(ExpectedConditions.elementToBeClickable(locateCard));
						js.executeScript("arguments[0].click();", locateCard);
					 
						status.add(registerPage.FocusWindow("about"));
						Thread.sleep(200);
						summaryWindow = driver.getWindowHandle();
                        break;
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
	String courseCardOrg = "";
	String courseCardEndDateText = "";
	public ArrayList<String> checkCourseCardTitleWithSummaryPage()
	{
		ArrayList<String> status = new ArrayList<String>();
		
		String titleSummaryPage = "//div[@class='profile-top']//h1";
		
		try 
		{
			
			status.add(this.checkWebElementComparision(titleSummaryPage, courseCardTitleText));
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkCourseCardOrgWithSummaryPage()
	{
		ArrayList<String> status = new ArrayList<String>();
		String orgLocatorFromContent = "//div[@class='info-profile']//span[@class='org']";
		try 
		{
			status.add(this.checkWebElementComparision(orgLocatorFromContent, courseCardOrganizationText));
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkCourseCardDateWithSummaryPage()
	{
		ArrayList<String> status = new ArrayList<String>();
		try 
		{
			//status.add(this.checkWebElementComparision(courseEndDateLocator, courseCardEndDateText));
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	
	public ArrayList<String> checkCourseCardTitleWithDashboard()
	{
		ArrayList<String> status = new ArrayList<String>();
		
		String cardsList = "//div[@data-testid='CourseCard']";
		
		String courseCardTitle = ".//*[@class='course-card-title']";
		
		String clickEnroll = "//a[contains(text(),' Enroll Now')]";
		
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		try 
		{
			WebElement enrollButton = driver.findElement(By.xpath(clickEnroll));
			js.executeScript("arguments[0].scrollIntoView(true);", enrollButton);
			wait.until(ExpectedConditions.visibilityOf(enrollButton));
            wait.until(ExpectedConditions.elementToBeClickable(enrollButton));
			if (enrollButton.isDisplayed()) 
			{
				System.out.println("Enroll button is displayed.");
				status.add(homePage.clickWebElement(clickEnroll));
			} else {
				System.out.println("Enroll button is not displayed.");
				status.add("Fail");
			}
			List<WebElement> cardsFromDashboard = driver.findElements(By.xpath(cardsList));
			for (WebElement card : cardsFromDashboard) 
			{
				WebElement courseCardTitleFromDashboardLocator = card.findElement(By.xpath(courseCardTitle));
				js.executeScript("arguments[0].scrollIntoView(true);", courseCardTitleFromDashboardLocator);
				wait.until(ExpectedConditions.visibilityOf(courseCardTitleFromDashboardLocator));
				String courseCardTitleTextFromDashboard = courseCardTitleFromDashboardLocator.getText();
				if(courseCardTitleText.equals(courseCardTitleTextFromDashboard))
				{
					status.add(this.textComparision(courseCardTitleTextFromDashboard, courseCardTitleText));
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
	public ArrayList<String> checkCourseCardOrgWithDashboard()
	{
		ArrayList<String> status = new ArrayList<String>();
		String cardsList = "//div[@data-testid='CourseCard']";
		String courseCardTitle = ".//*[@class='course-card-title']";
		String courseCardDetails = ".//div[contains(@class,'card-section')]//*[@data-testid='CourseCardDetails']";//org, date, etc.
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try 
		{
			List<WebElement> cardsFromDashboard = driver.findElements(By.xpath(cardsList));
			for (WebElement card : cardsFromDashboard) 
			{
				js.executeScript("arguments[0].scrollIntoView(true);", card);
				WebElement courseCardTitleFromDasboardLocator = card.findElement(By.xpath(courseCardTitle));
				js.executeScript("arguments[0].scrollIntoView(true);", courseCardTitleFromDasboardLocator);
				wait.until(ExpectedConditions.visibilityOf(courseCardTitleFromDasboardLocator));
				String	courseCardTitleTextFromDashboard = courseCardTitleFromDasboardLocator.getText();
				if(courseCardTitleText.equals(courseCardTitleTextFromDashboard))
				{
					WebElement courseCardDetailsLocator = card.findElement(By.xpath(courseCardDetails));
					js.executeScript("arguments[0].scrollIntoView(true);", courseCardDetailsLocator);
					wait.until(ExpectedConditions.visibilityOf(courseCardDetailsLocator));
					String getORGFromDashboard = courseCardDetailsLocator.getText();
					String getOrgFromDashboard[] = getORGFromDashboard.split("•");
					System.out.println("Organization from dashboard: " + getOrgFromDashboard[0]);
					if(getOrgFromDashboard[0].trim().equals(courseCardOrganizationText.trim()))
					{
						System.out.println("Organization matches: " + courseCardOrg);
					} else {
						System.out.println("Organization does not match: " + courseCardOrg);
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
	public ArrayList<String> checkCourseCardDateWithDashboard()
	{
		ArrayList<String> status = new ArrayList<String>();
		try 
		{
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	
	public ArrayList<String> clickWebElements(WebElement parent, By clickBeginCourseFromDashboardLocator)
	{
		ArrayList<String> status = new ArrayList<String>();
		  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		try
		{
			if (parent.findElements(clickBeginCourseFromDashboardLocator).size() > 0)
			{
				WebElement childElement = parent.findElement(clickBeginCourseFromDashboardLocator);
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].scrollIntoView(true);", childElement);
				wait.until(ExpectedConditions.visibilityOf(childElement));
                wait.until(ExpectedConditions.elementToBeClickable(childElement));
				js.executeScript("arguments[0].click();", childElement);
			} 
			else {
				System.out.println("Child element not found: " + clickBeginCourseFromDashboardLocator.toString());
				status.add("Fail");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Unable to click on the element: " + clickBeginCourseFromDashboardLocator.toString());
			status.add("Fail");
		}
		return status;
	}
	
	public ArrayList<String> checkBeginCourseCard()
	{
		ArrayList<String> status = new ArrayList<String>();
		String clickBeginCourse = ".//a[contains(text(),'Begin Course')]";
	//	String expandAllLink = "//button[contains(text(),'Expand all')]";
		String cardsList = "//div[@data-testid='CourseCard']";
		String courseCardTitle = ".//*[@class='course-card-title']";
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		try 
		{
			List<WebElement> cardsFromDashboard = driver.findElements(By.xpath(cardsList));
			for (WebElement card : cardsFromDashboard) 
			{
				js.executeScript("arguments[0].scrollIntoView(true);", card);
				wait.until(ExpectedConditions.visibilityOf(card));
				WebElement courseCardTitleFromDashboardLocator = card.findElement(By.xpath(courseCardTitle));
				js.executeScript("arguments[0].scrollIntoView(true);", courseCardTitleFromDashboardLocator);
				wait.until(ExpectedConditions.visibilityOf(courseCardTitleFromDashboardLocator));
				String courseCardTitleTextFromDashboard = courseCardTitleFromDashboardLocator.getText();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
				if(courseCardTitleText.equals(courseCardTitleTextFromDashboard))
				{
					By clickBeginCourseFromDashboardLocator = By.xpath(clickBeginCourse);
					status.addAll(this.clickWebElements(card, clickBeginCourseFromDashboardLocator));
					status.add(registerPage.FocusWindow("/home"));
					break;
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
	
	public ArrayList<String> checkExpandAll() {
		ArrayList<String> status = new ArrayList<String>();
		String expandAllLink = "//button[contains(text(),'Expand all')]";
		try {
			status.add(homePage.clickWebElement(expandAllLink));
		} catch (Exception e) {
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkCollapseAll()
	{
		ArrayList<String> status = new ArrayList<String>();
		String collapseAll = "//button[contains(text(),'Collapse all')]";
		try 
		{
			status.add(homePage.clickWebElement(collapseAll));
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	
	public ArrayList<String> checkStartCourseButton() 
	{
		ArrayList<String> status = new ArrayList<String>();
		String locateStartCourseButton = "//a[contains(text(),'Start course')]";
		try 
		{
			status.add(homePage.clickWebElement(locateStartCourseButton));
			status.add(registerPage.FocusWindow("course-"));
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkBookMarksPage()
	{
		ArrayList<String> status = new ArrayList<String>();
		String bookMarksLink = "//button[@aria-live='assertive']//descendant::span[3]";
		String getBookmarkedText = "Bookmarked";
		String getBookmarkTextFromHome = "Bookmark this page";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try
		{
			Thread.sleep(200);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			if (driver.findElements(By.xpath(bookMarksLink)).size() > 0)
			{
				WebElement bookmarkElement = driver.findElement(By.xpath(bookMarksLink));
				js.executeScript("arguments[0].scrollIntoView(true);", bookmarkElement);
				String bookmarkText = bookmarkElement.getText();
				Thread.sleep(200);
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
				if (bookmarkText.equals(getBookmarkedText))
				{
					System.out.println("page is book marked");
				} 
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
				if (bookmarkText.equals(getBookmarkTextFromHome))
				{
					System.out.println("Bookmark text is not present, clicking to bookmark the page.");
					status.add(homePage.clickWebElement(bookMarksLink));
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
	public ArrayList<String> checkPageISBookMarkedOrNot()
	{
		ArrayList<String> status = new ArrayList<String>();
		String clickBookmarkedLink = "//button[@aria-live='assertive']//descendant::span[3]";
		String getBookmarkText = "Bookmarked";
		try
		{
			Thread.sleep(200);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			if (driver.findElements(By.xpath(clickBookmarkedLink)).size() > 0)
            {
				WebElement bookmarkElement = driver.findElement(By.xpath(clickBookmarkedLink));
				String bookmarkText = bookmarkElement.getText();
				Thread.sleep(200); // wait for the text to be updated
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
				if (bookmarkText.equals(getBookmarkText)) 
                {
                    System.out.println("page is book marked");
                } 
                else 
                {
                    System.out.println("page is not book marked");
                    status.add("Fail");
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
	public ArrayList<String> checkPageIsNavigatoToCourse()
	{
		ArrayList<String> status = new ArrayList<String>();
		String clickCourseTab = "//nav[@aria-label='Course Material']/a[contains(text(),'Course')]";
		try
		{
			Thread.sleep(200); 
			status.add(homePage.clickWebElement(clickCourseTab));
			status.add(registerPage.FocusWindow("/home"));
			Thread.sleep(200); 
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkBookmarksLinkFromHome()
	{
		ArrayList<String> status = new ArrayList<String>();
		String clickBookmarkLinkFromHome = "//a[normalize-space()='Bookmarks']";
		try
		{
			Thread.sleep(200); 
			status.add(homePage.clickWebElement(clickBookmarkLinkFromHome));
			status.add(registerPage.FocusWindow("bookmarks/"));
			Thread.sleep(200); 
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	
	public ArrayList<String> checkViewButtonFromBookmarkPage() 
	{
		ArrayList<String> status = new ArrayList<String>();
		String clickBookmarkLinkFromHome = "//a[normalize-space()='Bookmarks']";
		String clickViewbutton = "//*[@id='bookmark-type-0']/span[1]";
		try {
			status.add(homePage.clickWebElement(clickBookmarkLinkFromHome));
			Thread.sleep(200); 
			status.add(homePage.clickWebElement(clickViewbutton));
			status.add(registerPage.FocusWindow("course-"));
			Thread.sleep(200); 
		} catch (Exception e) {
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	
	public ArrayList<String> checkCourseOutlineTrayIcon() {
		ArrayList<String> status = new ArrayList<String>();
		String courseOutlineTrayIcon = "//button[@aria-label='Toggle course outline tray']";
		try {
			status.add(homePage.clickWebElement(courseOutlineTrayIcon));
			Thread.sleep(200); 
		} catch (Exception e) {
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	
	public ArrayList<String> checkDiscussionIcon() {
		ArrayList<String> status = new ArrayList<String>();
		String clickDiscussionLink = "//*[@aria-label='Show discussions tray']";
		try {
			status.add(homePage.clickWebElement(clickDiscussionLink));
			Thread.sleep(200); 
		} catch (Exception e) {
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	
	public ArrayList<String> checkDiscussionFrame() {
		ArrayList<String> status = new ArrayList<String>();
		String DiscussionFrame = "//iframe[@title='Discussions']";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		try 
		{
			Thread.sleep(200); 
			WebElement focusFrameLocator = driver.findElement(By.xpath(DiscussionFrame));
			js.executeScript("arguments[0].scrollIntoView(true);", focusFrameLocator);
			 wait.until(ExpectedConditions.visibilityOf(focusFrameLocator));
			driver.switchTo().frame(focusFrameLocator);
			Thread.sleep(200);
			
		} catch (Exception e) {
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	
	public ArrayList<String> checkDiscussionTitle() {
		ArrayList<String> status = new ArrayList<String>();
		String DiscussionHeadingLocator = "//h4[contains(text(),'Discussions')]";
		try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			if (driver.findElements(By.xpath(DiscussionHeadingLocator)).size() > 0)
			{
				System.out.println("Discussion is available");
				Thread.sleep(200);
			} 
			else
			{
				System.out.println("Discussion is not available");
				status.add("Fail");
			}
		} catch (Exception e) {
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkDiscussionSectionCloseIcon() {
		ArrayList<String> status = new ArrayList<String>();
		String DiscussionCloseLocator = "//main[@id='main']//button[@aria-label='Close']";
		try {
			status.add(homePage.clickWebElement(DiscussionCloseLocator));
			Thread.sleep(200);
			driver.switchTo().defaultContent(); // Switch back to the main content after closing the discussion tray
		} catch (Exception e) {
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkBellIcon() {
		ArrayList<String> status = new ArrayList<String>();
		String BellIcon = "//span[@alt='Show notification tray']";
		try {
			status.add(homePage.clickWebElement(BellIcon));
		} catch (Exception e) {
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}

	public ArrayList<String> checkNotificationTray() {
		ArrayList<String> status = new ArrayList<String>();
		String checkNotification = "//*[contains(text(),'Notifications')]";
		try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			if(driver.findElements(By.xpath(checkNotification)).size()>0)
			{
				System.out.println("Notification is available");
			} 
			else
			{
				System.out.println("Notification is not available");
				status.add("Fail");
			}
		} catch (Exception e) {
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkNavigateToCourseHomePage()
	{
		ArrayList<String> status = new ArrayList<String>();
		String navigateCoursePage = "//nav[@aria-label='Course Material']/a[1]";
		try 
		{
			status.add(homePage.clickWebElement(navigateCoursePage));
			
			status.add(registerPage.FocusWindow("/home"));
			Thread.sleep(200); // wait for the page to load
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkLaunchTourLink()
	{
		ArrayList<String> status = new ArrayList<String>();
		String launchTourLink = "//li[@id='courseHome-launchTourLink']/button";
		try 
		{
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			if (driver.findElements(By.xpath(launchTourLink)).size() > 0) {
				System.out.println("Launch tour link is available");
				status.add(homePage.clickWebElement(launchTourLink));
			} else {
				System.out.println("Launch tour link is not available");
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkLaunchTourAlert()
	{
		ArrayList<String> status = new ArrayList<String>();
		String focusAlert = "//div[@id='pgn__checkpoint']";
		try 
		{
			if (driver.findElements(By.xpath(focusAlert)).size() > 0)
			{
				System.out.println("Launch tour alert is available");
			} 
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkDismissFromLaunchTourAlert()
	{
		ArrayList<String> status = new ArrayList<String>();
		String clickDismiss = "//button[contains(text(),'Dismiss')]";
		try 
		{
			if (driver.findElements(By.xpath(clickDismiss)).size() > 0) {
				System.out.println("Dismiss button is available");
				status.add(homePage.clickWebElement(clickDismiss));
			} else {
				System.out.println("Dismiss button is not available");
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkOkayFromLaunchTourAlert()
	{
		ArrayList<String> status = new ArrayList<String>();
		
		String clickOkay = "//button[contains(text(),'Okay')]";
		try 
		{
			if(driver.findElements(By.xpath(clickOkay)).size() > 0)
			{
				status.add(homePage.clickWebElement(clickOkay));
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkNextFromLaunchTourAlert()
	{
		ArrayList<String> status = new ArrayList<String>();
		String launchTourLink = "//li[@id='courseHome-launchTourLink']//button[contains(text(),'Launch tour')]";
		String clickOkay = "//button[contains(text(),'Okay')]";
		String clickNext = "//button[contains(text(),'Next')]";
		try 
		{
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			Thread.sleep(200); // wait for the page to load
			if (driver.findElements(By.xpath(launchTourLink)).size() > 0) {
				System.out.println("Launch tour link is available");
				status.add(homePage.clickWebElement(launchTourLink));
				while (driver.findElements(By.xpath(clickNext)).size() > 0) 
				{
					status.add(homePage.clickWebElement(clickNext));
				}
				if(driver.findElements(By.xpath(clickOkay)).size() > 0)
				{
					status.add(homePage.clickWebElement(clickOkay));
				}
			} else {
				System.out.println("Launch tour link is not available");
			}
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkViewAllCourseLink()
	{
		ArrayList<String> status = new ArrayList<String>();
		String ViewAllCourseLink = "//a[contains(text(),'View all course dates')]";
		try 
		{
			status.add(homePage.clickWebElement(ViewAllCourseLink));
			status.add(registerPage.FocusWindow("/dates"));
			status.addAll(this.checkNavigateToCourseHomePage());
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	String courseContentPage = "";
	int count = 0;
	public ArrayList<String> startCourse(String filepath)
	{
		ArrayList<String> status = new ArrayList<String>();
		String clickStartCourse = "//a[contains(text(),'Start course')]";
		String locateVideoFrame1 = "//div[@class='unit-iframe-wrapper']/iframe";
	 	String locateVideoFrame2 = "//div[@class='video-player']/iframe";
	 	String youTubePlayButton = "//span[@aria-label='Play video']"; // same tab to play
		String playButton = "//a[@aria-label='Watch on www.youtube.com']";
		String nextButton = "//*[@class='unit-navigation d-flex']//a[contains(text(),'Next')]";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		try
		{
			courseContentPage = driver.getWindowHandle();
			status.add(homePage.clickWebElement(clickStartCourse));
			status.add(registerPage.FocusWindow("course/"));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			while(driver.findElements(By.xpath(nextButton)).size()>0)
			{
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
				if(driver.findElements(By.xpath(locateVideoFrame1)).size()>0)
				{
					WebElement playButtonLocator =null;
					driver.switchTo().frame(driver.findElement(By.xpath(locateVideoFrame1)));
					try
					{
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
						if(driver.findElements(By.xpath(youTubePlayButton)).size()>0)
						{
							playButtonLocator = driver.findElement(By.xpath(youTubePlayButton));
							playButtonLocator.click(); // Click the play button directly
						}
						else
						{
							System.out.println("YouTube is not available");
						}
					}
					catch (ElementNotInteractableException  e) 
					{
				        System.out.println("Selenium click failed: " + e.getMessage());
				        System.out.println("Trying JavaScript click...");
				        try
				        {
				            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", playButtonLocator);
				            System.out.println("JavaScript click successful.");
				            String locator = "//button[@class='ytp-large-play-button ytp-button ytp-large-play-button-red-bg']";
				            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
				            if(driver.findElements(By.xpath(locator)).size()>0)
				            {
				            	WebElement jsPlayButton = driver.findElement(By.xpath(locator));
				            	js.executeScript("arguments[0].click();", jsPlayButton);
				            }
				            else
				            {
				            	wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath(locateVideoFrame1))));
				            	wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.ytp-large-play-button")));
				            }
				        } 
				        catch (Exception jsEx) 
				        {
				            System.out.println("JavaScript click also failed: " + jsEx.getMessage());
				            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
				            if(driver.findElements(By.xpath(locateVideoFrame2)).size()>0)
							  { 
								  WebElement  childFrameLocator = driver.findElement(By.xpath(locateVideoFrame2));
								  driver.switchTo().frame(childFrameLocator); List<WebElement> playButtons =
								  driver.findElements(By.xpath(playButton));
								  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
								  if (!playButtons.isEmpty())
								  {
									  WebElement playLocator = playButtons.get(0);
									  js.executeScript("arguments[0].click();", playLocator);
									  status.add(registerPage.FocusWindow("youtube.com")); 
									  driver.close();
									  driver.switchTo().window(courseContentPage);
								   }
							  }
				        }
					}
					
                	driver.switchTo().window(courseContentPage);

				} 
				else
				{
					System.out.println("Video frame is not available.");
				}
				status.addAll(this.fileUpload(filepath));
				status.addAll(this.surveyTable());
				status.addAll(this.Fullscreen());
				status.addAll(this.answerQuiz());
				driver.switchTo().defaultContent();
				count++;
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
				if(count == 4)
				{
					status.addAll(this.checkProgressTab());
				}
				status.add(homePage.clickWebElement(nextButton));
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return status;
	}
	public ArrayList<String> fileUpload(String filePath)
	{
		ArrayList<String> status = new ArrayList<String>();
		String frameLocator = "//iframe[@id='unit-iframe']";
		String fileUploadLocator = "//p[@class='upload']//input[@class='fileupload']";
		String submit = "//a[@class='button finalize-upload']";
		try
		{
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			if (driver.findElements(By.xpath(frameLocator)).size() > 0) 
			{
				WebElement frameElement = driver.findElement(By.xpath(frameLocator));
				driver.switchTo().frame(frameElement);
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
				if (driver.findElements(By.xpath(fileUploadLocator)).size() > 0)
				{
					WebElement fileUploadElement = driver.findElement(By.xpath(fileUploadLocator));
					JavascriptExecutor js = (JavascriptExecutor) driver;
					js.executeScript("arguments[0].scrollIntoView(true);", fileUploadElement);
					fileUploadElement.sendKeys(filePath); // Upload the file
					//js.executeScript("arguments[0].click();", fileUploadElement); // Click the upload element
					Thread.sleep(200); // Wait for the new tab to load
					WebElement submitButton = driver.findElement(By.xpath(submit));
					js.executeScript("arguments[0].scrollIntoView(true);", submitButton);
					js.executeScript("arguments[0].click();", submitButton); // Click the submit button
					Thread.sleep(200); // Wait for the new tab to load
				}
			} 
			driver.switchTo().defaultContent(); // Switch back to the main content
		}
		catch (Exception e) {
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> surveyTable()
	{
		ArrayList<String> status = new ArrayList<String>();
		String frameLocator = "//iframe[@id='unit-iframe']";
		String rows = "//table[@class='survey-table']//tr[@class='survey-row']";
		String cell = ".//td[1]//input[@type='radio'][1]";
		String submitPoll = "//button[@class='submit btn-brand']";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try
		{
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			if (driver.findElements(By.xpath(frameLocator)).size() > 0) 
			{
				WebElement frameElement = driver.findElement(By.xpath(frameLocator));
				driver.switchTo().frame(frameElement);
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
				if (driver.findElements(By.xpath(rows)).size() > 0)
				{
					List<WebElement> surveyRows = driver.findElements(By.xpath(rows));
					for (WebElement row : surveyRows)
					{
						WebElement cellElement = row.findElement(By.xpath(cell));
                        
                        js.executeScript("arguments[0].scrollIntoView(true);", cellElement);
                        js.executeScript("arguments[0].click();", cellElement); // Click the cell
                    }
					Thread.sleep(200); // Wait for the new tab to load
					WebElement submitButton = driver.findElement(By.xpath(submitPoll));
					js.executeScript("arguments[0].scrollIntoView(true);", submitButton);
					js.executeScript("arguments[0].click();", submitButton); // Click the submit button
					Thread.sleep(200); // Wait for the new tab to load
					System.out.println("All survey rows processed successfully.");
				}
			} 
			driver.switchTo().defaultContent(); // Switch back to the main content
		}
		catch (Exception e) {
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> Fullscreen()
	{
		ArrayList<String> status = new ArrayList<String>();
		String frameLocator = "//iframe[@id='unit-iframe']";
		String openFullScreen = "//button[@class='enter-fullscreen']";
		String exitFullscreen = "//button[@class='exit-fullscreen']";
		try
		{
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			if (driver.findElements(By.xpath(frameLocator)).size() > 0) 
			{
				WebElement frameElement = driver.findElement(By.xpath(frameLocator));
				driver.switchTo().frame(frameElement);
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
				if (driver.findElements(By.xpath(openFullScreen)).size() > 0)
				{
					WebElement fullScreenButton = driver.findElement(By.xpath(openFullScreen));
					JavascriptExecutor js = (JavascriptExecutor) driver;
					js.executeScript("arguments[0].scrollIntoView(true);", fullScreenButton);
					js.executeScript("arguments[0].click();", fullScreenButton); // Click to open fullscreen
					Thread.sleep(200); // Wait for the new tab to load
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
					if(driver.findElements(By.xpath(exitFullscreen)).size() > 0) 
                    {
                        WebElement exitFullScreenButton = driver.findElement(By.xpath(exitFullscreen));
                        js.executeScript("arguments[0].scrollIntoView(true);", exitFullScreenButton);
                        js.executeScript("arguments[0].click();", exitFullScreenButton); // Click to exit fullscreen
                        Thread.sleep(200); // Wait for the new tab to load
                    } 
				}
			} 
			driver.switchTo().defaultContent(); // Switch back to the main content
		}
		catch (Exception e) {
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> answerQuiz()
	{
		ArrayList<String> status = new ArrayList<String>();
		String quizPage = "//div[contains(@class,'vert vert-')]";
		String quizQuestionList = ".//div[contains(@aria-label,'Question')]//legend[@id][@class='response-fieldset-legend field-group-hd']";
		String quizAnswer = "./following-sibling::div[1]/input";
		String quizSubmitButton = ".//descendant::div[@class='action']//button[@data-should-enable-submit-button='True']";
		String frameSection = "//iframe[@id='unit-iframe']";
		try 
		{
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			
			if(driver.findElements(By.xpath(frameSection)).size() > 0)
			{
				WebElement frameLocator = driver.findElement(By.xpath(frameSection));
				driver.switchTo().frame(frameLocator);
				Thread.sleep(200); // Wait for the new tab to load
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
				if (driver.findElements(By.xpath(quizPage)).size() > 0)
				{
					List<WebElement> quizSections = driver.findElements(By.xpath(quizPage));
					for(WebElement quizSection : quizSections)
					{
						WebElement quizQuestion = quizSection; // Assuming 1 question per block
						Thread.sleep(200); // Wait for the new tab to load
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
						if(quizQuestion.findElements(By.xpath(quizQuestionList)).size()>0)
						{
							WebElement getEachQuestion = quizQuestion.findElement(By.xpath(quizQuestionList));
							WebElement markAnswer = getEachQuestion.findElement(By.xpath(quizAnswer));
							// Scroll and click answer using JS
							JavascriptExecutor js = (JavascriptExecutor) driver;
							js.executeScript("arguments[0].scrollIntoView(true);", markAnswer);
							js.executeScript("arguments[0].click();", markAnswer);
							Thread.sleep(200); // Wait for the new tab to load
							// Click Submit
							By submitAnswer = By.xpath(quizSubmitButton);
							status.addAll(this.clickWebElements(quizQuestion, submitAnswer));
							Thread.sleep(200); // Wait for the new tab to load
						}
						else
	                    {
	                        System.out.println("No quiz questions found in this section.");
	                        break;
	                    }
					}
	                
		          }
				else
				{
					System.out.println("Quiz page not found.");
				}
			} 
			else
			{
				System.out.println("Quiz frame not found, switching to default content.");
			}
			driver.switchTo().defaultContent();
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkProgressTab()
	{
		ArrayList<String> status = new ArrayList<String>();
		String progressTabLink = "//a[contains(text(),'Progress')]";
		String courseCompletionPercent = "//*[@class='donut-chart-number']";
		try 
		{
			status.add(homePage.clickWebElement(progressTabLink));
			status.add(registerPage.FocusWindow("progress"));
			if(driver.findElements(By.xpath(courseCompletionPercent)).size()>0)
			{
				WebElement courseCompletionPercentLocator = driver.findElement(By.xpath(courseCompletionPercent));
                String courseCompletionText = courseCompletionPercentLocator.getText();
                if (courseCompletionText.contains("0%")) 
                {
                    System.out.println("Course is not completed yet.");
                } 
                else 
                {
                    System.out.println("Course  progress: " + courseCompletionText);
                }
            } 
            else 
            {
                System.out.println("Course completion percentage not found.");
                status.add("Fail");
            }
			driver.navigate().back();
			driver.switchTo().window(courseContentPage);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	
	public ArrayList<String> openNotesTab()
	{
		ArrayList<String> status = new ArrayList<String>();
		String notesTabLink = "//a[contains(text(),'Notes')]";
		try
		{
			status.add(homePage.openLinkInNewTab(notesTabLink));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return 	status;
	}
	public ArrayList<String> openWikiTab()
	{
		ArrayList<String> status = new ArrayList<String>();
		String wikiTabLink = "//a[normalize-space()='Wiki']";
		try
		{
			status.add(homePage.openLinkInNewTab(wikiTabLink));
			driver.close();
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return 	status;
	}
	
	public ArrayList<String> enterTextOnField(String locator, String textToEnter)
	{
		ArrayList<String> status = new ArrayList<String>();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try
		{
		WebElement element = driver.findElement(By.xpath(locator));
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		if(element.isDisplayed()) 
        {
            element.clear();
            Thread.sleep(200); // wait for the element to be ready
            element.sendKeys(textToEnter);
        } 
        else 
        {
            System.out.println("Element is not displayed or enabled: " + locator);
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
	
	public ArrayList<String> checkDiscussionTabFromHomePage()
	{
		ArrayList<String> status = new ArrayList<String>();
		String discussionTabLink = "//a[normalize-space()='Discussion']";
		try {
			status.add(homePage.clickWebElement(discussionTabLink));
			status.add(registerPage.FocusWindow("discussion"));
		} catch (Exception e) {
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkAddPostButtonFromDiscussionHomePage()
	{
		ArrayList<String> status = new ArrayList<String>();
		String AddPostLink = "//button[normalize-space()='Add a post']";
		try {
			status.add(homePage.clickWebElement(AddPostLink));
		} catch (Exception e) {
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}

	public ArrayList<String> checkAddPostFromDisplayedFromDiscussionPage() {
		ArrayList<String> status = new ArrayList<String>();
		String checkPostForm = "//h4[normalize-space()='Add a post']";
		try {
			if(driver.findElements(By.xpath(checkPostForm)).size()>0)
			{
				System.out.println("Add post form is displayed");
			} else {
				System.out.println("Add post form is not displayed");
				status.add("Fail");
			}
		} catch (Exception e) {
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkCancelFromDiscussionPage()
	{
		ArrayList<String> status = new ArrayList<String>();
		String checkCancelButton = "//button[normalize-space()='Cancel']";
		try {
			
			status.add(homePage.clickWebElement(checkCancelButton));
		} catch (Exception e) {
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkSelectTopicAreaFromDiscussionPage()
	{
		ArrayList<String> status = new ArrayList<String>();
		String topicAreaSelectionFromDropdown = "//select[contains(@id,'form-field')]";
		try
		{
			status.addAll(this.checkAddPostButtonFromDiscussionHomePage());
			status.addAll(this.checkAddPostFromDisplayedFromDiscussionPage());
			Select topicAreaSelection = new Select(driver.findElement(By.xpath(topicAreaSelectionFromDropdown)));
			topicAreaSelection.selectByIndex(2);
		}
		catch (Exception e) {
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkPostFieldFromDiscussionPage() 
	{
		ArrayList<String> status = new ArrayList<String>();
		String enterPostTitle = "//input[@name='title'][contains(@id, 'form-field')]";
		try {
			status.addAll(this.enterTextOnField(enterPostTitle,"testing_discussion_post"));
		} catch (Exception e) {
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkParagraphFromDiscussionPage() {
		ArrayList<String> status = new ArrayList<String>();
		String frameToWriteMessage = "//body[@id='tinymce']";
		String focusFrameTextField = "//iframe[@title='Rich Text Area']";
		try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			if(driver.findElements(By.xpath(focusFrameTextField)).size()>0)
			{
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("post-editor-new_ifr")));
				status.addAll(this.enterTextOnField(frameToWriteMessage, "This is a test message for discussion post."));
			}
			driver.switchTo().defaultContent();
		} catch (Exception e) {
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkSubmitButtonDiscussionFromPage() {
		ArrayList<String> status = new ArrayList<String>();
		String checkSubmitButton = "//button[normalize-space()='Submit']";
		String conformPostPresentedOrNot = "//div[@role='list']//span[contains(text(),'just now')]";
		try {
			status.add(homePage.clickWebElement(checkSubmitButton));
			if(driver.findElements(By.xpath(conformPostPresentedOrNot)).size()>0)
			{
				System.out.println("Post is submitted successfully");
			} else {
				System.out.println("Post is not submitted");
				status.add("Fail");
			}
		} catch (Exception e) {
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkRecentActivityFromDiscussionPage() {
		ArrayList<String> status = new ArrayList<String>();
		String checkRecentActivity = "//span[contains(text(),'All  posts  sorted by recent activity')]";
		try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			if (driver.findElements(By.xpath(checkRecentActivity)).size() > 0)
			{
				System.out.println("Recent activity is available");
			} 
			else
			{
				System.out.println("Recent activity is not available");
				status.add("Fail");
			}
		} catch (Exception e) {
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkAllPostsFromDiscussionPage() {
		ArrayList<String> status = new ArrayList<String>();
		String clickAllPosts = "//a[contains(text(),'All posts')]";
		try {
			status.add(homePage.clickWebElement(clickAllPosts));
		} catch (Exception e) {
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkTopicsFromDiscussionPage() {
		ArrayList<String> status = new ArrayList<String>();
		String clickAllTopics = "//a[contains(text(),'Topics')]";
		try {
			status.add(homePage.clickWebElement(clickAllTopics));
		} catch (Exception e) {
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	
	public ArrayList<String> checkLearnersFromDiscussionPage()
	{
		ArrayList<String> status = new ArrayList<String>();
		String clickLearners = "//a[contains(text(),'Learners')]";
		try
		{
			status.add(homePage.clickWebElement(clickLearners));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkNotesTab()
	{
		ArrayList<String> status = new ArrayList<String>();
		String clickNotesTab = "//div[@id='courseTabsNavigation']//a[5]";
		try
		{
			status.add(homePage.clickWebElement(clickNotesTab));
			status.add(registerPage.FocusWindow("edxnotes"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkWikiTab()
	{
		ArrayList<String> status = new ArrayList<String>();
		String clickWikiTab = "//ol[@class='tabs course-tabs']/li[6]//a";
		try
		{
			status.add(homePage.clickWebElement(clickWikiTab));
			status.add(registerPage.FocusWindow("wiki"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkArticle()
	{
		ArrayList<String> status = new ArrayList<String>();
		String clickArticle = "//div[@class='global-functions pull-right']/a";
		try
		{
			status.add(homePage.clickWebElement(clickArticle));
			status.add(registerPage.FocusWindow("create"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkArticleFormBackButton()
	{
		ArrayList<String> status = new ArrayList<String>();
		String backButton = "//div[@class='form-actions']//a[@class='btn btn-large back']";
		try
		{
			status.add(homePage.clickWebElement(backButton));
			status.add(registerPage.FocusWindow("wiki"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	String WikiPae = ""; // Variable to store the wiki page window handle
	public ArrayList<String> checkArticleCreation()
	{
		ArrayList<String> status = new ArrayList<String>();
		String clickArticle = "//div[@class='global-functions pull-right']/a";
		String enterTitle = "//input[@id='id_title']";
		String enterSlug = "//input[@id='id_slug']";
		String enterContent = "//div[@class='CodeMirror-lines']";
		String enterSummary = "//input[@id='id_summary']";
		String submit = "//div[@class='form-actions']//button[@name='save_changes']";
		try
		{
			status.add(homePage.clickWebElement(clickArticle));
			status.add(registerPage.FocusWindow("create"));
			status.addAll(this.enterTextOnField(enterTitle, "testTitle"));
			status.addAll(this.enterTextOnField(enterSlug, "testSlug"));
			status.addAll(this.enterTextOnField(enterContent, "testCONTENT"));
			status.addAll(this.enterTextOnField(enterSummary, "testSummary"));
			status.add(homePage.clickWebElement(submit));
			status.add(registerPage.FocusWindow("wiki"));
			WikiPae = driver.getWindowHandle();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkViewArticleButton()
	{
		ArrayList<String> status = new ArrayList<String>();
		String viewButton = "//div[@class='article-functions']/ul/li[1]/a";
		try
		{
			status.add(homePage.openLinkInNewTab(viewButton));
			status.add(registerPage.FocusWindow("wiki"));
			driver.close(); // Close the article view tab
			driver.switchTo().window(WikiPae); // Switch back to the wiki page tab
		}
		catch(Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkEditArticleButton()
	{
		ArrayList<String> status = new ArrayList<String>();
		String viewButton = "//div[@class='article-functions']/ul/li[2]/a";
		String editContent = "//div[@class='CodeMirror-scroll']";
		String submitEditChanges = "//div[@class='form-actions']/button";
		try
		{
			status.add(homePage.openLinkInNewTab(viewButton));
			status.add(registerPage.FocusWindow("_edit"));
			status.addAll(this.enterTextOnField(editContent, "testEDITCONTENT"));
			status.add(homePage.clickWebElement(submitEditChanges));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}

	public ArrayList<String> checkAlertAfterModifyChanges() {
		ArrayList<String> status = new ArrayList<String>();
		String AlertText  = "//div[@id='alert_stat_bar']//*[contains(text(),'A new revision of the article was successfully added.')]";
		try 
		{
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			if(driver.findElements(By.xpath(AlertText)).size()>0)
			{
				System.out.println("Alert is displayed after modifying the article");
			}
			driver.close(); // Close the article view tab
			driver.switchTo().window(WikiPae); // Switch back to the wiki page tab
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkChangesArticleButton()
	{
		ArrayList<String> status = new ArrayList<String>();
		String viewButton = "//div[@class='article-functions']/ul/li[3]/a";
		try
		{
			status.add(homePage.openLinkInNewTab(viewButton));
			status.add(registerPage.FocusWindow("history"));
			driver.close(); // Close the article view tab
			driver.switchTo().window(WikiPae); 
		}
		catch(Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	String discussionPage = "";// Variable to store the discussion page window handle
	public ArrayList<String> checkAbleToOpenUserDropdown()
	{
		ArrayList<String> status = new ArrayList<String>();
		String userDropdown = "//header//div[@data-testid='dropdown']/button";
		try
		{
			discussionPage = driver.getWindowHandle();
			status.add(homePage.clickWebElement(userDropdown));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkCoursesOptionFromDropdown()
	{
		ArrayList<String> status = new ArrayList<String>();
		String courseFromUserDropdown = "//div[@class='dropdown-menu-right dropdown-menu show']//a[contains(text(),'My Courses')]";
		try
		{
			status.add(homePage.openLinkInNewTab(courseFromUserDropdown));
			status.add(registerPage.FocusWindow("dashboard"));
			Thread.sleep(200); // Wait for the new tab to load
			driver.close();
			driver.switchTo().window(discussionPage);
			Thread.sleep(200); 
		}
		catch(Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkDiscoverOptionFromDropdown()
	{
		ArrayList<String> status = new ArrayList<String>();
		String discoverFromUserDropdown = "//div[@class='dropdown-menu-right dropdown-menu show']//a[contains(text(),'Discover')]";
		try
		{
			status.add(homePage.openLinkInNewTab(discoverFromUserDropdown));
			status.add(registerPage.FocusWindow("courses"));
			Thread.sleep(200); // Wait for the new tab to load
			driver.close();
			driver.switchTo().window(discussionPage);
			Thread.sleep(200); 
		}
		catch(Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkDashboardOptionFromDropdown()
	{
		ArrayList<String> status = new ArrayList<String>();
		String discoverFromUserDropdown = "//div[@class='dropdown-menu-right dropdown-menu show']//a[contains(text(),'Dashboard')]";
		try
		{
			status.add(homePage.openLinkInNewTab(discoverFromUserDropdown));
			status.add(registerPage.FocusWindow("courses"));
			Thread.sleep(200); // Wait for the new tab to load
			driver.close();
			driver.switchTo().window(discussionPage);
			Thread.sleep(200); 
		}
		catch(Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkProfileOptionsFromUserDropdown()
	{
		ArrayList<String> status = new ArrayList<String>();
		String ProfileFromUserDropdown = "//div[@class='dropdown-menu-right dropdown-menu show']//a[contains(text(),'Profile')]";		
		try
		{
			status.add(homePage.openLinkInNewTab(ProfileFromUserDropdown));
			status.add(registerPage.FocusWindow("profile"));
			Thread.sleep(200); 
		}
		catch (Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	String focusLearnerRecod ="";
	public ArrayList<String> checkViewRecordFromProfilePage()
	{
		ArrayList<String> status = new ArrayList<String>();
		String clickViewRecord = "//div[@class='container-fluid']/div[2]/descendant::a[contains(text(),'View My Records')]";		
		try
		{
			Thread.sleep(200); 
			status.add(homePage.clickWebElement(clickViewRecord));
			status.add(registerPage.FocusWindow("learner-record"));
			 focusLearnerRecod = driver.getWindowHandle();
			status.add(registerPage.FocusWindow("profile"));
			driver.close(); // Close the learner record tab
			driver.switchTo().window(focusLearnerRecod); // Switch back to the learner record tab
			Thread.sleep(200); 
		}
		catch (Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkLinkFromRecordPage()
	{
		ArrayList<String> status = new ArrayList<String>();
		String clickLinkFromRecordPage = "//a[@class='pgn__hyperlink default-link standalone-link']";		
		try
		{
			status.add(homePage.clickWebElement(clickLinkFromRecordPage));
			status.add(registerPage.FocusWindow("learner-record"));
			Thread.sleep(200); 
		}
		catch (Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkBackToProfileIcon()
	{
		ArrayList<String> status = new ArrayList<String>();
		String clickBackToProfile = "//a[contains(text(),'Back to My Profile')]";		
		try
		{
			status.add(homePage.clickWebElement(clickBackToProfile));
			status.add(registerPage.FocusWindow("profile"));
			
			Thread.sleep(200); 
		}
		catch (Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkDOBLink()
	{
		ArrayList<String> status = new ArrayList<String>();
		String clickDOBLink = "//a[contains(text(),'Set your date of birth')]";		
		try
		{
			status.add(homePage.clickWebElement(clickDOBLink));
			status.add(registerPage.FocusWindow("account"));
			Thread.sleep(200); 
		}
		catch (Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkEditFullnameFromAccountInformation()
	{
		ArrayList<String> status = new ArrayList<String>();
		String clickAccountSection = "//ul[@class='list-unstyled']/li[1]/a";
		String editFullname = "//div[@id='basic-information']/div[2]//button";
		try
		{
			status.add(homePage.clickWebElement(clickAccountSection));
			Thread.sleep(200); 
			status.add(homePage.clickWebElement(editFullname));
			Thread.sleep(200); 
		}
		catch (Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkcancelFromEditFullnameInAccountInformation()
	{
		ArrayList<String> status = new ArrayList<String>();
		String clickCancelButton = "//div[@id='basic-information']/div[2]//button[contains(text(),'Cancel')]";
		try
		{
			status.add(homePage.clickWebElement(clickCancelButton));
			Thread.sleep(200); 
		}
		catch (Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkSaveFromEditFullnameInAccountInformation()
	{
		ArrayList<String> status = new ArrayList<String>();
		String clickAccountSection = "//ul[@class='list-unstyled']/li[1]/a";
		String editFullname = "//div[@id='basic-information']/div[2]//button";
		String focusFullnameField = "//div[@id='basic-information']/div[2]//input[@id='field-name']";
		String clickSaveButton = "//div[@id='basic-information']/div[2]//button[@type='submit']";
		
		try
		{
			status.add(homePage.clickWebElement(clickAccountSection));
			Thread.sleep(200); 
			status.add(homePage.clickWebElement(editFullname));
			Thread.sleep(200); 
			status.addAll(this.enterTextOnField(focusFullnameField, "Test User"));
			Thread.sleep(500); 
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			if(driver.findElements(By.xpath(clickSaveButton)).size() > 0)
			{
				status.add(homePage.clickWebElement(clickSaveButton));
				Thread.sleep(200); 
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkEditBirthFromAccountInformation()
	{
		ArrayList<String> status = new ArrayList<String>();
		String clickAccountSection = "//ul[@class='list-unstyled']/li[1]/a";
		String editBirth = "//div[@id='basic-information']/div[5]//button";
		try
		{
			status.add(homePage.clickWebElement(clickAccountSection));
			Thread.sleep(200); 
			status.add(homePage.clickWebElement(editBirth));
			Thread.sleep(200); 
		}
		catch (Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkcancelFromBirthInAccountInformation()
	{
		ArrayList<String> status = new ArrayList<String>();
		String clickCancelButton = "//div[@id='basic-information']/div[5]//button[contains(text(),'Cancel')]";
		try
		{
			status.add(homePage.clickWebElement(clickCancelButton));
			Thread.sleep(200); 
		}
		catch (Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkSaveFromBirthInAccountInformation()
	{
		ArrayList<String> status = new ArrayList<String>();
		String clickAccountSection = "//ul[@class='list-unstyled']/li[1]/a";
		String editBirth = "//div[@id='basic-information']/div[5]//button";
		String selectBirthField = "//div[@id='basic-information']/div[5]//select[@id='field-year_of_birth']";
		String clickSaveButton = "//div[@id='basic-information']/div[5]//button[@type='submit']";
		try
		{
			status.add(homePage.clickWebElement(clickAccountSection));
			Thread.sleep(200); 
			status.add(homePage.clickWebElement(editBirth));
			Thread.sleep(200); 
			WebElement dropdown = driver.findElement(By.xpath(selectBirthField));
			Select select = new Select(dropdown);
			select.selectByIndex(1);
			Thread.sleep(200); 
			status.add(homePage.clickWebElement(clickSaveButton));
			Thread.sleep(200); 
		}
		catch (Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkEditCountryFromAccountInformation()
	{
		ArrayList<String> status = new ArrayList<String>();
		String clickAccountSection = "//ul[@class='list-unstyled']/li[1]/a";
		String editCountry = "//div[@id='basic-information']/div[6]//button";
		try
		{
			status.add(homePage.clickWebElement(clickAccountSection));
			Thread.sleep(200); 
			status.add(homePage.clickWebElement(editCountry));
			Thread.sleep(200); 
		}
		catch (Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkcancelFromCountryInAccountInformation()
	{
		ArrayList<String> status = new ArrayList<String>();
		String clickCancelButton = "//div[@id='basic-information']/div[6]//button[contains(text(),'Cancel')]";
		try
		{
			status.add(homePage.clickWebElement(clickCancelButton));
			Thread.sleep(200); 
		}
		catch (Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkSaveFromCountryInAccountInformation()
	{
		ArrayList<String> status = new ArrayList<String>();
		String clickAccountSection = "//ul[@class='list-unstyled']/li[1]/a";
		String editBirth = "//div[@id='basic-information']/div[6]//button";
		String selectBirthField = "//div[@id='basic-information']/div[6]//select[@id='field-country']";
		String clickSaveButton = "//div[@id='basic-information']/div[6]//button[@type='submit']";
		try
		{
			status.add(homePage.clickWebElement(clickAccountSection));
			Thread.sleep(200); 
			status.add(homePage.clickWebElement(editBirth));
			Thread.sleep(200); 
			WebElement dropdown = driver.findElement(By.xpath(selectBirthField));
			Select select = new Select(dropdown);
			select.selectByIndex(1);
			Thread.sleep(200); 
			status.add(homePage.clickWebElement(clickSaveButton));
			Thread.sleep(200); 
		}
		catch (Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkEducationFromProfileInformation()
	{
		ArrayList<String> status = new ArrayList<String>();
		String clickProfileSection = "//ul[@class='list-unstyled']/li[2]/a";
		String editEducation = "//div[@id='profile-information']/div[1]//button";
		try
		{
			
			status.add(homePage.clickWebElement(clickProfileSection));
			Thread.sleep(200); 
			status.add(homePage.clickWebElement(editEducation));
			Thread.sleep(200); 
		}
		catch (Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkEducationToCancelFromProfileInformation()
	{
		ArrayList<String> status = new ArrayList<String>();
		String clickCancelButton = "//div[@id='profile-information']/div[1]//button[contains(text(),'Cancel')]";
		try
		{
			status.add(homePage.clickWebElement(clickCancelButton));
			Thread.sleep(200); 
		}
		catch (Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkEducationToSaveFromProfileInformation()
	{
		ArrayList<String> status = new ArrayList<String>();
		String clickProfileSection = "//ul[@class='list-unstyled']/li[2]/a";
		String editEducation = "//div[@id='profile-information']/div[1]//button";
		String selectLevelOfEdu = "//div[@id='profile-information']/div[1]//select[@id='field-level_of_education']";
		String clickSaveButton = "//div[@id='profile-information']/div[1]//button[@type='submit']";
		try
		{
			status.add(homePage.clickWebElement(clickProfileSection));
			Thread.sleep(200); 
			status.add(homePage.clickWebElement(editEducation));
			Thread.sleep(200); 
			WebElement dropdown = driver.findElement(By.xpath(selectLevelOfEdu));
			
			Select select = new Select(dropdown);
			select.selectByIndex(1);
			Thread.sleep(200); 
			status.add(homePage.clickWebElement(clickSaveButton));
			Thread.sleep(200); 
		}
		catch (Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkGenderFromProfileInformation()
	{
		ArrayList<String> status = new ArrayList<String>();
		String editGender = "//div[@id='profile-information']/div[2]//button";
		try
		{
			status.add(homePage.clickWebElement(editGender));
			Thread.sleep(200); 
		}
		catch (Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkGenderToCancelFromProfileInformation()
	{
		ArrayList<String> status = new ArrayList<String>();
		String clickCancelButton = "//div[@id='profile-information']/div[2]//button[contains(text(),'Cancel')]";
		try
		{
			status.add(homePage.clickWebElement(clickCancelButton));
			Thread.sleep(200); 
		}
		catch (Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	
	public ArrayList<String> checkGenderToSaveFromProfileInformation()
	{
		ArrayList<String> status = new ArrayList<String>();
		String clickProfileSection = "//ul[@class='list-unstyled']/li[2]/a";
		String editGender = "//div[@id='profile-information']/div[2]//button";
		String selectLevelOfGender = "//div[@id='profile-information']/div[2]//select[@id='field-gender']";
		String clickSaveButton = "//div[@id='profile-information']/div[2]//button[@type='submit']";
		try
		{
			status.add(homePage.clickWebElement(clickProfileSection));
			Thread.sleep(200); 
			status.add(homePage.clickWebElement(editGender));
			Thread.sleep(200); 
			Select selectEdu = new Select(driver.findElement(By.xpath(selectLevelOfGender)));
			selectEdu.selectByVisibleText("Female");
			Thread.sleep(200); 
			status.add(homePage.clickWebElement(clickSaveButton));
			Thread.sleep(200); 
		}
		catch (Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkSpokenlanguageFromProfileInformation()
	{
		ArrayList<String> status = new ArrayList<String>();
		String clickProfileSection = "//ul[@class='list-unstyled']/li[2]/a";
		String editSpokenLanguage = "//div[@id='profile-information']/div[3]//button";
		try
		{
			status.add(homePage.clickWebElement(clickProfileSection));
			Thread.sleep(200); 
			status.add(homePage.clickWebElement(editSpokenLanguage));
			Thread.sleep(200); 
		}
		catch (Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkCancelInSpokenlanguageFromProfileInformation()
	{
		ArrayList<String> status = new ArrayList<String>();
		String clickCancelButton = "//div[@id='profile-information']/div[3]//button[contains(text(),'Cancel')]";
		try
		{
			status.add(homePage.clickWebElement(clickCancelButton));
			Thread.sleep(200); 
		}
		catch (Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkSaveInSpokenlanguageFromProfileInformation()
	{
		ArrayList<String> status = new ArrayList<String>();
		String clickProfileSection = "//ul[@class='list-unstyled']/li[2]/a";
		String editSpokenLanguage = "//div[@id='profile-information']/div[3]//button";
		String selectSpokenLanguage = "//div[@id='profile-information']/div[3]//select[@id='field-language_proficiencies']";
		String clickSaveButton = "//div[@id='profile-information']/div[3]//button[@type='submit']";
		try
		{
			status.add(homePage.clickWebElement(clickProfileSection));
			Thread.sleep(200);
			status.add(homePage.clickWebElement(editSpokenLanguage));
			Thread.sleep(200); 
			Select selectEdu = new Select(driver.findElement(By.xpath(selectSpokenLanguage)));
			selectEdu.selectByIndex(3);
			Thread.sleep(200); 
			status.add(homePage.clickWebElement(clickSaveButton));
			Thread.sleep(200); 
		}
		catch (Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkSocialMediaLinks()
	{
		ArrayList<String> status = new ArrayList<String>();
		String clickSocialLinksSection = "//ul[@class='list-unstyled']/li[3]/a";
		try
		{
			status.add(homePage.clickWebElement(clickSocialLinksSection));
			Thread.sleep(200); 
		}
		catch (Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkLinkedInFromSocialMediaLinks()
	{
		ArrayList<String> status = new ArrayList<String>();
		String editLinkedIn = "//div[@id='social-media']/div[1]//button";
		try
		{
			status.add(homePage.clickWebElement(editLinkedIn));
			Thread.sleep(200); 
		}
		catch (Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkLinkedInToCancelFromSocialMediaLinks()
	{
		ArrayList<String> status = new ArrayList<String>();
		String clickCancelButton = "//div[@id='social-media']/div[1]//button[contains(text(),'Cancel')]";
		try
		{
			status.add(homePage.clickWebElement(clickCancelButton));
			Thread.sleep(200); 
		}
		catch (Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkLinkedInToSaveFromSocialMediaLinks()
	{
		ArrayList<String> status = new ArrayList<String>();
		String clickSocialLinksSection = "//ul[@class='list-unstyled']/li[3]/a";
		String editLinkedIn = "//div[@id='social-media']/div[1]//button";
		String enterLinkedInField = "//div[@id='social-media']/div[1]//input[@id='field-social_link_linkedin']";
		String clickSaveButton = "//div[@id='social-media']/div[1]//button[@type='submit']";
		try
		{
			status.add(homePage.clickWebElement(clickSocialLinksSection));
			Thread.sleep(200); 
			status.add(homePage.clickWebElement(editLinkedIn));
			Thread.sleep(200); 
			status.addAll(this.enterTextOnField(enterLinkedInField, "test"));
			Thread.sleep(200); 
			status.add(homePage.clickWebElement(clickSaveButton));
			Thread.sleep(200); 
		}
		catch (Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkFacebookFromSocialMediaLinks()
	{
		ArrayList<String> status = new ArrayList<String>();
		String clickSocialLinksSection = "//ul[@class='list-unstyled']/li[3]/a";
		String editFacebook = "//div[@id='social-media']/div[2]//button";
		try
		{
			status.add(homePage.clickWebElement(clickSocialLinksSection));
			Thread.sleep(200); 
			status.add(homePage.clickWebElement(editFacebook));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkFacebookToCancelFromSocialMediaLinks()
	{
		ArrayList<String> status = new ArrayList<String>();
		String clickCancelButton = "//div[@id='social-media']/div[2]//button[contains(text(),'Cancel')]";
		try
		{
			Thread.sleep(200); 
			status.add(homePage.clickWebElement(clickCancelButton));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkFacebookToSaveFromSocialMediaLinks()
	{
		ArrayList<String> status = new ArrayList<String>();
		String clickSocialLinksSection = "//ul[@class='list-unstyled']/li[3]/a";
		String editFacebook = "//div[@id='social-media']/div[2]//button";
		String enterFacebookField = "//div[@id='social-media']/div[2]//input[@id='field-social_link_facebook']";
		String clickSaveButton = "//div[@id='social-media']/div[2]//button[@type='submit']";
		try
		{
			status.add(homePage.clickWebElement(clickSocialLinksSection));
			Thread.sleep(200); 
			status.add(homePage.clickWebElement(editFacebook));
			Thread.sleep(500); 
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
			if(driver.findElements(By.xpath(enterFacebookField)).size()>0)
			{
				status.addAll(this.enterTextOnField(enterFacebookField, "test"));
			}
			Thread.sleep(200); 
			status.add(homePage.clickWebElement(clickSaveButton));
			Thread.sleep(200); 
		}
		catch (Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkTwitterFromSocialMediaLinks()
	{
		ArrayList<String> status = new ArrayList<String>();
		String clickSocialLinksSection = "//ul[@class='list-unstyled']/li[3]/a";
		String editTwitter = "//div[@id='social-media']/div[3]//button";
		try
		{
			status.add(homePage.clickWebElement(clickSocialLinksSection));
			Thread.sleep(200);
			status.add(homePage.clickWebElement(editTwitter));
			Thread.sleep(200); 
		}
		catch (Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkTwitterToCancelFromSocialMediaLinks()
	{
		ArrayList<String> status = new ArrayList<String>();
		String clickCancelButton = "//div[@id='social-media']/div[3]//button[contains(text(),'Cancel')]";
		try
		{
			status.add(homePage.clickWebElement(clickCancelButton));
			Thread.sleep(200); 
		}
		catch (Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkTwitterToSaveFromSocialMediaLinks()
	{
		ArrayList<String> status = new ArrayList<String>();
		String clickSocialLinksSection = "//ul[@class='list-unstyled']/li[3]/a";
		String editTwitter = "//div[@id='social-media']/div[3]//button";
		String enterTwitterField = "//div[@id='social-media']/div[3]//input[@id='field-social_link_twitter']";
		String clickSaveButton = "//div[@id='social-media']/div[3]//button[@type='submit']";
		try
		{
			status.add(homePage.clickWebElement(clickSocialLinksSection));
			Thread.sleep(200);
			status.add(homePage.clickWebElement(editTwitter));
			Thread.sleep(200); 
			status.addAll(this.enterTextOnField(enterTwitterField, "test"));
			status.add(homePage.clickWebElement(clickSaveButton));
			Thread.sleep(200); 
		}
		catch (Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkSitePreference()
	{
		ArrayList<String> status = new ArrayList<String>();
		String clickSitePreference = "//ul[@class='list-unstyled']/li[4]/a";
		try
		{
			status.add(homePage.clickWebElement(clickSitePreference));
		}
		catch (Exception e) {
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}

	public ArrayList<String> checkTimeZone()
	{
		ArrayList<String> status = new ArrayList<String>();
		String editTimeZone = "//div[@id='site-preferences']//div[2]//button";
		String selectTimeZone = "//div[@id='site-preferences']//div[2]//select[@id='field-time_zone']";
		String submit = "//div[@id='site-preferences']//div[2]//button[@type='submit']";
		try 
		{
			status.add(homePage.clickWebElement(editTimeZone));
			Select selectEdu = new Select(driver.findElement(By.xpath(selectTimeZone)));
			selectEdu.selectByIndex(3);
			Thread.sleep(200); 
			status.add(homePage.clickWebElement(submit));
		} 
		catch (Exception e) {
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkLinkedAccounts()
	{
		ArrayList<String> status = new ArrayList<String>();
		String editLinkedAccounts = "//ul[@class='list-unstyled']/li[5]/a";
		String clickGoogleLink = "//div[@id='linked-accounts']//div[1]/a";
		
		try
		{
			status.add(homePage.clickWebElement(editLinkedAccounts));
			Thread.sleep(200); 
			status.add(homePage.openLinkInNewTab(clickGoogleLink));
			Thread.sleep(200); 
			status.add(registerPage.FocusWindow("account"));
			Thread.sleep(100); // Wait for Google sign-in page to load
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.close();
			Thread.sleep(200); 
			driver.switchTo().window(discussionPage);
			Thread.sleep(200); 
		}
		catch (Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		
		return status;
	}
	
	public ArrayList<String> checkOrderHistory()
	{
		ArrayList<String> status = new ArrayList<String>();
		String clickOrderHistoryFromUserDropdown = "//a[contains(text(),'Order History')]";
		try
		{
			status.add(homePage.openLinkInNewTab(clickOrderHistoryFromUserDropdown));
			Thread.sleep(200); 
			status.add(registerPage.FocusWindow("orders"));
			Thread.sleep(200); 
			
		}
		catch (Exception e) {
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkSupportLinkFromOrderHistory()
	{
		ArrayList<String> status = new ArrayList<String>();
		String clickSupportLink = "//a[contains(text(),'contact support')]";
		try
		{
			status.add(homePage.clickWebElement(clickSupportLink));
			Thread.sleep(200); 
			status.add(registerPage.FocusWindow("requests"));
			Thread.sleep(200); 
			driver.close();
			driver.switchTo().window(discussionPage);
		}
		catch (Exception e) {
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkSignOutProcess()
	{
		ArrayList<String> status = new ArrayList<String>();
		String signOutButton = "//a[contains(text(),'Sign Out')]";
		try
		{
			if (driver.findElements(By.xpath(signOutButton)).size() > 0) 
			{
				status.add(homePage.clickWebElement(signOutButton));
				Thread.sleep(200); 
				status.add(registerPage.FocusWindow("logout"));
				Thread.sleep(200); 
			} 
		}
		catch (Exception e) {
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkEnrollCourseWithoutSignIn(String courseName)
	{
		ArrayList<String> status = new ArrayList<String>();
		String clickHomePageLink = "//a[contains(text(),'click here to go to the home page')]";
		String listOfCourses = "//ul[@class='courses-listing']/li";
		String enrollButton = "//a[@class='register']";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try
		{
			status.add(homePage.openLinkInNewTab(clickHomePageLink));
			Thread.sleep(200); 
			status.add(registerPage.FocusWindow("stagecourses.skillup.online"));
			Thread.sleep(200); 
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			if (driver.findElements(By.xpath(listOfCourses)).size() > 0)
			{
				System.out.println("List of courses is displayed");
				List<WebElement> courses = driver.findElements(By.xpath(listOfCourses));
				for (WebElement course : courses) 
				{
					WebElement locateCardTitle = course.findElement(By.xpath(".//h2/span[2]"));
					String courseCardName = locateCardTitle.getText();
					if(courseCardName.equalsIgnoreCase(courseName))
					{
						WebElement clickLearnMoreButton = course.findElement(By.xpath("./following-sibling::a[contains(text(),'Learn More')]"));
						js.executeScript("arguments[0].scrollIntoView(true);", clickLearnMoreButton);
						js.executeScript("arguments[0].click();", clickLearnMoreButton);
						status.add(registerPage.FocusWindow("/about"));
						Thread.sleep(200); // Wait for the new tab to load
						status.add(homePage.clickWebElement(enrollButton));
						Thread.sleep(200); 
						status.add(registerPage.FocusWindow("login?"));
					}
				}
			} 
			else 
			{
				System.out.println("List of courses is not displayed");
				status.add("Fail");
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
}
