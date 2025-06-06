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
			status.add(homePage.clickWebElement(clickFindCourse));
			 
			status.add(registerPage.FocusWindow("/courses"));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	
	public ArrayList<String> checkCardSelection(ArrayList<String> data)
	{
		ArrayList<String> status = new ArrayList<String>();
		String courseCardList = "//ul[@class='courses-listing courses-list']/li";
		String courseCardTitle = ".//span[@class='course-title']";
		String courseCardOrg = ".//span[@class='course-organization']";
		String courseCardButton = ".//a[@class='learn-more']";
		String courseCardDate = ".//div[@class='course-date']";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		try
		{
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			
			if(driver.findElements(By.xpath(courseCardList)).size()>0)
			{
				List<WebElement> courseCards = driver.findElements(By.xpath(courseCardList));
				for(WebElement card : courseCards)
				{
					js.executeScript("arguments[0].scrollIntoView(true);", card);
					wait.until(ExpectedConditions.visibilityOf(card));
					WebElement courseCard = card.findElement(By.xpath(courseCardTitle));
					js.executeScript("arguments[0].scrollIntoView(true);", courseCard);
					wait.until(ExpectedConditions.visibilityOf(courseCard));
					courseCardTitleText = courseCard.getText();
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
					if(courseCardTitleText.equals(data.get(1)))
                    {
						WebElement courseCardOrganization = card.findElement(By.xpath(courseCardOrg));
						js.executeScript("arguments[0].scrollIntoView(true);", courseCardOrganization);
						wait.until(ExpectedConditions.visibilityOf(courseCardOrganization));
						courseCardOrganizationText = courseCardOrganization.getText();
						WebElement courseCardDateLocate = card.findElement(By.xpath(courseCardDate));
						js.executeScript("arguments[0].scrollIntoView(true);", courseCardDateLocate);
						wait.until(ExpectedConditions.visibilityOf(courseCardDateLocate));
						String courseCardDateText[] = courseCardDateLocate.getText().split("Starts:");
						courseCardDateTextValue = courseCardDateText[1];
						WebElement  locateCard = card.findElement(By.xpath(courseCardButton));
						js.executeScript("arguments[0].scrollIntoView(true);", locateCard);
						wait.until(ExpectedConditions.visibilityOf(locateCard));
						 wait.until(ExpectedConditions.elementToBeClickable(locateCard));
						js.executeScript("arguments[0].click();", locateCard);
					 
						status.add(registerPage.FocusWindow("about"));
						
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
	
	
	public ArrayList<String> CourseCardSelection_EnrollCard(ArrayList<String> data)
	{
		ArrayList<String> status = new ArrayList<String>();
		String clickFindCourse = "//a[contains(text(),'Find a course ')]";
		String courseCardList = "//ul[@class='courses-listing courses-list']/li";
		String courseCardTitle = ".//span[@class='course-title']";
		String courseCardOrg = ".//span[@class='course-organization']";
		String courseCardButton = ".//a[@class='learn-more']";
		String courseCardDate = ".//div[@class='course-date']";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		try 
		{
			
			status.add(homePage.clickWebElement(clickFindCourse));
		 
			status.add(registerPage.FocusWindow("/courses"));
			
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			
			if(driver.findElements(By.xpath(courseCardList)).size()>0)
			{
				List<WebElement> courseCards = driver.findElements(By.xpath(courseCardList));
				for(WebElement card : courseCards)
				{
					js.executeScript("arguments[0].scrollIntoView(true);", card);
					wait.until(ExpectedConditions.visibilityOf(card));
					WebElement courseCard = card.findElement(By.xpath(courseCardTitle));
					js.executeScript("arguments[0].scrollIntoView(true);", courseCard);
					wait.until(ExpectedConditions.visibilityOf(courseCard));
					courseCardTitleText = courseCard.getText();
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
					if(courseCardTitleText.equals(data.get(1)))
                    {
						WebElement courseCardOrganization = card.findElement(By.xpath(courseCardOrg));
						js.executeScript("arguments[0].scrollIntoView(true);", courseCardOrganization);
						wait.until(ExpectedConditions.visibilityOf(courseCardOrganization));
						courseCardOrganizationText = courseCardOrganization.getText();
						WebElement courseCardDateLocate = card.findElement(By.xpath(courseCardDate));
						js.executeScript("arguments[0].scrollIntoView(true);", courseCardDateLocate);
						wait.until(ExpectedConditions.visibilityOf(courseCardDateLocate));
						String courseCardDateText[] = courseCardDateLocate.getText().split("Starts:");
						courseCardDateTextValue = courseCardDateText[1];
						WebElement  locateCard = card.findElement(By.xpath(courseCardButton));
						js.executeScript("arguments[0].scrollIntoView(true);", locateCard);
						wait.until(ExpectedConditions.visibilityOf(locateCard));
						 wait.until(ExpectedConditions.elementToBeClickable(locateCard));
						js.executeScript("arguments[0].click();", locateCard);
					 
						status.add(registerPage.FocusWindow("about"));
						
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
			} else {
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
			/*
			 * this.registerPage.FocusWindow("mail"); driver.close();
			 */
			driver.switchTo().window(summaryWindow);
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
			status.add(this.checkWebElementComparision(locateAlertText, checkAlertText));
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
			status.add(homePage.clickWebElement(neverButton));
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
		String skipsurveyButton = "//button[contains(text(),'Skip survey')]";
		try
		{
			status.add(homePage.clickWebElement(settingsLink));
			status.add(homePage.clickWebElement(unenrollButton));
			status.add(homePage.clickWebElement(skipsurveyButton));
			
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
			status.add(registerPage.FocusWindow("learner-dashboard/"));
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
		try
		{
			status.addAll(this.CourseCardSelection_EnrollCard(data));
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
		String settingsLink = "//div[@data-testid='CourseCard'][1]//span[@class='pgn__icon btn-icon__icon']";
		String unenrollButtonFromSettingsOption = "//a[contains(text(),'Unenroll')]";
		String locateAlertTextFromUnEnroll = "//div[@class='bg-white p-3 rounded']/h4";
		String selectReasonToUnEnroll = "//div[@role='radiogroup']/div//input[1]";
		String submitSurveyButton = "//button[contains(text(),'Submit reason')]";
		String AlertTextFromUnEnroll = "What's your main reason for unenrolling?";
		try
		{
			status.add(homePage.clickWebElement(settingsLink));
			status.add(homePage.clickWebElement(unenrollButtonFromSettingsOption));
			if(driver.findElements(By.xpath(locateAlertTextFromUnEnroll)).size() > 0)
			{
				status.add(this.checkWebElementComparision(locateAlertTextFromUnEnroll, AlertTextFromUnEnroll));
			}
			else
			{
				status.add("Fail");
			}
			status.add(homePage.clickWebElement(selectReasonToUnEnroll));
			status.add(homePage.clickWebElement(submitSurveyButton));
			
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
			status.addAll(this.CourseCardSelection_EnrollCard(data));
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
		 
			status.add(registerPage.FocusWindow("/courses"));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
			if(driver.findElements(By.xpath(courseCardList)).size()>0)
			{
				List<WebElement> courseCards = driver.findElements(By.xpath(courseCardList));
				for(WebElement card : courseCards)
				{
					
					js.executeScript("arguments[0].scrollIntoView(true);", card);
					wait.until(ExpectedConditions.visibilityOf(card));
					WebElement courseCodeLocator = card.findElement(By.xpath(courseCode));
					js.executeScript("arguments[0].scrollIntoView(true);", courseCodeLocator);
					String courseCodeText = courseCodeLocator.getAttribute("textContent");
					System.out.println(courseCodeText);
					WebElement courseCard = card.findElement(By.xpath(courseCardTitle));
					 js.executeScript("arguments[0].scrollIntoView(true);", courseCard);
			            wait.until(ExpectedConditions.visibilityOf(courseCard));
					courseCardTitleText = courseCard.getText();
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
					if(courseCardTitleText.equals(data.get(1))&&courseCodeText.equals(data.get(2)))
                    {
						WebElement courseCardOrganization = card.findElement(By.xpath(courseCardOrg));
						 js.executeScript("arguments[0].scrollIntoView(true);", courseCardOrganization);
				            wait.until(ExpectedConditions.visibilityOf(courseCardOrganization));
						courseCardOrganizationText = courseCardOrganization.getText();
						WebElement courseCardDateLocate = card.findElement(By.xpath(courseCardDate));
						String courseCardDateText[] = courseCardDateLocate.getText().split("Starts:");
						courseCardDateTextValue = courseCardDateText[1];
						
						WebElement  locateCard = card.findElement(By.xpath(courseCardButton));
						
						js.executeScript("arguments[0].scrollIntoView(true);", locateCard);
						wait.until(ExpectedConditions.visibilityOf(locateCard));
		                wait.until(ExpectedConditions.elementToBeClickable(locateCard));
						js.executeScript("arguments[0].click();", locateCard);
					 
						status.add(registerPage.FocusWindow("about"));
						
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
			if (enrollButton.isDisplayed()) {
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
		String expandAllLink = "//button[contains(text(),'Expand all')]";
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
	
	public ArrayList<String> checkStartCourseButton(String courseCardTitleText) 
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
		try
		{
			status.add(homePage.clickWebElement(bookMarksLink));
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
		try
		{
			status.add(homePage.clickWebElement(clickBookmarkedLink));
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkPageIsNavigatoTOBackward()
	{
		ArrayList<String> status = new ArrayList<String>();
		try
		{
			driver.navigate().back();
			status.add(registerPage.FocusWindow("/home"));
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkPageIsBookmarkedOrNot()
	{
		ArrayList<String> status = new ArrayList<String>();
		String clickBookmarkLinkFromHome = "//a[normalize-space()='Bookmarks']";
		try
		{
			status.add(homePage.clickWebElement(clickBookmarkLinkFromHome));
			status.add(registerPage.FocusWindow("bookmarks/"));
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
		String clickViewbutton = "//span[normalize-space()='View']";
		try {
			status.add(homePage.clickWebElement(clickViewbutton));
			status.add(registerPage.FocusWindow("course-"));
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
			WebElement focusFrameLocator = driver.findElement(By.xpath(DiscussionFrame));
			js.executeScript("arguments[0].scrollIntoView(true);", focusFrameLocator);
			 wait.until(ExpectedConditions.visibilityOf(focusFrameLocator));
			driver.switchTo().frame(focusFrameLocator);
			
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
			driver.switchTo().defaultContent(); // Switch back to the main content after closing the discussion tray
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
			status.add(homePage.clickWebElement(launchTourLink));
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
			} else {
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
	public ArrayList<String> checkDismissFromLaunchTourAlert()
	{
		ArrayList<String> status = new ArrayList<String>();
		String clickDismiss = "//button[contains(text(),'Dismiss')]";
		try 
		{
			status.add(homePage.clickWebElement(clickDismiss));
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
			status.add(homePage.clickWebElement(clickOkay));
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
			status.add(homePage.clickWebElement(launchTourLink));
			while (driver.findElements(By.xpath(clickNext)).size() > 0) 
			{
				status.add(homePage.clickWebElement(clickNext));
			}
			status.add(homePage.clickWebElement(clickOkay));
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
	public ArrayList<String> startCourse()
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
			while(driver.findElements(By.xpath(nextButton)).size()>0)
			{
				
				if(driver.findElements(By.xpath(locateVideoFrame1)).size()>0)
				{
					WebElement playButtonLocator =null;
					driver.switchTo().frame(driver.findElement(By.xpath(locateVideoFrame1)));
					try
					{
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
				            if(driver.findElements(By.xpath(locateVideoFrame2)).size()>0)
							  { 
								  WebElement  childFrameLocator = driver.findElement(By.xpath(locateVideoFrame2));
								  driver.switchTo().frame(childFrameLocator); List<WebElement> playButtons =
								  driver.findElements(By.xpath(playButton)); if (!playButtons.isEmpty())
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
				status.addAll(this.answerQuiz());
				driver.switchTo().defaultContent();
				count++;
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
				if (driver.findElements(By.xpath(quizPage)).size() > 0)
				{
					List<WebElement> quizSections = driver.findElements(By.xpath(quizPage));
					for(WebElement quizSection : quizSections)
					{
						WebElement quizQuestion = quizSection; // Assuming 1 question per block
						if(quizQuestion.findElements(By.xpath(quizQuestionList)).size()>0)
						{
							WebElement getEachQuestion = quizQuestion.findElement(By.xpath(quizQuestionList));
							WebElement markAnswer = getEachQuestion.findElement(By.xpath(quizAnswer));
							// Scroll and click answer using JS
							JavascriptExecutor js = (JavascriptExecutor) driver;
							js.executeScript("arguments[0].scrollIntoView(true);", markAnswer);
							js.executeScript("arguments[0].click();", markAnswer);
							
							// Click Submit
							By submitAnswer = By.xpath(quizSubmitButton);
							status.addAll(this.clickWebElements(quizQuestion, submitAnswer));
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
	public ArrayList<String> enterTextOnField(String locator, String textToEnter)
	{
		ArrayList<String> status = new ArrayList<String>();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try
		{
		WebElement element = driver.findElement(By.xpath(locator));
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		if(element.isDisplayed()) 
        {
            element.clear();
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
			driver.close();
			driver.switchTo().window(discussionPage);
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
			driver.close();
			driver.switchTo().window(discussionPage);
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
			driver.close();
			driver.switchTo().window(discussionPage);
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
		}
		catch (Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkViewRecordFromProfilePage()
	{
		ArrayList<String> status = new ArrayList<String>();
		String clickViewRecord = "//div[@class='container-fluid']/div[2]/descendant::a[contains(text(),'View My Records')]";		
		try
		{
			status.add(homePage.clickWebElement(clickViewRecord));
			status.add(registerPage.FocusWindow("learner-record"));
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
		String clickAccountSection = "//a[contains(text(),'Account Information')]";
		String editFullname = "//h6[contains(text(),'Full name')]/following-sibling::button";
		try
		{
			status.add(homePage.clickWebElement(clickAccountSection));
			status.add(homePage.clickWebElement(editFullname));
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
		String clickCancelButton = "//button[contains(text(),'Cancel')]";
		try
		{
			status.add(homePage.clickWebElement(clickCancelButton));
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
		String clickAccountSection = "//a[contains(text(),'Account Information')]";
		String editFullname = "//h6[contains(text(),'Full name')]/following-sibling::button";
		String focusFullnameField = "//input[@id='field-name']";
		String clickSaveButton = "//button[@type='submit']/descendant::span[contains(text(),'Save')]";
		try
		{
			status.add(homePage.clickWebElement(clickAccountSection));
			status.add(homePage.clickWebElement(editFullname));
			status.add(homePage.clickWebElement(editFullname));
			status.addAll(this.enterTextOnField(focusFullnameField, "Test User"));
			status.add(homePage.clickWebElement(clickSaveButton));
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
		String clickProfileSection = "//a[contains(text(),'Profile Information')]";
		String editEducation = "//h6[contains(text(),'Education')]/following-sibling::button";
		try
		{
			status.add(homePage.clickWebElement(clickProfileSection));
			status.add(homePage.clickWebElement(editEducation));
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
		String clickCancelButton = "//button[contains(text(),'Cancel')]";
		try
		{
			status.add(homePage.clickWebElement(clickCancelButton));
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
		String clickProfileSection = "//a[contains(text(),'Profile Information')]";
		String editEducation = "//h6[contains(text(),'Education')]/following-sibling::button";
		String selectLevelOfEdu = "//select[@id='field-level_of_education']";
		String clickSaveButton = "//button[@type='submit']/descendant::span[contains(text(),'Save')]";
		try
		{
			status.add(homePage.clickWebElement(clickProfileSection));
			status.add(homePage.clickWebElement(editEducation));
			status.add(homePage.clickWebElement(editEducation));
			WebElement dropdown = driver.findElement(By.xpath(selectLevelOfEdu));
			Select select = new Select(dropdown);
			select.selectByIndex(1);
			status.add(homePage.clickWebElement(clickSaveButton));
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
		String editGender = "//h6[contains(text(),'Gender')]/following-sibling::button";
		try
		{
			status.add(homePage.clickWebElement(editGender));
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
		String clickCancelButton = "//button[contains(text(),'Cancel')]";
		try
		{
			status.add(homePage.clickWebElement(clickCancelButton));
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
		String editGender = "//h6[contains(text(),'Gender')]/following-sibling::button";
		String selectLevelOfGender = "//select[@id='field-gender']";
		String clickSaveButton = "//button[@type='submit']/descendant::span[contains(text(),'Save')]";
		try
		{
			status.add(homePage.clickWebElement(editGender));
			status.add(homePage.clickWebElement(editGender));
			Select selectEdu = new Select(driver.findElement(By.xpath(selectLevelOfGender)));
			selectEdu.selectByVisibleText("Female");
			status.add(homePage.clickWebElement(clickSaveButton));
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
		String editSpokenLanguage = "//h6[contains(text(),'Spoken language')]/following-sibling::button";
		try
		{
			status.add(homePage.clickWebElement(editSpokenLanguage));
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
		String clickCancelButton = "//button[contains(text(),'Cancel')]";
		try
		{
			status.add(homePage.clickWebElement(clickCancelButton));
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
		String editSpokenLanguage = "//h6[contains(text(),'Spoken language')]/following-sibling::button";
		String selectSpokenLanguage = "//select[@id='field-language_proficiencies']";
		String clickSaveButton = "//button[@type='submit']/descendant::span[contains(text(),'Save')]";
		try
		{
			status.add(homePage.clickWebElement(editSpokenLanguage));
			status.add(homePage.clickWebElement(editSpokenLanguage));
			Select selectEdu = new Select(driver.findElement(By.xpath(selectSpokenLanguage)));
			selectEdu.selectByIndex(3);
			status.add(homePage.clickWebElement(clickSaveButton));
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
		String clickSocialLinksSection = "//a[contains(text(),'Social Media Links')]";
		try
		{
			status.add(homePage.clickWebElement(clickSocialLinksSection));
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
		String editLinkedIn = "//h6[contains(text(),'LinkedIn')]/following-sibling::button";
		try
		{
			status.add(homePage.clickWebElement(editLinkedIn));
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
		String clickCancelButton = "//button[contains(text(),'Cancel')]";
		try
		{
			status.add(homePage.clickWebElement(clickCancelButton));
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
		String editLinkedIn = "//h6[contains(text(),'LinkedIn')]/following-sibling::button";
		String enterLinkedInField = "//input[@id='field-social_link_linkedin']";
		String clickSaveButton = "//button[@type='submit']/descendant::span[contains(text(),'Save')]";
		try
		{
			status.add(homePage.clickWebElement(editLinkedIn));
			status.addAll(this.enterTextOnField(enterLinkedInField, "test"));
			status.add(homePage.clickWebElement(clickSaveButton));
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
		String editFacebook = "//h6[contains(text(),'Facebook')]/following-sibling::button";
		try
		{
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
		String clickCancelButton = "//button[contains(text(),'Cancel')]";
		try
		{
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
		String editFacebook = "//h6[contains(text(),'Facebook')]/following-sibling::button";
		String enterFacebookField = "//input[@id='field-social_link_facebook']";
		String clickSaveButton = "//button[@type='submit']/descendant::span[contains(text(),'Save')]";
		try
		{
			status.add(homePage.clickWebElement(editFacebook));
			status.addAll(this.enterTextOnField(enterFacebookField, "test"));
			status.add(homePage.clickWebElement(clickSaveButton));
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
		String editTwitter = "//h6[contains(text(),'Twitter')]/following-sibling::button";
		try
		{
			status.add(homePage.clickWebElement(editTwitter));
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
		String clickCancelButton = "//button[contains(text(),'Cancel')]";
		try
		{
			status.add(homePage.clickWebElement(clickCancelButton));
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
		String editTwitter = "//h6[contains(text(),'Twitter')]/following-sibling::button";
		String enterTwitterField = "//input[@id='field-social_link_twitter']";
		String clickSaveButton = "//button[@type='submit']/descendant::span[contains(text(),'Save')]";
		try
		{
			status.add(homePage.clickWebElement(editTwitter));
			status.addAll(this.enterTextOnField(enterTwitterField, "test"));
			status.add(homePage.clickWebElement(clickSaveButton));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkLinkedAccounts()
	{
		ArrayList<String> status = new ArrayList<String>();
		String editLinkedAccounts = "//li//a[contains(text(),'Linked Accounts')]";
		String clickGoogleLink = "//a[contains(text(),'Sign in with Google')]";
		
		try
		{
			status.add(homePage.clickWebElement(editLinkedAccounts));
			status.add(homePage.openLinkInNewTab(clickGoogleLink));
			status.add(registerPage.FocusWindow("account"));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.close();
			driver.switchTo().window(discussionPage);
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
		String clickSupportLink = "//a[contains(text(),'contact support')]";
		try
		{
			status.add(homePage.openLinkInNewTab(clickOrderHistoryFromUserDropdown));
			status.add(registerPage.FocusWindow("orders"));
			status.add(homePage.clickWebElement(clickSupportLink));
			status.add(registerPage.FocusWindow("requests"));
			driver.close();
			driver.switchTo().window(discussionPage);
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
			status.add(registerPage.FocusWindow("requests"));
			driver.close();
			driver.switchTo().window(discussionPage);
		}
		catch (Exception e) {
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkDeleteAccount()
	{
		ArrayList<String> status = new ArrayList<String>();

		try
		{
		}
		catch (Exception e) {
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
}
