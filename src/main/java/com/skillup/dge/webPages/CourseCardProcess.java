package com.skillup.dge.webPages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;

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
	
	public ArrayList<String> CourseCardSelection_EnrollCard(ArrayList<String> data)
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
					if(courseCardTitleText.equals(data.get(1)))
                    {
						WebElement courseCardOrganization = card.findElement(By.xpath(courseCardOrg));
						courseCardOrganizationText = courseCardOrganization.getText();
						WebElement courseCardDateLocate = card.findElement(By.xpath(courseCardDate));
						String courseCardDateText[] = courseCardDateLocate.getText().split("Starts:");
						courseCardDateTextValue = courseCardDateText[1];
						WebElement  locateCard = card.findElement(By.xpath(courseCardButton));
						JavascriptExecutor js = (JavascriptExecutor) driver;
						js.executeScript("arguments[0].scrollIntoView(true);", locateCard);
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
	public ArrayList<String> checkEnrollButton()
	{
		ArrayList<String> status = new ArrayList<String>();
		String enrollButton = "//a[@class='register']";
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
			status.add(homePage.openLinkInNewTab(mailButton));
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
	
	public ArrayList<String> checkUnEnrollOption()
	{
		ArrayList<String> status = new ArrayList<String>();
		String settingsLink = "//div[@data-testid='CourseCard'][1]//span[@class='pgn__icon btn-icon__icon']";
		String unenrollButton = "//a[normalize-space()='Unenroll']";
		checkAlertText = "Unenroll from course?";
		String locateAlertText = "//div[@class='bg-white p-3 rounded shadow']/h4";
		try 
		{
			status.add(homePage.clickWebElement(settingsLink));
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
	public ArrayList<String> checkUnEnrollProcess()
	{
		ArrayList<String> status = new ArrayList<String>();
		String settingsLink = "//div[@data-testid='CourseCard'][1]//span[@class='pgn__icon btn-icon__icon']";
		String neverButton = "//button[contains(text(),'Never mind')]";
		String unenrollButton = "//a[contains(text(),'Unenroll')]";
		String locateAlertText = "//div[@class='bg-white p-3 rounded shadow']/h4";
		String selectReasonToUnEnroll = "//div[@role='radiogroup']/div//input[1]";
		String skipsurveyButton = "//button[contains(text(),'Skip survey')]";
		String submitSurveyButton = "//button[contains(text(),'Submit reason')]";
		String clickReturnToDashboard = "//button[contains(text(),'Return to dashboard')]";
		try 
		{
			status.add(homePage.clickWebElement(neverButton));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			if (driver.findElements(By.xpath(locateAlertText)).size() > 0)
			{
				status.add("Fail");
			}
			status.add(homePage.clickWebElement(settingsLink));
			status.add(homePage.clickWebElement("//a[contains(text(),'Unenroll')]"));
			status.add(this.checkWebElementComparision(locateAlertText, checkAlertText));
			status.add(homePage.clickWebElement("//button[contains(text(),'Unenroll')]"));
			status.add(homePage.clickWebElement(selectReasonToUnEnroll));
			status.add(homePage.clickWebElement(skipsurveyButton));
			status.add(homePage.clickWebElement(clickReturnToDashboard));
			status.add(homePage.clickWebElement(settingsLink));
			status.add(homePage.clickWebElement(unenrollButton));
			status.add(homePage.clickWebElement("//button[contains(text(),'Unenroll')]"));
			status.add(homePage.clickWebElement(selectReasonToUnEnroll));
			status.add(homePage.clickWebElement(submitSurveyButton));
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
	public ArrayList<String> checkEmailSettingsOption()
	{
		ArrayList<String> status = new ArrayList<String>();
		String settingsLink = "//div[@data-testid='CourseCard'][1]//span[@class='pgn__icon btn-icon__icon']";
		String emailSettings = "//a[contains(text(),'Email settings')]";
		String locateAlertText = "//div[@class='bg-white p-3 rounded shadow']/h4";
		String EmailAlertText = "Receive course emails?";
		try 
		{
			status.add(homePage.clickWebElement(settingsLink));
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
	public ArrayList<String> checkEmailSettingProcess()
	{
		ArrayList<String> status = new ArrayList<String>();
		String settingsLink = "//div[@data-testid='CourseCard'][1]//span[@class='pgn__icon btn-icon__icon']";
		String emailSettings = "//a[contains(text(),'Email settings')]";
		String locateAlertText = "//div[@class='bg-white p-3 rounded shadow']/h4";
		String EmailAlertText = "Receive course emails?";
		String emailButtonOnOff = "//input[contains(@id,'form-field')]";
		String neverMindButton = "//button[contains(text(),'Never mind')]";
		String saveSettings = "//button[contains(text(),'Save settings')]";
		try 
		{
			status.add(homePage.clickWebElement(emailButtonOnOff));
			status.add(homePage.clickWebElement(saveSettings));
			status.add(homePage.clickWebElement(settingsLink));
			status.add(homePage.clickWebElement(emailSettings));
			status.add(this.checkWebElementComparision(locateAlertText, EmailAlertText));
			status.add(homePage.clickWebElement(neverMindButton));
			status.add(registerPage.FocusWindow("learner-dashboard/"));
			status.add(homePage.clickWebElement(settingsLink));
			status.add(homePage.clickWebElement(emailSettings));
			status.add(homePage.clickWebElement(emailButtonOnOff));
			status.add(homePage.clickWebElement(saveSettings));
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
		String clickFindCourse = "//a[contains(text(),'Find a course ')]";
		String courseCardList = "//ul[@class='courses-listing courses-list']/li";
		String courseCardTitle = ".//span[@class='course-title']";
		String courseCardOrg = ".//span[@class='course-organization']";
		String courseCardButton = ".//a[@class='learn-more']";
		String courseCardDate = ".//div[@class='course-date']"; // course card starts date
		JavascriptExecutor js = (JavascriptExecutor) driver;
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
					if(courseCardTitleText.equals(data.get(1)))
                    {
						WebElement courseCardOrganization = card.findElement(By.xpath(courseCardOrg));
						courseCardOrganizationText = courseCardOrganization.getText();
						WebElement courseCardDateLocate = card.findElement(By.xpath(courseCardDate));
						String courseCardDateText[] = courseCardDateLocate.getText().split("Starts:");
						courseCardDateTextValue = courseCardDateText[1];
						WebElement  locateCard = card.findElement(By.xpath(courseCardButton));
						
						js.executeScript("arguments[0].scrollIntoView(true);", locateCard);
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
		String courseEndDateLocator = "//div[@class='ml-1 font-weight-bold']";
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
		
		try 
		{
			WebElement enrollButton = driver.findElement(By.xpath(clickEnroll));
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
		try 
		{
			List<WebElement> cardsFromDashboard = driver.findElements(By.xpath(cardsList));
			for (WebElement card : cardsFromDashboard) 
			{
				WebElement courseCardTitleFromDasboardLocator = card.findElement(By.xpath(courseCardTitle));
				String	courseCardTitleTextFromDashboard = courseCardTitleFromDasboardLocator.getText();
				if(courseCardTitleText.equals(courseCardTitleTextFromDashboard))
				{
					WebElement courseCardDetailsLocator = card.findElement(By.xpath(courseCardDetails));
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
		String orgLocatorFromContent = "//span[@class='d-block org']";
		JavascriptExecutor js = (JavascriptExecutor) driver;
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
		try
		{
			if (parent.findElements(clickBeginCourseFromDashboardLocator).size() > 0)
			{
				WebElement childElement = parent.findElement(clickBeginCourseFromDashboardLocator);
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].scrollIntoView(true);", childElement);
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
	
	public ArrayList<String> checkExpandAllProcess()
	{
		ArrayList<String> status = new ArrayList<String>();
		String clickBeginCourse = ".//a[contains(text(),'Begin Course')]";
		String expandAllLink = "//button[contains(text(),'Expand all')]";
		String cardsList = "//div[@data-testid='CourseCard']";
		String courseCardTitle = ".//*[@class='course-card-title']";
		
		try 
		{
			List<WebElement> cardsFromDashboard = driver.findElements(By.xpath(cardsList));
			for (WebElement card : cardsFromDashboard) 
			{
				WebElement courseCardTitleFromDashboardLocator = card.findElement(By.xpath(courseCardTitle));
				String courseCardTitleTextFromDashboard = courseCardTitleFromDashboardLocator.getText();
				if(courseCardTitleText.equals(courseCardTitleTextFromDashboard))
				{
					By clickBeginCourseFromDashboardLocator = By.xpath(clickBeginCourse);
					status.addAll(this.clickWebElements(card, clickBeginCourseFromDashboardLocator));
				}
			}
			status.add(homePage.clickWebElement(expandAllLink));
		}
		catch (Exception e) 
		{
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
	public ArrayList<String> checkBookMarksLink()
	{
		ArrayList<String> status = new ArrayList<String>();
		String bookMarksLink = "//a[contains(text(),'Bookmarks')]";
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
	public ArrayList<String> checkLaunchTourLink()
	{
		ArrayList<String> status = new ArrayList<String>();
		String launchTourLink = "//li[@id='courseHome-launchTourLink']//button[contains(text(),'Launch tour')]";
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
	public ArrayList<String> checkViewAllCourseLink()
	{
		ArrayList<String> status = new ArrayList<String>();
		String ViewAllCourseLink = "//a[contains(text(),'View all course dates')]";
		try 
		{
			status.add(homePage.clickWebElement(ViewAllCourseLink));
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkAllLinksFromCourseContent()
	{
		ArrayList<String> status = new ArrayList<String>();
		String expandAllLink = "//button[contains(text(),'Expand all')]";
		String courseOutlineSectionLink = "//ol[@id='courseHome-outline']/li//ol[@class='list-unstyled']/li//a";
		String frameForVideo = "//div[@class='unit-iframe-wrapper']//iframe";
		String playVideo = "//div[@id='player']//button[@title='Play']";
		String NextButtonAtBottom = "//div[@class='unit-navigation d-flex']/a";
		String pauseVideo = "//button[@class='control video_control pause']";
		String courseContentPage = "";
		String courseOutlineSectionURLText = "";
		try 
		{
			status.add(homePage.clickWebElement(expandAllLink));
			List<WebElement> courseOutlineSection = driver.findElements(By.xpath(courseOutlineSectionLink));
			for (WebElement courseOutlineSectionLocator : courseOutlineSection) 
			{
				courseOutlineSectionURLText = courseOutlineSectionLocator.getAttribute("href");
				courseContentPage = driver.getWindowHandle();
				driver.switchTo().newWindow(WindowType.TAB);
				driver.get(courseOutlineSectionURLText);
				System.out.println("Course content page URL: " + courseOutlineSectionURLText);
				break;
			}
			
			while(driver.findElements(By.xpath(NextButtonAtBottom)).size()>0)
			{
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
				if (driver.findElements(By.xpath(frameForVideo)).size() > 0)
				{
					System.out.println("Specific iframe is available.");
					driver.switchTo().frame(0);
					status.add(homePage.clickWebElement(playVideo));
					status.add(homePage.clickWebElement(pauseVideo));
				} 
				else
				{
					System.out.println("Specific iframe is NOT available.");
				}
				status.add(homePage.clickWebElement(NextButtonAtBottom));
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
